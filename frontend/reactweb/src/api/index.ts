import axios from "axios";
import { useNavigate } from "react-router-dom";

const axiosInterface = axios.create({
  baseURL: "http://msds-capstone.store",
  timeout: 10000,
});

axiosInterface.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return Promise.resolve(config);
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInterface.interceptors.response.use(
  (config) => config,
  async function (error) {
    const navigate = useNavigate();
    const status = error.response?.status;
    if (status === 401) {
      localStorage.removeItem("token");
      navigate("/login");
    }
    return Promise.reject(error);
  }
);

const currentTime = new Date().toJSON();

export { axiosInterface, currentTime };
