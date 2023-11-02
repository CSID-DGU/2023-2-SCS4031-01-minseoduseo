package msds.homefarming.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController
{
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/home")
    public String home(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        String nickname = jwtTokenProvider.getPrincipalNickname(token);
        return "<h1>Home</h1>\n" +
                "<h3>로그인을 완료한 사용자만 볼 수 있는 페이지.</h3>\n" +
                "<h3> 안녕하세요! " + nickname + "님!</h3>";
    }
}
