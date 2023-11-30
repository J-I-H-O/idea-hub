package com.jbnu.ideahub.competition.domain;

import com.jbnu.ideahub.category.domain.Category;
import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.member.domain.Member;
import com.jbnu.ideahub.member.domain.MemberCompetition;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Competition(Member host, Category category, List<Entry> entries, String title,
                       String content, CompetitionStatus status, String place,
                       RegistrationDatetime registrationDatetime, CompetitionDatetime competitionDatetime,
                       List<MemberCompetition> memberCompetitions, DatetimeMetadata datetimeMetadata) {
        this.host = host;
        this.category = category;
        this.entries = entries;
        this.title = title;
        this.content = content;
        this.status = status;
        this.place = place;
        this.registrationDatetime = registrationDatetime;
        this.competitionDatetime = competitionDatetime;
        this.memberCompetitions = memberCompetitions;
        this.datetimeMetadata = datetimeMetadata;
    }
}
