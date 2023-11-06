package com.jbnu.ideahub.entry.dto.response;

import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
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
        PrizeDto prizeDto = PrizeDto.of(entry.getPrize());

        DatetimeMetadataDto datetimeMetadata = DatetimeMetadataDto.createDatetimeMetadataResponse(
                entry.getDatetimeMetadata().getCreatedAt(),
                entry.getDatetimeMetadata().getUpdatedAt()
        );

        return new EntryResponse(
                entry.getId(),
                entry.getCompetition().getId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getStatus(),
                entry.getGithub(),
                prizeDto,
                datetimeMetadata
        );
    }
}
