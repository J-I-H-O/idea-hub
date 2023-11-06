package com.jbnu.ideahub.comment.dto.response;

import com.jbnu.ideahub.comment.domain.Comment;
import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {

    private final Long id;
    private final Long memberId;
    private final Long entryId;
    private final String content;
    private final DatetimeMetadataDto datetimeMetadataResponse;

    public static CommentResponse of(
            final Comment comment,
            final Member member,
            final Entry entry
    ) {
        DatetimeMetadataDto datetimeMetadataResponse = DatetimeMetadataDto.createDatetimeMetadataResponse(
                comment.getDatetimeMetadata().getCreatedAt(),
                comment.getDatetimeMetadata().getUpdatedAt()
        );

        return new CommentResponse(
                comment.getId(),
                member.getId(),
                entry.getId(),
                comment.getContent(),
                datetimeMetadataResponse
        );
    }
}
