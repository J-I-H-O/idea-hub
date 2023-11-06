package com.jbnu.ideahub.member.domain;

import com.jbnu.ideahub.competition.domain.Competition;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class MemberCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
