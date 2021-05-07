package com.hackathon.sessionratelimiter.client;

import com.hackathon.sessionratelimiter.configuration.client.KsqlRestProxyConfiguration;
import com.hackathon.sessionratelimiter.model.UserRateLimiterResponse;
import com.hackathon.sessionratelimiter.model.UserRateLimiterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ksql-rest-proxy", url = "${feign.client.ksql.url}", configuration = KsqlRestProxyConfiguration.class)
public interface KsqlRestProxyClient {

    @PostMapping("/query")
    List<UserRateLimiterResponse> getUserRateLimiter(@RequestBody UserRateLimiterRequest request);
}
