import axios from "axios";

const axiosInterface = axios.create({
  baseURL: "http://msds-capstone.store",
  timeout: 100000,
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
    const status = error.response?.status;
    if (status === 401) {
      localStorage.removeItem("token");
    }
    return Promise.reject(error);
  }
);

const currentTime = new Date().toJSON();

export { axiosInterface, currentTime };
