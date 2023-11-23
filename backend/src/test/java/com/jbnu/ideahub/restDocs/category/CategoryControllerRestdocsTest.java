package com.jbnu.ideahub.restDocs.category;

import com.jbnu.ideahub.category.domain.Category;
import com.jbnu.ideahub.category.dto.request.CategoryRequest;
import com.jbnu.ideahub.category.dto.response.CategoryResponse;
import com.jbnu.ideahub.category.presentation.CategoryController;
import com.jbnu.ideahub.category.service.CategoryService;
import com.jbnu.ideahub.restDocs.common.ControllerRestdocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;

import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerRestdocsTest extends ControllerRestdocsTest {

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 등록 API")
    void createCategory() throws Exception {
        // given
        CategoryRequest request = new CategoryRequest("카테고리1");
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isCreated())
                .andDo(document("categories/create-category",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("카테고리 이름")
                        )
                ));
    }

    @Test
    @DisplayName("카테고리 목록 조회 API")
    void getCategories() throws Exception {
        // given
        given(categoryService.findAll())
                .willReturn(List.of(
                        CategoryResponse.of(new Category(1L, "카테고리1")),
                        CategoryResponse.of(new Category(2L, "카테고리2")))
                );

        // when
        ResultActions result = mockMvc.perform(get("/categories"));

        // then
        result.andExpect(status().isOk())
                .andDo(document("categories/get-categories",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("카테고리 id"),
                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("카테고리 이름")
                        )
                ));
    }

    @Test
    @DisplayName("카테고리 수정 API")
    void updateCategory() throws Exception {
        // given
        CategoryRequest request = new CategoryRequest("카테고리2");
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/categories/{categoryId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("categories/update-category",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("categoryId").description("수정할 카테고리의 id")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("새로운 카테고리 이름")
                        )
                ));
    }

    @Test
    @DisplayName("카테고리 삭제 API")
    void deleteCategory() throws Exception {
        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/categories/{categoryId}", 1L));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("categories/delete-category",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("categoryId").description("삭제할 카테고리의 id")
                        )
                ));
    }
}