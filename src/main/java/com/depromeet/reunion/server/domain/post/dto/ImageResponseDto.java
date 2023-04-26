package com.depromeet.reunion.server.domain.post.dto;

import com.depromeet.reunion.server.domain.member.dto.MemberSimpleResponseDto;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponseDto {
    private String imgUrl;
    private boolean isMain;

    public static ImageResponseDto of (ImageFile imageFile) {
        return ImageResponseDto.builder()
                .imgUrl(imageFile.getImgUrl())
                .isMain(imageFile.isMain())
                .build();
    }
}
