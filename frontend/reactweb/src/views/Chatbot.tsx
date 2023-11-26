import styled from "styled-components";
import Header from "components/common/Header";
import COLOR from "styles/colors";
import ThoughtBubble from "components/common/ThoughtBubble";
import { ReactComponent as UpArrowIcon } from "assets/icons/icon_up-arrow.svg";
import { FONT_STYLES } from "styles/fontStyle";
import { postChat } from "api/chatbot";
import { useRef, useState, useEffect } from "react";
export default function Chatbot() {
  const inputRef = useRef<HTMLInputElement | null>(null);
  const containerRef = useRef<HTMLElement | null>(null);
  const inputSwitch = useRef<boolean>(true);
  const [totalChats, setTotalChats] = useState<
    { chat: string; type: "answer" | "question" }[] | []
  >([]);
  useEffect(() => {
    window.scrollTo({
      top: containerRef.current?.scrollHeight,
      behavior: "smooth",
    });
  }, [totalChats]);

  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    if (inputRef.current?.value) {
      const question = inputRef.current.value;
      setChat(question, "question");
      setChat("", "answer");
      inputSwitch.current = false;
      inputRef.current.value = "";
      const { answer } = await postChat(question);
      setLastChat(answer);
      inputSwitch.current = true;
    }
  };

  const setChat = (newChat: string, type: "answer" | "question") => {
    const chat = { chat: newChat, type };
    setTotalChats((chats) => [...chats, chat]);
  };

  const setLastChat = (newChat: string) => {
    const chat = { chat: newChat, type: "answer" };
    setTotalChats((chats) => {
      const cp = JSON.parse(JSON.stringify(chats));
      cp[chats.length - 1] = chat;
      return cp;
    });
  };

  return (
    <StyledContainer>
      <StyledHeader>
        <Header icon="previous" title="챗봇" />
      </StyledHeader>
      <StyledBubbleContainer ref={containerRef}>
        <StyledDate>2023.01.07 (화)</StyledDate>
        {totalChats.map(({ chat, type }, idx) => (
          <ThoughtBubble theme={type} txt={chat} key={idx} />
        ))}
      </StyledBubbleContainer>
      <StyledInputWrapper>
        <StyledInputContainer onSubmit={handleSubmit}>
          <StyledInput ref={inputRef} />
          <StyledSendBtn disabled={!inputSwitch.current}>
            <UpArrowIcon />
          </StyledSendBtn>
        </StyledInputContainer>
      </StyledInputWrapper>
    </StyledContainer>
  );
}

const StyledContainer = styled.section`
  width: 100vw;
  height: 100vh;
`;
const StyledHeader = styled.div`
  background-color: white;
  padding-bottom: 0.9rem;
  position: fixed;
  top: 0;
  width: 100vh;
  width: -webkit-fill-available;
  width: -moz-fill-available;
`;

const StyledDate = styled.div`
  ${FONT_STYLES.PR_R};
  color: ${COLOR.FONT_GRAY_59};
  font-size: 1.4rem;
  margin: 0 auto;
`;

const StyledBubbleContainer = styled.section`
  background: ${COLOR.BG_GRAY_F5};
  min-height: 100vh;
  padding: 9.3rem 1.8rem 12rem 1.8rem;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
`;
const StyledInputWrapper = styled.div`
  width: 100%;
  position: fixed;
  background-color: ${COLOR.BG_GRAY_F5};
  bottom: 0;
  padding: 0.2rem 2rem 4rem 2rem;
`;

const StyledInputContainer = styled.form`
  display: flex;
  background: ${COLOR.BG_GRAY_E4};
  padding: 0.8rem;
  height: 4.5rem;
  border-radius: 1.2rem;
  margin: auto;
`;

const StyledInput = styled.input`
  background: transparent;
  flex-grow: 1;
  color: ${COLOR.FONT_BLACK_24};
  font-size: 1.6rem;
  ${FONT_STYLES.PR_R}
`;

const StyledSendBtn = styled.button`
  background: ${({ disabled }) =>
    disabled ? `${COLOR.BG_GREEN_28}70` : COLOR.BG_GREEN_28};
  border-radius: 50%;
  width: 2.8rem;
  height: 2.8rem;
`;
