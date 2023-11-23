package com.jbnu.ideahub.restDocs.entry;

import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import com.jbnu.ideahub.entry.dto.request.EntryRequest;
import com.jbnu.ideahub.entry.dto.response.EntryResponse;
import com.jbnu.ideahub.entry.presentation.EntryController;
import com.jbnu.ideahub.entry.service.EntryService;
import com.jbnu.ideahub.restDocs.common.RestdocsTestController;
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

import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntryController.class)
public class EntryControllerRestdocsTest extends RestdocsTestController {

    @MockBean
    private EntryService entryService;

    @Test
    @DisplayName("작품 등록 API")
    void createEntry() throws Exception {
        // given
        PrizeDto prize = new PrizeDto("대상", 1);
        EntryRequest request = EntryRequest
                .builder()
                .competitionId(1L)
                .title("작품1")
                .content("멋진 작품입니다.")
                .status(EntryStatus.DEVELOPING)
                .github("www.github.com/J-I-H-O")
                .prize(prize)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isCreated())
                .andDo(document("entries/create-entry",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("competitionId").type(JsonFieldType.NUMBER).optional().description("해당 작품이 등록된 대회의 id. 대회에 등록한 것이 아닌 작품만 별개로 등록한 경우 null"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("작품 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("작품 상세 정보"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("작품 진행 상태"),
                                fieldWithPath("github").type(JsonFieldType.STRING).description("작품 깃허브 주소"),
                                fieldWithPath("prize").optional().description("작품 수상 정보. 수상 작품이 없다면 null"),
                                fieldWithPath("prize.prizeName").type(JsonFieldType.STRING).optional().description("수상한 상의 이름"),
                                fieldWithPath("prize.prizePriority").type(JsonFieldType.NUMBER).optional().description("수상한 상의 우선순위. 상격이 높을수록 낮은 숫자를 가짐")
                        )
                ));
    }

