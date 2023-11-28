package com.jbnu.ideahub.entry.domain;

import com.jbnu.ideahub.common.domain.EnumType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EntryStatus implements EnumType {

    SETTING_UP("개발 준비중"),
    DEVELOPING("개발중"),
    DONE("개발 완료");
    
    private final String description;

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
