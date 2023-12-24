package com.jbnu.ideahub.notification.domain;

import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
