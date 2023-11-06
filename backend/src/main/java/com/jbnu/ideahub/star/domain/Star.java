package com.jbnu.ideahub.star.domain;

import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Star {

    @EmbeddedId
    private StarId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entryId")
    @JoinColumn(name = "entry_id")
    private Entry entry;
}
