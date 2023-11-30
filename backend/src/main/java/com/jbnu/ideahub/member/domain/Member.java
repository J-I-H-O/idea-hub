package com.jbnu.ideahub.member.domain;

import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.comment.domain.Comment;
import com.jbnu.ideahub.competition.domain.Competition;
import com.jbnu.ideahub.notification.domain.NotificationMember;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
