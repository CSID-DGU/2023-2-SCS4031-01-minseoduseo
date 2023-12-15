package msds.homefarming.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import msds.homefarming.domain.Member;
import msds.homefarming.domain.dto.memberDto.UpdatePrincipalRequestDto;
import msds.homefarming.exception.NoExistMemberException;
import msds.homefarming.repository.MemberRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService
{
    private final MemberRepository memberRepository;

    //==회원가입==//
    @Transactional
    public Member join(Member member)
    {
        return memberRepository.save(member);
    }

    //회원정보 아이디 조회==//
    public Member findById(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    //회원정보 유저네임 조회==//
    public Member findByUsername(String username)
    {
        return memberRepository.findByUsername(username);
    }

    //==회원정보 수정==//
    @Transactional
    public Member update(Long memberId, UpdatePrincipalRequestDto requestDto)
    {
        return memberRepository.findById(memberId)
                .update(requestDto.getImage(),
                        requestDto.getNickname());
    }

    //==회원 탈퇴==//
    @Transactional
    public Boolean deleteById(Long memberId, String username)
    {
        System.out.println("memberService - deleteById!!");
        final String kakaoUnlinkUrl = "https://kapi.kakao.com/v1/user/unlink";
        final String kakaoAk = "e7b9831a8c75f96849106ed77375ab0f";

        final String naverUnlinkUrl = "https://nid.naver.com/oauth2.0/token";
        final String naverClientId = "PHCg_5rwDgHfJ4YlvmAW";
        final String naverClientSecret = "IxgS1qy4U1";
        final String naverGrantType = "delete";
        final String naverServiceProvider = "NAVER";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, requestHeaders);

        //--회원 존재 여부 검사--//
        System.out.println("memberId !! "+memberId);
        Member principal = memberRepository.findById(memberId);
        if (principal == null)
        {
            throw new NoExistMemberException("존재하지 않는 회원입니다.");
        }

        System.out.println("username = " + username);
        String[] splitUsername = username.split("_",2);
        String provider = splitUsername[0];
        String clientId = splitUsername[1];

        System.out.println("provider!! = " +  provider);
        System.out.println("clientId!! = " + clientId);

        //==카카오 유저 회원탈퇴==//
        if(provider.equals("kakao"))
        {
            System.out.println("카카오 탈퇴!!");
            requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            requestHeaders.set("Authorization", "KakaoAK " + kakaoAk);
            requestBody.add("target_id_type", "user_id");
            requestBody.add("target_id", clientId);

            restTemplate.postForEntity(kakaoUnlinkUrl, requestEntity, String.class);
        }
        //==네이버 유저 회원탈퇴==//
        else if(provider.equals("naver"))
        {
            //==리프레시 토큰으로 액세스토큰 재발급 요청==//
            String acUrl = "https://nid.naver.com/oauth2.0/token";
            RestTemplate acTemplate = new RestTemplate();
            HttpHeaders acHeaders = new HttpHeaders();
            MultiValueMap<String, String> acRequestBody = new LinkedMultiValueMap<>();
            acHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            acRequestBody.add("client_id",naverClientId);
            acRequestBody.add("client_secret",naverClientSecret);
            acRequestBody.add("refresh_token",principal.getRefreshToken());
            acRequestBody.add("grant_type","refresh_token");
            HttpEntity<MultiValueMap<String, String>> acRequestEntity = new HttpEntity<>(acRequestBody, acHeaders);

            ResponseEntity<acResponse> acResponseEntity = restTemplate.postForEntity(acUrl, acRequestEntity, acResponse.class);
            //==혹시 몰라서 refresh토큰을 저장==//
            principal.setRefreshToken(acResponseEntity.getBody().getRefreshToken());
            String accessToken = acResponseEntity.getBody().getAccessToken();
            //====//
            //====//

            //==재발급 받은 액세스토큰으로 네이버에 회원탈퇴 요청==//
            requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            requestBody.add("grant_type", naverGrantType);
            requestBody.add("client_id", naverClientId);
            requestBody.add("client_secret", naverClientSecret);
            requestBody.add("access_token", accessToken);
            requestBody.add("service_provider", naverServiceProvider);

            restTemplate.postForEntity(naverUnlinkUrl, requestEntity, String.class);
        }
        return memberRepository.deleteById(memberId);
    }
}

@Data
class acResponse
{
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
