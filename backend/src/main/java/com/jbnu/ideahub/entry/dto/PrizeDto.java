package com.jbnu.ideahub.entry.dto;

import com.jbnu.ideahub.entry.domain.Prize;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrizeDto {

    private static final int MAX_NAME_SIZE = 255;
    private static final String NAME_LENGTH_ERROR_MESSAGE = "상 이름은 " + MAX_NAME_SIZE + "자를 넘을 수 없습니다.";

    @Size(max = MAX_NAME_SIZE, message = NAME_LENGTH_ERROR_MESSAGE)
    private final String prizeName;

    private final int prizePriority;

    public static PrizeDto of(final Prize prize) {
        return new PrizeDto(prize.getPrizeName(), prize.getPrizePriority());
    }
}
