package com.hackathon.sessionratelimiter.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private static final int MAX_AGE = 3600;
    private final RateLimitingInterceptor rateLimitingInterceptor;

    @Value("${swagger.host.url}")
    private String applicationUrl;

    public InterceptorConfiguration(RateLimitingInterceptor rateLimitingInterceptor) {
        this.rateLimitingInterceptor = rateLimitingInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitingInterceptor).addPathPatterns("/hello");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(applicationUrl)
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods(GET.name(), POST.name(), DELETE.name(), PUT.name(), OPTIONS.name(), PATCH.name())
                .maxAge(MAX_AGE);
    }
}