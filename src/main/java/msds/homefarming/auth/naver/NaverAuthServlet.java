package msds.homefarming.auth.naver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "naverAuthServlet", urlPatterns = "/naver/login")
public class NaverAuthServlet extends HttpServlet
{
    public static String NAVER_AUTH_CODE_URI = "https://nid.naver.com/oauth2.0/authorize";
    public static String NAVER_AUTH_CLIENT_ID = "PHCg_5rwDgHfJ4YlvmAW";
    public static String NAVER_AUTH_RESPONSE_TYPE = "code";
    //    public static String NAVER_AUTH_REDIRECT_URI = "http://localhost:8080/naver/callback";
    //==EC2서버 V1==//
    //    public static String NAVER_AUTH_REDIRECT_URI = "http://homefarm-env.eba-c9f3xbye.ap-northeast-2.elasticbeanstalk.com/naver/callback";

    //==EC2 서버V2==//
    public static String NAVER_AUTH_REDIRECT_URI = "Homefarming-v2-env.eba-fjp82mhn.ap-northeast-2.elasticbeanstalk.com/naver/callback";

    public static String NAVER_AUTH_STATE = "msdshomefarmingapplication";

    static String NAVER_CODE_REQUEST_QUERY_PARAMETER = "?"
            + "response_type=" + NAVER_AUTH_RESPONSE_TYPE + "&"
            + "client_id=" + NAVER_AUTH_CLIENT_ID + "&"
            + "redirect_uri=" + NAVER_AUTH_REDIRECT_URI + "&"
            + "state=" + NAVER_AUTH_STATE;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", NAVER_AUTH_CODE_URI + NAVER_CODE_REQUEST_QUERY_PARAMETER);
    }
}
