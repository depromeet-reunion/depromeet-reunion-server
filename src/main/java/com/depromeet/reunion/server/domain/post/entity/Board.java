package com.depromeet.reunion.server.domain.post.entity;
import com.depromeet.reunion.server.domain.common.BaseTimeEntity;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String boardName;

    @ColumnDefault("false")
    @Column(nullable = false)
    protected boolean deleted;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Board (String boardName, Member member) {
        this.boardName = boardName;
        this.member = member;
    }
}
