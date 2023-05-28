import { useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [_token, setToken] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (event: any) => {
    event.preventDefault();

    const data = {
      username: username,
      password: password,
    };

    try {
      const response = await axios.post(
        "http://localhost:8081/api/auth/login",
        data
      );

      const token = response.data;
      const tokenString = JSON.stringify(token);
      setToken(tokenString);
      console.log("Token:", tokenString, typeof tokenString);
      navigate(`/components/NewPage?username=${username}&token=${tokenString}`);
    } catch (error) {
      setError("Please check your credentials.");
      console.error(error);
    }
  };

  return (
    <>
      <div className="container mt-5">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <h2 className="text-center mb-4">Login</h2>
            <Form onSubmit={handleLogin}>
              <Form.Group className="mb-3" controlId="formBasicUserName">
                <Form.Label htmlFor="username">Username:</Form.Label>
                <Form.Control
                  type="text"
                  id="username"
                  name="username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label htmlFor="password">Password:</Form.Label>
                <Form.Control
                  type="password"
                  id="password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </Form.Group>
              {error && <Alert variant="danger">{error}</Alert>}
              <Button
                variant="primary"
                type="submit"
                className="btn btn-primary"
              >
                Login
              </Button>
            </Form>
          </div>
        </div>
      </div>
    </>
  );
};
