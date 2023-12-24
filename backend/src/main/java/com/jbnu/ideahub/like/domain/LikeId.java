package com.jbnu.ideahub.like.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LikeId implements Serializable {

    private Long memberId;
    private Long entryId;
}
