package com.jbnu.ideahub.domain.competition.entity;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class RegistrationDatetime {

    private LocalDateTime registerStartDatetime;
    private LocalDateTime registerEndDatetime;
}
