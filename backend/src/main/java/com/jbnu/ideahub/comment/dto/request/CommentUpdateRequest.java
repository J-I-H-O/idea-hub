package com.jbnu.ideahub.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentUpdateRequest {

    private static final int MAX_TEXT_SIZE = 65535;
    private static final String BLANK_ERROR_MESSAGE = "댓글 내용은 공백일 수 없습니다.";
    private static final String LENGTH_ERROR_MESSAGE = "댓글 내용은 " + MAX_TEXT_SIZE + "자를 넘을 수 없습니다.";

    @NotBlank(message = BLANK_ERROR_MESSAGE)
    @Size(max = MAX_TEXT_SIZE, message = LENGTH_ERROR_MESSAGE)
    private final String content;
}
