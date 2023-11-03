package com.jbnu.ideahub.domain.member.entity;

import com.jbnu.ideahub.domain.entry.entity.Entry;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class MemberEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;
}
