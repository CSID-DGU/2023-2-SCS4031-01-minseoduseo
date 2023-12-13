import { axiosInterface } from "api";

const getLogout = async () => {
  try {
    const { data } = await axiosInterface.get("/kakao/logout");
    return data;
  } catch {
    return {};
  }
};

export { getLogout };
