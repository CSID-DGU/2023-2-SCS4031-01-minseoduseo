import styled from "styled-components";
import Header from "components/common/Header";
import COLOR from "styles/colors";
import ThoughtBubble from "components/common/ThoughtBubble";
import { ReactComponent as UpArrowIcon } from "assets/icons/icon_up-arrow.svg";
import { FONT_STYLES } from "styles/fontStyle";
export default function Chatbot() {
  return (
    <StyledContainer>
      <StyledHeader>
        <Header icon="previous" title="챗봇" />
      </StyledHeader>
      <StyledBubbleContainer>
        <ThoughtBubble direction={"left"} txt={"안녕하세요"} />
        <ThoughtBubble direction={"right"} txt={"안녕하세요 👋"} />
        <ThoughtBubble direction={"left"} txt={"안녕하십니까"} />
        <ThoughtBubble direction={"right"} txt={"반갑습니다"} />
      </StyledBubbleContainer>
      <StyledInputWrapper>
        <StyledInputContainer>
          <StyledInput />
          <StyledSendBtn>
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

const StyledInputContainer = styled.div`
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
  font-size: 1.5rem;
  ${FONT_STYLES.PR_R}
`;

const StyledSendBtn = styled.button`
  background: ${COLOR.BG_GREEN_28};
  border-radius: 50%;
  width: 2.8rem;
  height: 2.8rem;
`;
