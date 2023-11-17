package msds.homefarming.auth.kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.kakao.dto.KakaoAccessTokenResponseDto;
import msds.homefarming.auth.kakao.dto.KakaoMemberDto;
import msds.homefarming.domain.Member;
import msds.homefarming.service.MemberService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static msds.homefarming.auth.kakao.KakaoAuthServlet.KAKAO_AUTH_CLIENT_ID;
import static msds.homefarming.auth.kakao.KakaoAuthServlet.KAKAO_AUTH_REDIRECT_URI;

@Slf4j
@RequiredArgsConstructor
@WebServlet(name = "kakaoCallbackServlet", urlPatterns = "/kakao/callback")
public class KakaoCallbackServlet extends HttpServlet
{
    static String KAKAO_AUTH_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    static String KAKAO_AUTH_USERINFO_URI = "https://kapi.kakao.com/v2/user/me";
    static String KAKAO_AUTH_GRANT_TYPE = "authorization_code";
    static String KAKAO_AUTH_CODE;
    
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        KAKAO_AUTH_CODE = request.getParameter("code");
        RestTemplate restTemplate = new RestTemplate();

        //사용자 토큰 요청 및 응답받기.
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", KAKAO_AUTH_GRANT_TYPE);
        tokenRequestBody.add("client_id", KAKAO_AUTH_CLIENT_ID);
        tokenRequestBody.add("redirect_uri", KAKAO_AUTH_REDIRECT_URI);
        tokenRequestBody.add("code", KAKAO_AUTH_CODE);

        HttpEntity<MultiValueMap<String, String>> tokenRequestEntity
                = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        KakaoAccessTokenResponseDto tokenResponse = restTemplate.postForObject(KAKAO_AUTH_TOKEN_URI, tokenRequestEntity, KakaoAccessTokenResponseDto.class);

        //사용자 정보 요청 및 응답받기.
        HttpHeaders userinfoRequestHeaders = new HttpHeaders();
        userinfoRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        userinfoRequestHeaders.set("Authorization", "Bearer " + tokenResponse.getAccessToken());

        MultiValueMap<String, String> userinfoRequestBody = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> userinfoRequestEntity
                = new HttpEntity<>(userinfoRequestBody, userinfoRequestHeaders);

        KakaoMemberDto kakaoMember = restTemplate.postForObject(KAKAO_AUTH_USERINFO_URI, userinfoRequestEntity, KakaoMemberDto.class);

        // 처음 로그인한 회원은 강제 회원가입
        String username = "kakao_" + kakaoMember.getId();
        String nickname = kakaoMember.getNickname();
        String profileImage = kakaoMember.getProfileImage();

        Member memberEntity = memberService.findByUsername(username);
        if (memberEntity == null)
        {
            System.out.println("카카오 최초 회원 가입!");
//            Member member = new Member(username, nickname, profileImage);
            memberService.join(Member.create(profileImage,username,nickname));
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
        response.getWriter().write(memberJsonData);
    }
}
