package msds.homefarming.auth.kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*")
@WebServlet(name = "kakaoRedirectServlet", urlPatterns = "/kakao/login")
public class KakaoAuthServlet extends HttpServlet
{

    @Value("${kakao.auth.code-uri}")
    public String KAKAO_AUTH_CODE_URI;
//    public final static String KAKAO_AUTH_CODE_URI = "https://kauth.kakao.com/oauth/authorize";

    @Value("${kakao.auth.client-id}")
    public String KAKAO_AUTH_CLIENT_ID;
//    public static String KAKAO_AUTH_CLIENT_ID = "f698ae044a771060c21588e56dcb50f0";

    @Value("${kakao.auth.redirect-uri}")
    public String KAKAO_AUTH_REDIRECT_URI;

    //==EC2서버 V1==//
    //    public static String KAKAO_AUTH_REDIRECT_URI = "http://homefarm-env.eba-c9f3xbye.ap-northeast-2.elasticbeanstalk.com/kakao/callback";

    //==EC2서버 V2==//
//    public static String KAKAO_AUTH_REDIRECT_URI = "http://msds-v2-nlb-fae0e679a067aaf3.elb.ap-northeast-2.amazonaws.com/kakao/callback";

    public static String KAKAO_AUTH_RESPONSE_TYPE = "code";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setStatus(HttpServletResponse.SC_FOUND);

        String KAKAO_CODE_REQUEST_QUERY_PARAMETER = "?"
                + "client_id=" + KAKAO_AUTH_CLIENT_ID + "&"
                + "redirect_uri=" + KAKAO_AUTH_REDIRECT_URI + "&"
                + "response_type=" + KAKAO_AUTH_RESPONSE_TYPE;

        response.setHeader("Location", KAKAO_AUTH_CODE_URI + KAKAO_CODE_REQUEST_QUERY_PARAMETER);
    }
}
