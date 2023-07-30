package com.depromeet.reunion.server.domain.post.dto.request;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class PostRequestDto {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @Schema(description = "게시글 이미지", type = "Multipart", format = "binary", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private MultipartFile imageFile;


    public Post toEntity(Member member, Board board) {

        return Post.builder()
                .title(title)
                .content(content)
                .board(board)
                .member(member)
                .build();
    }
}