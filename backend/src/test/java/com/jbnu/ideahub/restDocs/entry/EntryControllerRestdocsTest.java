package com.jbnu.ideahub.restDocs.entry;

import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import com.jbnu.ideahub.entry.dto.request.EntryRequest;
import com.jbnu.ideahub.entry.presentation.EntryController;
import com.jbnu.ideahub.entry.service.EntryService;
import com.jbnu.ideahub.restDocs.common.ControllerRestdocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentRequest;
import static com.jbnu.ideahub.restDocs.utils.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntryController.class)
public class EntryControllerRestdocsTest extends ControllerRestdocsTest {

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
}
