package msds.homefarming.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import msds.homefarming.domain.Member;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider
{
    private final static String SECRET_KEY = "msdshomefarmingapplication";
    private final static int MIN = 60000;
    public String createJwtToken(Member member)
    {
        return JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + MIN * 60 * 10))
                .withClaim("id", member.getId())
                .withClaim("username", member.getUsername())
                .withClaim("nickname", member.getNickname())
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public boolean validateJwtToken(String jwtToken)
    {
        try
        {
            JWT.require(Algorithm.HMAC512(SECRET_KEY)).build()
                    .verify(jwtToken);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    //==request로부터 cookieName의 쿠키를 추출합니다.==//
    public static String extractJwtToken(HttpServletRequest request, String cookieName)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies)
        {
            if(cookieName.equals(cookie.getName()))
            {
                return cookie.getValue();
            }
        }
        return null;
    }

    public Long getId(String jwtToken)
    {
        Long id;

        try
        {
            id = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build()
                    .verify(jwtToken)
                    .getClaim("id")
                    .asLong();
        }
        catch (Exception e)
        {
            return null;
        }
        return id;
    }


    public String getUsername(String jwtToken)
    {
        String username;

        try
        {
            username = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build()
                    .verify(jwtToken)
                    .getClaim("username")
                    .asString();
        }
        catch (Exception e)
        {
            return null;
        }
        return username;
    }

    public String getNickname(String jwtToken)
    {
        String nickname;

        try
        {
            nickname = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build()
                    .verify(jwtToken)
                    .getClaim("nickname")
                    .asString();
        }
        catch (Exception e)
        {
            return null;
        }
        return nickname;
    }
}
