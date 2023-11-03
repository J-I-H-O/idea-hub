package com.jbnu.ideahub.domain.member.entity;

import com.jbnu.ideahub.domain.competition.entity.Competition;
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
