package com.jbnu.ideahub.document.enumDocs;

import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.document.common.RestdocsTestController;
import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;

import static com.jbnu.ideahub.document.enumDocs.EnumSnippetGenerator.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnumController.class)
public class EnumControllerRestdocsTest extends RestdocsTestController {

    @Test
    @DisplayName("Enum 타입 설명")
    public void enums() throws Exception {
        ResultActions result = mockMvc.perform(get("/docs/enums"));
        result.andExpect(status().isOk())
                .andDo(document("enum",
                        generateEnumSnippet("enum", beneathPath("competitionStatus").withSubsectionId("competitionStatus"),
                                attributes(key("title").value("대회 진행 상태")),
                                enumConvertFieldDescriptor(CompetitionStatus.values())
                        ),
                        generateEnumSnippet("enum", beneathPath("entryStatus").withSubsectionId("entryStatus"),
                                attributes(key("title").value("작품 개발 상태")),
                                enumConvertFieldDescriptor(EntryStatus.values())
                        ),
                        generateEnumSnippet("enum", beneathPath("memberRole").withSubsectionId("memberRole"),
                                attributes(key("title").value("회원 권한")),
                                enumConvertFieldDescriptor(MemberRole.values())
                        ),
                        generateEnumSnippet("enum", beneathPath("memberStatus").withSubsectionId("memberStatus"),
                                attributes(key("title").value("회원 상태")),
                                enumConvertFieldDescriptor(MemberStatus.values())
                        )
                ));
    }
}
