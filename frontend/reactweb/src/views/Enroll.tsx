import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import CommonBtn from "components/common/CommonBtn";
import Routes from "router/Routes";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate, useParams } from "react-router-dom";
import { ColorResult, SliderPicker } from "react-color";
import { FormEvent, useEffect, useRef, useState } from "react";
import { deletePlant, getPlantDetail, postPlant, putPlant } from "api/myPlant";
import CommonModal from "components/common/CommonModal";
import modalTxtAsset from "assets/json/modalTxt.json";
export default function Enroll() {
  const params = useParams();
  useEffect(() => {
    if (params.plantId) {
      setPlantInfo();
    }
  }, []);
  const setPlantInfo = async () => {
    const info = await getPlantDetail(Number(params.plantId));
    setCurrentColor(info.color);
    if (nickNameRef.current && nameRef.current && dateRef.current) {
      nickNameRef.current.value = info.nickname;
      nameRef.current.value = info.name;
      const createdDate = new Date(info.creatDate);
      dateRef.current.value = String(
        `${createdDate.getFullYear()}-${String(
          createdDate.getMonth() + 1
        ).padStart(2, "0")}-${String(createdDate.getDate()).padStart(2, "0")}`
      );
    }
  };

  const [modalActive, setModalActive] = useState<boolean>(false);
  const [currentColor, setCurrentColor] = useState<string>("#ffff");
  const modalTxt = useRef<{
    contents: string;
    btnTxt: string[];
    cancelHandler?: () => void;
    confirmHandler: () => void;
  }>({
    contents: "",
    btnTxt: ["확인"],
    confirmHandler: () => {},
  });
  const nickNameRef = useRef<HTMLInputElement | null>(null);
  const nameRef = useRef<HTMLInputElement | null>(null);
  const dateRef = useRef<HTMLInputElement | null>(null);
  const handleChange = (result: ColorResult) => {
    setCurrentColor(result.hex);
  };
  const handleDelBtn = () => {
    modalTxt.current = {
      ...modalTxtAsset.plantDelete,
      cancelHandler: () => {
        setModalActive(false);
      },
      confirmHandler: async () => {
        await deletePlant(Number(params.plantId));
        navigate(Routes.MyInfo);
      },
    };
    setModalActive(true);
  };
  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    if (!(nickNameRef.current && nameRef.current && dateRef.current)) return;

    const plantInfo = {
      color: currentColor,
      nickname: nickNameRef.current.value,
      name: nameRef.current.value,
      createDate: new Date(dateRef.current.value).toJSON(),
    };

    if (params.plantId) {
      await putPlant(Number(params.plantId), plantInfo);
      modalTxt.current = {
        ...modalTxtAsset.plantModify,
        confirmHandler: () => {
          setModalActive(false);
        },
      };
    } else {
      await postPlant(plantInfo);
      modalTxt.current = {
        ...modalTxtAsset.plantEnrollment,
        confirmHandler: () => {
          navigate(Routes.MyInfo);
        },
      };
    }
    setModalActive(true);
  };
  const navigate = useNavigate();
  return (
    <StyledEnrollWrapper onSubmit={(e) => handleSubmit(e)}>
      <Header
        title={params.plantId ? "작물 상세" : "작물 등록"}
        icon="previous"
        link={Routes.MyInfo}
      />
      <StyledEnrollContainer>
        <div>
          <StyledInputLabel>작물종</StyledInputLabel>
          <StyledInput placeholder="작물명을 입력해주세요" ref={nameRef} />
        </div>
        <div>
          <StyledInputLabel>작물 이름</StyledInputLabel>
          <StyledInput
            placeholder="식물 이름을 입력해주세요"
            ref={nickNameRef}
          />
        </div>
        <div>
          <StyledInputLabel>작물 태그 색상</StyledInputLabel>
          <StyledColorChange>
            <SliderPicker onChange={handleChange} color={currentColor} />
          </StyledColorChange>
        </div>
        <div>
          <StyledInputLabel>키운 날짜</StyledInputLabel>
          <StyledInput
            placeholder="작물명을 입력해주세요"
            type="date"
            ref={dateRef}
          />
        </div>
        <StyledBtnContainer>
          {params.plantId ? (
            <CommonBtn
              label="삭제"
              theme="delete"
              handler={() => handleDelBtn()}
            />
          ) : (
            <CommonBtn
              label="취소"
              theme="cancel"
              handler={() => navigate(-1)}
            />
          )}
          <CommonBtn label={params.plantId ? "수정" : "저장"} type="submit" />
        </StyledBtnContainer>
      </StyledEnrollContainer>
      {modalActive && <CommonModal {...modalTxt.current} />}
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
