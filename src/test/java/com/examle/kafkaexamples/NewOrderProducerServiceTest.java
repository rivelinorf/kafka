package com.examle.kafkaexamples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
public class NewOrderProducerServiceTest {

    @Test
    public void producer() throws ExecutionException, InterruptedException {
        for (var i = 0; i < 10; i++) {
            String key = i+":"+UUID.randomUUID().toString();
            String value = "message:" + UUID.randomUUID().toString();
            var producer = new KafkaProducer<String, String>(loadProperties());
            var record = new ProducerRecord<>("NEW_ORDER", key, value);
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                    return;
                }
                System.out.println("topic: " + metadata.topic() + "\tmessage: " + value);
            }).get();
        }
    }

    private Properties loadProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
