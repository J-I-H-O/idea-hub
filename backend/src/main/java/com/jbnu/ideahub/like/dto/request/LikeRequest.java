package com.jbnu.ideahub.like.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequest {

    @NotNull(message = "멤버 식별자는 NULL일 수 없습니다.")
    private Long memberId;

    @NotNull(message = "작품 식별자는 NULL일 수 없습니다.")
    private Long entryId;
}
