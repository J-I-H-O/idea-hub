package com.jbnu.ideahub.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest {

    private static final int MAX_TEXT_SIZE = 65535;
    private static final String BLANK_ERROR_MESSAGE = "댓글 내용은 공백일 수 없습니다.";
    private static final String LENGTH_ERROR_MESSAGE = "댓글 내용은 " + MAX_TEXT_SIZE + "자를 넘을 수 없습니다.";

    private Long parentId;

    @NotBlank(message = BLANK_ERROR_MESSAGE)
    @Size(max = MAX_TEXT_SIZE, message = LENGTH_ERROR_MESSAGE)
    private String content;

}
