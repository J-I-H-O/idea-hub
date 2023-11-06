package com.jbnu.ideahub.comment.domain;

import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> comments = new ArrayList<>();

    @Lob
    @Column(nullable = false)
    private String Content;

    @Embedded
    private DatetimeMetadata datetimeMetadata;
}
