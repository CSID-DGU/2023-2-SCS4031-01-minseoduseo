import "styles/fonts.scss";
import { css } from "styled-components";
const FONT_FAMILY = (name: string) => css`
  font-family: ${name};
`;
export const FONT_STYLES = {
  PR_R: FONT_FAMILY("Pretendard-Regular"),
  PR_M: FONT_FAMILY("Pretendard-Medium"),
  HWJ_R: FONT_FAMILY("HakgyoansimWoojuR"),
};
