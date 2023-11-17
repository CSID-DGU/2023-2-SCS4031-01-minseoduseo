import styled from "styled-components";
import Tag from "components/common/Tag";
import { FONT_STYLES } from "styles/fontStyle";

export default function CalendarItem() {
  return (
    <StyledContainer>
      <StyledTitle>고구마 식물일지 3일차</StyledTitle>
      <StyledTagContainer>
        <Tag type="고구마" color="#b75d6e" />
        <Tag type="토마토" color="#af1a1a" />
      </StyledTagContainer>
    </StyledContainer>
  );
}
const StyledContainer = styled.section`
  background-color: white;
  border-radius: 1.8rem;
  height: 9rem;
  box-shadow: 0px 6px 5px 0px rgba(13, 63, 103, 0.1);
  padding: 2rem;
`;

const StyledTagContainer = styled.div`
  display: flex;
  gap: 0.6rem;
`;

const StyledTitle = styled.h3`
  font-size: 1.5rem;
  ${FONT_STYLES.PR_R};
  margin-bottom: 1rem;
`;
