import axios from "axios";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export type Product = {
  productId: number;
  productName: string;
  productCost: string;
  productType: string;
  productCount: string;
};

export const NewPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [products, setProducts] = useState<Product[]>();

  const searchParams = new URLSearchParams(location.search);
  const username = searchParams.get("username");
  const tokenString = searchParams.get("token");
  const tokenObject = tokenString ? JSON.parse(tokenString) : null;
  const accessToken = tokenObject?.accessToken;

  console.log("Token:", tokenObject, typeof tokenObject);

  const getAllProduct = async () => {
    try {
      if (tokenObject && accessToken) {
        const response = await axios.get(
          "http://localhost:8081/api/product/allProduct",
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );
        setProducts(response.data.content);
        console.log("Token Object:", tokenObject);
        console.log("Access Token:", accessToken);
        console.log("Hello token ..........");
        console.log("Response Data:", response.data.content);
      } else {
        navigate("/");
      }
    } catch (error) {
      console.error(error);
      setProducts([]);
    }
  };

  const handleGetAllProduct = () => {
    if (!username || !tokenObject) {
      navigate("/");
    } else {
      getAllProduct();
    }
  };

  return (
    <div>
      <h3 className="text-center mt-2">Welcome {username}</h3>
      <nav className="navbar-dark bg-dark">
        <form className="container-fluid justify-content-start">
          <button
            className="btn btn-outline-success me-2"
            type="button"
            onClick={handleGetAllProduct}
          >
            All Product
          </button>
          <button className="btn btn-outline-success me-2" type="button">
            Add Product
          </button>
          <button className="btn btn-outline-success me-2" type="button">
            Refresh Page
          </button>
        </form>
      </nav>
      <div className="product-container">
        {products && products.length > 0 ? (
          products.map((product) => (
            <div key={product.productId} className="product-box card">
              <h4>{product.productName}</h4>
              <p>Cost: {product.productCost}</p>
              <p>Type: {product.productType}</p>
              <p>Count: {product.productCount}</p>
            </div>
          ))
        ) : (
          <p>No products available.</p>
        )}
      </div>
    </div>
  );
};
