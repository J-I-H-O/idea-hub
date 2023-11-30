package com.jbnu.ideahub.member.domain;

import com.jbnu.ideahub.common.domain.EnumType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberRole implements EnumType {
    ADMIN("관리자"),
    MEMBER("일반 사용자");

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
