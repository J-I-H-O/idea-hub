package com.jbnu.ideahub.competition.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RegistrationDatetime {

    private LocalDateTime registerStartDatetime;
    private LocalDateTime registerEndDatetime;
}
