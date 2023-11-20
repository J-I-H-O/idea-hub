package com.jbnu.ideahub.restDocs.category;

import com.jbnu.ideahub.category.domain.Category;
import com.jbnu.ideahub.category.dto.response.CategoryResponse;
import com.jbnu.ideahub.category.presentation.CategoryController;
import com.jbnu.ideahub.category.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerRestdocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("카테고리 목록 조회 API")
    void getCategories() throws Exception {
        given(categoryService.findAll())
                .willReturn(List.of(
                        CategoryResponse.of(new Category(1L, "카테고리1")),
                        CategoryResponse.of(new Category(2L, "카테고리2")))
                );

        ResultActions result = mockMvc.perform(get("/categories"));
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
}