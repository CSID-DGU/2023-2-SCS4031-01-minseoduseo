import styled from "styled-components";
import COLOR from "styles/colors";
interface selectBtnParams {
  BtnTxt: string[];
  Selected: string;
  handler: (txt: string) => void;
}
export default function SelectBtn({
  BtnTxt,
  Selected,
  handler,
}: selectBtnParams) {
  return (
    <StyledContainer>
      {BtnTxt.map((txt) => {
        return (
          <StyledBtn
            selected={Selected === txt}
            onClick={() => handler(txt)}
            key={txt}
          >
            {txt}
          </StyledBtn>
        );
      })}
    </StyledContainer>
  );
}
interface StyledBtnProps {
  selected: boolean;
}
const StyledContainer = styled.div`
  display: flex;
  gap: 1rem;
`;

const StyledBtn = styled.button<StyledBtnProps>`
  border-radius: 1.8rem;
  width: 100%;
  height: 3.9rem;
  font-size: 1.5rem;
  box-shadow: 0px 3px 4px 0px rgba(13, 63, 103, 0.1);
  color: white;
  background-color: ${(props) =>
    props.selected ? COLOR.BG_GREEN_28 : COLOR.BTN_GRAY_D8};
`;
