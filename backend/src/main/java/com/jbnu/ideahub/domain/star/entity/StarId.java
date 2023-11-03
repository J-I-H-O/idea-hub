package com.jbnu.ideahub.domain.star.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StarId implements Serializable {

    private Long memberId;
    private Long entryId;
}
