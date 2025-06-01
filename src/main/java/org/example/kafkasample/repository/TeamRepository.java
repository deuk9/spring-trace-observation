package org.example.kafkasample.repository;

import org.example.kafkasample.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}