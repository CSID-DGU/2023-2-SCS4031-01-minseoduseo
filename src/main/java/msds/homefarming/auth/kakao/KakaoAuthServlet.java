package msds.homefarming.auth.kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
@WebServlet(name = "kakaoRedirectServlet", urlPatterns = "/kakao/login")
public class KakaoAuthServlet extends HttpServlet
{
    public final static String KAKAO_AUTH_CODE_URI = "https://kauth.kakao.com/oauth/authorize";
    
    public static String KAKAO_AUTH_CLIENT_ID = "f698ae044a771060c21588e56dcb50f0";
    public static String KAKAO_AUTH_REDIRECT_URI = "http://localhost:8080/kakao/callback";
//    public static String KAKAO_AUTH_REDIRECT_URI = "http://homefarm-env.eba-c9f3xbye.ap-northeast-2.elasticbeanstalk.com/kakao/callback";
    public static String KAKAO_AUTH_RESPONSE_TYPE = "code";

    static String KAKAO_CODE_REQUEST_QUERY_PARAMETER = "?"
            + "client_id=" + KAKAO_AUTH_CLIENT_ID + "&"
            + "redirect_uri=" + KAKAO_AUTH_REDIRECT_URI + "&"
            + "response_type=" + KAKAO_AUTH_RESPONSE_TYPE;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("1~~");
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", KAKAO_AUTH_CODE_URI + KAKAO_CODE_REQUEST_QUERY_PARAMETER);
    }
}
