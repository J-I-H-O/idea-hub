package com.jbnu.ideahub.domain.entry.entity;

import com.jbnu.ideahub.domain.comment.entity.Comment;
import com.jbnu.ideahub.domain.common.DatetimeMetadata;
import com.jbnu.ideahub.domain.competition.entity.Competition;
import com.jbnu.ideahub.domain.member.entity.MemberEntry;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EntryStatus status;

    private String github;

    @Embedded
    private Prize prize;

    @OneToMany(mappedBy = "entry")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "entry")
    private List<MemberEntry> userEntries = new ArrayList<>();

    @Embedded
    private DatetimeMetadata datetimeMetadata;
}
