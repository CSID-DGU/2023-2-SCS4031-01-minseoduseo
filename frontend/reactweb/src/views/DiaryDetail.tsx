import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import { FONT_STYLES } from "styles/fontStyle";
import Tag from "components/common/Tag";
import CommonBtn from "components/common/CommonBtn";
export default function DiaryDetail() {
  return (
    <StyledContainer>
      <Header title="일기 상세" icon="previous" />
      <StyledMain>
        <StyledDate>2021년 07월 21일(월) 오후 24:00</StyledDate>
        <StyledTitle>고구마 식물일지</StyledTitle>
        <Tag type="토마토" color="#8A3141" />
        <StyledContent>
          오늘 고구마 잎 3개 정도 떡잎이 나왔어요... 앞으로도 물 잘 주면 더 쑥쑥
          자라겠죠?
        </StyledContent>
      </StyledMain>
      <StyledBtnContainer>
        <CommonBtn label="삭제" theme="delete" />
        <CommonBtn label="수정" />
      </StyledBtnContainer>
    </StyledContainer>
  );
}

const StyledContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1.8rem;
  min-height: 100vh;
`;

const StyledMain = styled.main`
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  padding: 0 2rem;
`;

const StyledDate = styled.section`
  color: ${COLOR.FONT_GRAY_9A};
  ${FONT_STYLES.PR_R};
  letter-spacing: -0.03rem;
  font-size: 1.2rem;
`;

const StyledTitle = styled.h2`
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.GM_M};
  font-size: 1.8rem;
`;

const StyledContent = styled.section`
  margin-top: 2rem;
  color: ${COLOR.FONT_GRAY_40};
  ${FONT_STYLES.PR_R};
  letter-spacing: -0.05rem;
  font-size: 1.5rem;
`;

const StyledBtnContainer = styled.div`
  display: flex;
  gap: 1.1rem;
  padding: 0 2rem;
  margin-top: auto;
  margin-bottom: 2rem;
`;
