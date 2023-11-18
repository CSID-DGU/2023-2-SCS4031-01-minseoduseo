import styled, { css } from "styled-components";
import COLOR from "styles/colors";
import { FONT_STYLES } from "styles/fontStyle";
interface directionType {
  direction: "left" | "right";
  txt?: string;
}
export default function ThoughtBubble({ direction, txt }: directionType) {
  return (
    <StyledContainer direction={direction}>
      <StyledBubbleTriangle direction={direction} />
      <StyledBubble direction={direction}>
        <StyledInnerTxt direction={direction}>{txt}</StyledInnerTxt>
      </StyledBubble>
    </StyledContainer>
  );
}

const StyledContainer = styled.section<directionType>`
  display: flex;
  gap: 0;
  margin-left: ${({ direction }) => (direction === "left" ? 0 : "auto")};
`;

const StyledBubbleTriangle = styled.div<directionType>`
  width: 0;
  height: 0;
  border-style: solid;
  ${({ direction }) => {
    if (direction === "left") {
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

const StyledBubble = styled.div<directionType>`
  padding: 1.5rem;
  max-width: 19rem;
  ${({ direction }) => {
    if (direction === "left") {
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

const StyledInnerTxt = styled.div<directionType>`
  word-wrap: break-word;
  font-size: 1.5rem;
  ${FONT_STYLES.PR_R};
  word-spacing: -0.05rem;
  color: ${({ direction }) =>
    direction === "left" ? "white" : COLOR.FONT_BLACK_24};
`;
