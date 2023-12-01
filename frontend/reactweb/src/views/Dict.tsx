import COLOR from "styles/colors";
import styled from "styled-components";
import { ReactComponent as Previous } from "assets/icons/icon_previous.svg";
import { ReactComponent as SearchIcon } from "assets/icons/icon_search.svg";
import { ReactComponent as ClearIcon } from "assets/icons/icon_clear.svg";
import DictionaryItem from "components/dictionary/DictionaryItem";
import { useNavigate } from "react-router-dom";

export default function Dict() {
  const navigate = useNavigate();
  return (
    <StyledDictContainer>
      <StyledSearchContainer>
        <Previous onClick={() => navigate(-1)} />
        <StyledSearchBar>
          <SearchIcon />
          <StyledSearchInput />
          <ClearIcon />
        </StyledSearchBar>
      </StyledSearchContainer>
      <StyledDictList>
        <DictionaryItem />
      </StyledDictList>
    </StyledDictContainer>
  );
}

const StyledDictContainer = styled.main`
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
  font-size: 1.5rem;
`;
const StyledDictList = styled.div`
  margin-top: 3rem;
  padding: 0 2rem;
`;
