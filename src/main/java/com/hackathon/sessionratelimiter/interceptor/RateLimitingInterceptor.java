package com.hackathon.sessionratelimiter.interceptor;

import com.hackathon.sessionratelimiter.model.RequestHandled;
import com.hackathon.sessionratelimiter.model.UserRateLimiterResponse;
import com.hackathon.sessionratelimiter.producer.RequestHandledProducer;
import com.hackathon.sessionratelimiter.service.RateLimitingService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    private final RequestHandledProducer requestHandledProducer;
    private final RateLimitingService rateLimitingService;

    public RateLimitingInterceptor(RequestHandledProducer requestHandledProducer,
                                   @Lazy RateLimitingService rateLimitingService) {
        this.requestHandledProducer = requestHandledProducer;
        this.rateLimitingService = rateLimitingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        List<UserRateLimiterResponse> rateLimiterResponses = rateLimitingService.request(request.getHeader("userId"));
        if (rateLimiterResponses.size() > 1) {
            Integer requestCount = rateLimiterResponses.get(1).getRow().getColumns().get(0);
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), ("Your request limit exceed, requestCount:" + requestCount));
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RequestHandled requestHandled = new RequestHandled();
        requestHandled.setUserId(Long.parseLong(request.getHeader("userId")));
        requestHandled.setRequestPath(request.getRequestURI());
        requestHandled.setRequestType(request.getMethod());
        requestHandled.setRequestTime(DateTime.now(DateTimeZone.UTC).toString("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        requestHandledProducer.produce(requestHandled);
    }
}
