package com.jbnu.ideahub.competition.dto.request;

import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.competition.dto.datetimeMetadata.CompetitionDatetimeDto;
import com.jbnu.ideahub.competition.dto.datetimeMetadata.RegistrationDatetimeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionCreateRequest {

    private static final int MAX_TITLE_SIZE = 255;
    private static final int MAX_CONTENT_SIZE = 65535;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_PLACE_SIZE = 255;

    @NotNull(message = "대회 이름은 NULL일 수 없습니다.")
    @NotBlank(message = "대회 이름은 공백일 수 없습니다.")
    @Size(max = MAX_TITLE_SIZE, message = "대회 이름은 " + MAX_TITLE_SIZE + "자를 넘을 수 없습니다.")
    private String title;

    @NotNull(message = "대회 내용은 NULL일 수 없습니다.")
    @NotBlank(message = "대회 내용은 공백일 수 없습니다.")
    @Size(max = MAX_CONTENT_SIZE, message = "대회 내용은 " + MAX_CONTENT_SIZE + "자를 넘을 수 없습니다.")
    private String content;

    @NotNull(message = "대회 상태는 NULL일 수 없습니다.")
    private CompetitionStatus status;

    @Size(max = MAX_PLACE_SIZE, message = "대회 장소는 " + MAX_PLACE_SIZE + "자를 넘을 수 없습니다.")
    private String place;

    private RegistrationDatetimeDto registrationDatetime;
    private CompetitionDatetimeDto competitionDatetime;
}
