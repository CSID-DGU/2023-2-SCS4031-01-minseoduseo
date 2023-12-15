import styled, { keyframes } from "styled-components";
import COLOR from "styles/colors";

export default function Loading() {
  return (
    <LoadingBg>
      <LoadingContainer>
        <LoadingBar />
      </LoadingContainer>
      ;
    </LoadingBg>
  );
}

const LoadingBg = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #0000007d;
  z-index: 9999;
`;

const LoadingContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 10rem;
  height: 10rem;
  border-radius: 0.5rem;
  background-color: ${COLOR.BG_GREEN_DA};
`;

const spin = keyframes`
 from { transform: rotate(0deg); }
  to { transform: rotate(359deg); }
`;

const LoadingBar = styled.div`
  margin: 0.5rem auto;
  height: 4rem;
  width: 4rem;
  border: 0.4rem solid transparent;
  border-radius: 50%;
  border-right-color: white;
  border-top-color: white;

  animation: ${spin} 1s infinite linear;
`;
