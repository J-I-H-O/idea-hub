package com.jbnu.ideahub.competition.dto.request;

import com.jbnu.ideahub.competition.domain.CompetitionDatetime;
import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.competition.domain.RegistrationDatetime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionRequest {

    private static final int MAX_TITLE_SIZE = 255;
    private static final int MAX_CONTENT_SIZE = 65535;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_PLACE_SIZE = 255;
    private static final String TITLE_BLANK_ERROR_MESSAGE = "대회 이름은 공백일 수 없습니다.";
    private static final String TITLE_LENGTH_ERROR_MESSAGE = "대회 이름은 " + MAX_TITLE_SIZE + "자를 넘을 수 없습니다.";
    private static final String CONTENT_BLANK_ERROR_MESSAGE = "대회 내용은 공백일 수 없습니다.";
    private static final String CONTENT_LENGTH_ERROR_MESSAGE = "대회 내용은 " + MAX_CONTENT_SIZE + "자를 넘을 수 없습니다.";
    private static final String STATUS_BLANK_ERROR_MESSAGE = "대회 상태는 공백일 수 없습니다.";
    private static final String STATUS_LENGTH_ERROR_MESSAGE = "대회 상태는 " + MAX_STATUS_SIZE + "자를 넘을 수 없습니다.";
    private static final String PLACE_LENGTH_ERROR_MESSAGE = "대회 장소는 " + MAX_PLACE_SIZE + "자를 넘을 수 없습니다.";

    @NotBlank(message = TITLE_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_TITLE_SIZE, message = TITLE_LENGTH_ERROR_MESSAGE)
    private String title;

    @NotBlank(message = CONTENT_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_CONTENT_SIZE, message = CONTENT_LENGTH_ERROR_MESSAGE)
    private String content;

    @NotBlank(message = STATUS_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_STATUS_SIZE, message = STATUS_LENGTH_ERROR_MESSAGE)
    private CompetitionStatus status;

    @Size(max = MAX_PLACE_SIZE, message = PLACE_LENGTH_ERROR_MESSAGE)
    private  String place;

    private RegistrationDatetime registrationDatetime;

    private CompetitionDatetime competitionDatetime;
}
