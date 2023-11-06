package com.jbnu.ideahub.member.domain;

import com.jbnu.ideahub.entry.domain.Entry;
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
