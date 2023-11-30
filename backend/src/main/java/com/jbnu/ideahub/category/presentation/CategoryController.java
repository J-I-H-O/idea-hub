package com.jbnu.ideahub.category.presentation;

import com.jbnu.ideahub.category.dto.request.CategoryRequest;
import com.jbnu.ideahub.category.dto.response.CategoryResponse;
import com.jbnu.ideahub.category.application.CategoryService;
import com.jbnu.ideahub.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(
            @RequestBody @Valid final CategoryRequest categoryRequest
    ) {
        final Long categoryId = categoryService.save(categoryRequest);
        return ResponseEntity.created(URI.create("/categories/" + categoryId)).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategories() {
        final List<CategoryResponse> categoryResponses = categoryService.findAll();
        return ResponseEntity.ok().body(new ApiResponse<>(categoryResponses));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable final Long categoryId,
            @RequestBody @Valid final CategoryRequest categoryRequest
    ) {
        categoryService.update(categoryId, categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable final Long categoryId
    ) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
}
