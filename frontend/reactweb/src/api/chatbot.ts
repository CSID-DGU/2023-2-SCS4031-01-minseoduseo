import { axiosInterface } from "api";
const postChat = async (question: string) => {
  const { data } = await axiosInterface.post("/api/chatbot", { question });
  return data;
};

export { postChat };
