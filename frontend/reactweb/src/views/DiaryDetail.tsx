import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import { FONT_STYLES } from "styles/fontStyle";
import Tag from "components/common/Tag";
import CommonBtn from "components/common/CommonBtn";
import { useEffect, useState } from "react";
import { getDiary } from "api/diary";
import { useParams } from "react-router-dom";
import { fromJSONtoDateStr } from "utils/getDate";
import TagDropDown from "components/common/TagDropDown";
export default function DiaryDetail() {
  const params = useParams();
  interface contentsType {
    id: number;
    createDate: string;
    title: string;
    author: string;
    color: string;
    plantName: string;
    contents: string;
  }
  const [contents, setContents] = useState<contentsType>({
    id: 1,
    createDate: `${new Date()}`,
    title: "",
    author: "",
    color: "",
    plantName: "",
    contents: "",
  });
  const setDiaryDetail = async () => {
    const diaryDetail = await getDiary(Number(params.id));
    setContents(diaryDetail as contentsType);
  };
  useEffect(() => {
    setDiaryDetail();
  }, []);
  return (
    <StyledContainer>
      <Header title="일기 상세" icon="previous" />
      <StyledMain>
        <StyledDate>{fromJSONtoDateStr(contents.createDate, true)}</StyledDate>
        <StyledTitle>{contents.title}</StyledTitle>
        <Tag type={contents.plantName} color={contents.color} />
        <StyledContent>{contents.contents}</StyledContent>
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
