import styled from "styled-components";
import CommonBtn from "components/common/CommonBtn";
import Diary from "components/PlantDiary/Diary";
import Routes from "router/Routes";
import { useNavigate } from "react-router-dom";
interface listType {
  id: number;
  createDate: string;
  title: string;
  author: string;
  color: string;
  plantName: string;
  contents: string;
}
interface diaryListProps {
  list: listType[] | [];
}
export default function DiaryList({ list }: diaryListProps) {
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
      {list.map(({ createDate, title, color, plantName, contents }, index) => (
        <Diary
          {...{ createDate, title, color, plantName, contents }}
          key={index}
        />
      ))}
      <StyledBtnContainer>
        <CommonBtn label="글쓰기" handler={() => navigate(Routes.DiaryWrite)} />
      </StyledBtnContainer>
    </StContainer>
  );
}

const StContainer = styled.section`
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 8rem;
`;

const StyledBtnContainer = styled.div`
  width: 100%;
  left: 0;
  position: fixed;
  padding: 0 2rem;
  bottom: 2rem;
`;
