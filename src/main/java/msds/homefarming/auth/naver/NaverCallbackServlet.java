package msds.homefarming.auth.naver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.naver.dto.NaverAccessTokenResponseDto;
import msds.homefarming.auth.naver.dto.NaverMemberDto;
import msds.homefarming.domain.Member;
import msds.homefarming.service.MemberService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static msds.homefarming.auth.naver.NaverAuthServlet.NAVER_AUTH_CLIENT_ID;
import static msds.homefarming.auth.naver.NaverAuthServlet.NAVER_AUTH_STATE;

@RequiredArgsConstructor
@WebServlet(name = "naverCallbackServlet", urlPatterns = "/naver/callback")
public class NaverCallbackServlet extends HttpServlet
{
    static String NAVER_AUTH_TOKEN_URI = "https://nid.naver.com/oauth2.0/token";
    static String NAVER_AUTH_USERINFO_URI = "https://openapi.naver.com/v1/nid/me";
    static String NAVER_AUTH_GRANT_TYPE = "authorization_code";
    static String NAVER_AUTH_CLIENT_SECRET = "IxgS1qy4U1";
    static String NAVER_AUTH_CODE;
    static String NAVER_TOKEN_REQUEST_QUERY_PARAMETER;

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        NAVER_AUTH_CODE = request.getParameter("code");
        NAVER_TOKEN_REQUEST_QUERY_PARAMETER = "?"
                + "grant_type=" + NAVER_AUTH_GRANT_TYPE + "&"
                + "client_id=" + NAVER_AUTH_CLIENT_ID + "&"
                + "client_secret=" + NAVER_AUTH_CLIENT_SECRET + "&"
                + "code=" + NAVER_AUTH_CODE + "&"
                + "state=" + NAVER_AUTH_STATE;

        RestTemplate restTemplate = new RestTemplate();

        //사용자 토큰 요청 및 응답받기.
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> tokenRequestEntity
                = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        NaverAccessTokenResponseDto tokenResponse = restTemplate.postForObject(NAVER_AUTH_TOKEN_URI + NAVER_TOKEN_REQUEST_QUERY_PARAMETER
                , tokenRequestEntity, NaverAccessTokenResponseDto.class);

        // 사용자 정보 요청 및 응답받기.
        HttpHeaders userinfoRequestHeaders = new HttpHeaders();
        userinfoRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        userinfoRequestHeaders.set("Authorization", "Bearer " + tokenResponse.getAccessToken());

        MultiValueMap<String, String> userinfoRequestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> userinfoRequestEntity
                = new HttpEntity<>(userinfoRequestBody, userinfoRequestHeaders);

        NaverMemberDto naverMember = restTemplate.postForObject(NAVER_AUTH_USERINFO_URI, userinfoRequestEntity, NaverMemberDto.class);

        // 처음 로그인한 회원은 강제 회원가입.
        // 네이버는 username을 만들 때, id가 아닌, accessToken을 이용해야함!!
        // 그래야 삭제할 때, 이 accessToken을 보고 삭제를 할 수있음.
        String username = "naver_" + naverMember.getId();
        String nickname = naverMember.getNickname();
        String profileImage = naverMember.getProfileImage();

        //==리프레시 토큰 저장==//
        String refreshToken = tokenResponse.getRefreshToken();

        Member memberEntity = memberService.findByUsername(username);

        //==네이버 최초 회원가입==//
        if (memberEntity == null)
        {
            Member joinMember = Member.create(profileImage, username, nickname);
            joinMember.setRefreshToken(refreshToken);
            memberService.join(joinMember);
        }


        // JWT토큰 생성 후 클라이언트에게 전송
        Member member = memberService.findByUsername(username);
        String jwtToken = jwtTokenProvider.createJwtToken(member);

        String memberJsonData = "{"
                + "\"jwtToken\": " + "\"" + jwtToken + "\",\n"
                + "\"id\": " + "\"" + member.getId() + "\",\n"
                + "\"username\": " + "\"" + member.getUsername() + "\",\n"
                + "\"nickname\": " + "\"" + member.getNickname() + "\"\n"
                + "}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //==쿠키에 토큰을 넣고 홈으로 리다이렉트==//
//        response.getWriter().write(memberJsonData);
        Cookie cookie = new Cookie("Authorization", jwtToken);
        cookie.setMaxAge(36000); //10시간
        cookie.setPath("/");
        response.addCookie(cookie);
        //==1. 아래는 Spring의 /home으로 리다이렉트 시킴==//
//        response.setHeader("Location","/");
        
        //==2. 아래는 React의 /으로 리다이렉트 시킴==//
        //==실제 서비스 시 React서버  홈 URI로 변경해야 함.==//
        response.setHeader("Location","http://localhost:3000");
//        response.setHeader("Location","https://social-login-front.d2q2g823gv40cu.amplifyapp.com/");

        response.setStatus(HttpServletResponse.SC_FOUND);
        //====//
    }
}
