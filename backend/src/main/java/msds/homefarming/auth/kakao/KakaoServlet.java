package msds.homefarming.auth.kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@Slf4j
@WebServlet(name = "kakaoServlet", urlPatterns = "/kakao/login")
public class KakaoServlet extends HttpServlet
{
    @Value("${kakao.auth.code-uri}")
    public String KAKAO_AUTH_CODE_URI;

    @Value("${kakao.auth.client-id}")
    public String KAKAO_AUTH_CLIENT_ID;

    @Value("${kakao.auth.redirect-uri}")
    public String KAKAO_AUTH_REDIRECT_URI;

    @Value("${kakao.auth.response-type}")
    public String KAKAO_AUTH_RESPONSE_TYPE;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("//==KakaoServlet()실행==//");
        response.setStatus(HttpServletResponse.SC_FOUND);

        String KAKAO_CODE_REQUEST_QUERY_PARAMETER = "?"
                + "client_id=" + KAKAO_AUTH_CLIENT_ID + "&"
                + "redirect_uri=" + KAKAO_AUTH_REDIRECT_URI + "&"
                + "response_type=" + KAKAO_AUTH_RESPONSE_TYPE;

        response.setHeader("Location", KAKAO_AUTH_CODE_URI + KAKAO_CODE_REQUEST_QUERY_PARAMETER);
    }
}
