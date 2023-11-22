package com.jbnu.ideahub.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DatetimeMetadataDto {

    private final String createdAt;
    private final String updatedAt;

    public static DatetimeMetadataDto createDatetimeMetadataResponse(
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return new DatetimeMetadataDto(
                formatDateTime(createdAt),
                formatDateTime(updatedAt)
        );
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(dateTime);
    }
}
