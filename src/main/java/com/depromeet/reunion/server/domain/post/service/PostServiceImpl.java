package com.depromeet.reunion.server.domain.post.service;

import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.domain.post.dto.PostListResponseDto;
import com.depromeet.reunion.server.domain.post.dto.PostRequestDto;
import com.depromeet.reunion.server.domain.post.dto.PostResponseDto;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.depromeet.reunion.server.domain.post.entity.PostLike;
import com.depromeet.reunion.server.domain.post.repository.BoardRepository;
import com.depromeet.reunion.server.domain.post.repository.PostLikeRepository;
import com.depromeet.reunion.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        findBoard(boardId);
        // board id로 게시글 리스트 반환
        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts.stream().map(PostListResponseDto::of).collect(Collectors.toList());
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        // postId 확인
        Post post = findPost(postId);
        return PostResponseDto.of(post);
    }

    @Override
    public PostResponseDto createPost(Long boardId, Long memberId, PostRequestDto postRequestDto) {
        Board board = findBoard(boardId);
        Member member = findMember(memberId);

        // post 객체 만들어서 save
        Post post = postRepository.save(postRequestDto.toEntity(member, board));
        return PostResponseDto.of(post);
    }

    @Override
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = findPost(postId);
        post.updatePost(postRequestDto);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        findPost(id);
        // soft delete
        postRepository.deleteById(id);
    }

    @Override
    public void likePost(Long postId, Long memberId) {
        Post post = findPost(postId);
        Member member = findMember(memberId);

        // 이미 좋아요한 게시글인지 확인
        Optional<PostLike> postLike = postLikeRepository.findByMemberIdAndPostId(memberId, postId);
        if (postLike.isPresent()) {
            throw new IllegalArgumentException();
        }
        postLikeRepository.save(PostLike.builder().member(member).post(post).build());
    }

    @Override
    public List<PostListResponseDto> getMyPosts(Long memberId) {
        List<Post> posts = postRepository.findByMemberId(memberId);
        return posts.stream().map(PostListResponseDto::of).collect(Collectors.toList());
    }

    private Board findBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }


}
