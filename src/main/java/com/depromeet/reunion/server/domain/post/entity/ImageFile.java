package com.depromeet.reunion.server.domain.post.entity;

import com.depromeet.reunion.server.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "image_file")
public class ImageFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(nullable = false, name = "is_main")
    private boolean isMain; //  대표이미지 여부

    @Column(nullable = false)
    private String imgUrl; // 이미지 경로

    @ColumnDefault("false")
    @Column(nullable = false)
    protected boolean deleted;
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
