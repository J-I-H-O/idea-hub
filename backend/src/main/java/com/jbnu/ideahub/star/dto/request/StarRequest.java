package com.jbnu.ideahub.star.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StarRequest {

    private static final String MEMBER_ID_BLANK_ERROR_MESSAGE = "멤버 식별자는 공백일 수 없습니다.";
    private static final String ENTRY_ID_BLANK_ERROR_MESSAGE = "작품 식별자는 공백일 수 없습니다.";

    @NotBlank(message = MEMBER_ID_BLANK_ERROR_MESSAGE)
    private final Long memberId;

    @NotBlank(message = ENTRY_ID_BLANK_ERROR_MESSAGE)
    private final Long entryId;
}
