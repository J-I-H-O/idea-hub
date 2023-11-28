package com.jbnu.ideahub.member.domain;

import com.jbnu.ideahub.common.domain.EnumType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberStatus implements EnumType {

    ACTIVE("활성"),
    DELETED("삭제됨");

    private final String description;

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
