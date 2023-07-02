package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.domain.post.dto.response.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.request.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.response.PostResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.depromeet.reunion.server.domain.post.entity.PostLike;
import com.depromeet.reunion.server.domain.post.repository.BoardRepository;
import com.depromeet.reunion.server.domain.post.repository.PostLikeRepository;
import com.depromeet.reunion.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;


    @Override
    public List<PostListResponseDto> getPostsByBoard(Long boardId) {
        // board id 확인
        validateBoard(boardId);
        // board id로 게시글 리스트 반환
        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts.stream().map(PostListResponseDto::fromEntity).toList();
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        // postId 확인
        Post post = validatePost(postId);
        return PostResponseDto.fromEntity(post);
    }


    @Override
    public PostResponseDto createPost(Long boardId, Long memberId, PostRequestDto postRequestDto, List<MultipartFile> imagefiles) {
        Board board = validateBoard(boardId);
        Member member = validateMember(memberId);

        // TODO: 이미지 파일 처리

        // post 객체 만들어서 save
        Post post = postRepository.save(postRequestDto.toEntity(member, board, imagefiles));
        return PostResponseDto.fromEntity(post);
    }

    @Override
    public void updatePost(Long postId, Long memberId, PostRequestDto postRequestDto) {
        Post post = validatePost(postId);
        if(post.getMember().getId().equals(memberId)) {
            // TODO: 이미지 파일 처리
            post.updatePost(postRequestDto);
            postRepository.save(post);
        }
    }

    @Override
    public void deletePost(Long postId, Long memberId) {
        Post post = validatePost(postId);
        if(post.getMember().getId().equals(memberId)) {
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
        return posts.stream().map(PostListResponseDto::fromEntity).toList();
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
