package com.jbnu.ideahub.competition.dto.datetimeMetadata;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDatetimeDto {

    private String start;
    private String end;

    public static CompetitionDatetimeDto createdRegistrationDatetime(
            LocalDateTime competitionStartDatetime,
            LocalDateTime competitionEndDatetime
    ) {
        return new CompetitionDatetimeDto(
                formatDateTime(competitionStartDatetime),
                formatDateTime(competitionEndDatetime)
        );
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(dateTime);
    }
}
