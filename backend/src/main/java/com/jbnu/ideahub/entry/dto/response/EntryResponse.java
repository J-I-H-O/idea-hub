package com.jbnu.ideahub.entry.dto.response;

import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.competition.domain.Competition;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EntryResponse {

    private final Long id;
    private final Long competitionId;
    private final String title;
    private final String content;
    private final EntryStatus status;
    private final String github;
    private final PrizeDto prize;
    private final DatetimeMetadataDto datetimeMetadata;

    public static EntryResponse of(final Entry entry) {
        Long competitionId = Optional.ofNullable(entry.getCompetition())
                .map(Competition::getId)
                .orElse(null);

        PrizeDto prizeDto = Optional.ofNullable(entry.getPrize())
                .map(PrizeDto::of)
                .orElse(null);

        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                entry.getDatetimeMetadata().getCreatedAt(),
                entry.getDatetimeMetadata().getUpdatedAt()
        );

        return EntryResponse.builder()
                .id(entry.getId())
                .competitionId(competitionId)
                .title(entry.getTitle())
                .content(entry.getContent())
                .status(entry.getStatus())
                .github(entry.getGithub())
                .prize(prizeDto)
                .datetimeMetadata(datetimeMetadataDto)
                .build();
    }
}
