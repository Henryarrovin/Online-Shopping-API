import axios from "axios";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Form, Button, Alert } from "react-bootstrap";

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
  const [addProducts, setAddProducts] = useState(false);
  const [error, setError] = useState("");

  const [productName, setProductName] = useState("");
  const [productCost, setProductCost] = useState("");
  const [productType, setProductType] = useState("");
  const [productCount, setProductCount] = useState("");

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

  const handleAddProductClick = () => {
    setAddProducts(true);
  };

  const addProduct = async (e: React.FormEvent) => {
    e.preventDefault();

    const data = {
      productName: productName,
      productCost: productCost,
      productType: productType,
      productCount: productCount,
    };

    try {
      const response = await axios.post(
        "http://localhost:8081/api/product/addNewProduct",
        data,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );
      setError("Product inserted...");
      setAddProducts(false);
      const res = response.data;
      console.log(res);
    } catch (error) {
      setError("Product not inserted...");
      console.error(error);
    }
  };

  return (
    <div>
      <h3 className="text-center mt-2">Welcome {username}</h3>
      <nav className="navbar-dark bg-dark mb-2">
        <form className="container-fluid justify-content-start">
          <button
            className="btn btn-outline-success me-2"
            type="button"
            onClick={handleGetAllProduct}
          >
            All Product
          </button>
          <button
            className="btn btn-outline-success me-2"
            type="button"
            onClick={handleAddProductClick}
          >
            Add Product
          </button>
          <button className="btn btn-outline-success me-2" type="button">
            Refresh Page
          </button>
        </form>
      </nav>
      <div>
        {addProducts ? (
          <div>
            <Form onSubmit={addProduct}>
              <Form.Group className="mb-3" controlId="formBasicUserName">
                <Form.Label htmlFor="productName">productName:</Form.Label>
                <Form.Control
                  type="text"
                  id="productName"
                  name="productName"
                  value={productName}
                  onChange={(e) => setProductName(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="productCost">productCost:</Form.Label>
                <Form.Control
                  type="text"
                  id="productCost"
                  name="productCost"
                  value={productCost}
                  onChange={(e) => setProductCost(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="productType">productType:</Form.Label>
                <Form.Control
                  type="text"
                  id="productType"
                  name="productType"
                  value={productType}
                  onChange={(e) => setProductType(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="productCount">productCount:</Form.Label>
                <Form.Control
                  type="text"
                  id="productCount"
                  name="productCount"
                  value={productCount}
                  onChange={(e) => setProductCount(e.target.value)}
                  required
                />
              </Form.Group>
              {error && <Alert variant="danger">{error}</Alert>}
              <Button
                variant="primary"
                type="submit"
                className="btn btn-primary"
              >
                Add Product
              </Button>
            </Form>
          </div>
        ) : (
          <div className="product-container d-flex flex-wrap row row-cols-1 row-cols-md-3 g-4">
            {products && products.length > 0 ? (
              products.map((product) => (
                <div key={product.productId} className="col-md-2 mb-2">
                  <div className="product-box card border-dark bg-dark text-white">
                    <div className="card-body">
                      <h4 className="card-title">{product.productName}</h4>
                      <p className="card-text">Cost: {product.productCost}</p>
                      <p className="card-text">Type: {product.productType}</p>
                      <p className="card-text">Count: {product.productCount}</p>
                    </div>
                  </div>
                </div>
              ))
            ) : (
              <p>No products available.</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
};
