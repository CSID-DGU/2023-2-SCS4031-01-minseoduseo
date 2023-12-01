import styled from "styled-components";
import COLOR from "styles/colors";
import TOMATO_IMG from "assets/images/tomato.jpeg";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate } from "react-router-dom";

export default function DictionaryItem() {
  const navigate = useNavigate();
  const characteristics = [
    { title: "이름", content: "토마토" },
    {
      title: "특징",
      content:
        "비타민 A, B₁ B₂C를 고루 함유하고 있고 특히, 비타민 C의 함량이 높아 피로회복은 물론 체력을 기르는 데에도 도움을 주는 건강식품",
    },
  ];
  return (
    <StyledDictItem onClick={() => navigate("/dict/tomato")}>
      <StyledImg bgimg={TOMATO_IMG} />
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
    </StyledDictItem>
  );
}
interface BgImg {
  bgimg: string;
}
const StyledDictItem = styled.div`
  border-radius: 1.6rem;
  max-width: 100%;
  background: ${COLOR.BG_GRAY_F};
  box-shadow: 0px 5px 18px -5px rgba(0, 0, 0, 0.1);
`;
const StyledImg = styled.div<BgImg>`
  border-top-right-radius: 1.6rem;
  border-top-left-radius: 1.6rem;
  width: 100%;
  height: 20.8rem;
  background: ${(props) => `url(${props.bgimg}) no-repeat top center`};
  background-size: cover;
`;
const StyledDesc = styled.section`
  padding: 2rem;
  background-color: white;
  border-radius: 1.6rem;
`;

const StyledDescName = styled.h2`
  font-size: 1.8rem;
  color: ${COLOR.FONT_BLACK_1F};
  ${FONT_STYLES.GM_M};
  margin-bottom: 0.4rem;
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
  font-size: 1.2rem;
  color: ${COLOR.FONT_GRAY_B4};
  ${FONT_STYLES.PR_R};
  margin-bottom: 1.4rem;
`;

const StyledDescTitle = styled.h5`
  flex-shrink: 0;
  ${FONT_STYLES.GM_M};
  letter-spacing: -0.03rem;
  font-size: 1.2rem;
`;

const StyledDescContent = styled.h5`
  ${FONT_STYLES.PR_R};
  font-size: 1.3rem;
  letter-spacing: -0.015rem;
  color: ${COLOR.FONT_GRAY_59};
`;
