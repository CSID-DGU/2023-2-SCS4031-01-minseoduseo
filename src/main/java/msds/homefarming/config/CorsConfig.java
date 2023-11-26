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
        System.out.println("//==doFilter()실행시작==//");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //==아래의 React서버URI에 대해서만 CORS정책을 적용함.==//
        //원래는 CORS_ALLOWED_ORIGIN이었음!

        //==1.원래 CORS설정==//
//        corsConfiguration.addAllowedOrigin("localhost:3000");
//        corsConfiguration.addAllowedOrigin("localhost:53729");
//        corsConfiguration.addAllowedOrigin("http://172.30.1.9:3000/");
        //==1. 원래 CORS설정 끝==//
        
        //==2. 새로운 CORS 설정==//
        corsConfiguration.addAllowedOriginPattern("*");
        //====//
        
//        corsConfiguration.addAllowedOrigin("https://accounts.kakao.com");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        CorsFilter corsFilter = new CorsFilter(source);
        System.out.println("//==doFilter()실행 끝==//");
        corsFilter.doFilter(request,response,chain);
    }

    @Override
    public void destroy()
    {
    }
}
