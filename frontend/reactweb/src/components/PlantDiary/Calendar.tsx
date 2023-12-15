import { css, styled } from "styled-components";
import COLOR from "styles/colors";
import CalendarItem from "components/PlantDiary/CalendarItem";
import { ReactComponent as PlusIcon } from "assets/icons/icon_plus.svg";
import { FONT_STYLES } from "styles/fontStyle";
import getCalendar from "utils/getCalendar";
import { useState, useMemo, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Routes from "router/Routes";
interface listType {
  id: number;
  createDate: string;
  title: string;
  author: string;
  color: string;
  plantName: string;
  contents: string;
}
interface calendarProps {
  currYM: { month: number; year: number };
  list: listType[] | [];
}

const getlistbyDate = (diaries: listType[] | []) => {
  const initial: { [date: number]: listType[] } = {};
  diaries.forEach((diary) => {
    const date = new Date(diary.createDate).getDate();
    if (!initial[date]) {
      initial[date] = [];
    }
    initial[date].push(diary);
  });
  return initial;
};
export default function Calendar({ currYM, list }: calendarProps) {
  const DAYS = ["일", "월", "화", "수", "목", "금", "토"];
  const navigate = useNavigate();
  const [listBydate, setListByDate] = useState<{ [date: number]: listType[] }>(
    {}
  );
  const [selDate, setSelDate] = useState(new Date().getDate());
  useEffect(() => setListByDate(getlistbyDate(list)), [list]);
  const curDateTxt = useMemo(() => {
    const strYear = String(currYM.year).slice(-2);
    const strMonth = String(currYM.month).padStart(2, "0");
    const strDate = String(selDate).padStart(2, "0");
    const day = new Date(currYM.year, currYM.month - 1, selDate).getDay();
    const strDay = DAYS[day];
    return `${strYear}.${strMonth}.${strDate} (${strDay})`;
  }, [currYM, selDate]);
  const onSelect = (date: number | null) => {
    if (date) setSelDate(date);
  };

  return (
    <StyledContainer>
      <StyledCalendar>
        <StyledDayContainer>
          {DAYS.map((day) => (
            <StyledDay key={day} day={day}>
              {day}
            </StyledDay>
          ))}
        </StyledDayContainer>
        <StyledWeekContainer>
          {getCalendar(currYM.year, currYM.month).map((dates, index) => {
            return (
              <StyledWeek key={index}>
                {dates.map((date, index) => (
                  <StyledDate
                    key={index}
                    selected={selDate === date}
                    onClick={() => onSelect(date)}
                  >
                    {date}
                    <StyledSpotContainer>
                      {date &&
                        listBydate[date] &&
                        listBydate[date].map(({ color, id }: listType) => (
                          <StyledSpot color={color} key={id} />
                        ))}
                    </StyledSpotContainer>
                  </StyledDate>
                ))}
              </StyledWeek>
            );
          })}
        </StyledWeekContainer>
      </StyledCalendar>
      <StyledPreview>
        <StyledPreviewHeader>
          <StyledPreviewDate>{curDateTxt}</StyledPreviewDate>
          <StyledBtn onClick={() => navigate(Routes.DiaryWrite)}>
            <PlusIcon />
          </StyledBtn>
        </StyledPreviewHeader>
        <StyledPreviewContent>
          {listBydate[selDate] &&
            listBydate[selDate].map(
              ({ title, plantName, color, id }, index) => (
                <Link to={`/diary/${id}`} key={id}>
                  <CalendarItem {...{ color, plantName, title }} />
                </Link>
              )
            )}
        </StyledPreviewContent>
      </StyledPreview>
    </StyledContainer>
  );
}
interface dayType {
  day: string;
}
interface selectedType {
  selected: boolean;
}
const StyledContainer = styled.section``;
const StyledCalendar = styled.section`
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 1.8rem;
  width: 86vw;
  margin: 0 auto;
  height: 86vw;
  padding: 2.3rem 2rem 1.75rem 2rem;
  box-shadow: 0.2rem 1rem 1.2rem 0rem rgba(13, 63, 103, 0.1);
`;
const StyledPreview = styled.section`
  margin-top: 2.4rem;
`;
const StyledPreviewHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
`;

const StyledBtn = styled.button`
  background: transparent;
`;

const StyledPreviewDate = styled.h3`
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.PR_R}
  font-size: 1.7rem;
  > svg {
    color: ${COLOR.BG_GREEN_28};
  }
`;

const StyledDayContainer = styled.div`
  display: flex;
`;
const StyledDay = styled.h2<dayType>`
  flex: 0 0 0;
  margin: 0 auto;
  font-size: 1.4rem;
  ${FONT_STYLES.GM_M}
  color: ${({ day }) => {
    if (day === "일") return COLOR.BTN_RED_F8;
    else if (day === "토") return "#3E85F1";
  }};
`;
const StyledWeekContainer = styled.div`
  margin-top: 2.3rem;
  height: -webkit-fill-available;
  height: -moz-available;
  display: grid;
  grid-template-rows: repeat(7, 1fr);
`;
const StyledWeek = styled.div`
  display: flex;
  height: 100%;
`;
const StyledDate = styled.h2<selectedType>`
  flex: 1 0 0;
  font-size: 1.2rem;
  padding: 0.3rem 0.6rem;
  ${FONT_STYLES.PR_R}
  ${({ selected }) =>
    selected &&
    css`
      border-radius: 0.8rem;
      background: ${COLOR.BG_GREEN_EE};
      box-shadow: inset 0rem -0.33rem 0.33rem #c6d4c6;
      outline: 1px solid #a0bd9445;
      outline-offset: 1px;
    `}
  &:first-of-type {
    color: ${COLOR.BTN_RED_F8};
  }
`;
const StyledSpotContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 0.35rem;
`;
const StyledSpot = styled.div`
  width: 0.5rem;
  height: 0.5rem;
  border-radius: 50%;
  background-color: ${({ color }) => color};
`;

const StyledPreviewContent = styled.section`
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  margin-bottom: 4rem;
`;
