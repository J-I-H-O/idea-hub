package com.jbnu.ideahub.entry.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Prize {

    private String prizeName;
    private int prizePriority;

    public Prize(final String prizeName, final int prizePriority) {
        this.prizeName = prizeName;
        this.prizePriority = prizePriority;
    }
}
