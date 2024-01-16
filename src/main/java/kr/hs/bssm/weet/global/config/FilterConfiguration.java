package kr.hs.bssm.weet.global.config;

import kr.hs.bssm.weet.global.error.ExceptionFilter;
import kr.hs.bssm.weet.global.jwt.filter.JwtAuthenticationFilter;
import kr.hs.bssm.weet.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfiguration {

    private final JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new JwtAuthenticationFilter(jwtUtil));
        bean.setOrder(1);
        bean.addUrlPatterns("/*");

        return bean;
    }

    @Bean
    public FilterRegistrationBean<ExceptionFilter> exceptionFilter() {
        FilterRegistrationBean<ExceptionFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new ExceptionFilter());
        bean.setOrder(2);
        bean.addUrlPatterns("/*");

        return bean;
    }
}
