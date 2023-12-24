package com.jbnu.ideahub.competition.domain.repository;

import com.jbnu.ideahub.competition.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
