package com.jbnu.ideahub.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private static final String BLANK_ERROR_MESSAGE = "카테고리 이름은 공백일 수 없습니다.";
    private static final String LENGTH_ERROR_MESSAGE = "카테고리 이름은 50자를 초과할 수 없습니다.";

    @NotBlank(message = BLANK_ERROR_MESSAGE)
    @Size(max = 50, message = LENGTH_ERROR_MESSAGE)
    private String name;
}
