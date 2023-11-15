import styled from "styled-components";
import COLOR from "styles/colors";
import { ReactComponent as PlusIcon } from "assets/icons/icon_plus.svg";
import { FONT_STYLES } from "styles/fontStyle";
import getCalendar from "utils/getCalendar";

export default function Calendar() {
  const days = ["일", "월", "화", "수", "목", "금", "토"];
  return (
    <StyledContainer>
      <StyledCalendar>
        <StyledDayContainer>
          {days.map((day) => (
            <StyledDay key={day} day={day}>
              {day}
            </StyledDay>
          ))}
        </StyledDayContainer>
        <StyledWeekContainer>
          {getCalendar(2023, 12).map((dates) => {
            return (
              <StyledWeek>
                {dates.map((date) => (
                  <StyledDate>{date}</StyledDate>
                ))}
              </StyledWeek>
            );
          })}
        </StyledWeekContainer>
      </StyledCalendar>
      <StyledPreview>
        <StyledPreviewHeader>
          <StyledPreviewDate>23.07.25 (금)</StyledPreviewDate>
          <PlusIcon />
        </StyledPreviewHeader>
      </StyledPreview>
    </StyledContainer>
  );
}
interface dayType {
  day: string;
}
const StyledContainer = styled.section``;
const StyledCalendar = styled.section`
  background-color: white;
  border-radius: 1.8rem;
  width: 90%;
  margin: 0 auto;
  height: 37vh;
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
`;
const StyledPreviewDate = styled.h3`
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.PR_R}
  font-size: 1.6rem;
  > svg {
    color: ${COLOR.FONT_BLACK_1F};
  }
`;
const StyledDayContainer = styled.div`
  display: flex;
`;
const StyledDay = styled.h2<dayType>`
  flex: 0 0 0;
  margin: 0 auto;
  color: ${({ day }) => {
    if (day === "일") return COLOR.BTN_RED_F8;
    else if (day === "토") return "#3E85F1";
  }};
`;
const StyledWeekContainer = styled.div`
  margin-top: 2.3rem;
  height: -webkit-fill-available;
`;
const StyledWeek = styled.div`
  display: flex;
  height: 4.2rem;
  height: 15%;
`;
const StyledDate = styled.h2`
  flex: 1 0 0;
  font-size: 1.2rem;
  padding-left: 1rem;
  ${FONT_STYLES.PR_R}
  &:first-of-type {
    color: ${COLOR.BTN_RED_F8};
  }
`;
