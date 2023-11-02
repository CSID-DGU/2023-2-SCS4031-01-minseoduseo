package msds.homefarming.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController
{
    private final UserPrincipal userPrincipal;

    @GetMapping("/home")
    public String home()
    {
        Long id = userPrincipal.getId();
        String username = userPrincipal.getUsername();
        String nickname = userPrincipal.getNickname();

        return "<h1>Home</h1>\n" +
                "<h3>로그인을 완료한 사용자만 볼 수 있는 페이지.</h3>\n" +
                "<h3> 안녕하세요! " + nickname + "님!</h3>\n" +
                "<h3>" + nickname + "님의 아이디는 " + id + "입니다. </h3>\n" +
                "<h3>" + nickname + "님의 유저네임은 " + username + "입니다. \n</h3>";
    }
}
