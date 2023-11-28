package com.jbnu.ideahub.document.star;

import com.jbnu.ideahub.document.common.RestdocsTestController;
import com.jbnu.ideahub.star.dto.request.StarRequest;
import com.jbnu.ideahub.star.presentation.StarController;
import com.jbnu.ideahub.star.service.StarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.jbnu.ideahub.document.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.document.utils.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StarController.class)
public class StarControllerRestdocsTest extends RestdocsTestController {

    @MockBean
    private StarService starService;

    @Test
    @DisplayName("추천 등록 API")
    void createStar() throws Exception {
        // given
        StarRequest request = new StarRequest(1L, 1L);
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/stars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("stars/create-star",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("추천을 누른 회원의 id"),
                                fieldWithPath("entryId").type(JsonFieldType.NUMBER).description("추천을 받은 작품의 id")
                        )
                ));
    }

    @Test
    @DisplayName("추천 삭제 API")
    void deleteStar() throws Exception {
        // given
        StarRequest request = new StarRequest(1L, 1L);
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(delete("/stars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("stars/delete-star",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("추천을 누른 회원의 id"),
                                fieldWithPath("entryId").type(JsonFieldType.NUMBER).description("추천을 받은 작품의 id")
                        )
                ));
    }
}
