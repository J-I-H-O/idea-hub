package com.jbnu.ideahub.comment.dto.response;

import com.jbnu.ideahub.comment.domain.Comment;
import com.jbnu.ideahub.common.dto.DatetimeMetadataDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CommentResponse {

    private final Long id;
    private final Long memberId;
    private final Long entryId;
    private final Long parentId;
    private final String content;
    private final DatetimeMetadataDto datetimeMetadata;

    public static CommentResponse of(
            final Comment comment
    ) {
        DatetimeMetadataDto datetimeMetadata = DatetimeMetadataDto.createDatetimeMetadataResponse(
                comment.getDatetimeMetadata().getCreatedAt(),
                comment.getDatetimeMetadata().getUpdatedAt()
        );

        return new CommentResponse(
                comment.getId(),
                comment.getMember().getId(),
                comment.getEntry().getId(),
                comment.getParent().getId(),
                comment.getContent(),
                datetimeMetadata
        );
    }
}
