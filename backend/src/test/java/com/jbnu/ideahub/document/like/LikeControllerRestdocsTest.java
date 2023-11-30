package com.jbnu.ideahub.document.like;

import com.jbnu.ideahub.document.common.RestdocsTestController;
import com.jbnu.ideahub.like.dto.request.LikeRequest;
import com.jbnu.ideahub.like.presentation.LikeController;
import com.jbnu.ideahub.like.service.LikeService;
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

@WebMvcTest(LikeController.class)
public class LikeControllerRestdocsTest extends RestdocsTestController {

    @MockBean
    private LikeService likeService;

    @Test
    @DisplayName("추천 등록 API")
    void createLike() throws Exception {
        // given
        LikeRequest request = new LikeRequest(1L, 1L);
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("likes/create-like",
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
    void deleteLike() throws Exception {
        // given
        LikeRequest request = new LikeRequest(1L, 1L);
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(delete("/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("likes/delete-like",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("추천을 누른 회원의 id"),
                                fieldWithPath("entryId").type(JsonFieldType.NUMBER).description("추천을 받은 작품의 id")
                        )
                ));
    }
}
