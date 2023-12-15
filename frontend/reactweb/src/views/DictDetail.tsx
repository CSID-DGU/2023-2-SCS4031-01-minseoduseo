import COLOR from "styles/colors";
import styled from "styled-components";
import Header from "components/common/Header";
import { FONT_STYLES } from "styles/fontStyle";
import { useState, useEffect, useRef } from "react";
import readCSV from "utils/getLabels";
import plantData from "assets/files/plant_data.csv";
import { useParams } from "react-router-dom";
import Routes from "router/Routes";
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

export default function DictDetail() {
  const curPlant = useParams();
  const [dictionary, setDictionary] = useState<dictionaryItemProps>();

  useEffect(() => {
    const fetchData = async () => {
      const data = await readCSV<dictionaryItemProps>(plantData);
      const currentDict = data.find((props) => props.plantName === curPlant.id);
      setDictionary(currentDict);
    };
    fetchData();
  }, []);
  const characteristics = [
    {
      title: "심는 시기",
      contents: dictionary?.plantingSeason,
    },
    {
      title: "파종법",
      contents: dictionary?.seedingMethod,
    },
    {
      title: "물주기",
      contents: dictionary?.wateringSchedule,
    },
    {
      title: "관리법",
      contents: dictionary?.careMethod,
    },
    {
      title: "주요질병",
      contents: dictionary?.majorDiseases,
    },
    {
      title: "수확 시기",
      contents: dictionary?.harvestingSeason,
    },
  ];
  return (
    <StyledContainer>
      <StyledTop bgimg={dictionary?.image ?? ""}>
        <Header
          title="이전으로"
          icon="previous"
          color="white"
          link={Routes.Dictionary}
        />
      </StyledTop>
      <StyledMain>
        <StyledDesc>
          <StyledDescName>{dictionary?.plantName}</StyledDescName>
          <StyledDescLabel>{dictionary?.plantName} 도감</StyledDescLabel>
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
  font-size: 2.5rem;
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
  font-size: 1.5rem;
  word-break: keep-all;
  color: ${COLOR.FONT_GRAY_59};
`;
