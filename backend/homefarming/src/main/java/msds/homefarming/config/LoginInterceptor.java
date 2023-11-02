package msds.homefarming.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor
{
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken == null || !jwtTokenProvider.validateJwtToken(jwtToken))
        {
//            System.out.println("권한이 없음!");
//            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//            return false;/
            return true;
        }
        else
        {
            return true;
        }
    }
}
