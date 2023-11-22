package msds.homefarming.auth.kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@WebServlet(name = "kakaoCallbackServlet", urlPatterns = "/kakao/callback")
public class KakaoCallbackServlet extends HttpServlet
{
    @Value("${kakao.auth.token-uri}")
    String KAKAO_AUTH_TOKEN_URI;

    @Value("${kakao.auth.userinfo-uri}")
    String KAKAO_AUTH_USERINFO_URI;

    @Value("${kakao.auth.grant-type}")
    String KAKAO_AUTH_GRANT_TYPE;

    String KAKAO_AUTH_CODE;

    @Value("${kakao.auth.redirect-uri}")
    String KAKAO_AUTH_REDIRECT_URI;

    @Value("${msds.front.home-uri}")
    String HOME_REDIRECT_URI;

    @Value("${kakao.auth.client-id}")
    String KAKAO_AUTH_CLIENT_ID;

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        KAKAO_AUTH_CODE = request.getParameter("code");
        RestTemplate restTemplate = new RestTemplate();

        //== 1.사용자 토큰 요청 및 응답받기==//
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

        //== 2.사용자 정보 요청 및 응답받기==//
        HttpHeaders userinfoRequestHeaders = new HttpHeaders();
        userinfoRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        userinfoRequestHeaders.set("Authorization", "Bearer " + tokenResponse.getAccessToken());

        MultiValueMap<String, String> userinfoRequestBody = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> userinfoRequestEntity
                = new HttpEntity<>(userinfoRequestBody, userinfoRequestHeaders);

        KakaoMemberDto kakaoMember = restTemplate.postForObject(KAKAO_AUTH_USERINFO_URI, userinfoRequestEntity, KakaoMemberDto.class);

        //== 3.처음 로그인한 회원은 강제 회원가입==//
        String username = "kakao_" + kakaoMember.getId();
        String nickname = kakaoMember.getNickname();
        String profileImage = kakaoMember.getProfileImage();

        Member memberEntity = memberService.findByUsername(username);
        if (memberEntity == null)
        {
            System.out.println("카카오 최초 회원 가입!");
            memberService.join(Member.create(profileImage,username,nickname));
        }

        //== 4.JWT토큰 생성 후 클라이언트에게 전송==//
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
        //== 5.쿠키를 생성하고 담음==//
        Cookie cookie = new Cookie("Authorization", jwtToken);
        cookie.setMaxAge(36000); //10시간
        cookie.setPath("/");
        response.addCookie(cookie);

        //== 6.프론트의 홈 화면/으로 리다이렉트==//
        response.setHeader("Location",HOME_REDIRECT_URI);
        response.setStatus(HttpServletResponse.SC_FOUND);
        //====//

    }
}
