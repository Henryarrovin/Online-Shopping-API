import { useParams } from "react-router-dom";
import axios from "axios";

export const NewPage = () => {
  const { username, token } = useParams<{ username: string; token: string }>();

  const handleGetAllProduct = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8081/api/product/allProduct",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response);
    } catch (error) {
      console.error(error);
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
    </div>
  );
};
