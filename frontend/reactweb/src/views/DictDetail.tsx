import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import TOMATO_IMG from "assets/images/tomato.jpeg";
import { FONT_STYLES } from "styles/fontStyle";
export default function DictDetail() {
  const characteristics = [
    { title: "이름", content: "토마토" },
    {
      title: "특징",
      content:
        "비타민 A, B₁ B₂C를 고루 함유하고 있고 특히, 비타민 C의 함량이 높아 피로회복은 물론 체력을 기르는 데에도 도움을 주는 건강식품",
    },
    {
      title: "물 주기",
      content: "2~3일마다",
    },
  ];
  return (
    <StyledContainer>
      <StyledTop bgimg={TOMATO_IMG}>
        <Header title="이전으로" icon="previous" color="white" />
      </StyledTop>
      <StyledMain>
        <StyledDesc>
          <StyledDescName>토마토</StyledDescName>
          <StyledDescLabel>tomato</StyledDescLabel>
          <StyledDescItemContainer>
            {characteristics.map(({ title, content }) => {
              return (
                <StyledDescItem key={title}>
                  <StyledDescTitle>{title}</StyledDescTitle>
                  <StyledDescContent>{content}</StyledDescContent>
                </StyledDescItem>
              );
            })}
          </StyledDescItemContainer>
        </StyledDesc>
      </StyledMain>
    </StyledContainer>
  );
}
interface BgImg {
  bgimg: string;
}
const StyledContainer = styled.main`
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
`;
const StyledTop = styled.section<BgImg>`
  height: 42vh;
  width: 100%;
  position: absolute;
  background: ${(props) => `url(${props.bgimg}) no-repeat top center`};
  background-size: cover;
`;
const StyledMain = styled.main`
  border-top-right-radius: 1.6rem;
  border-top-left-radius: 1.6rem;
  background-color: white;
  margin-top: 40vh;
  z-index: 1;
  flex-grow: 1;
`;
const StyledDesc = styled.section`
  padding: 2rem;
  background-color: white;
  border-radius: 1.6rem;
`;

const StyledDescName = styled.h2`
  font-size: 2rem;
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.GM_M};
  margin-bottom: 0.2rem;
`;

const StyledDescItemContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

const StyledDescItem = styled.div`
  display: flex;
  gap: 1.6rem;
`;

const StyledDescLabel = styled.h6`
  font-size: 1.3rem;
  color: ${COLOR.FONT_GRAY_B4};
  ${FONT_STYLES.PR_R};
  margin-bottom: 2rem;
`;

const StyledDescTitle = styled.h5`
  flex-shrink: 0;
  ${FONT_STYLES.GM_M};
  letter-spacing: -0.02rem;
  font-size: 1.3rem;
  width: 15vw;
`;

const StyledDescContent = styled.h5`
  ${FONT_STYLES.PR_R};
  font-size: 1.4rem;
  letter-spacing: -0.015rem;
  color: ${COLOR.FONT_GRAY_59};
`;
