package com.jbnu.ideahub.domain.notification.entity;

import com.jbnu.ideahub.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class NotificationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
