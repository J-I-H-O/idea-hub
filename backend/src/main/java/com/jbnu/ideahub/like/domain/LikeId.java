package com.jbnu.ideahub.like.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class LikeId implements Serializable {

    private Long memberId;
    private Long entryId;
}
