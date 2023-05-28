import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login } from "./components/Login";
import { NewPage } from "./components/NewPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route
          path="/components/NewPage/:username/:token"
          element={<NewPage />}
        />
      </Routes>
    </Router>
  );
}

export default App;
