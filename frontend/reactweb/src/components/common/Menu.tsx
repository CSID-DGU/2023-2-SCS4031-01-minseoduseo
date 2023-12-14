import styled from "styled-components";
import COLOR from "styles/colors";
import { ReactComponent as SmallProfile } from "assets/icons/icon_profile-small.svg";
import { FONT_STYLES } from "styles/fontStyle";
import { ReactComponent as EditIcon } from "assets/icons/icon_edit.svg";
import { ReactComponent as TreeIcon } from "assets/icons/icon_tree.svg";
import { ReactComponent as MagnifyIcon } from "assets/icons/icon_magnifier.svg";
import { ReactComponent as MsgIcon } from "assets/icons/icon_msg.svg";
import { ReactComponent as UserIcon } from "assets/icons/icon_user.svg";
import { ReactComponent as HomeIcon } from "assets/icons/icon_home.svg";
import Routes from "router/Routes";
import { Link, useNavigate } from "react-router-dom";
import modalTxtAsset from "assets/json/modalTxt.json";
import CommonModal from "./CommonModal";
import { getLogout } from "api/social";
import { useEffect, useRef, useState } from "react";
import { getInfo } from "api/auth";
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
];
interface userInfoType {
  id: number;
  image: string;
  username: string;
  nickname: string;
}
export default function Menu() {
  useEffect(() => {
    const setInfo = async () => {
      const data: userInfoType = await getInfo();
      setUserInfo(data);
    };
    setInfo();
  }, []);
  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useState<userInfoType | undefined>();
  const modalTxt = useRef<{
    contents: string;
    btnTxt: string[];
    cancelHandler?: () => void;
    confirmHandler: () => void;
  }>({
    ...modalTxtAsset.logout,
    confirmHandler: async () => {
      navigate("/kakao/logout");
    },
    cancelHandler: () => {
      setModalActive(false);
    },
  });
  const [modalActive, setModalActive] = useState<boolean>(false);
  return (
    <div>
      <StyledNav>
        <StyledProfileContainer>
          <StyledProfile>
            {userInfo ? (
              <StyledProfileImg bgSrc={userInfo.image} />
            ) : (
              <SmallProfile />
            )}
          </StyledProfile>
          <StyledProfileTxt>
            <StyledName>{userInfo?.nickname} 님</StyledName>
            <StyledEmail>@{userInfo?.username} </StyledEmail>
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
        <StyledLogoutBtn
          onClick={async () => {
            setModalActive(true);
          }}
        >
          로그아웃
        </StyledLogoutBtn>
      </StyledNav>
      {modalActive && <CommonModal {...modalTxt.current} />}
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

interface StyledProfileProps {
  bgSrc: string;
}
const StyledProfileImg = styled.div<StyledProfileProps>`
  width: 8rem;
  height: 8rem;
  border-radius: 50%;
  background-size: cover;
  background-image: ${({ bgSrc }) => `url(${bgSrc})`};
`;
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

const StyledLogoutBtn = styled.button`
  width: 19rem;
  height: 3.5rem;
  bottom: 3rem;
  font-size: 1.5rem;
  ${FONT_STYLES.PR_M};
  letter-spacing: -0.03rem;
  color: white;
  position: absolute;
  margin: 0 0.7rem;
  border: 0.1rem solid white;
  background: unset;
  border-radius: 0.4rem;
  &:hover {
    background: transparent;
  }
`;
