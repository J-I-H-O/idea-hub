package com.jbnu.ideahub.document.enumDocs;

import com.jbnu.ideahub.common.domain.EnumType;
import com.jbnu.ideahub.common.dto.ApiResponse;
import com.jbnu.ideahub.competition.domain.CompetitionStatus;
import com.jbnu.ideahub.entry.domain.EntryStatus;
import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/docs/enums")
public class EnumController {

    @GetMapping
    public ResponseEntity<?> findEnums() {
        Map<String, String> competitionStatus = getDocs(CompetitionStatus.values());
        Map<String, String> entryStatus = getDocs(EntryStatus.values());
        Map<String, String> memberRole = getDocs(MemberRole.values());
        Map<String, String> memberStatus = getDocs(MemberStatus.values());

        return ResponseEntity.ok(
                EnumDocs.builder()
                        .competitionStatus(competitionStatus)
                        .entryStatus(entryStatus)
                        .memberRole(memberRole)
                        .memberStatus(memberStatus)
                        .build()
        );
    }

    private Map<String, String> getDocs(EnumType[] enumTypes) {
        return Arrays.stream(enumTypes)
                .collect(Collectors.toMap(EnumType::getName, EnumType::getDescription));
    }
}
