import { useEffect, useState } from "react";
import Tag from "./Tag";
import { getPlants } from "api/myPlant";
import styled from "styled-components";

export default function TagDropDown({
  handler,
  selectedPlant,
}: {
  handler: (plantname: string, plantcolor: string) => void;
  selectedPlant?: { color: string; name: string };
}) {
  useEffect(() => {
    setPlants();
  }, []);
  interface plantType {
    id: number;
    image: string;
    color: string;
    name: string;
    nickname: string;
    creatDate: string;
    owner: string;
  }
  const setPlants = async () => {
    const plants = await getPlants();
    setPlantList(plants.plantList);
  };
  const [plantList, setPlantList] = useState<plantType[] | null>();
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const handleTagSelect = (plant: plantType) => {
    setIsOpen(false);
    handler(plant.name, plant.color);
  };
  return (
    <StyledTagDropDown>
      <StyledSelectedTag onClick={() => setIsOpen(true)}>
        {plantList && (
          <Tag
            color={selectedPlant?.color ?? plantList[0].color}
            type={selectedPlant?.name ?? plantList[0].name}
          ></Tag>
        )}
      </StyledSelectedTag>

      {isOpen && (
        <StyledTagsContainer>
          {plantList &&
            plantList.map((plant) => (
              <div onClick={() => handleTagSelect(plant)} key={plant.id}>
                <Tag color={plant.color} type={plant.name} />
              </div>
            ))}
        </StyledTagsContainer>
      )}
    </StyledTagDropDown>
  );
}

const StyledTagDropDown = styled.div`
  position: relative;
`;

const StyledSelectedTag = styled.div`
  padding: 1rem;
  border-radius: 1.6rem;
  border: 0.2rem solid #eaeaea;
`;

const StyledTagsContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 1rem;
  padding-top: 2rem;
  gap: 1rem;
  position: absolute;
  background-color: white;
  border-radius: 1.6rem;
  border: 0.2rem solid #eaeaea;
  border-top: 0rem;
  border-top-right-radius: 0;
  border-top-left-radius: 0;

  transform: translateY(-1.2rem);
`;
