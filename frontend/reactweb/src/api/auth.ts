import { axiosInterface } from "api";

const getInfo = async () => {
  try {
    const { data } = await axiosInterface.get("/api/member");
    return data;
  } catch {
    return {};
  }
};

export { getInfo };
