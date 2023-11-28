package com.jbnu.ideahub.document.enumDocs;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumDocs {
    
    // 문서화하고 싶은 모든 enum 명시
    Map<String, String> competitionStatus;
    Map<String, String> entryStatus;
    Map<String, String> memberRole;
    Map<String, String> memberStatus;
}
