package com.depromeet.reunion.server.domain.comment.entity;

import com.depromeet.reunion.server.domain.common.BaseEntity;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment (Post post, Member member, String content) {
        this.post = post;
        this.member = member;
        this.content = content;
    }

}
