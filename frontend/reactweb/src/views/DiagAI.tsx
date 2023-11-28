import styled from "styled-components";
import Header from "components/common/Header";
import { useMemo, useRef, useState } from "react";
import COLOR from "styles/colors";
import { ReactComponent as Camera } from "assets/icons/icon_camera.svg";
import axios from "axios";
import { FONT_STYLES } from "styles/fontStyle";
import SelectBtn from "components/PlantDiary/SelectBtn";
import Result from "components/DiagAI/Result";
import useReadCSV from "utils/getLabels";

export default function DiagAI() {
  const labels = useReadCSV();
  const BTN_TXT = ["원인 및 증상", "해결 방법"];
  const resultTxt = useRef([
    { title: "원인", contents: "" },
    { title: "증상", contents: "" },
  ]);
  const solveTxt = useRef([
    { title: "해결책", contents: "" },
    { title: "개선제", contents: "" },
  ]);
  const [curState, setCurState] = useState(BTN_TXT[0]);
  const [disease, setDisease] = useState("");
  const txtByType = useMemo(
    () => (curState === BTN_TXT[0] ? resultTxt.current : solveTxt.current),
    [curState]
  );
  const [src, setSrc] = useState<string>();
  const toURL = async (e: any) => {
    const image = e.target.files[0];
    const formData = new FormData();
    formData.append("file", image);
    const imgSrc = URL.createObjectURL(image);
    setSrc(imgSrc);
    const { data } = await axios.post(
      "http://43.202.176.11:5000/predict",
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    const diseaseLabel = data.top1_class as string;
    labels?.forEach(
      ({
        label,
        disease,
        medicine,
        plant_name,
        reason,
        solution,
        symptoms,
      }) => {
        console.log(symptoms);
        if (label === diseaseLabel) {
          setDisease(disease);
          if (disease === "정상") return;
          resultTxt.current[0].contents = reason;
          resultTxt.current[1].contents = symptoms;
          solveTxt.current[0].contents = solution;
          solveTxt.current[1].contents = medicine;
        }
      }
    );
  };
  return (
    <StyledContainer>
      <Header title="식물 진단" icon="previous" />
      <StyledImgContainer available={Boolean(src)}>
        <StyledImg src={src} />
        {!src && (
          <StyledDefaultTxt>
            <Camera />
            사진을 찍어주세요
          </StyledDefaultTxt>
        )}
      </StyledImgContainer>
      <StyledBottom>
        {!src ? (
          <StyledBtnContainer>
            <StyledButton>
              사진 찍기
              <RequestCamera
                type="file"
                accept="image/*"
                capture
                onChange={toURL}
              />
            </StyledButton>
          </StyledBtnContainer>
        ) : (
          <StyledResult>
            <StyledName>{disease}</StyledName>
            <StyledPercent>정확도 약 80%</StyledPercent>
            <StyledBtnResult>
              <SelectBtn
                BtnTxt={BTN_TXT}
                Selected={curState}
                handler={(name) => setCurState(name)}
              />
              <StyledResultsContainer>
                {txtByType.map(({ title, contents }) => (
                  <Result title={title} contents={contents} />
                ))}
              </StyledResultsContainer>
            </StyledBtnResult>
          </StyledResult>
        )}
      </StyledBottom>
    </StyledContainer>
  );
}

interface StyledImgContainerProps {
  available: boolean;
}

const StyledContainer = styled.section`
  display: flex;
  flex-direction: column;
  background-color: ${COLOR.BG_GRAY_E};
`;

const StyledImgContainer = styled.div<StyledImgContainerProps>`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: ${({ available }) => (available ? "auto" : "77vh")};
`;
const StyledImg = styled.img`
  border: unset;
  width: 100%;
  height: 100%;
  margin-bottom: -2rem;
`;

const StyledDefaultTxt = styled.h3`
  display: flex;
  align-items: center;
  gap: 0.7rem;
  position: absolute;
  font-size: 1.7rem;
  color: ${COLOR.FONT_GRAY_AB};
  .svg {
    color: ${COLOR.FONT_GRAY_AB};
  }
`;

const StyledBottom = styled.section`
  border-top-right-radius: 2.5rem;
  border-top-left-radius: 2.5rem;
  padding: 2.8rem;
  background-color: white;
`;

const StyledBtnContainer = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const RequestCamera = styled.input`
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
`;

const StyledButton = styled.button`
  border-radius: 1.5rem;
  position: relative;
  width: 87vw;
  height: 5.3rem;
  margin: auto 0;
  font-size: 1.5rem;
  background-color: ${COLOR.BG_GREEN_28};
  color: white;
`;

const StyledResult = styled.div``;
const StyledName = styled.h3`
  ${FONT_STYLES.GM_M};
  letter-spacing: -0.03rem;
  font-size: 2.8rem;
  margin-left: auto;
`;

const StyledPercent = styled.h4`
  font-size: 1.4rem;
  margin-top: 0.8rem;
  color: ${COLOR.FONT_GRAY_9A};
  ${FONT_STYLES.PR_R}
`;

const StyledBtnResult = styled.div`
  margin-top: 2rem;
`;

const StyledResultsContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 3rem;
  margin-top: 4rem;
`;
