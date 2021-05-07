package com.hackathon.sessionratelimiter.service;

import com.hackathon.sessionratelimiter.client.KsqlRestProxyClient;
import com.hackathon.sessionratelimiter.model.UserRateLimiterRequest;
import com.hackathon.sessionratelimiter.model.UserRateLimiterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitingService {

    private final KsqlRestProxyClient ksqlRestProxyClient;


    public RateLimitingService(KsqlRestProxyClient ksqlRestProxyClient) {
        this.ksqlRestProxyClient = ksqlRestProxyClient;
    }

    public List<UserRateLimiterResponse> request(String userId){
        UserRateLimiterRequest rateLimiterRequest = new UserRateLimiterRequest();
        rateLimiterRequest.setKsql(userId);
        return ksqlRestProxyClient.getUserRateLimiter(rateLimiterRequest);
    }

}