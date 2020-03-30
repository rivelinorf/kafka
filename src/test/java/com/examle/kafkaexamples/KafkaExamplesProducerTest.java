package com.examle.kafkaexamples;

import com.examle.kafkaexamples.service.KafkaExamplesProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class KafkaExamplesProducerTest {

    @Autowired
    private KafkaExamplesProducer producer;

    @Test
    public void test() throws ExecutionException, InterruptedException {
        producer.producer("hello workd kafka");
    }
}
