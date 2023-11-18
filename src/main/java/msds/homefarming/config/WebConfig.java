package msds.homefarming.config;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.repository.MemberRepository;
import msds.homefarming.service.MemberService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private final UserPrincipal userPrincipal;

    //테스트계정 강제회원가입을 위한 의존성 추가
    private final MemberService memberService;
    //배포 시에는 삭제할 것.


    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //테스트계정 강제회원가입을 위한 memberRepository 파라미터의 추가. 배포에는 삭제할 것.
        registry.addInterceptor(new LoginInterceptor(new JwtTokenProvider(), userPrincipal, memberService))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }

    //==CORS 설정==///
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Authorization", "Content-Type")
                .exposedHeaders("Custom-Header")
                .maxAge(3600);
    }
}
