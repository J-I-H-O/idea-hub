package com.jbnu.ideahub.document.member;

import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import com.jbnu.ideahub.member.dto.request.MemberCreateRequest;
import com.jbnu.ideahub.member.dto.request.MemberUpdateRequest;
import com.jbnu.ideahub.member.dto.response.MemberResponse;
import com.jbnu.ideahub.member.presentation.MemberController;
import com.jbnu.ideahub.member.application.MemberService;
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
import static com.jbnu.ideahub.document.utils.DocumentFormatGenerator.getDatetimeFormat;
import static com.jbnu.ideahub.document.utils.DocumentFormatGenerator.getEnumFormat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerRestdocsTest extends RestdocsTestController {

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원 등록 API")
    void createMember() throws Exception {
        // given
        MemberCreateRequest request = MemberCreateRequest
                .builder()
                .studentId("20231124")
                .password("password123")
                .name("지호")
                .email("jiho@test.com")
                .github("github.com/J-I-H-O")
                .status(MemberStatus.ACTIVE)
                .role(MemberRole.MEMBER)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isCreated())
                .andDo(document("members/create-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("studentId").type(JsonFieldType.STRING).description("회원 학번"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                fieldWithPath("github").type(JsonFieldType.STRING).optional().description("회원 개인의 깃허브 주소"),
                                fieldWithPath("status").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 활성화 여부"),
                                fieldWithPath("role").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 권한")
                        )
                ));
    }

    @Test
    @DisplayName("전체 회원 목록 조회 API")
    void getMembers() throws Exception {
        // given
        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        MemberResponse response1 = MemberResponse
                .builder()
                .id(1L)
                .studentId("20231124")
                .name("지호")
                .email("jiho@test.com")
                .github("github.com/J-I-H-O")
                .status(MemberStatus.ACTIVE)
                .role(MemberRole.MEMBER)
                .datetimeMetadata(datetimeMetadataDto)
                .build();

        MemberResponse response2 = MemberResponse
                .builder()
                .id(2L)
                .studentId("202341241")
                .name("호지")
                .email("hoji@test.com")
                .github("github.com/H-O-J-I")
                .status(MemberStatus.DELETED)
                .role(MemberRole.MEMBER)
                .datetimeMetadata(datetimeMetadataDto)
                .build();

        given(memberService.findAll())
                .willReturn(List.of(response1, response2));

        // when
        ResultActions result = mockMvc.perform(get("/members"));

        // then
        result.andExpect(status().isOk())
                .andDo(document("members/get-members",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("회원 id"),
                                fieldWithPath("data[].studentId").type(JsonFieldType.STRING).description("회원 학번"),
                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("회원 이름"),
                                fieldWithPath("data[].email").type(JsonFieldType.STRING).description("회원 이메일"),
                                fieldWithPath("data[].github").type(JsonFieldType.STRING).optional().description("회원 개인의 깃허브 주소"),
                                fieldWithPath("data[].status").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 활성화 여부"),
                                fieldWithPath("data[].role").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 권한"),
                                fieldWithPath("data[].datetimeMetadata").description("등록 및 수정 시각 정보"),
                                fieldWithPath("data[].datetimeMetadata.createdAt").type(JsonFieldType.STRING).attributes(getDatetimeFormat()).description("회원 최초 등록 시각"),
                                fieldWithPath("data[].datetimeMetadata.updatedAt").type(JsonFieldType.STRING).attributes(getDatetimeFormat()).description("회원 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("회원 상세 정보 조회 API")
    void getMemberById() throws Exception {
        // given
        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        MemberResponse response = MemberResponse
                .builder()
                .id(1L)
                .studentId("20231124")
                .name("지호")
                .email("jiho@test.com")
                .github("github.com/J-I-H-O")
                .status(MemberStatus.ACTIVE)
                .role(MemberRole.MEMBER)
                .datetimeMetadata(datetimeMetadataDto)
                .build();

        given(memberService.findById(anyLong()))
                .willReturn(response);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders
                .get("/members/{memberId}", 1L));

        // then
        result.andExpect(status().isOk())
                .andDo(document("members/get-member-by-id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("상세 조회할 회원의 id")
                        ),
                        responseFields(
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("회원 id"),
                                fieldWithPath("data.studentId").type(JsonFieldType.STRING).description("회원 학번"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("회원 이름"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                fieldWithPath("data.github").type(JsonFieldType.STRING).optional().description("회원 개인의 깃허브 주소"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 활성화 여부"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 권한"),
                                fieldWithPath("data.datetimeMetadata").description("등록 및 수정 시각 정보"),
                                fieldWithPath("data.datetimeMetadata.createdAt").type(JsonFieldType.STRING).attributes(getDatetimeFormat()).description("회원 최초 등록 시각"),
                                fieldWithPath("data.datetimeMetadata.updatedAt").type(JsonFieldType.STRING).attributes(getDatetimeFormat()).description("회원 최종 수정 시각")
                        )
                ));
    }

    @Test
    @DisplayName("회원 수정 API")
    void updateMember() throws Exception {
        // given
        MemberUpdateRequest request = MemberUpdateRequest
                .builder()
                .studentId("20231124")
                .password("password123")
                .name("지호")
                .email("jiho@test.com")
                .github("github.com/J-I-H-O")
                .status(MemberStatus.ACTIVE)
                .role(MemberRole.MEMBER)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders
                .patch("/members/{memberId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonRequest));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("members/update-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("수정할 회원의 id")
                        ),
                        requestFields(
                                fieldWithPath("studentId").type(JsonFieldType.STRING).description("회원 학번"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                fieldWithPath("github").type(JsonFieldType.STRING).optional().description("회원 개인의 깃허브 주소"),
                                fieldWithPath("status").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 활성화 여부"),
                                fieldWithPath("role").type(JsonFieldType.STRING).attributes(getEnumFormat()).description("회원 권한")
                        )
                ));
    }

    @Test
    @DisplayName("회원 삭제 API")
    void deleteMember() throws Exception {
        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders
                .delete("/members/{memberId}", 1L));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("members/delete-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("삭제할 회원의 id")
                        )
                ));
    }
}
