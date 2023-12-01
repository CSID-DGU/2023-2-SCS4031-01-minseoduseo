import styled from "styled-components";
import Tag from "components/common/Tag";
import { FONT_STYLES } from "styles/fontStyle";
interface calendarItemProps {
  title: string;
  plantName: string;
  color: string;
}
export default function CalendarItem({
  title,
  plantName,
  color,
}: calendarItemProps) {
  return (
    <StyledContainer>
      <StyledTitle>{title}</StyledTitle>
      <Tag type={plantName} color={color} />
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

const StyledTitle = styled.h3`
  font-size: 1.5rem;
  ${FONT_STYLES.PR_R};
  margin-bottom: 1rem;
`;
