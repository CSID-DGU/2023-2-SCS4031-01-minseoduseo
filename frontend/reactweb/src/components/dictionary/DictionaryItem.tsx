import styled from "styled-components";
import COLOR from "styles/colors";
import { FONT_STYLES } from "styles/fontStyle";
import { useNavigate } from "react-router-dom";
interface dictionaryItemProps {
  plantName: string;
  image: string;
  plantingSeason: string;
  seedingMethod: string;
  wateringSchedule: string;
  careMethod: string;
  majorDiseases: string;
  harvestingSeason: string;
}
export default function DictionaryItem({
  plantName,
  image,
  plantingSeason,
  seedingMethod,
  wateringSchedule,
  careMethod,
  majorDiseases,
  harvestingSeason,
}: dictionaryItemProps) {
  const navigate = useNavigate();
  const characteristics = [
    {
      title: "심는 시기",
      contents: plantingSeason,
    },
    {
      title: "파종법",
      contents: seedingMethod,
    },
    {
      title: "물주기",
      contents: wateringSchedule,
    },
    {
      title: "관리법",
      contents: careMethod,
    },
    {
      title: "주요질병",
      contents: majorDiseases,
    },
    {
      title: "수확 시기",
      contents: harvestingSeason,
    },
  ];
  return (
    <StyledDictItem onClick={() => navigate(`/dict/${plantName}`)}>
      <StyledImg bgimg={image} />
      <StyledDesc>
        <StyledDescName>{plantName}</StyledDescName>
        <StyledDescLabel>{plantName} 정보</StyledDescLabel>
        <StyledDescItemContainer>
          {characteristics.map(({ title, contents }) => {
            return (
              <StyledDescItem key={title}>
                <StyledDescTitle>{title}</StyledDescTitle>
                <StyledDescContent>{contents}</StyledDescContent>
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
  width: 5rem;
  letter-spacing: -0.03rem;
  font-size: 1.2rem;
`;

const StyledDescContent = styled.h5`
  ${FONT_STYLES.PR_R};
  font-size: 1.3rem;
  letter-spacing: -0.015rem;
  color: ${COLOR.FONT_GRAY_59};
  overflow: hidden;
  white-space: normal;
  display: -webkit-box;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
`;
