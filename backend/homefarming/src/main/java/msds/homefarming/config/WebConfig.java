package msds.homefarming.config;

import lombok.RequiredArgsConstructor;
import msds.homefarming.auth.JwtTokenProvider;
import msds.homefarming.auth.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private final UserPrincipal userPrincipal;

    @Override

    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginInterceptor(new JwtTokenProvider(), userPrincipal))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }
}
