package com.jbnu.ideahub.domain.notification.entity;

import com.jbnu.ideahub.domain.common.DatetimeMetadata;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "notification")
    private List<NotificationMember> notificationMembers = new ArrayList<>();

    @Embedded
    private DatetimeMetadata datetimeMetadata;
}
