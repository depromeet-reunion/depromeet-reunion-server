package com.depromeet.reunion.server.domain.post.dto.request;

import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String content;


    public Post toEntity(Member member, Board board, List<MultipartFile> imageFiles) {

        return Post.builder()
                .title(title)
                .content(content)
                .board(board)
                .member(member)
                .build();
    }
}
