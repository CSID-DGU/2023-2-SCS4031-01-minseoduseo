import styled, { css, keyframes } from "styled-components";
import COLOR from "styles/colors";
import { FONT_STYLES } from "styles/fontStyle";
interface themeType {
  theme: "answer" | "question";
  txt?: string;
  time?: Date;
}
export default function ThoughtBubble({ theme, txt, time }: themeType) {
  return (
    <StyledContainer theme={theme}>
      <StyledTime theme={theme}>오후 1:00</StyledTime>
      <StyledBubbleTriangle theme={theme} />
      <StyledBubble theme={theme}>
        <StyledInnerTxt theme={theme}>
          {txt ? (
            txt
          ) : (
            <StyledLoading>
              <StyledDot />
              <StyledDot />
              <StyledDot />
            </StyledLoading>
          )}
        </StyledInnerTxt>
      </StyledBubble>
    </StyledContainer>
  );
}

const float = keyframes`
  0%{
    transform: translateY(0)
  }
  50%{
    transform: translateY(0)
  }
  75%{
    transform: translateY(-0.9rem)
  }
  100%{
    transform: translate(0)
  }
`;
const StyledContainer = styled.section<themeType>`
  display: flex;
  gap: 0;
  margin-left: ${({ theme }) => (theme === "answer" ? 0 : "auto")};
`;

const StyledTime = styled.span<themeType>`
  ${FONT_STYLES.PR_R};
  font-size: 1rem;
  color: ${COLOR.FONT_GRAY_9A};
  margin: auto 0.7rem 0.1rem 0.7rem;
  order: ${({ theme }) => (theme === "answer" ? 1 : -1)};
`;

const StyledBubbleTriangle = styled.div<themeType>`
  width: 0;
  height: 0;
  border-style: solid;
  ${({ theme }) => {
    if (theme === "answer") {
      return css`
        border-width: 0 0.6rem 1.3rem 0;
        border-color: transparent ${COLOR.BG_GREEN_28} transparent transparent;
      `;
    } else {
      return css`
        order: 2;
        border-width: 1.3rem 0.6rem 0 0;
        border-color: ${COLOR.BG_GRAY_E4} transparent transparent transparent;
      `;
    }
  }}
`;

const StyledBubble = styled.div<themeType>`
  padding: 1.5rem;
  max-width: 23.5rem;
  ${({ theme }) => {
    if (theme === "answer") {
      return css`
        background: ${COLOR.BG_GREEN_28};
        border-radius: 0 0.6rem 0.6rem;
      `;
    } else {
      return css`
        background: ${COLOR.BG_GRAY_E4};
        border-radius: 0.6rem 0 0.6rem 0.6rem;
      `;
    }
  }}
`;

const StyledInnerTxt = styled.div<themeType>`
  word-wrap: break-word;
  font-size: 1.5rem;
  ${FONT_STYLES.PR_R};
  word-spacing: -0.05rem;
  color: ${({ theme }) => (theme === "answer" ? "white" : COLOR.FONT_BLACK_24)};
`;

const StyledLoading = styled.ul`
  display: flex;
  gap: 0.8rem;
  li:nth-child(1) {
    animation-delay: 0;
  }
  li:nth-child(2) {
    animation-delay: 0.15s;
  }
  li:nth-child(3) {
    animation-delay: 0.33s;
  }
`;

const StyledDot = styled.li`
  list-style: none;
  height: 0.7rem;
  width: 0.7rem;
  background: ${COLOR.BG_GREEN_EE};
  border-radius: 50%;
  animation: ${float} 1s ease-in-out infinite;
`;
