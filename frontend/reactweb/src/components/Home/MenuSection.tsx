import { ReactComponent as BookIcon } from "assets/icons/icon_book.svg";
import { ReactComponent as ChatIcon } from "assets/icons/icon_chat.svg";
import { ReactComponent as PlantIcon } from "assets/icons/icon_plant.svg";
import { ReactComponent as WateringIcon } from "assets/icons/icon_watering.svg";
import Routes from "router/Routes";
import { Link } from "react-router-dom";
import COLOR from "styles/colors";
import styled from "styled-components";
export default function MenuSection() {
  return (
    <StyledBottomSection>
      <Link to={Routes.Dictionary}>
        <StyledMainIcon>
          <StyledIconContainer>
            <PlantIcon />
            <StyledLabel>식물도감</StyledLabel>
          </StyledIconContainer>
        </StyledMainIcon>
      </Link>
      <Link to={Routes.Diary}>
        <StyledMainIcon>
          <StyledIconContainer>
            <BookIcon />
            <StyledLabel>식물일지</StyledLabel>
          </StyledIconContainer>
        </StyledMainIcon>
      </Link>
      <Link to={Routes.Chatbot}>
        <StyledMainIcon>
          <StyledIconContainer>
            <ChatIcon />
            <StyledLabel>챗봇</StyledLabel>
          </StyledIconContainer>
        </StyledMainIcon>
      </Link>
      <Link to={Routes.MyInfo}>
        <StyledMainIcon>
          <StyledIconContainer>
            <WateringIcon />
            <StyledLabel>내 식물 관리</StyledLabel>
          </StyledIconContainer>
        </StyledMainIcon>
      </Link>
    </StyledBottomSection>
  );
}
const StyledBottomSection = styled.section`
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
`;
const StyledMainIcon = styled.div`
  width: 15.7rem;
  height: 13rem;
  border-radius: 1.8rem;
  background: #fff;
  cursor: pointer;
  /* box1 */
  box-shadow: 0px 6px 5px 0px rgba(13, 63, 103, 0.1);
`;

const StyledIconContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: auto;
  width: 100%;
  height: 100%;
`;

const StyledLabel = styled.h3`
  color: ${COLOR.FONT_BLACK_1F};
  text-align: center;
  font-size: 16px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
`;
