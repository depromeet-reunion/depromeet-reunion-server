package com.depromeet.reunion.server.domain.post.dto.response;

import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponseDto {
    private String imgUrl;
    private boolean isMain;

    public static ImageResponseDto fromEntity(ImageFile imageFile) {
        return ImageResponseDto.builder()
                .imgUrl(imageFile.getImgUrl())
                .isMain(imageFile.isMain())
                .build();
    }
}
