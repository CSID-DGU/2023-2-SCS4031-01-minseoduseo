import styled from "styled-components";
import COLOR from "styles/colors";
import { FONT_STYLES } from "styles/fontStyle";
interface modalProps {
  contents: string;
  btnTxt: string;
  handler: () => void;
}
export default function CommonModal({ contents, btnTxt, handler }: modalProps) {
  return (
    <StyledBg>
      <StyledContainer>
        <StyledContents>{contents}</StyledContents>
        <StyledBtn onClick={handler}>{btnTxt}</StyledBtn>
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

const StyledBtn = styled.button`
  height: 4rem;
  width: 100%;
  color: white;
  ${FONT_STYLES.GM_M}
  border-radius: 1rem;
  background: ${COLOR.BG_GREEN_28};
`;
