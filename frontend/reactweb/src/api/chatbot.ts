import { axiosInterface } from "api";
const postChat = async (question: string) => {
  const { data } = await axiosInterface.post("/api/member/chatbot", {
    question,
  });
  alert(data);
  return data;
};

export { postChat };
