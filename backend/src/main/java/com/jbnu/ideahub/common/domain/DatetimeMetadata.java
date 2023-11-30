package com.jbnu.ideahub.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DatetimeMetadata {

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public void entityCreated() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void entityUpdated() {
        updatedAt = LocalDateTime.now();
    }
}
