package com.jbnu.ideahub.category.dto.response;

import com.jbnu.ideahub.category.domain.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryResponse {

    private final Long id;
    private final String name;

    public static CategoryResponse of(final Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
