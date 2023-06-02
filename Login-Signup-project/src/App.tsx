import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login } from "./components/Login";
import { NewPage } from "./components/NewPage";
import { ProductPage } from "./components/ProductPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/components/NewPage" element={<NewPage />} />
        <Route path="/components/ProductPage" element={<ProductPage />} />
      </Routes>
    </Router>
  );
}

export default App;
