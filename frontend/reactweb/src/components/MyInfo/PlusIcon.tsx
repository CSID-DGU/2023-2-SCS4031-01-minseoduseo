import styled from "styled-components";
import COLOR from "styles/colors";
import { ReactComponent as Plus } from "assets/icons/icon_plus.svg";
export default function PlusIcon() {
  return (
    <StyledPlusIcon>
      <Plus />
    </StyledPlusIcon>
  );
}

const StyledPlusIcon = styled.button`
  position: fixed;
  right: 2.2rem;
  bottom: 5vh;
  width: 6rem;
  height: 6rem;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  background-color: ${COLOR.BG_GREEN_28};
  box-shadow: 0px 1.6rem 1.5rem 0px rgba(13, 63, 103, 0.1);
  > svg {
    color: white;
  }
`;
