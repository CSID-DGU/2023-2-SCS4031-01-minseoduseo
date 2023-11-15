import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import SelectBtn from "components/PlantDiary/SelectBtn";
import { ReactComponent as RightArrow } from "assets/icons/icon_right-arrow.svg";
import { ReactComponent as LeftArrow } from "assets/icons/icon_left-arrow.svg";
import { FONT_STYLES } from "styles/fontStyle";
import CommonBtn from "components/common/CommonBtn";
import { useNavigate } from "react-router-dom";
import Routes from "router/Routes";
import { useState } from "react";
import DiaryList from "components/PlantDiary/DiaryList";
import Calendar from "components/PlantDiary/Calendar";
export default function PlantDiary() {
  const navigate = useNavigate();
  const [curState, setCurState] = useState("일기");

  return (
    <StyledDiaryWrapper>
      <Header title="식물일지" icon="menu" />
      <StyledDiaryContainer>
        <SelectBtn
          BtnTxt={["달력", "일기"]}
          Selected={curState}
          handler={(name) => {
            setCurState(name);
          }}
        />
        <StyledSelectDate>
          <LeftArrow />
          2023년 11월
          <RightArrow />
        </StyledSelectDate>
        {curState === "달력" ? <Calendar /> : <DiaryList />}
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
