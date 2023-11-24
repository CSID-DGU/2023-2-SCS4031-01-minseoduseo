package msds.homefarming.config;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;

@Component
public class CorsConfig implements Filter
{
    //==CORS ORIGIN 테스트==//
//    private String CORS_ALLOWED_ORIGIN = "http://localhost:3000/";
    //==CORS ORIGIN 실제배포==//
//    private String CORS_ALLOWED_ORIGIN = "http://localhost:3000/";

    @Value("${config.cors.allowed-origin}")
    private String CORS_ALLOWED_ORIGIN;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //==아래의 React서버URI에 대해서만 CORS정책을 적용함.==//
        //원래는 CORS_ALLOWED_ORIGIN이었음!
        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedOrigin("https://accounts.kakao.com");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        CorsFilter corsFilter = new CorsFilter(source);

        corsFilter.doFilter(request,response,chain);

    }

    @Override
    public void destroy()
    {
    }
}
