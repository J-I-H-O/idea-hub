package com.jbnu.ideahub.restDocs.competition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.competition.dto.CompetitionDatetimeDto;
import com.jbnu.ideahub.competition.dto.RegistrationDatetimeDto;
import com.jbnu.ideahub.competition.dto.request.CompetitionRequest;
import com.jbnu.ideahub.competition.dto.response.CompetitionResponse;
import com.jbnu.ideahub.competition.presentation.CompetitionController;
import com.jbnu.ideahub.competition.service.CompetitionService;
import com.jbnu.ideahub.restDocs.common.ControllerRestdocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompetitionController.class)
public class CompetitionControllerRestdocsTest extends ControllerRestdocsTest {

    @MockBean
    private CompetitionService competitionService;

    @Test
    @DisplayName("대회 등록 API")
    void createCompetition() throws Exception {
        // given
        CompetitionRequest request = CompetitionRequest
                .builder()
                .title("대회 이름짓기 대회")
                .content("누가 제일 멋진 이름을 지을까요?")
                .status(CompetitionStatus.ONGOING)
                .place("우리집 앞마당")
                .registrationDatetime(
                        RegistrationDatetimeDto.createdRegistrationDatetime(
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                )
                .competitionDatetime(
                        CompetitionDatetimeDto.createdRegistrationDatetime(
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                )
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/competitions")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        result.andExpect(status().isCreated())
                .andDo(document("competitions/create-competition",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("대회 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("대회 상세 설명"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("대회 진행 상태"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("대회 진행 장소"),
                                fieldWithPath("registrationDatetime.start").type(JsonFieldType.STRING).description("대회 참가 신청 시작 시각"),
                                fieldWithPath("registrationDatetime.end").type(JsonFieldType.STRING).description("대회 참가 신청 마감 시각"),
                                fieldWithPath("competitionDatetime.start").type(JsonFieldType.STRING).description("대회 시작 시각"),
                                fieldWithPath("competitionDatetime.end").type(JsonFieldType.STRING).description("대회 종료 시각")
                        )
                ));
    }

    @Test
    @DisplayName("대회 전체 목록 조회 API")
    void getCompetitions() throws Exception {
        // given
        RegistrationDatetimeDto registrationDatetimeDto = RegistrationDatetimeDto.createdRegistrationDatetime(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        CompetitionDatetimeDto competitionDatetimeDto = CompetitionDatetimeDto.createdRegistrationDatetime(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        CompetitionResponse response1 = CompetitionResponse
                .builder()
                .id(1L)
                .hostId(1L)
                .categoryId(1L)
                .title("대회 타이틀1")
                .content("대회 설명1")
                .status(CompetitionStatus.ONGOING)
                .place("우리집 앞마당")
                .registrationDatetime(registrationDatetimeDto)
                .competitionDatetime(competitionDatetimeDto)
                .datetimeMetadata(datetimeMetadataDto)
                .build();

        CompetitionResponse response2 = CompetitionResponse
                .builder()
                .id(2L)
                .hostId(1L)
                .categoryId(1L)
                .title("대회 타이틀2")
                .content("대회 설명2")
                .status(CompetitionStatus.FINISHED)
                .place("우리집 앞마당")
                .registrationDatetime(registrationDatetimeDto)
                .competitionDatetime(competitionDatetimeDto)
                .datetimeMetadata(datetimeMetadataDto)
                .build();

        given(competitionService.findAll())
                .willReturn(List.of(response1, response2));

        // when
        ResultActions result = mockMvc.perform(get("/competitions"));

        // then
        result.andExpect(status().isOk())
                .andDo(document("competitions/get-competitions",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("대회 id"),
                                fieldWithPath("data[].hostId").type(JsonFieldType.NUMBER).description("대회를 등록한 회원의 id"),
                                fieldWithPath("data[].categoryId").type(JsonFieldType.NUMBER).description("대회 카테고리의 id"),
                                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("대회 이름"),
                                fieldWithPath("data[].content").type(JsonFieldType.STRING).description("대회 상세 설명"),
                                fieldWithPath("data[].status").type(JsonFieldType.STRING).description("대회 진행 상태"),
                                fieldWithPath("data[].place").type(JsonFieldType.STRING).description("대회 진행 장소"),
                                fieldWithPath("data[].registrationDatetime.start").type(JsonFieldType.STRING).description("대회 참가 신청 시작 시각"),
                                fieldWithPath("data[].registrationDatetime.end").type(JsonFieldType.STRING).description("대회 참가 신청 마감 시각"),
                                fieldWithPath("data[].competitionDatetime.start").type(JsonFieldType.STRING).description("대회 시작 시각"),
                                fieldWithPath("data[].competitionDatetime.end").type(JsonFieldType.STRING).description("대회 종료 시각"),
                                fieldWithPath("data[].datetimeMetadata.createdAt").type(JsonFieldType.STRING).description("대회 최초 등록 시각"),
                                fieldWithPath("data[].datetimeMetadata.updatedAt").type(JsonFieldType.STRING).description("대회 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("대회 상세 정보 조회 API")
    void getCompetitionById() throws Exception {
        // given
        RegistrationDatetimeDto registrationDatetimeDto = RegistrationDatetimeDto.createdRegistrationDatetime(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        CompetitionDatetimeDto competitionDatetimeDto = CompetitionDatetimeDto.createdRegistrationDatetime(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        CompetitionResponse response = CompetitionResponse
                .builder()
                .id(1L)
                .hostId(1L)
                .categoryId(1L)
                .title("대회 타이틀1")
                .content("대회 설명1")
                .status(CompetitionStatus.ONGOING)
                .place("우리집 앞마당")
                .registrationDatetime(registrationDatetimeDto)
                .competitionDatetime(competitionDatetimeDto)
                .datetimeMetadata(datetimeMetadataDto)
                .build();

        given(competitionService.findById(anyLong()))
                .willReturn(response);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/competitions/{competitionId}", 1L));

        // then
        result.andExpect(status().isOk())
                .andDo(document("competitions/get-competition-by-id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("competitionId").description("상세 조회할 대회의 id")
                        ),
                        responseFields(
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("대회 id"),
                                fieldWithPath("data.hostId").type(JsonFieldType.NUMBER).description("대회를 등록한 회원의 id"),
                                fieldWithPath("data.categoryId").type(JsonFieldType.NUMBER).description("대회 카테고리의 id"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("대회 이름"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("대회 상세 설명"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("대회 진행 상태"),
                                fieldWithPath("data.place").type(JsonFieldType.STRING).description("대회 진행 장소"),
                                fieldWithPath("data.registrationDatetime.start").type(JsonFieldType.STRING).description("대회 참가 신청 시작 시각"),
                                fieldWithPath("data.registrationDatetime.end").type(JsonFieldType.STRING).description("대회 참가 신청 마감 시각"),
                                fieldWithPath("data.competitionDatetime.start").type(JsonFieldType.STRING).description("대회 시작 시각"),
                                fieldWithPath("data.competitionDatetime.end").type(JsonFieldType.STRING).description("대회 종료 시각"),
                                fieldWithPath("data.datetimeMetadata.createdAt").type(JsonFieldType.STRING).description("대회 최초 등록 시각"),
                                fieldWithPath("data.datetimeMetadata.updatedAt").type(JsonFieldType.STRING).description("대회 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("대회 수정 API")
    void updateCompetition() throws Exception {
        // given
        CompetitionRequest request = CompetitionRequest
                .builder()
                .title("대회 이름짓기 대회")
                .content("누가 제일 멋진 이름을 지을까요?")
                .status(CompetitionStatus.ONGOING)
                .place("우리집 앞마당")
                .registrationDatetime(
                        RegistrationDatetimeDto.createdRegistrationDatetime(
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                )
                .competitionDatetime(
                        CompetitionDatetimeDto.createdRegistrationDatetime(
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                )
                .build();
        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/competitions/{competitionId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("uTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("competitions/update-competition",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("competitionId").description("수정할 대회의 id")
                        ),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("대회 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("대회 상세 설명"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("대회 진행 상태"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("대회 진행 장소"),
                                fieldWithPath("registrationDatetime.start").type(JsonFieldType.STRING).description("대회 참가 신청 시작 시각"),
                                fieldWithPath("registrationDatetime.end").type(JsonFieldType.STRING).description("대회 참가 신청 마감 시각"),
                                fieldWithPath("competitionDatetime.start").type(JsonFieldType.STRING).description("대회 시작 시각"),
                                fieldWithPath("competitionDatetime.end").type(JsonFieldType.STRING).description("대회 종료 시각")
                        )
                ));
    }

    @Test
    @DisplayName("대회 삭제 API")
    void deleteCompetition() throws Exception {
        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/competitions/{competitionId}", 1L));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("competitions/delete-competition",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("competitionId").description("삭제할 대회의 id")
                        )
                ));
    }
}
