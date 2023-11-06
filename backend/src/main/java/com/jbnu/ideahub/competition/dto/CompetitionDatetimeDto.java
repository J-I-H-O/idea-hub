package com.jbnu.ideahub.competition.dto;

import com.jbnu.ideahub.competition.domain.CompetitionDatetime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CompetitionDatetimeDto {

    private final LocalDateTime competitionStartDatetime;
    private final LocalDateTime competitionEndDatetime;

    public static CompetitionDatetimeDto createdRegistrationDatetime(
            CompetitionDatetime competitionDatetime
    ) {
        return new CompetitionDatetimeDto(
                competitionDatetime.getCompetitionStartDatetime(),
                competitionDatetime.getCompetitionEndDatetime()
        );
    }
}
