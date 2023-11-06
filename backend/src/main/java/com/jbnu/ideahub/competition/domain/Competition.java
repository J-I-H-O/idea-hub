package com.jbnu.ideahub.competition.domain;

import com.jbnu.ideahub.category.domain.Category;
import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.member.domain.Member;
import com.jbnu.ideahub.member.domain.MemberCompetition;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member host;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "competition")
    private List<Entry> entries = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CompetitionStatus status;

    private String place;

    @Embedded
    private RegistrationDatetime registrationDatetime;

    @Embedded
    private CompetitionDatetime competitionDatetime;

    @OneToMany(mappedBy = "competition")
    private List<MemberCompetition> memberCompetitions = new ArrayList<>();

    @Embedded
    private DatetimeMetadata datetimeMetadata;
}
