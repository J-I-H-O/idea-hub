package com.jbnu.ideahub.entry.dto.request;

import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryCreateRequest {

    private static final int MAX_TITLE_SIZE = 255;
    private static final int MAX_CONTENT_SIZE = 65535;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_GITHUB_SIZE = 255;

    private Long competitionId;

    @NotNull(message = "작품 이름은 NULL일 수 없습니다.")
    @NotBlank(message = "작품 이름은 공백일 수 없습니다.")
    @Size(max = MAX_TITLE_SIZE, message = "작품 이름은 " + MAX_TITLE_SIZE + "자를 넘을 수 없습니다.")
    private String title;

    @NotNull(message = "작품 내용은 NULL일 수 없습니다.")
    @NotBlank(message = "작품 내용은 공백일 수 없습니다.")
    @Size(max = MAX_CONTENT_SIZE, message = "작품 내용은 " + MAX_CONTENT_SIZE + "자를 넘을 수 없습니다.")
    private String content;

    @NotNull(message = "작품 상태는 NULL일 수 없습니다.")
    private EntryStatus status;

    @Size(max = MAX_GITHUB_SIZE, message = "작품 깃허브 주소는 " + MAX_GITHUB_SIZE + "자를 넘을 수 없습니다.")
    private String github;

    private PrizeDto prize;
}
