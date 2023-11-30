package com.jbnu.ideahub.member.dto.response;

import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.member.domain.Member;
import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberResponse {

    private final Long id;
    private final String studentId;
    private final String name;
    private final String email;
    private final String github;
    private final MemberStatus status;
    private final MemberRole role;
    private final DatetimeMetadataDto datetimeMetadata;

    public static MemberResponse of(final Member member) {
        DatetimeMetadataDto datetimeMetadataDto = DatetimeMetadataDto.createDatetimeMetadataResponse(
                member.getDatetimeMetadata().getCreatedAt(),
                member.getDatetimeMetadata().getUpdatedAt()
        );

        return new MemberResponse(
                member.getId(),
                member.getStudentId(),
                member.getName(),
                member.getEmail(),
                member.getGithub(),
                member.getStatus(),
                member.getRole(),
                datetimeMetadataDto
        );
    }
}
