package com.jbnu.ideahub.competition.dto;

import com.jbnu.ideahub.competition.domain.RegistrationDatetime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationDatetimeDto {

    private final LocalDateTime registerStartDatetime;
    private final LocalDateTime registerEndDatetime;

    public static RegistrationDatetimeDto createdRegistrationDatetime(
            RegistrationDatetime registrationDatetime
    ) {
        return new RegistrationDatetimeDto(
                registrationDatetime.getRegisterStartDatetime(),
                registrationDatetime.getRegisterEndDatetime()
        );
    }
}
