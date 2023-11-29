import styled from "styled-components";
import COLOR from "styles/colors";
interface btnParams {
  theme?: "cancel" | "delete";
  label: string;
  type?: "button" | "submit";
  handler?: () => void;
}
export default function CommonBtn({ theme, label, handler, type }: btnParams) {
  let TxtColor = "";
  let bgcolor = "";
  switch (theme) {
    case "cancel":
      TxtColor = COLOR.BTN_GRAY_97;
      break;
    case "delete":
      TxtColor = COLOR.BTN_RED_F8;
      break;
    default:
      TxtColor = COLOR.BG_GRAY_F;
  }

  switch (theme) {
    case "cancel":
    case "delete":
      bgcolor = COLOR.BG_GRAY_F;
      break;
    default:
      bgcolor = COLOR.BG_GREEN_28;
  }

  return (
    <Button
      color={TxtColor}
      bgcolor={bgcolor}
      onClick={handler}
      type={type ?? "button"}
    >
      {label}
    </Button>
  );
}
interface Colors {
  bgcolor: string;
  color: string;
}

const Button = styled.button<Colors>`
  display: flex;
  width: 100%;
  height: 5.6rem;
  justify-content: center;
  align-items: center;
  font-size: 1.6rem;
  background-color: ${(props) => props.bgcolor};
  color: ${(props) => props.color};
  border: 0.1rem solid ${(props) => props.color};
  border-radius: 1.6rem;
`;
