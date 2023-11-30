package com.jbnu.ideahub.document.comment;

import com.jbnu.ideahub.comment.dto.request.CommentCreateRequest;
import com.jbnu.ideahub.comment.dto.request.CommentUpdateRequest;
import com.jbnu.ideahub.comment.dto.response.CommentResponse;
import com.jbnu.ideahub.comment.presentation.CommentController;
import com.jbnu.ideahub.comment.service.CommentService;
import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.document.common.RestdocsTestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.jbnu.ideahub.document.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.document.utils.ApiDocumentUtils.getDocumentResponse;
import static com.jbnu.ideahub.document.utils.DocumentFormatGenerator.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerRestdocsTest extends RestdocsTestController {

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("댓글 등록 API")
    void createComment() throws Exception {
        // given
        CommentCreateRequest request = new CommentCreateRequest(null, "너무 좋은 내용이네요");
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        result.andExpect(status().isCreated())
                .andDo(document("comments/create-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("parentId").type(JsonFieldType.NUMBER).optional().description("부모 댓글의 id. 부모 댓글이 없는 경우 null"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 조회 API")
    void getComments() throws Exception {
        // given
        DatetimeMetadataDto datetimeMetadata = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        CommentResponse response1 = CommentResponse.builder()
                .id(1L)
                .memberId(1L)
                .entryId(1L)
                .parentId(null)
                .content("너무 유용해요")
                .datetimeMetadata(datetimeMetadata)
                .build();

        CommentResponse response2 = CommentResponse.builder()
                .id(2L)
                .memberId(2L)
                .entryId(1L)
                .parentId(1L)
                .content("감사합니다^^")
                .datetimeMetadata(datetimeMetadata)
                .build();

        given(commentService.findAllByEntryId(anyLong()))
                .willReturn(List.of(response1, response2));

        // when
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/entries/{entryId}/comments", 1L)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(document("comments/get-comments",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("entryId").description("댓글이 작성된 작품의 id")
                        ),
                        responseFields(
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("댓글 id"),
                                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("댓글 작성자의 id"),
                                fieldWithPath("data[].entryId").type(JsonFieldType.NUMBER).description("댓글이 작성된 작품의 id"),
                                fieldWithPath("data[].parentId").type(JsonFieldType.NUMBER).optional().description("부모 댓글의 id. 부모 댓글이 없는 경우 null"),
                                fieldWithPath("data[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("data[].datetimeMetadata").description("등록 및 수정 시각 정보"),
                                fieldWithPath("data[].datetimeMetadata.createdAt").type(JsonFieldType.STRING).attributes(getDatetimeFormat()).description("댓글 작성 시각"),
                                fieldWithPath("data[].datetimeMetadata.updatedAt").type(JsonFieldType.STRING).attributes(getDatetimeFormat()).description("댓글 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 수정 API")
    void updateComment() throws Exception {
        // given
        CommentUpdateRequest request = new CommentUpdateRequest("수정 내용");
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.patch("/comments/{commentId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("comments/update-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                            parameterWithName("commentId").description("수정할 댓글의 id")
                        ),
                        requestFields(
                            fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 댓글 내용")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 삭제 API")
    void deleteComment() throws Exception {
        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/comments/{commentId}", 1L));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("comments/delete-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("commentId").description("삭제할 댓글의 id")
                        )
                ));
    }
}
