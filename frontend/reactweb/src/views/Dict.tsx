import COLOR from "styles/colors";
import styled from "styled-components";
import { ReactComponent as Previous } from "assets/icons/icon_previous.svg";
import { ReactComponent as SearchIcon } from "assets/icons/icon_search.svg";
import { ReactComponent as ClearIcon } from "assets/icons/icon_clear.svg";
import DictionaryItem from "components/dictionary/DictionaryItem";
import { useNavigate } from "react-router-dom";
import { useEffect, useMemo, useState } from "react";
import readCSV from "utils/getLabels";
import plantData from "assets/files/plant_data.csv";
import data from "assets/json/plantList.json";
import { FONT_STYLES } from "styles/fontStyle";
import Routes from "router/Routes";
type PlantData = {
  image: string;
  plantName: string;
  plantingSeason: string;
  seedingMethod: string;
  wateringSchedule: string;
  careMethod: string;
  majorDiseases: string;
  harvestingSeason: string;
};

export default function Dict() {
  const navigate = useNavigate();
  const [inputValue, setInputValue] = useState("");
  const [selectedPlant, setSelectedPlant] = useState<string | "">("");
  const [listItems, setListItems] = useState<string[] | []>([]);
  const [dictionaryList, setDictionaryList] = useState<
    PlantData[] | undefined
  >();
  const searchedPlants = useMemo(() => {
    if (dictionaryList) {
      return dictionaryList.filter(({ plantName }) =>
        plantName.includes(selectedPlant)
      );
    }
  }, [selectedPlant]);
  useEffect(() => {
    const fetchData = async () => {
      const data = await readCSV<PlantData>(plantData);
      setDictionaryList(data);
    };
    fetchData();
  }, []);
  const handleInput = (element: HTMLInputElement) => {
    const input = element.value.trim();
    setInputValue(input);
    let list: string[] | [] = [];
    if (input) {
      list = data.plantNames.filter((plantName) => plantName.includes(input));
    }
    if (JSON.stringify(list) !== JSON.stringify(listItems)) {
      setListItems(list);
    }
  };
  return (
    <StyledDictContainer
      onSubmit={(e) => {
        e.preventDefault();
        setSelectedPlant(inputValue);
      }}
    >
      <StyledSearchContainer>
        <Previous onClick={() => navigate(Routes.Home)} />
        <StyledSearchBar>
          <SearchIcon />
          <StyledSearchInput
            onInput={(e: React.FormEvent<HTMLInputElement>) => {
              handleInput(e.target as HTMLInputElement);
            }}
            value={inputValue}
          />
          <ClearIcon />
        </StyledSearchBar>
      </StyledSearchContainer>

      {selectedPlant ? (
        <StyledDictList>
          {searchedPlants &&
            searchedPlants.map((plant) => (
              <DictionaryItem {...plant} key={plant.plantName} />
            ))}
        </StyledDictList>
      ) : (
        <StyledList>
          <StyledListTitle>유사 검색어</StyledListTitle>
          <StyledListContainer>
            {listItems.map((item) => (
              <StyledListItems key={item} onClick={() => setInputValue(item)}>
                {item}
              </StyledListItems>
            ))}
            <StyledBtn>검색하기</StyledBtn>
          </StyledListContainer>
        </StyledList>
      )}
    </StyledDictContainer>
  );
}

const StyledDictContainer = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const StyledSearchContainer = styled.div`
  display: flex;
  gap: 2rem;
  align-items: center;
  justify-content: center;
  border-radius: 0 0 3.6rem 3.6rem;
  width: 100%;
  box-shadow: 0 1.6rem 4rem 0 rgba(13, 63, 103, 0.1);
  height: 12rem;
  padding: 2rem;
`;

const StyledSearchBar = styled.div`
  display: flex;
  align-items: center;
  padding: 0 1rem;
  height: 4.8rem;
  width: 100%;
  font-size: 1.5rem;
  background-color: ${COLOR.BG_GRAY_F5};
  border-radius: 1.6rem;
  &::placeholder {
    color: ${COLOR.PLACEHOLDER_GRAY_C1};
  }
`;

const StyledSearchInput = styled.input`
  height: 100%;
  width: 100%;
  padding: 1.4rem 1rem;
  background-color: ${COLOR.BG_GRAY_F5};
  ${FONT_STYLES.PR_R}
  font-size: 1.7rem;
`;
const StyledDictList = styled.div`
  margin-top: 3rem;
  padding: 0 2rem;
`;

const StyledList = styled.div`
  margin-top: 2rem;
  padding: 0 2rem;
`;
const StyledListTitle = styled.h1`
  ${FONT_STYLES.GM_M}
  font-size: 2.1rem;
  color: ${COLOR.FONT_GREEN_BD};
`;

const StyledListContainer = styled.div`
  padding: 1rem 0.2rem;
  color: ${COLOR.FONT_GREEN_BA};
`;

const StyledListItems = styled.h3`
  ${FONT_STYLES.GM_M}
  font-size: 1.8rem;
  letter-spacing: -0.06rem;
  padding: 1rem;
  border-radius: 0.4rem;
  cursor: pointer;
  &:hover {
    background: ${COLOR.BG_GREEN_E4};
  }
`;

const StyledBtn = styled.button`
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: ${COLOR.BG_GREEN_28};
  height: 6rem;
  color: white;
  font-size: 1.8rem;
  ${FONT_STYLES.GM_M}
`;
