import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";

export const ProductPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const searchParams = new URLSearchParams(location.search);

  const productId = searchParams.get("productId");
  const productName = searchParams.get("productName");
  const productCost = searchParams.get("productCost");
  const productType = searchParams.get("productType");
  const productCount = searchParams.get("productCount");

  const [update, setUpdate] = useState(false);
  const [productNameUpdate, setProductNameUpdate] = useState<string | null>("");
  const [productCostUpdate, setProductCostUpdate] = useState<string | null>("");
  const [productTypeUpdate, setProductTypeUpdate] = useState<string | null>("");
  const [productCountUpdate, setProductCountUpdate] = useState<string | null>(
    ""
  );
  const [error, setError] = useState("");
  const token = searchParams.get("token");
  const tokenObject = token ? JSON.parse(token) : null;
  const accessToken = tokenObject?.accessToken;

  console.log("TOken string is........", accessToken);

  const handleClickBack = () => {
    navigate(`/components/NewPage?token=${token}`);
    // setUpdate(false);
  };

  const updateProduct = () => {
    const data = {
      productId: productId,
      productName: productName,
      productCost: productCost,
      productType: productType,
      productCount: productCount,
    };

    axios
      .put(
        `http://localhost:8081/api/product/update/${productId}?productName=${productName}&productCost=${productCost}&productType=${productType}&productCount=${productCount}`,
        data,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleUpdate = () => {
    setUpdate(true);
    if (token) {
      updateProduct();
    } else {
      setError("Error in token...");
    }

    setProductNameUpdate(productName);
    setProductCostUpdate(productCost);
    setProductTypeUpdate(productType);
    setProductCountUpdate(productCount);
  };

  return (
    <div>
      <h1 className="text-center text-success mb-4">Update or Delete page</h1>
      <div>
        {update ? (
          <div>
            <Form onSubmit={handleUpdate}>
              <Form.Group className="mb-3" controlId="formBasicUserName">
                <Form.Label htmlFor="productName">productName:</Form.Label>
                <Form.Control
                  type="text"
                  id="productName"
                  name="productName"
                  value={productNameUpdate as string}
                  onChange={(e) => setProductNameUpdate(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="productCost">productCost:</Form.Label>
                <Form.Control
                  type="text"
                  id="productCost"
                  name="productCost"
                  value={productCostUpdate as string}
                  onChange={(e) => setProductCostUpdate(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="productType">productType:</Form.Label>
                <Form.Control
                  type="text"
                  id="productType"
                  name="productType"
                  value={productTypeUpdate as string}
                  onChange={(e) => setProductTypeUpdate(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="productCount">productCount:</Form.Label>
                <Form.Control
                  type="text"
                  id="productCount"
                  name="productCount"
                  value={productCountUpdate as string}
                  onChange={(e) => setProductCountUpdate(e.target.value)}
                  required
                />
              </Form.Group>
              {error && <Alert variant="danger">{error}</Alert>}
              <Button
                variant="primary"
                type="submit"
                className="btn btn-primary mx-2"
              >
                Update
              </Button>
              <Button
                variant="primary"
                type="submit"
                className="btn btn-primary mx-2"
              >
                Delete
              </Button>
              <Button
                variant="primary"
                type="submit"
                className="btn btn-primary mx-2"
                onClick={() => {
                  setProductNameUpdate("");
                  setProductCostUpdate("");
                  setProductTypeUpdate("");
                  setProductCountUpdate("");
                }}
              >
                Refresh
              </Button>
              <Button
                variant="primary"
                type="submit"
                className="btn btn-primary mx-2"
                onClick={() => setUpdate(false)}
              >
                Back
              </Button>
            </Form>
          </div>
        ) : (
          <div className="my-5">
            <div className="product-box card border-dark bg-dark text-white text-center ">
              <div className="card-body">
                <h4 className="my-5">Product ID: {productId}</h4>
                <h4 className="my-5">Product Name: {productName}</h4>
                <h4 className="my-5">Product Cost: {productCost}</h4>
                <h4 className="my-5">Product Type: {productType}</h4>
                <h4 className="my-5">Product Count: {productCount}</h4>
                <button
                  type="button"
                  className="btn btn-primary my-5 mx-2"
                  onClick={handleUpdate}
                >
                  Update
                </button>
                <button
                  type="button"
                  className="btn btn-primary my-5 mx-2"
                  onClick={handleClickBack}
                >
                  Back
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};
