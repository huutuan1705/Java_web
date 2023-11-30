import { useEffect } from "react"
import { useLocalState } from './util/useLocalStorage';
import { Route, Routes } from "react-router-dom"
import Dashboard from "./Dashboard"
import HomePage from "./HomePage";
import Login from "./Login";
import PrivateRoute from "./PrivateRoute";
import AssignmentView from "./AssignmentView";
import 'bootstrap/dist/css/bootstrap.min.css';


function App() {
  const [jwt, setJwt] = useLocalState("", "jwt");

  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route
        path="dashboard"
        element={
          <PrivateRoute>
            <Dashboard />
          </PrivateRoute>
        }
      />
      <Route path="login" element={<Login />} />
      <Route
      path="/assignments/:id" 
      element={
        <PrivateRoute>
            <AssignmentView/>
        </PrivateRoute>
      } />
    </Routes>
  );
}

export default App;
