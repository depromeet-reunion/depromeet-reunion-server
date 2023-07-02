package com.depromeet.reunion.server.domain.post.entity;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.common.BaseTimeEntity;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "post")
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE id = ?")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("false")
    @Column(nullable = false)
    private boolean deleted;

    @ColumnDefault("0")
    @Column(name = "comment_count", nullable = false)
    private Integer commentCount = 0;
    @ColumnDefault("0")
    @Column(name = "like_count", nullable = false)
    private Integer likeCount = 0;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // == 게시글 삭제하면 달려있는 이미지 모두 삭제 == //
    @OneToMany(mappedBy = "post")
    private List<ImageFile> imageFiles = new ArrayList<>();

    // == 게시글 삭제하면 달려있는 댓글 모두 삭제 == //
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content, List<ImageFile> imageFiles, Member member, Board board) {
        this.title = title;
        this.content = content;
        this.imageFiles = imageFiles;
        this.member = member;
        this.board = board;
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    public void likePost() {
        this.likeCount += 1;
    }

    public void unlikePost() {
        this.likeCount -= 1;
    }
}
