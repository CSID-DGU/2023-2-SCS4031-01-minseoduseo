import COLOR from "styles/colors";
import styled from "styled-components";
interface params {
  type: string;
  color?: string;
}
export default function Tag({ type, color }: params) {
  return (
    <StyledTagContainer>
      <StyledColorPoint color={color} />
      <StyledType>{type}</StyledType>
    </StyledTagContainer>
  );
}

const StyledTagContainer = styled.div`
  display: flex;
  gap: 0.4rem;
  padding: 0.4rem;
  border-radius: 0.7rem;
  width: fit-content;
  background-color: ${COLOR.BG_YELLOW_FF};
`;
const StyledColorPoint = styled.div`
  width: 1.2rem;
  height: 1.2rem;
  border-radius: 50%;
  background-color: ${(props) => props.color};
`;
const StyledType = styled.h3`
  font-size: 1.3rem;
  color: ${COLOR.FONT_BLACK_1F};
  letter-spacing: -0.0325rem;
`;
