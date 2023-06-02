import { useLocation } from "react-router-dom";

export const ProductPage = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);

  const productId = searchParams.get("productId");
  const productName = searchParams.get("productName");
  const productCost = searchParams.get("productCost");
  const productType = searchParams.get("productType");
  const productCount = searchParams.get("productCount");

  return (
    <div>
      <h1 className="text-center text-success mb-4">
        User Product Detail Update/View Page
      </h1>
      <div className="my-5">
        <div className="product-box card border-dark bg-dark text-white text-center ">
          <div className="card-body">
            <h4 className="my-5">Product ID: {productId}</h4>
            <h4 className="my-5">Product Name: {productName}</h4>
            <h4 className="my-5">Product Cost: {productCost}</h4>
            <h4 className="my-5">Product Type: {productType}</h4>
            <h4 className="my-5">Product Count: {productCount}</h4>
            <button type="button" className="btn btn-primary my-5 mx-2">
              Update
            </button>
            <button type="button" className="btn btn-primary my-5 mx-2">
              Refresh
            </button>
            <button type="button" className="btn btn-primary my-5 mx-2">
              Back
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
