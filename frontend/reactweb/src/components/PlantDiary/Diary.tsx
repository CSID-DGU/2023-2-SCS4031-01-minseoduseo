import styled from "styled-components";
import COLOR from "styles/colors";
import Tag from "components/common/Tag";
import { FONT_STYLES } from "styles/fontStyle";
interface diaryProps {
  title: string;
  date: string;
  content: string;
  tag: string;
  color: string;
}
export default function Diary({
  title,
  date,
  content,
  tag,
  color,
}: diaryProps) {
  return (
    <StyledDiaryContainer>
      <StyledDiary>
        <StyledTitle>{title}</StyledTitle>
        <StyledDate>{date}</StyledDate>
        <StyledContent>{content}</StyledContent>
      </StyledDiary>
      <StyledTag>
        <Tag type={tag} color={color} />
      </StyledTag>
    </StyledDiaryContainer>
  );
}

const StyledDiaryContainer = styled.div`
  position: relative;
  height: 15.2rem;
  box-shadow: 0px 6px 5px 0px rgba(13, 63, 103, 0.1);
  border-radius: 1.2rem;
  background: ${COLOR.BG_GRAY_F};
  padding: 2rem;
`;
const StyledDiary = styled.div``;
const StyledTitle = styled.h3`
  font-size: 1.7rem;
  color: ${COLOR.FONT_BLACK_1F};
  margin-bottom: 0.5rem;
  ${FONT_STYLES.PR_M}
  letter-spacing: -0.09rem;
`;
const StyledDate = styled.h6`
  margin-bottom: 1rem;
  color: ${COLOR.FONT_GRAY_9A};
  ${FONT_STYLES.PR_R}
`;
const StyledContent = styled.div`
  color: ${COLOR.FONT_GRAY_40};
  font-size: 1.4rem;
  ${FONT_STYLES.PR_R}
  letter-spacing: -0.036rem;
`;
const StyledTag = styled.div`
  position: absolute;
  bottom: 2rem;
`;