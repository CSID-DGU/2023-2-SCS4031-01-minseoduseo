package msds.homefarming.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.domain.Member;
import msds.homefarming.repository.MemberRepository;
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
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken == null || !jwtTokenProvider.validateJwtToken(jwtToken))
        {
            // 실제 배포용 코드
//            System.out.println("권한이 없음!");
//            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//            return false;

            // 테스트 계정을 위한, 강제 회원 가입 코드
            String testUsername = "msds_test_username";
            String testNickname = "민서두서";

            Member testMember = memberService.findMemberByUsername(testUsername);
            if (testMember == null)
            {
                Member newTestMember = new Member(testUsername, testNickname, null);
                memberService.joinMember(newTestMember);

            }
            testMember = memberService.findMemberByUsername(testUsername);
            userPrincipal.setId(testMember.getId());
            userPrincipal.setUsername(testMember.getUsername());
            userPrincipal.setNickname(testMember.getNickname());
            return true;
            // 배포 시에는 이 부분은 반드시 제거할 것!
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
