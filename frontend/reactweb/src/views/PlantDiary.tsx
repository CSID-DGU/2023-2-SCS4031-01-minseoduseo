import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import SelectBtn from "components/PlantDiary/SelectBtn";
import { ReactComponent as RightArrow } from "assets/icons/icon_right-arrow.svg";
import { ReactComponent as LeftArrow } from "assets/icons/icon_left-arrow.svg";
import { FONT_STYLES } from "styles/fontStyle";
import { useEffect, useMemo, useState } from "react";
import DiaryList from "components/PlantDiary/DiaryList";
import Calendar from "components/PlantDiary/Calendar";
import { getDiaryList } from "api/diary";
export default function PlantDiary() {
  const btnTxts = ["달력", "일기"];
  const [totalList, setTotalList] = useState([]);
  const [curState, setCurState] = useState(btnTxts[0]);
  const [curDate, setCurDate] = useState(new Date());
  const nxtMonth = useMemo(() => curDate.getMonth() + 1, [curDate]);
  const preMonth = useMemo(() => curDate.getMonth() - 1, [curDate]);
  const getTotalList = async () => {
    const totalList = await getDiaryList({
      year: curDate.getFullYear(),
      month: curDate.getMonth() + 1,
    });

    setTotalList(totalList?.diaries);
  };
  useEffect(() => {
    getTotalList();
  }, [curDate]);

  const currYM = {
    year: curDate.getFullYear(),
    month: curDate.getMonth() + 1,
  };
  const setMonth = (month: number) => {
    const nxtMonthDate = new Date(curDate.setMonth(month));
    setCurDate(nxtMonthDate);
  };

  return (
    <StyledDiaryWrapper>
      <Header title="식물일지" icon="menu" />
      <StyledDiaryContainer>
        <StyledSelectBtn>
          <SelectBtn
            BtnTxt={btnTxts}
            Selected={curState}
            handler={(name) => setCurState(name)}
          />
        </StyledSelectBtn>
        <StyledSelectDate>
          <LeftArrow onClick={() => setMonth(preMonth)} />
          {currYM.year}년 {currYM.month}월
          <RightArrow onClick={() => setMonth(nxtMonth)} />
        </StyledSelectDate>
        {curState === "달력" ? (
          <Calendar currYM={currYM} list={totalList} />
        ) : (
          <DiaryList list={totalList} />
        )}
      </StyledDiaryContainer>
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

const StyledSelectBtn = styled.div`
  padding: 0 6rem;
`;

const StyledSelectDate = styled.section`
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 1.75rem;
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.PR_R}
`;
