package com.jbnu.ideahub.domain.comment.entity;

import com.jbnu.ideahub.domain.common.BaseEntity;
import com.jbnu.ideahub.domain.entry.entity.Entry;
import com.jbnu.ideahub.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Comment extends BaseEntity {

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

    @Column(nullable = false)
    private String Content;
}
