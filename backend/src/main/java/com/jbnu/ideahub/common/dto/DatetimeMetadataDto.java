package com.jbnu.ideahub.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DatetimeMetadataDto {

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static DatetimeMetadataDto createDatetimeMetadataResponse(
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return new DatetimeMetadataDto(createdAt, updatedAt);
    }
}
