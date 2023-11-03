package com.jbnu.ideahub.domain.competition.entity;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class CompetitionDatetime {

    private LocalDateTime competitionStartDatetime;
    private LocalDateTime competitionEndDatetime;
}
