import styled from "styled-components";
import COLOR from "styles/colors";
interface selectBtnParams {
  BtnTxt: string[];
  Selected: string;
}
export default function SelectBtn({ BtnTxt, Selected }: selectBtnParams) {
  return (
    <StyledContainer>
      {BtnTxt.map((txt) => {
        return <StyledBtn selected={Selected === txt}>{txt}</StyledBtn>;
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
  padding: 0 5rem;
`;

const StyledBtn = styled.button<StyledBtnProps>`
  border-radius: 1.8rem;
  width: 100%;
  height: 3.7rem;
  color: ${(props) => (props.selected ? "white" : COLOR.FONT_GRAY_AB)};
  background-color: ${(props) =>
    props.selected ? COLOR.BG_GREEN_28 : COLOR.BG_GRAY_F};

  ${({ selected }) =>
    !selected &&
    `border: 0.1rem solid ${COLOR.FONT_GRAY_AB}
  `}
`;
