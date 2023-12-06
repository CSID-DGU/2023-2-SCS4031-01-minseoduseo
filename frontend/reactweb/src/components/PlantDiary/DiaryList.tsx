import styled from "styled-components";
import CommonBtn from "components/common/CommonBtn";
import Diary from "components/PlantDiary/Diary";
import Routes from "router/Routes";
import { Link, useNavigate } from "react-router-dom";
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
  const navigate = useNavigate();
  return (
    <StContainer>
      {list.map(
        ({ createDate, title, color, plantName, contents, id }, index) => (
          <Link to={`/diary/${id}`}>
            <Diary
              {...{ createDate, title, color, plantName, contents }}
              key={index}
            />
          </Link>
        )
      )}
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
