package com.jbnu.ideahub.category.service;

import com.jbnu.ideahub.category.domain.Category;
import com.jbnu.ideahub.category.dto.request.CategoryRequest;
import com.jbnu.ideahub.category.dto.response.CategoryResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    // TODO: 구현
    public Long save(CategoryRequest categoryRequest) {
        return 1L;
    }

    public void delete(Long categoryId) {

    }

    public void update(Long categoryId, CategoryRequest categoryRequest) {

    }

    public List<CategoryResponse> findAll() {
        List<CategoryResponse> sample = new ArrayList<>();
        sample.add(CategoryResponse.of(new Category(1L, "카테고리1")));
        sample.add(CategoryResponse.of(new Category(2L, "카테고리2")));

        return sample;
    }
}
