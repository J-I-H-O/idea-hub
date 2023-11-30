package com.jbnu.ideahub.competition.dto.request;

import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.competition.dto.datetimeMetadata.CompetitionDatetimeDto;
import com.jbnu.ideahub.competition.dto.datetimeMetadata.RegistrationDatetimeDto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionUpdateRequest {

    private static final int MAX_TITLE_SIZE = 255;
    private static final int MAX_CONTENT_SIZE = 65535;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_PLACE_SIZE = 255;

    @Size(max = MAX_TITLE_SIZE, message = "대회 이름은 " + MAX_TITLE_SIZE + "자를 넘을 수 없습니다.")
    private String title;

    @Size(max = MAX_CONTENT_SIZE, message = "대회 내용은 " + MAX_CONTENT_SIZE + "자를 넘을 수 없습니다.")
    private String content;

    private CompetitionStatus status;

    @Size(max = MAX_PLACE_SIZE, message = "대회 장소는 " + MAX_PLACE_SIZE + "자를 넘을 수 없습니다.")
    private String place;

    private RegistrationDatetimeDto registrationDatetime;
    private CompetitionDatetimeDto competitionDatetime;
}
