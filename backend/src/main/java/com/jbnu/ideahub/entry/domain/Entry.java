package com.jbnu.ideahub.entry.domain;

import com.jbnu.ideahub.comment.domain.Comment;
import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.competition.domain.Competition;
import com.jbnu.ideahub.entry.dto.PrizeDto;
import com.jbnu.ideahub.entry.dto.request.EntryUpdateRequest;
import com.jbnu.ideahub.member.domain.MemberEntry;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Builder
    public Entry(final Competition competition, final String title, final String content,
                 final EntryStatus status, final String github, final Prize prize, final List<Comment> comments,
                 final List<MemberEntry> userEntries, final DatetimeMetadata datetimeMetadata) {
        Assert.hasText(title, "title은 빈 값일 수 없습니다.");
        Assert.hasText(content, "content는 빈 값일 수 없습니다.");
        Assert.notNull(status, "status는 NULL일 수 없습니다.");
        Assert.notNull(datetimeMetadata, "datetimeMetadata는 NULL일 수 없습니다.");

        this.competition = competition;
        this.title = title;
        this.content = content;
        this.status = status;
        this.github = github;
        this.prize = prize;
        this.comments = comments;
        this.userEntries = userEntries;
        this.datetimeMetadata = datetimeMetadata;
    }

    public void update(Competition competition, EntryUpdateRequest request) {
        this.competition = competition;
        this.title = request.getTitle();
        this.content = request.getContent();
        this.status = request.getStatus();
        this.github = request.getGithub();
        this.prize = Optional.ofNullable(request.getPrize())
                .map(PrizeDto::toEntity)
                .orElse(null);

        datetimeMetadata.update();
    }
}
