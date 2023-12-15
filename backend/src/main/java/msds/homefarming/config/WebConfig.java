package msds.homefarming.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import msds.homefarming.service.MemberService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private final CorsConfig corsConfig;
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

    //==CORS 필터 등록==//
    @Bean
    public FilterRegistrationBean<Filter> corsFilter()
    {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(corsConfig);
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
    //==CORS 필터 등록 끝==//

}
