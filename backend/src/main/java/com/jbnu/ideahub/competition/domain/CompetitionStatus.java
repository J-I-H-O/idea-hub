package com.jbnu.ideahub.competition.domain;

import com.jbnu.ideahub.common.domain.EnumType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CompetitionStatus implements EnumType {

    PREPARING("준비중"),
    REGISTERING("접수중"), 
    ONGOING("진행중"),
    FINISHED("종료");

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
