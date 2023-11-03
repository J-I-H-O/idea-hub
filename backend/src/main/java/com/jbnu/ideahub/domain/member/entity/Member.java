package com.jbnu.ideahub.domain.member.entity;

import com.jbnu.ideahub.domain.comment.entity.Comment;
import com.jbnu.ideahub.domain.common.BaseEntity;
import com.jbnu.ideahub.domain.competition.entity.Competition;
import com.jbnu.ideahub.domain.notification.entity.NotificationMember;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int studentId;

    private String password;

    private String name;

    private String email;

    private String github;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
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
}
