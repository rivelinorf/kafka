package com.examle.kafkaexamples.resource;

import com.examle.kafkaexamples.data.Message;
import com.examle.kafkaexamples.service.NewOrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/order",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class NewOrderResource {

    @Autowired
    private NewOrderProducerService service;

    @PostMapping
    ResponseEntity<String> newOrder(@RequestBody Message body) {
        try {
            if (body.getKey() == null) {
                body.setKey(UUID.randomUUID().toString());
            }
            if (body.getValue() == null) {
                body.setValue(UUID.randomUUID().toString());
            }
            service.producer(body.getKey(), body.getValue(), body.getTopic());
            return ResponseEntity.ok("OK");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
