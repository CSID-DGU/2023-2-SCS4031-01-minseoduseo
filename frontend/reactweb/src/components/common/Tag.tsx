import COLOR from "styles/colors";
import styled from "styled-components";
import { FONT_STYLES } from "styles/fontStyle";
interface params {
  type: string;
  color?: string;
}
export default function Tag({ type, color }: params) {
  return (
    <StyledTagContainer color={color}>
      <StyledColorPoint color={color} />
      <StyledType>{type}</StyledType>
    </StyledTagContainer>
  );
}

const StyledTagContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 0.4rem;
  padding: 0.4rem;
  border-radius: 0.7rem;
  width: fit-content;
  background-color: ${({ color }) => `${color}25`};
`;
const StyledColorPoint = styled.div`
  width: 1.2rem;
  height: 1.2rem;
  border-radius: 50%;
  line-height: 100%;
  background-color: ${(props) => props.color};
`;
const StyledType = styled.h3`
  font-size: 1.1rem;
  line-height: 1.2rem;
  ${FONT_STYLES.GM_M}
  color: ${COLOR.FONT_BLACK_1F};
  letter-spacing: -0.0325rem;
`;
