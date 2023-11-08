import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import CommonBtn from "components/common/CommonBtn";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate } from "react-router-dom";
export default function Enroll() {
  const navigate = useNavigate();
  return (
    <StyledEnrollWrapper>
      <Header title="작물 등록" icon="previous" />
      <StyledEnrollContainer>
        <div>
          <StyledInputLabel>작물종</StyledInputLabel>
          <StyledInput placeholder="작물명을 입력해주세요" />
        </div>
        <div>
          <StyledInputLabel>작물 이름</StyledInputLabel>
          <StyledInput placeholder="식물 이름을 입력해주세요" />
        </div>
        <div>
          <StyledInputLabel>날짜</StyledInputLabel>
          <StyledInput placeholder="작물명을 입력해주세요" />
        </div>
        <StyledBtnContainer>
          <CommonBtn label="취소" type="cancel" handler={() => navigate(-1)} />
          <CommonBtn label="저장" />
        </StyledBtnContainer>
      </StyledEnrollContainer>
    </StyledEnrollWrapper>
  );
}
const StyledEnrollWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
`;

const StyledEnrollContainer = styled.main`
  display: flex;
  flex-grow: 1;
  flex-direction: column;
  gap: 2rem;
  padding: 1rem 2rem;
  margin-top: 1rem;
`;

const StyledInputLabel = styled.h5`
  margin-bottom: 1rem;
  font-size: 1.3rem;
  ${FONT_STYLES.GM_M}
`;

const StyledInput = styled.input`
  height: 5rem;
  padding: 1.4rem 2rem;
  width: 100%;
  font-size: 1.5rem;
  background-color: ${COLOR.BG_GRAY_F5};
  border-radius: 1.6rem;
  &::placeholder {
    color: ${COLOR.PLACEHOLDER_GRAY_C1};
  }
`;

const StyledBtnContainer = styled.div`
  display: flex;
  margin-top: auto;
  gap: 1.1rem;
  margin-bottom: 1rem;
`;
