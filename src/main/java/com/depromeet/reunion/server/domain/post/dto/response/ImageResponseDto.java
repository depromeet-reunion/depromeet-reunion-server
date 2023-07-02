package com.depromeet.reunion.server.domain.post.dto.response;

import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "이미지 응답 모델")
public class ImageResponseDto {
    @Schema(description = "이미지 경로")
    private String imgUrl;
    @Schema(description = "대표 이미지면 true")
    private boolean isMain;

    public static ImageResponseDto fromEntity(ImageFile imageFile) {
        return ImageResponseDto.builder()
                .imgUrl(imageFile.getImgUrl())
                .build();
    }
}