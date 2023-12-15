import { FONT_STYLES } from "styles/fontStyle";
import { ReactComponent as MenuBar } from "assets/icons/icon_hamburger.svg";
import { ReactComponent as Logo } from "assets/icons/Icon_logo.svg";
import styled from "styled-components";
import { ReactComponent as Previous } from "assets/icons/icon_previous.svg";
import COLOR from "styles/colors";
import { useLocation, useNavigate } from "react-router-dom";
import Menu from "./Menu";
import { useState } from "react";
import Routes from "router/Routes";
interface HeaderParams {
  title: string;
  icon: "previous" | "menu";
  color?: string;
  link?: string;
}
export default function Header({ title, icon, color, link }: HeaderParams) {
  const navigate = useNavigate();
  const location = useLocation();
  const [isOpened, setIsOpened] = useState(false);
  return (
    <StyledHeader color={color}>
      {icon === "menu" ? (
        <MenuBar onClick={() => setIsOpened(true)} />
      ) : (
        <Previous
          onClick={() => {
            if (link) navigate(link);
            else navigate(Routes.Home);
          }}
        />
      )}
      <StyledTitleWrapper>
        <StyledTitle color={color}>{title}</StyledTitle>
        {location.pathname === "/" && <Logo />}
      </StyledTitleWrapper>
      {isOpened && (
        <div>
          <Menu />
          <StyledBg onClick={() => setIsOpened(false)} />
        </div>
      )}
    </StyledHeader>
  );
}

const StyledBg = styled.div`
  background-color: #00000087;
  height: 100vh;
  width: 100vw;
  padding: 2.5rem 1.6rem;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 990;
`;
const StyledTitle = styled.h1`
  ${FONT_STYLES.HWJ_R}
  font-size: 2.5rem;
  color: ${(props) => props.color || COLOR.FONT_BLACK_24};
`;

const StyledHeader = styled.header`
  display: flex;
  gap: 2rem;
  align-items: center;
  padding: 1.9rem 1.5rem 1rem 1.5rem;
  > svg {
    color: ${(props) => props.color || COLOR.FONT_BLACK_24};
    fill: ${(props) => props.color || COLOR.FONT_BLACK_24};
    cursor: pointer;
  }
`;

const StyledTitleWrapper = styled.div`
  display: flex;
  gap: 0.3rem;
  align-items: center;
`;
