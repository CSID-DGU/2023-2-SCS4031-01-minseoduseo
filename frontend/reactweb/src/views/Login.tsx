import styled from "styled-components";
import { ReactComponent as Main } from "assets/icons/icon_main.svg";
import { ReactComponent as KakaoIcon } from "assets/icons/icon_kakaoLogin.svg";
import { ReactComponent as GoogleIcon } from "assets/icons/icon_googleLogin.svg";
import COLOR from "styles/colors";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const navigate = useNavigate();
  return (
    <StyledContainer>
      <StyledMainIconWrapper>
        <Main />
      </StyledMainIconWrapper>
      <StyledKakaoBtn onClick={() => navigate("/kakao/login")}>
        <KakaoIcon />
        카카오 계정으로 로그인
      </StyledKakaoBtn>
      <StyledGoogleBtn disabled={true}>
        <GoogleIcon />
        구글 계정으로 로그인
      </StyledGoogleBtn>
      <StyledDivider>
        <StyledExplain>소셜 로그인을 진행해주세요.</StyledExplain>
        <StyledExplain>구글 로그인은 지원 예정입니다.</StyledExplain>
      </StyledDivider>
    </StyledContainer>
  );
}

const StyledContainer = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-end;
  width: 100vw;
  height: 100vh;
  padding-bottom: 5rem;
  background-color: ${COLOR.BG_GREEN_E1};
`;

const StyledMainIconWrapper = styled.div`
  position: absolute;
  margin-bottom: 16.8rem;
`;

const StyledKakaoBtn = styled.button`
  display: flex;
  gap: 0.5rem;
  align-items: center;
  justify-content: center;
  font-size: 1.7rem;
  background-color: ${COLOR.LOGO_KAKAO};
  box-shadow: 0px 4px 6px 2px rgba(13, 63, 103, 0.1);
  border-radius: 0.4rem;
  height: 5rem;
  width: 85vw;
  z-index: 2;
  &:active {
    background-color: #f2dc19;
  }
`;

const StyledGoogleBtn = styled.button`
  display: flex;
  gap: 0.5rem;
  align-items: center;
  justify-content: center;
  font-size: 1.7rem;
  background-color: white;
  box-shadow: 0px 4px 6px 2px rgba(13, 63, 103, 0.1);
  border-radius: 0.4rem;
  height: 5rem;
  width: 85vw;
  margin-top: 2rem;
  z-index: 2;
`;

const StyledDivider = styled.div`
  margin-top: 3rem;
`;

const StyledExplain = styled.h4`
  font-size: 1.4rem;
  color: ${COLOR.FONT_GRAY_AB};
  ${FONT_STYLES.PR_R};
  margin-top: 0.5rem;
  text-align: center;
  letter-spacing: -0.04rem;
`;
