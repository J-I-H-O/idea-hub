package com.jbnu.ideahub.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateRequest {

    private static final int MAX_TEXT_SIZE = 65535;

    @NotBlank(message = "댓글 내용은 공백일 수 없습니다.")
    @Size(max = MAX_TEXT_SIZE, message = "댓글 내용은 " + MAX_TEXT_SIZE + "자를 넘을 수 없습니다.")
    private String content;
}