    @Test
    @DisplayName("전체 작품 목록 조회 API")
    void getEntries() throws Exception {
        // given
        PrizeDto prize = new PrizeDto("대상", 1);
        DatetimeMetadataDto datetimeMetadata = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        EntryResponse response1 = EntryResponse
                .builder()
                .id(1L)
                .competitionId(null)
                .title("작품1")
                .content("작품 설명")
                .status(EntryStatus.DEVELOPING)
                .github("www.github.com/J-I-H-O")
                .prize(prize)
                .datetimeMetadata(datetimeMetadata)
                .build();

        EntryResponse response2 = EntryResponse
                .builder()
                .id(2L)
                .competitionId(null)
                .title("작품2")
                .content("작품 설명")
                .status(EntryStatus.DONE)
                .github("www.github.com/J-I-H-O")
                .prize(prize)
                .datetimeMetadata(datetimeMetadata)
                .build();

        given(entryService.findAll())
                .willReturn(List.of(response1, response2));

        // when
        ResultActions result = mockMvc.perform(get("/entries"));

        // then
        result.andExpect(status().isOk())
                .andDo(document("entries/get-entries",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("작품 id"),
                                fieldWithPath("data[].competitionId").type(JsonFieldType.NUMBER).optional().description("해당 작품이 등록된 대회의 id. 대회에 등록한 것이 아닌 작품만 별개로 등록한 경우 null"),
                                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("작품 제목"),
                                fieldWithPath("data[].content").type(JsonFieldType.STRING).description("작품 상세 정보"),
                                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("작품 진행 상태"),
                                fieldWithPath("data[].github").type(JsonFieldType.STRING).description("작품 깃허브 주소"),
                                fieldWithPath("data[].prize").optional().description("작품 수상 정보. 수상 작품이 없다면 null"),
                                fieldWithPath("data[].prize.prizeName").type(JsonFieldType.STRING).optional().description("수상한 상의 이름"),
                                fieldWithPath("data[].prize.prizePriority").type(JsonFieldType.NUMBER).optional().description("수상한 상의 우선순위. 상격이 높을수록 낮은 숫자를 가짐"),
                                fieldWithPath("data[].datetimeMetadata.createdAt").type(JsonFieldType.STRING).description("작품 최초 등록 시각"),
                                fieldWithPath("data[].datetimeMetadata.updatedAt").type(JsonFieldType.STRING).description("작품 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("작품 상세 정보 조회 API")
    void getEntityById() throws Exception {
        // given
        PrizeDto prize = new PrizeDto("대상", 1);
        DatetimeMetadataDto datetimeMetadata = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        EntryResponse response = EntryResponse
                .builder()
                .id(1L)
                .competitionId(null)
                .title("작품1")
                .content("작품 설명")
                .status(EntryStatus.DEVELOPING)
                .github("www.github.com/J-I-H-O")
                .prize(prize)
                .datetimeMetadata(datetimeMetadata)
                .build();

        given(entryService.findById(anyLong()))
                .willReturn(response);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/entries/{entryId}", 1L));

        // then
        result.andExpect(status().isOk())
                .andDo(document("entries/get-entry-by-id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("entryId").description("상세 조회할 작품의 id")
                        ),
                        responseFields(
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("작품 id"),
                                fieldWithPath("data.competitionId").type(JsonFieldType.NUMBER).optional().description("해당 작품이 등록된 대회의 id. 대회에 등록한 것이 아닌 작품만 별개로 등록한 경우 null"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("작품 제목"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("작품 상세 정보"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("작품 진행 상태"),
                                fieldWithPath("data.github").type(JsonFieldType.STRING).description("작품 깃허브 주소"),
                                fieldWithPath("data.prize").optional().description("작품 수상 정보. 수상 작품이 없다면 null"),
                                fieldWithPath("data.prize.prizeName").type(JsonFieldType.STRING).optional().description("수상한 상의 이름"),
                                fieldWithPath("data.prize.prizePriority").type(JsonFieldType.NUMBER).optional().description("수상한 상의 우선순위. 상격이 높을수록 낮은 숫자를 가짐"),
                                fieldWithPath("data.datetimeMetadata.createdAt").type(JsonFieldType.STRING).description("작품 최초 등록 시각"),
                                fieldWithPath("data.datetimeMetadata.updatedAt").type(JsonFieldType.STRING).description("작품 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("작품 수정 API")
    void updateEntry() throws Exception {
        // given
        PrizeDto prize = new PrizeDto("대상", 1);
        EntryRequest request = EntryRequest
                .builder()
                .competitionId(1L)
                .title("작품1")
                .content("멋진 작품입니다.")
                .status(EntryStatus.DEVELOPING)
                .github("www.github.com/J-I-H-O")
                .prize(prize)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/entries/{entryId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("entries/update-entry",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("entryId").description("수정할 작품의 id")
                        ),
                        requestFields(
                                fieldWithPath("competitionId").type(JsonFieldType.NUMBER).optional().description("해당 작품이 등록된 대회의 id. 대회에 등록한 것이 아닌 작품만 별개로 등록한 경우 null"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("작품 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("작품 상세 정보"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("작품 진행 상태"),
                                fieldWithPath("github").type(JsonFieldType.STRING).description("작품 깃허브 주소"),
                                fieldWithPath("prize").optional().description("작품 수상 정보. 수상 작품이 없다면 null"),
                                fieldWithPath("prize.prizeName").type(JsonFieldType.STRING).optional().description("수상한 상의 이름"),
                                fieldWithPath("prize.prizePriority").type(JsonFieldType.NUMBER).optional().description("수상한 상의 우선순위. 상격이 높을수록 낮은 숫자를 가짐")
                        )
                ));
    }

    @Test
    @DisplayName("작품 삭제 API")
    void deleteEntry() throws Exception {
        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders
                .delete("/entries/{entryId}", 1));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("entries/delete-entry",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("entryId").description("삭제할 작품의 id")
                        )
                ));
    }
}
