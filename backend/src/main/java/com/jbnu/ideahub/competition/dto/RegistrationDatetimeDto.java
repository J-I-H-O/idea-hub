package com.jbnu.ideahub.competition.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDatetimeDto {

    private String start;
    private String end;

    public static RegistrationDatetimeDto createdRegistrationDatetime(
            LocalDateTime registerStartDatetime,
            LocalDateTime registerEndDatetime
    ) {
        return new RegistrationDatetimeDto(
                formatDateTime(registerStartDatetime),
                formatDateTime(registerEndDatetime)
        );
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(dateTime);
    }
}
