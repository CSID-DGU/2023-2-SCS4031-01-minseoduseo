package msds.homefarming.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.exception.NoExistMemberException;
import msds.homefarming.service.MemberService;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor
{
    private final JwtTokenProvider jwtTokenProvider;
    private final UserPrincipal userPrincipal;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("//==LoginIntercepter()실행시작==//");

        String jwtToken = JwtTokenProvider.extractJwtToken(request, "Authorization");

        if (jwtToken == null || !jwtTokenProvider.validateJwtToken(jwtToken))
        {
            //==실제 배포용 코드==//
//            System.out.println("권한이 없음!");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
            //==실제 배포용 코드 끝==//

            //==테스트 계정 코드==//
            String testUsername = "msds_test_username";
            String testNickname = "민서두서";

            Member testMember = memberService.findByUsername(testUsername);
            if (testMember == null)
            {
                String testImageUri = "https://th.bing.com/th/id/OIP.QjAvas4wmO7llZ_dmFspGAAAAA?rs=1&pid=ImgDetMain";
                memberService.join(Member.create(testImageUri, testUsername, testNickname));
            }
            userPrincipal.setPrincipal(memberService.findByUsername(testUsername));
            System.out.println("//==LoginInterceptor()실행 끝==//");
            return true;
            //==테스트 계정 코드 끝==//
        }
        else
        {
            Member member = memberService.findByUsername(jwtTokenProvider.getUsername(jwtToken));

            if (member == null) throw new NoExistMemberException("존재하지 않는 회원입니다.");

            userPrincipal.setPrincipal(member);
            System.out.println("//==LoginInterceptor()실행 끝==//");
            return true;
        }
    }
//    public String extractJwtToken(HttpServletRequest request,String cookieName)
//    {
//        Cookie[] cookies = request.getCookies();
//        if(cookies == null) return null;
//        for(Cookie cookie : cookies)
//        {
//            if(cookieName.equals(cookie.getName()))
//            {
//                return cookie.getValue();
//            }
//        }
//        return null;
//    }
}
