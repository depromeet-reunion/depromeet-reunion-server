package com.depromeet.reunion.server.domain.post.entity;

import com.depromeet.reunion.server.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "image_file")
public class ImageFile extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @Column(nullable = false, name = "is_main")
    private boolean isMain; //  대표이미지 여부

    @Column(nullable = false)
    private String imgUrl; // 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public ImageFile(boolean isMain, String imgUrl, Post post) {
        this.isMain = isMain;
        this.imgUrl = imgUrl;
        this.post = post;
    }
}
