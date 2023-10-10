package com.modsen.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaSenderImpl {
    private final KafkaTemplate<String, Long> kafkaTemplate;



    public void sendMessage(Long id, String topicName) {
        kafkaTemplate.send(topicName, id);
    }
}
