package com.hackathon.sessionratelimiter.producer;

import com.hackathon.sessionratelimiter.model.RequestHandled;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestHandledProducer {

    private static final String TOPIC_NAME = "request.handled";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public RequestHandledProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(RequestHandled requestHandled){
        kafkaTemplate.send(TOPIC_NAME, requestHandled.getUserId().toString(), requestHandled);
    }
}
