package org.example.kafkasample.consumer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkasample.entity.Team;
import org.example.kafkasample.service.TeamService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyConsumer {

    private final TeamService teamService;

    @KafkaListener(topics = "my-topic")
    public void listen(String message, Acknowledgment acknowledgment) {
        log.info("Received Message: {}", message);
        teamService.createTeam(new Team(message));
        acknowledgment.acknowledge();
    }

}
