package msds.homefarming.auth.kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.kakao.dto.KakaoAccessTokenResponseDto;
import msds.homefarming.auth.kakao.dto.KakaoMemberDto;
import msds.homefarming.domain.Member;
import msds.homefarming.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.*;

@Slf4j
@RequiredArgsConstructor
@WebServlet(name = "kakaoCallbackServlet", urlPatterns = "/kakao/callback")
public class KakaoCallbackServlet extends HttpServlet
{
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.auth.token-uri}")
    String KAKAO_AUTH_TOKEN_URI;

    @Value("${kakao.auth.userinfo-uri}")
    String KAKAO_AUTH_USERINFO_URI;

    @Value("${kakao.auth.grant-type}")
    String KAKAO_AUTH_GRANT_TYPE;

    @Value("${kakao.auth.redirect-uri}")
    String KAKAO_AUTH_REDIRECT_URI;

    @Value("${msds.front.home-uri}")
    String HOME_REDIRECT_URI;

    @Value("${kakao.auth.client-id}")
    String KAKAO_AUTH_CLIENT_ID;


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //== 1.카카오에 AccessToken 요청하기==//
        Map<String, String> atHeaders = Map.of(
                "Content-Type", APPLICATION_FORM_URLENCODED_VALUE);

        Map<String, String> atBody = Map.of(
                "grant_type", KAKAO_AUTH_GRANT_TYPE,
                "client_id", KAKAO_AUTH_CLIENT_ID,
                "redirect_uri", KAKAO_AUTH_REDIRECT_URI,
                "code", request.getParameter("code"));

        String accessToken = KakaoAccessTokenRequest.builder()
                .uri(KAKAO_AUTH_TOKEN_URI)
                .headers(atHeaders)
                .body(atBody)
                .build()
                .post()
                .getAccessToken();
//        System.out.println("1.카카오에 AccessToken요청 후 받은 토큰 == " + accessToken);
        log.info("[카카오] 1.카카오에 AccessToken 후 받은 토큰 : {}", accessToken);

        //== 2.카카오에 사용자 정보 요청==//
        Map<String, String> miHeaders = Map.of(
                "Content-Type", APPLICATION_FORM_URLENCODED_VALUE,
                "Authorization", "Bearer " + accessToken);

        KakaoMemberDto kakaoMember = KakaoMemberInfoRequest.builder()
                .uri(KAKAO_AUTH_USERINFO_URI)
                .headers(miHeaders)
                .body(new HashMap<>())
                .build()
                .post();

        Member memberEntity = Member.create(
                kakaoMember.getProfileImage(),
                "kakao_" + kakaoMember.getId(),
                kakaoMember.getNickname());

//        System.out.println("2.카카오에 회원정보 요청 후 받은 회원이름 == " + memberEntity.getNickname());
        log.info("[카카오] 2.카카오에 회원정보 요청 후 받은 회원이름 : {}", memberEntity.getNickname());

        //== 3.처음 로그인 시 강제 회원가입==//
        if (!isExist(memberEntity)) memberService.join(memberEntity);

        String jwtToken = jwtTokenProvider.createJwtToken(
                memberService.findByUsername(memberEntity.getUsername()));

//        System.out.println("3. 받은 회원정보를 바탕으로 생성한 JWT토큰 == " + jwtToken);
        log.info("[카카오] 3.카카오서버로부터 받은 회원정보로 생성한 JWT토큰 : {}", jwtToken);

        Cookie cookie = new Cookie("Authorization", jwtToken);
        cookie.setMaxAge(36000); //10시간

        //==FE가 서버의 해당 경로와 하위 경로를 갈 때 쿠키를 주도록 함==//
        cookie.setPath("/");

        //==여기서 도메인을 설정해야 해당 도메인에서 쿠키를 저장함==//
        //==FE서버의 도메인 주소를 적어야 함==//
        //==로컬에서 FE를 돌릴 때는 쿠키는 들어오지만 저장을 안해서 작동안함==//
//        cookie.setDomain("ec2-15-165-244-200.ap-northeast-2.compute.amazonaws.com");
        cookie.setDomain("localhost");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addCookie(cookie);
        response.setHeader("Location", HOME_REDIRECT_URI);
        response.setStatus(HttpServletResponse.SC_FOUND);

    }

    public Boolean isExist(Member member)
    {
        Member memberEntity = memberService.findByUsername(member.getUsername());
        return memberEntity != null;
    }

}

@Builder
class KakaoAccessTokenRequest
{
    private String uri;
    private Map<String, String> headers;
    private Map<String, String> body;

    public KakaoAccessTokenResponseDto post()
    {
        MultiValueMap<String, String> reqHeaders = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> reqBody = new LinkedMultiValueMap<>();
        headers.forEach(reqHeaders::add);
        body.forEach(reqBody::add);

        return new RestTemplate().postForObject(
                uri,
                new HttpEntity<>(reqBody, reqHeaders),
                KakaoAccessTokenResponseDto.class);
    }
}

@Builder
class KakaoMemberInfoRequest
{
    private String uri;
    private Map<String, String> headers;
    private Map<String, String> body;

    public KakaoMemberDto post()
    {
        MultiValueMap<String, String> reqHeaders = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> reqBody = new LinkedMultiValueMap<>();
        headers.forEach(reqHeaders::add);
        body.forEach(reqBody::add);

        return new RestTemplate().postForObject(
                uri,
                new HttpEntity<>(reqBody, reqHeaders),
                KakaoMemberDto.class);
    }
}

