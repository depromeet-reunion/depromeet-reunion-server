package com.depromeet.reunion.server.domain.post.entity;

import com.depromeet.reunion.server.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "image_file")
@Getter
public class ImageFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(nullable = false)
    private String imgUrl; // 이미지 경로

    @ColumnDefault("false")
    @Column(nullable = false)
    protected boolean deleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public ImageFile(String imgUrl, Post post) {
        this.imgUrl = imgUrl;
        this.post = post;
    }
}