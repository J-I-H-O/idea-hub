package com.jbnu.ideahub.domain.notification.entity;

import com.jbnu.ideahub.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(mappedBy = "notification")
    private List<NotificationMember> notificationMembers = new ArrayList<>();
}
