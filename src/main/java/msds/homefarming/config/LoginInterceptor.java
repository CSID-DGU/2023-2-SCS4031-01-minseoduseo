package msds.homefarming.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.exception.UnAuthorizeException;
import msds.homefarming.service.MemberService;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor
{
    private final JwtTokenProvider jwtTokenProvider;
    private final UserPrincipal userPrincipal;

    //테스트계정을 위한,, memberService 의존성 추가.
    private final MemberService memberService;
    // 배포시에는 삭제할 것.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //==쿠키를 꺼낸다.==//
        String jwtToken = null;
        Cookie[] cookies = request.getCookies();

        if(cookies != null)
        {
            for(Cookie cookie : cookies)
            {
                if ("Authorization".equals(cookie.getName()))
                {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        //==쿠키 로직끝==//
        if (jwtToken == null || !jwtTokenProvider.validateJwtToken(jwtToken))
        {
            //==실제 배포용 코드==//
//            System.out.println("권한이 없음!");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

            //==찐 배포인듯?==//
//            if(!request.getRequestURI().toString().equals("/index")){
//                throw new UnAuthorizeException("권한이 없는 사용자입니다.");
//            }
//
//            return true;
            //==찐 배포인듯?==//

//            return false;
            //==실제 배포용 코드 끝==//

            //==테스트 계정 코드==//
            String testUsername = "msds_test_username";
            String testNickname = "민서두서";

            Member testMember = memberService.findByUsername(testUsername);
            if (testMember == null)
            {
//                Member newTestMember = new Member(testUsername, testNickname, null);
                memberService.join(Member.create(null, testUsername, testNickname));

            }
            testMember = memberService.findByUsername(testUsername);
            userPrincipal.setId(testMember.getId());
            userPrincipal.setImage(testMember.getImage());
            userPrincipal.setUsername(testMember.getUsername());
            userPrincipal.setNickname(testMember.getNickname());
            return true;
            //==테스트 계정 코드 끝==//
        }
        else
        {
            userPrincipal.setId(jwtTokenProvider.getId(jwtToken));
            userPrincipal.setNickname(jwtTokenProvider.getNickname(jwtToken));
            userPrincipal.setUsername(jwtTokenProvider.getUsername(jwtToken));
            return true;
        }
    }
}
