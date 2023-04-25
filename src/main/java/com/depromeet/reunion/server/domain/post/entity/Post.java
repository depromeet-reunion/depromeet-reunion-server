package com.depromeet.reunion.server.domain.post.entity;

import com.depromeet.reunion.server.domain.common.BaseEntity;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.post.dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
@SQLDelete(sql = "UPDATE post SET isDeleted = true WHERE id = ?")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore // fetchType.lazy -> 필요할때만 조회하기때문에 비어있는 객체 serialize하려고해서 문제 생김
    private Board board;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @OrderBy("id asc")
//    @OrderBy('id asc')
    private List<Comment> commentList = new ArrayList<>();

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }
}
