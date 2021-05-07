package com.hackathon.sessionratelimiter.producer;

import com.hackathon.sessionratelimiter.model.RequestHandled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class RequestHandledProducer {

    private static final String TOPIC_NAME = "request.handled";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public RequestHandledProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(RequestHandled requestHandled) {

        ListenableFuture<SendResult<String, Object>> sendResultListenableFuture = kafkaTemplate.send(TOPIC_NAME,
                requestHandled.getUserId().toString(), requestHandled);
        sendResultListenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                throw new UnsupportedOperationException(throwable);
            }

            @Override
            public void onSuccess(SendResult<String, Object> sendResult) {
                log.info("RequestHandled event published: {}", requestHandled);
            }
        });

        try {
            sendResultListenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurred while sending event", e);
        }
    }
}
