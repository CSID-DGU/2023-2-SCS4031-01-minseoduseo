import "styles/fonts.scss";
import { css } from "styled-components";
const FONT_FAMILY = (name: string, weight: number) => css`
  font-family: ${name};
  font-weight: ${weight};
`;
export const FONT_STYLES = {
  PR_R: FONT_FAMILY("Pretendard-Regular", 400),
  PR_M: FONT_FAMILY("Pretendard-Medium", 500),
  PR_B: FONT_FAMILY("Pretendard-Bold", 700),
  HWJ_R: FONT_FAMILY("HakgyoansimWoojuR", 400),
  GM_M: FONT_FAMILY("GmarketSansMedium", 500),
};
