package com.jbnu.ideahub.entry.dto.request;

import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EntryCreateUpdateRequest {

    private static final int MAX_TITLE_SIZE = 255;
    private static final int MAX_CONTENT_SIZE = 65535;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_GITHUB_SIZE = 255;
    private static final String TITLE_BLANK_ERROR_MESSAGE = "작품 이름은 공백일 수 없습니다.";
    private static final String TITLE_LENGTH_ERROR_MESSAGE = "작품 이름은 " + MAX_TITLE_SIZE + "자를 넘을 수 없습니다.";
    private static final String CONTENT_BLANK_ERROR_MESSAGE = "작품 내용은 공백일 수 없습니다.";
    private static final String CONTENT_LENGTH_ERROR_MESSAGE = "작품 내용은 " + MAX_CONTENT_SIZE + "자를 넘을 수 없습니다.";
    private static final String STATUS_BLANK_ERROR_MESSAGE = "작품 상태는 공백일 수 없습니다.";
    private static final String STATUS_LENGTH_ERROR_MESSAGE = "작품 상태는 " + MAX_STATUS_SIZE + "자를 넘을 수 없습니다.";
    private static final String GITHUB_LENGTH_ERROR_MESSAGE = "작품 깃허브 주소는 " + MAX_GITHUB_SIZE + "자를 넘을 수 없습니다.";

    private final Long competitionId;

    @NotBlank(message = TITLE_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_TITLE_SIZE, message = TITLE_LENGTH_ERROR_MESSAGE)
    private final String title;

    @NotBlank(message = CONTENT_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_CONTENT_SIZE, message = CONTENT_LENGTH_ERROR_MESSAGE)
    private final String content;

    @NotBlank(message = STATUS_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_STATUS_SIZE, message = STATUS_LENGTH_ERROR_MESSAGE)
    private final CompetitionStatus status;

    @Size(max = MAX_GITHUB_SIZE, message = GITHUB_LENGTH_ERROR_MESSAGE)
    private final String github;

    private final PrizeDto prize;
}
