import styled from "styled-components";
import { FONT_STYLES } from "styles/fontStyle";
interface ResultProps {
  title?: string;
  contents?: string;
}

export default function Result({ title, contents }: ResultProps) {
  return (
    <StyledContainer>
      <StyledTitle>{title}</StyledTitle>
      <StyledContents>{contents}</StyledContents>
    </StyledContainer>
  );
}

const StyledContainer = styled.section`
  display: flex;
  gap: 2rem;
`;
const StyledTitle = styled.h4`
  font-size: 1.8rem;
  flex-shrink: 0;
  ${FONT_STYLES.GM_M};
  letter-spacing: -0.08rem;
`;
const StyledContents = styled.div`
  font-size: 1.6rem;
  ${FONT_STYLES.PR_R}
  word-break: keep-all;
`;
