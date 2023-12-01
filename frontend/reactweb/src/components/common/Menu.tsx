import styled from "styled-components";
import COLOR from "styles/colors";
import { ReactComponent as SmallProfile } from "assets/icons/icon_profile-small.svg";
import { FONT_STYLES } from "styles/fontStyle";
import { ReactComponent as EditIcon } from "assets/icons/icon_edit.svg";
import { ReactComponent as TreeIcon } from "assets/icons/icon_tree.svg";
import { ReactComponent as MagnifyIcon } from "assets/icons/icon_magnifier.svg";
import { ReactComponent as MsgIcon } from "assets/icons/icon_msg.svg";
import { ReactComponent as SettingIcon } from "assets/icons/icon_setting.svg";
import { ReactComponent as UserIcon } from "assets/icons/icon_user.svg";
import { ReactComponent as HomeIcon } from "assets/icons/icon_home.svg";
import Routes from "router/Routes";
import { Link } from "react-router-dom";
const menuLinks = [
  {
    txt: "홈",
    Icon: HomeIcon,
    link: "/",
  },
  {
    txt: "식물 일지",
    Icon: EditIcon,
    link: Routes.Diary,
  },
  {
    txt: "식물 도감",
    Icon: TreeIcon,
    link: Routes.Dictionary,
  },
  {
    txt: "내 식물 관리",
    Icon: UserIcon,
    link: Routes.MyInfo,
  },
  {
    txt: "식물 진단",
    Icon: MagnifyIcon,
    link: Routes.DiagAI,
  },
  {
    txt: "챗봇",
    Icon: MsgIcon,
    link: "/chatbot",
  },
  {
    txt: "설정",
    Icon: SettingIcon,
    link: "/",
  },
];

export default function Menu() {
  return (
    <div>
      <StyledNav>
        <StyledProfileContainer>
          <StyledProfile>
            <SmallProfile />
          </StyledProfile>
          <StyledProfileTxt>
            <StyledName>홍길동 님</StyledName>
            <StyledEmail>abc@abc.com</StyledEmail>
          </StyledProfileTxt>
        </StyledProfileContainer>
        <StyledMenuContainer>
          {menuLinks.map(({ txt, Icon, link }) => {
            return (
              <Link to={link} key={link}>
                <StyledMenuItem>
                  <Icon />
                  <StyledMenuTxt>{txt}</StyledMenuTxt>
                </StyledMenuItem>
              </Link>
            );
          })}
        </StyledMenuContainer>
      </StyledNav>
    </div>
  );
}

const StyledNav = styled.nav`
  animation: moveForward 1s forwards ease;
  background-color: ${COLOR.BG_GREEN_28};
  height: 100vh;
  width: 24rem;
  padding: 2.5rem 1.6rem;
  border-top-right-radius: 2.3rem;
  border-end-end-radius: 2.3rem;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 999;
`;

const StyledProfileContainer = styled.div`
  display: flex;
  gap: 1.4rem;
  align-items: center;
`;
const StyledProfile = styled.div``;
const StyledProfileTxt = styled.div`
  flex-shrink: 0;
`;
const StyledName = styled.h3`
  font-size: 1.8rem;
  line-height: 2.4rem;
  color: ${COLOR.BG_GRAY_F};
  ${FONT_STYLES.GM_M}
`;
const StyledEmail = styled.h4`
  color: ${COLOR.BG_GRAY_F};
  ${FONT_STYLES.PR_R};
  line-height: 2rem;
`;

const StyledMenuContainer = styled.main`
  display: flex;
  flex-direction: column;
  gap: 3rem;
  margin-top: 4.8rem;
  margin-left: 1rem;
`;

const StyledMenuItem = styled.div`
  display: flex;
  gap: 1.6rem;
  align-items: center;
`;
const StyledMenuTxt = styled.h4`
  color: white;
  font-size: 1.6rem;
  ${FONT_STYLES.PR_R};
  letter-spacing: -0.08rem;
`;
