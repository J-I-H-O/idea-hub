package com.jbnu.ideahub.competition.domain.repository;

import com.jbnu.ideahub.competition.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    default Competition getById(final Long id) {
        return this.findById(id)
                .orElse(null);
    }
}
