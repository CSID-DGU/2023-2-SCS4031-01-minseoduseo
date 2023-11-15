import styled from "styled-components";
import Diary from "components/PlantDiary/Diary";
import { useNavigate } from "react-router-dom";
export default function DiaryList() {
  const diaryTxt = {
    title: "고구마 식물일지",
    date: "2021년 07월 21일(월) 오후 24:00",
    content: "오늘 고구마 잎 3개 정도 떡잎이 나왔어요...",
    tag: "고구마",
    color: "#8A3141",
  };
  const navigate = useNavigate();
  return (
    <StContainer>
      <Diary {...diaryTxt} />
      <Diary {...diaryTxt} />
    </StContainer>
  );
}

const StContainer = styled.section`
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 8rem;
`;
