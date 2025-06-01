package org.example.kafkasample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkasample.entity.Team;
import org.example.kafkasample.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Team createTeam(Team team) {
        log.info("Creating team: {}", team.getName());
        return teamRepository.save(team);
    }

}