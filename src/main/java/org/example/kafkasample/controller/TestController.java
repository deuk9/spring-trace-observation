package org.example.kafkasample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkasample.entity.Team;
import org.example.kafkasample.repository.TeamRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TeamRepository teamRepository;

    @PostMapping("/test")
    public void sendTestMessage() {
        kafkaTemplate.send("my-topic", "No1.Team")
            .thenAccept(o -> log.info("Sent message:{}", o.toString()));
    }
}
