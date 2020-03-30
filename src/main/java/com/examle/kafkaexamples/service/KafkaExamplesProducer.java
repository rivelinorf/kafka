package com.examle.kafkaexamples.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
@NoArgsConstructor
@Service
public class KafkaExamplesProducer {

    public void producer(String value) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(loadProperties());
        var record = new ProducerRecord<>("NEW_ORDER", value, value);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                return;
            }
            log.info("topic: " + metadata.topic() + "\tmessage: " + metadata.toString());
        }).get();
    }

    private Properties loadProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
