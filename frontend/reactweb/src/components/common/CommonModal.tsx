import { styled, css } from "styled-components";
import COLOR from "styles/colors";
import { FONT_STYLES } from "styles/fontStyle";
interface modalProps {
  contents: string;
  btnTxt: string[];
  cancelHandler?: () => void;
  confirmHandler: () => void;
}
export default function CommonModal({
  contents,
  btnTxt,
  cancelHandler,
  confirmHandler,
}: modalProps) {
  return (
    <StyledBg>
      <StyledContainer>
        <StyledContents>{contents}</StyledContents>
        <StyledBtnContainer>
          {btnTxt.map((txt, index) => (
            <StyledBtn
              key={index}
              theme={btnTxt.length >= 2 && index === 0 ? "cancel" : "confirm"}
              onClick={
                btnTxt.length >= 2 && index === 0
                  ? cancelHandler
                  : confirmHandler
              }
            >
              {txt}
            </StyledBtn>
          ))}
        </StyledBtnContainer>
      </StyledContainer>
    </StyledBg>
  );
}

const StyledBg = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #0000007d;
  z-index: 9999;
`;

const StyledContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 26rem;
  height: 17rem;
  background-color: white;
  border-radius: 1.6rem;
  padding: 1.6rem;
  z-index: 10000;
`;

const StyledContents = styled.div`
  ${FONT_STYLES.GM_M}
  display: flex;
  align-items: center;
  flex-grow: 1;
  color: ${COLOR.FONT_GRAY_40};
  font-size: 1.5rem;
  padding-bottom: 1.4rem;
`;

interface StyledBtnContainerProps {
  theme: string;
}

const StyledBtnContainer = styled.div`
  display: flex;
  width: 100%;
  gap: 1rem;
`;

const StyledBtn = styled.button<StyledBtnContainerProps>`
  ${({ theme }) => {
    if (theme === "cancel") {
      return css`
        background: white;
        color: ${COLOR.BTN_GRAY_97};
        border: 0.1rem solid ${COLOR.BTN_GRAY_97};
      `;
    } else {
      return css`
        background: ${COLOR.BG_GREEN_28};
        color: white;
      `;
    }
  }}
  height: 4rem;
  width: 100%;
  flex: 1 0 0;
  ${FONT_STYLES.GM_M}
  border-radius: 1rem;
`;
