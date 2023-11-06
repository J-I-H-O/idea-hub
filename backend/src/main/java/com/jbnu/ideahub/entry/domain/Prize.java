package com.jbnu.ideahub.entry.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Prize {

    private String prizeName;
    private int prizePriority;
}
