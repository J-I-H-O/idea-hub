package com.jbnu.ideahub.entry.domain;

import com.jbnu.ideahub.comment.domain.Comment;
import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.competition.domain.Competition;
import com.jbnu.ideahub.member.domain.MemberEntry;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
