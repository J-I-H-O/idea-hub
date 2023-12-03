package com.jbnu.ideahub.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class DatetimeMetadata {

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected DatetimeMetadata() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static DatetimeMetadata create() {
        return new DatetimeMetadata();
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
