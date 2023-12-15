import "styles/fonts.scss";
import COLOR from "styles/colors";
import styled from "styled-components";
import TopSection from "components/Home/TopSection";
import MenuSection from "components/Home/MenuSection";
import Header from "components/common/Header";

export default function Home() {
  return (
    <StyledMainContainer>
      <Header icon="menu" title="싱그리" />

      <StyledMain>
        <TopSection />
        <MenuSection />
      </StyledMain>
    </StyledMainContainer>
  );
}
const StyledMainContainer = styled.div`
  height: 100vh;
  width: 100vw;
  background: ${COLOR.BG_GRAY_F5};
`;

const StyledMain = styled.main`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 2rem;
`;
