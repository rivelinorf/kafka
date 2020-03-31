package com.examle.kafkaexamples.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
@NoArgsConstructor
@Service
public class NewOrderConsumerService {

    @Async
    public void consume(String consumerName, String topic, String group) {
        log.info("Start Consumer " + consumerName);
        var consumer = new KafkaConsumer<String, String>(loadProperties(group));
        consumer.subscribe(Collections.singleton(topic));
        while(true) {
            var records = consumer.poll(Duration.ofMillis(10000));
            if (!records.isEmpty()) {
                for (var record : records) {
                    log.info("--------------------" + consumerName + "----------------------");
                    log.info("Processing new order, checking" );
                    log.info("TOPIC => " + record.topic());
                    log.info("GROUP => " + group);
                    log.info("KEY => " + record.key());
                    log.info("VALUE => " + record.value());
                    log.info("PARTITION => " + record.partition());
                    log.info("OFFSET => " + record.offset());
                    log.info("--------------------" + consumerName + "----------------------");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // ignoring
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Properties loadProperties(String group) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group);
        return properties;
    }
}
