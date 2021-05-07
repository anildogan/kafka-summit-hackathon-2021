package com.hackathon.sessionratelimiter.configuration.client;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KsqlRestProxyAuthenticationProperties.class)
public class KsqlRestProxyConfiguration {

    private final KsqlRestProxyAuthenticationProperties ksqlRestProxyAuthenticationProperties;

    public KsqlRestProxyConfiguration(KsqlRestProxyAuthenticationProperties ksqlRestProxyAuthenticationProperties) {
        this.ksqlRestProxyAuthenticationProperties = ksqlRestProxyAuthenticationProperties;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(ksqlRestProxyAuthenticationProperties.getUsername(),
                ksqlRestProxyAuthenticationProperties.getPassword());
    }
}