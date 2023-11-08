import styled from "styled-components";
import COLOR from "styles/colors";
interface btnParams {
  type?: "cancel" | "delete";
  label: string;
  handler?: () => void;
}
export default function CommonBtn({ type, label, handler }: btnParams) {
  let TxtColor = "";
  let BgColor = "";
  switch (type) {
    case "cancel":
      TxtColor = COLOR.BTN_GRAY_97;
      break;
    case "delete":
      TxtColor = COLOR.BTN_RED_F8;
      break;
    default:
      TxtColor = COLOR.BG_GRAY_F;
  }

  switch (type) {
    case "cancel":
    case "delete":
      BgColor = COLOR.BG_GRAY_F;
      break;
    default:
      BgColor = COLOR.BG_GREEN_28;
  }

  return (
    <Button color={TxtColor} BgColor={BgColor} onClick={handler}>
      {label}
    </Button>
  );
}
interface Colors {
  BgColor: string;
  color: string;
}

const Button = styled.button<Colors>`
  display: flex;
  width: 100%;
  height: 5.6rem;
  justify-content: center;
  align-items: center;
  font-size: 1.6rem;
  background-color: ${(props) => props.BgColor};
  color: ${(props) => props.color};
  border: 0.1rem solid ${(props) => props.color};
  border-radius: 1.6rem;
`;
