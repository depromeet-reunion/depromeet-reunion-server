package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.common.AmazonS3Uploader;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.depromeet.reunion.server.domain.post.entity.PostLike;
import com.depromeet.reunion.server.domain.post.repository.BoardRepository;
import com.depromeet.reunion.server.domain.post.repository.ImageFileRepository;
import com.depromeet.reunion.server.domain.post.repository.PostLikeRepository;
import com.depromeet.reunion.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;
    private final ImageFileRepository imageFileRepository;
    private final AmazonS3Uploader amazonS3Uploader;


    @Override
    public List<PostListResponseDto> getPostsByBoard(Long boardId, Long memberId) {
        // board id 확인
        validateBoard(boardId);
        // board id로 게시글 리스트 반환
        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts.stream().map(post -> PostListResponseDto.fromEntity(post, memberId)).toList();
    }

    @Override
    public PostResponseDto getPostById(Long postId, Long memberId) {
        // postId 확인
        Post post = validatePost(postId);
        Optional<PostLike> byMemberAndPost = postLikeRepository.findByMemberIdAndPostId(memberId, postId);
        boolean isLiked = byMemberAndPost.isPresent();

        return PostResponseDto.fromEntity(post, memberId, isLiked);
    }

    @Override
    public PostResponseDto createPost(Long boardId, Long memberId, PostRequestDto postRequestDto, MultipartFile imagefile) {
        Board board = validateBoard(boardId);
        Member member = validateMember(memberId);

        Post post = postRepository.save(postRequestDto.toEntity(member, board));

        // 이미지 s3 업로드
        if (imagefile != null && !imagefile.isEmpty()) {
            try {
                String path = amazonS3Uploader.upload(imagefile, "images");
                ImageFile imageFile = imageFileRepository.save(ImageFile.builder().imgUrl(path).post(post).build());
                post.setImageFile(imageFile);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return PostResponseDto.fromEntity(post, member.getId());
    }

    @Override
    public void updatePost(Long postId, Long memberId, PostRequestDto postRequestDto, MultipartFile newImageFile) {
        Post post = validatePost(postId);
        if (post.getMember().getId().equals(memberId)) {
            ImageFile existingImage = post.getImageFile();
            // 이미지 변경 or 없었는데 추가
            if (newImageFile != null) {
                if (existingImage != null) {
                    // 기존 이미지 삭제
                    amazonS3Uploader.deleteS3(existingImage.getImgUrl());
                    imageFileRepository.deleteById(existingImage.getId());
                }

                try {
                    // 새 이미지 업로드
                    String newPath = amazonS3Uploader.upload(newImageFile, "images");
                    // 이미지 생성 및 경로 업데이트
                    ImageFile newImage = ImageFile.builder().imgUrl(newPath).build();
                    newImage = imageFileRepository.save(newImage);
                    post.setImageFile(newImage);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            } else {
                if (existingImage != null) {
                    // 기존 이미지 삭제
                    amazonS3Uploader.deleteS3(existingImage.getImgUrl());
                    imageFileRepository.deleteById(existingImage.getId());
                    post.setImageFile(null);
                }
            }

            // 내용 업데이트
            post.updatePost(postRequestDto);
            postRepository.save(post);
        }
    }

    @Override
    public void deletePost(Long postId, Long memberId) {
        Post post = validatePost(postId);
        if (post.getMember().getId().equals(memberId)) {
            // soft delete
            postRepository.deleteById(postId);
        }
    }

    @Override
    public void likePost(Long postId, Long memberId) {
        Post post = validatePost(postId);
        Member member = validateMember(memberId);

        // 이미 좋아요한 게시글인지 확인
        Optional<PostLike> byMemberAndPost = postLikeRepository.findByMemberIdAndPostId(memberId, postId);
        byMemberAndPost.ifPresentOrElse(
                postLike -> {
                    // 좋아요취소
                    postLikeRepository.delete(postLike);
                    post.unlikePost();
                },
                () -> {
                    // 좋아요 생성
                    postLikeRepository.save(PostLike.builder().member(member).post(post).build());
                    post.likePost();
                }
        );
    }


    @Override
    public List<PostListResponseDto> getMyPosts(Long memberId) {
        validateMember(memberId);
        List<Post> posts = postRepository.findByMemberId(memberId);
        return posts.stream().map(post -> PostListResponseDto.fromEntity(post, memberId)).toList();
    }


    private Board validateBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    private Post validatePost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    private Member validateMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }
}