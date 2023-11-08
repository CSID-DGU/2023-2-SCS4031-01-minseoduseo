import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import SelectBtn from "components/PlantDiary/SelectBtn";
import Diary from "components/PlantDiary/Diary";
import { ReactComponent as RightArrow } from "assets/icons/icon_right-arrow.svg";
import { ReactComponent as LeftArrow } from "assets/icons/icon_left-arrow.svg";
import { FONT_STYLES } from "styles/fontStyle";
import CommonBtn from "components/common/CommonBtn";
import { useNavigate } from "react-router-dom";
import Routes from "router/Routes";
export default function PlantDiary() {
  const diaryTxt = {
    title: "고구마 식물일지",
    date: "2021년 07월 21일(월) 오후 24:00",
    content: "오늘 고구마 잎 3개 정도 떡잎이 나왔어요...",
    tag: "고구마",
    color: "#8A3141",
  };
  const navigate = useNavigate();
  return (
    <StyledDiaryWrapper>
      <Header title="식물일지" icon="menu" />
      <StyledDiaryContainer>
        <SelectBtn BtnTxt={["달력", "일기"]} Selected="일기" />
        <StyledSelectDate>
          <LeftArrow />
          2023년 7월
          <RightArrow />
        </StyledSelectDate>
        <StyledDiaryList>
          <Diary {...diaryTxt} />
          <Diary {...diaryTxt} />
        </StyledDiaryList>
      </StyledDiaryContainer>
      <StyledBtnContainer>
        <CommonBtn label="글쓰기" handler={() => navigate(Routes.DiaryWrite)} />
      </StyledBtnContainer>
    </StyledDiaryWrapper>
  );
}

const StyledDiaryWrapper = styled.div`
  background-color: ${COLOR.BG_GRAY_F5};
  min-height: 100vh;
`;
const StyledDiaryContainer = styled.main`
  display: flex;
  flex-direction: column;
  gap: 2rem;
  margin-top: 1rem;
  padding: 0 2rem;
`;
const StyledSelectDate = styled.section`
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 1.6rem;
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.PR_R}
`;

const StyledBtnContainer = styled.div`
  width: 100%;
  position: fixed;
  padding: 0 2rem;
  bottom: 2rem;
`;

const StyledDiaryList = styled.section`
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 8rem;
`;
