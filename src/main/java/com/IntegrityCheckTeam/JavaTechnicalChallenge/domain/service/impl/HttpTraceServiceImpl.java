package com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.impl;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.HttpTraceService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class HttpTraceServiceImpl implements HttpTraceService {
    private final RabbitTemplate rabbitTemplate;

    public HttpTraceServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendHttpTrace(String trace) {
        rabbitTemplate.convertAndSend("http-trace-exchange", "http-trace", trace);
    }
}
