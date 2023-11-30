package com.jbnu.ideahub.competition.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class CompetitionDatetime {

    private LocalDateTime competitionStartDatetime;
    private LocalDateTime competitionEndDatetime;
}
