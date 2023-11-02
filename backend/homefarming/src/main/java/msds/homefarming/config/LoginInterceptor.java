package msds.homefarming.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor
{
    private final JwtTokenProvider jwtTokenProvider;
    private final UserPrincipal userPrincipal;

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

            // 테스트 개발용 계정 하나 생성 하기.
            userPrincipal.setId(10000L);
            userPrincipal.setUsername("msds_test_username");
            userPrincipal.setNickname("민서두서");
            return true;
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
