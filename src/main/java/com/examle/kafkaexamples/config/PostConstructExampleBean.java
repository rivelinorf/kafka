package com.examle.kafkaexamples.config;

import com.examle.kafkaexamples.service.NewOrderConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructExampleBean {

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() throws InterruptedException {
        context.getBean(NewOrderConsumerService.class).consume("Consumer 01", "NEW_ORDER", "ORDER_GROUP");
//        context.getBean(NewOrderConsumerService.class).consume("Consumer 02", "NEW_ORDER", "ORDER_GROUP");
        context.getBean(NewOrderConsumerService.class).consume("Consumer Log 01", "NEW_ORDER", "ORDER_LOG_GROUP");
    }

}
