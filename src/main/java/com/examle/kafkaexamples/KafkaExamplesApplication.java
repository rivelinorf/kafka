package com.examle.kafkaexamples;

import com.examle.kafkaexamples.service.NewOrderConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KafkaExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaExamplesApplication.class, args);
    }

}
