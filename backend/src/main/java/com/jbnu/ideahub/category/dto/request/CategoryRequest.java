package com.jbnu.ideahub.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotNull(message = "카테고리 이름은 NULL일 수 없습니다.")
    @NotBlank(message = "카테고리 이름은 공백일 수 없습니다.")
    @Size(max = 50, message = "카테고리 이름은 50자를 초과할 수 없습니다.")
    private String name;
}
