package com.jbnu.ideahub.competition.dto.response;

import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.competition.domain.Competition;
import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.competition.dto.datetimeMetadata.CompetitionDatetimeDto;
import com.jbnu.ideahub.competition.dto.datetimeMetadata.RegistrationDatetimeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CompetitionResponse {

    private final Long id;
    private final Long hostId;
    private final Long categoryId;
    private final String title;
    private final String content;
    private final CompetitionStatus status;
    private final String place;
    private final RegistrationDatetimeDto registrationDatetime;
    private final CompetitionDatetimeDto competitionDatetime;
    private final DatetimeMetadataDto datetimeMetadata;

    public static CompetitionResponse of(final Competition competition) {
        RegistrationDatetimeDto registrationDatetime = RegistrationDatetimeDto
                .createdRegistrationDatetime(
                        competition.getRegistrationDatetime().getRegisterStartDatetime(),
                        competition.getRegistrationDatetime().getRegisterEndDatetime()
                );

        CompetitionDatetimeDto competitionDatetime = CompetitionDatetimeDto
                .createdRegistrationDatetime(
                        competition.getCompetitionDatetime().getCompetitionStartDatetime(),
                        competition.getCompetitionDatetime().getCompetitionEndDatetime()
                );

        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                competition.getDatetimeMetadata().getCreatedAt(),
                competition.getDatetimeMetadata().getUpdatedAt()
        );

        return new CompetitionResponse(
                competition.getId(),
                competition.getHost().getId(),
                competition.getCategory().getId(),
                competition.getTitle(),
                competition.getContent(),
                competition.getStatus(),
                competition.getPlace(),
                registrationDatetime,
                competitionDatetime,
                datetimeMetadataDto
        );
    }
}
