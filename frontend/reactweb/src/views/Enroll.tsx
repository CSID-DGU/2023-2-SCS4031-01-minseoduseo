import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import CommonBtn from "components/common/CommonBtn";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate } from "react-router-dom";
import Routes from "router/Routes";
import { ColorResult, SliderPicker } from "react-color";
import { FormEvent, useState } from "react";
import { postPlant } from "api/myPlant";
import CommonModal from "components/common/CommonModal";
export default function Enroll() {
  const [modalActive, setModalActive] = useState<boolean>(false);
  const [currentColor, setCurrentColor] = useState<string>("#ffff");
  const handleChange = (result: ColorResult) => {
    setCurrentColor(result.hex);
  };
  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const nameInput = form[0] as HTMLInputElement;
    const nicknameInput = form[1] as HTMLInputElement;
    const dateInput = form[2] as HTMLInputElement;
    const res = await postPlant({
      color: currentColor,
      nickname: nicknameInput.value,
      name: nameInput.value,
      createDate: new Date(dateInput.value).toJSON(),
    });
    setModalActive(true);
  };
  const navigate = useNavigate();
  return (
    <StyledEnrollWrapper onSubmit={(e) => handleSubmit(e)}>
      <Header title="작물 등록" icon="menu" />
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
          <StyledInputLabel>작물 태그 색상</StyledInputLabel>
          <StyledColorChange>
            <SliderPicker onChange={handleChange} color={currentColor} />
          </StyledColorChange>
        </div>
        <div>
          <StyledInputLabel>키운 날짜</StyledInputLabel>
          <StyledInput placeholder="작물명을 입력해주세요" type="date" />
        </div>
        <StyledBtnContainer>
          <CommonBtn label="취소" theme="cancel" handler={() => navigate(-1)} />
          <CommonBtn label="저장" type="submit" />
        </StyledBtnContainer>
      </StyledEnrollContainer>
      {modalActive && (
        <CommonModal
          contents="식물을 등록하였습니다."
          btnTxt="확인"
          handler={() => navigate(Routes.MyInfo)}
        />
      )}
    </StyledEnrollWrapper>
  );
}
const StyledEnrollWrapper = styled.form`
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
  font-size: 1.5rem;
  ${FONT_STYLES.PR_M}
`;

const StyledInput = styled.input`
  height: 5rem;
  padding: 1.4rem 2rem;
  width: 100%;
  font-size: 1.6rem;
  ${FONT_STYLES.PR_R}
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

const StyledColorChange = styled.div`
  border-radius: 1.6rem;
  padding: 1.7rem;
  background: ${COLOR.BG_GRAY_F5};
`;
