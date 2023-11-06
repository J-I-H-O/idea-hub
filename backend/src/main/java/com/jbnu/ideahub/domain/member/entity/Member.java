package com.jbnu.ideahub.domain.member.entity;

import com.jbnu.ideahub.domain.comment.entity.Comment;
import com.jbnu.ideahub.domain.common.DatetimeMetadata;
import com.jbnu.ideahub.domain.competition.entity.Competition;
import com.jbnu.ideahub.domain.notification.entity.NotificationMember;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String studentId;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String github;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberRole role;

    @OneToMany(mappedBy = "member")
    private List<MemberEntry> memberEntries = new ArrayList<>();

    @OneToMany(mappedBy = "host")
    private List<Competition> competitions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberCompetition> memberCompetitions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<NotificationMember> notificationMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Embedded
    private DatetimeMetadata datetimeMetadata;
}
