package com.IntegrityCheckTeam.JavaTechnicalChallenge.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Exchange httpTraceExchange() {
        return ExchangeBuilder.directExchange("http-trace-exchange").durable(true).build();
    }

    @Bean
    public Queue httpTraceQueue() {
        return QueueBuilder.durable("http-trace-queue").build();
    }

    @Bean
    public Binding httpTraceBinding() {
        return BindingBuilder.bind(httpTraceQueue()).to(httpTraceExchange()).with("http-trace").noargs();
    }
}
