package com.jbnu.ideahub.star.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StarRequest {
    private static final String MEMBER_ID_NULL_ERROR_MESSAGE = "멤버 식별자는 NULL일 수 없습니다.";
    private static final String ENTRY_ID_NULL_ERROR_MESSAGE = "작품 식별자는 NULL일 수 없습니다.";

    @NotNull(message = MEMBER_ID_NULL_ERROR_MESSAGE)
    private Long memberId;

    @NotNull(message = ENTRY_ID_NULL_ERROR_MESSAGE)
    private Long entryId;
}
