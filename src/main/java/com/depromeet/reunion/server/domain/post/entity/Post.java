package com.depromeet.reunion.server.domain.post.entity;

import com.depromeet.reunion.server.domain.common.BaseTimeEntity;
import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "post")
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE post_id = ?")
@Where(clause = "deleted = false")
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
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private ImageFile imageFile;

    // == 게시글 삭제하면 달려있는 댓글 모두 삭제 == //
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id asc")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content, Member member, Board board) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.board = board;
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    // 이미지 파일과의 연결 설정 (양방향 연관관계)
    public void setImageFile(ImageFile imageFile) {
        if (imageFile == null) {
            if (this.imageFile != null) {
                this.imageFile.setPost(null);
            }
        } else {
            imageFile.setPost(this);
        }
        this.imageFile = imageFile;
    }

    public void likePost() {
        this.likeCount += 1;
    }

    public void unlikePost() {
        this.likeCount -= 1;
    }

    public void addComment() {
        this.commentCount += 1;
    }

    public void deleteComment() {
        this.commentCount -= 1;
    }
}