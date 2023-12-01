import { ReactComponent as Camera } from "assets/icons/icon_camera.svg";
import { ReactComponent as Main } from "assets/icons/icon_main.svg";
import COLOR from "styles/colors";
import styled from "styled-components";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate } from "react-router-dom";
import Routes from "router/Routes";

export default function TopSection() {
  const diseaseEx = ["#원인", "#해결법", "#증상", "#개선제"];
  const navigate = useNavigate();
  return (
    <StyledTopSection>
      <Main className="main-svg" />
      <StyledDiagnose>
        <StyledDiagnoseBar>
          <StyledDiagnoseTxt>식물 질병을 진단해보세요!</StyledDiagnoseTxt>
          <StyledCameraBtn>
            <RequestCamera onClick={() => navigate(Routes.DiagAI)} />
            <Camera />
          </StyledCameraBtn>
        </StyledDiagnoseBar>
        <StyledDiseaseContainer>
          {diseaseEx.map((disease) => (
            <StyledDisease key={disease}>{disease}</StyledDisease>
          ))}
        </StyledDiseaseContainer>
      </StyledDiagnose>
    </StyledTopSection>
  );
}

const StyledTopSection = styled.section`
  padding-top: 2.7rem;
  position: relative;
  margin-bottom: 2rem;
  .main-svg {
    position: absolute;
  }
`;
const StyledDiagnose = styled.div`
  width: 33.5rem;
  height: 14.8rem;
  margin-top: 12rem;
  position: relative;
  border-radius: 1.8rem;
  padding: 3rem 2rem;
  background-color: white;
  z-index: 1;
  box-shadow: 0px 6px 5px 0px rgba(13, 63, 103, 0.1);
`;
const StyledDiagnoseBar = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-radius: 1.8rem;
  background: ${COLOR.BG_GRAY_E};
`;
const StyledDiagnoseTxt = styled.h5`
  flex-grow: 1;
  padding-left: 1.5rem;
  font-size: 1.5rem;
  ${FONT_STYLES.PR_R}
  color: ${COLOR.FONT_GRAY_AB};
`;
const StyledCameraBtn = styled.button`
  position: relative;
  width: 4.8rem;
  height: 4.8rem;
  border-radius: 1.8rem;
  padding: 1.2rem;
  background-color: white;
  box-shadow: 0px 3px 5px 0px rgba(13, 63, 103, 0.1);
`;

const RequestCamera = styled.div`
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
`;
const StyledDiseaseContainer = styled.div`
  margin-top: 2rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0 1.2rem;
`;
const StyledDisease = styled.div`
  ${FONT_STYLES.PR_M}
  border-radius: 0.5rem;
  background: ${COLOR.BG_GREEN_DA};
  color: ${COLOR.FONT_GREEN_13};
  padding: 0.2rem 1rem;
  letter-spacing: -0.07rem;
  font-size: 1.3rem;
`;
