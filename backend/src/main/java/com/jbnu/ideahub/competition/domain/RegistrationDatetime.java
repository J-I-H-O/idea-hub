package com.jbnu.ideahub.competition.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class RegistrationDatetime {

    private LocalDateTime registerStartDatetime;
    private LocalDateTime registerEndDatetime;
}
