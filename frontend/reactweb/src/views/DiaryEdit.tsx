import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import { FONT_STYLES } from "styles/fontStyle";
import Tag from "components/common/Tag";
import CommonBtn from "components/common/CommonBtn";
export default function DiaryEdit() {
  return (
    <StyledContainer>
      <Header title="일기 작성" icon="previous" />
      <StyledMain>
        <div>
          <StyledInputLabel>날짜</StyledInputLabel>
          <StyledInput type="date" />
        </div>
        <div>
          <StyledInputLabel>제목</StyledInputLabel>
          <StyledInput placeholder="제목을 입력해주세요" />
        </div>
        <div>
          <StyledInputLabel>작물종</StyledInputLabel>
          <StyledTagContainer>
            <Tag type="고구마" color="#8A3141" />
          </StyledTagContainer>
        </div>
        <div className="grow-flex">
          <StyledInputLabel>내용</StyledInputLabel>
          <StyledTextArea />
        </div>
      </StyledMain>
      <StyledBtnContainer>
        <CommonBtn label="저장" />
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
  flex-grow: 1;
  flex-direction: column;
  gap: 1.2rem;
  padding: 0 2rem;
  .grow-flex {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
  }
`;

const StyledBtnContainer = styled.div`
  display: flex;
  gap: 1.1rem;
  padding: 0 2rem;
  margin-top: auto;
  margin-bottom: 2rem;
`;

const StyledInputLabel = styled.h5`
  margin-bottom: 1rem;
  font-size: 1.3rem;
  ${FONT_STYLES.GM_M}
`;

const StyledTagContainer = styled.div`
  padding: 1rem;
  border-radius: 1.6rem;
  border: 0.2rem solid #eaeaea;
`;

const StyledInput = styled.input`
  height: 5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: -webkit-fill-available;
  min-width: -moz-fill-available;
  width: 100%;
  padding: 1.4rem 2rem;
  font-size: 1.6rem;
  border-radius: 1.6rem;
  background-color: ${COLOR.BG_GRAY_F5};
  ${FONT_STYLES.PR_R}
  &::placeholder {
    font-size: 1.4rem;
    color: ${COLOR.PLACEHOLDER_GRAY_C1};
  }
`;

const StyledTextArea = styled.textarea`
  flex-grow: 1;
  padding: 1.4rem 2rem;
  width: 100%;
  font-size: 1.6rem;
  ${FONT_STYLES.PR_R}
  background-color: ${COLOR.BG_GRAY_F5};
  border-radius: 1.6rem;
  border: unset;
`;
