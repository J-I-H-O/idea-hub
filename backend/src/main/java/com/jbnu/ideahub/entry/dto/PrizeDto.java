package com.jbnu.ideahub.entry.dto;

import com.jbnu.ideahub.entry.domain.Prize;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrizeDto {

    private static final int MAX_NAME_SIZE = 255;

    @Size(max = MAX_NAME_SIZE, message = "상 이름은 " + MAX_NAME_SIZE + "자를 넘을 수 없습니다.")
    private String prizeName;

    private Integer prizePriority;

    public static PrizeDto of(final Prize prize) {
        if (prize == null) {
            return null;
        }
        return new PrizeDto(prize.getPrizeName(), prize.getPrizePriority());
    }

    public Prize toEntity() {
        return new Prize(prizeName, prizePriority);
    }
}
