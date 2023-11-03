package com.jbnu.ideahub.domain.entry.entity;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Prize {

    private String prizeName;

    private int prizePriority;
}
