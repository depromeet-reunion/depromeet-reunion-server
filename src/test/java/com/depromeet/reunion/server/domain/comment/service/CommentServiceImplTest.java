package com.depromeet.reunion.server.domain.comment.service;

import com.depromeet.reunion.server.domain.comment.repository.CommentRepository;
import com.depromeet.reunion.server.domain.member.entity.Member;
import com.depromeet.reunion.server.domain.member.entity.PartType;
import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.domain.post.entity.Board;
import com.depromeet.reunion.server.domain.post.entity.Post;
import com.depromeet.reunion.server.domain.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // 가짜 객체로 테스트 진행
class CommentServiceImplTest {


    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private MemberRepository memberRepository;
    @BeforeEach
    void init() {
        String testContent = "댓글 내용";
        Member member1 = Member.builder().id(1L).name("멤버1").part(PartType.WEB).unit("12").build();
        Board board1 = Board.
        Post post1 = Post.builder().title("게시글제목").content("게시글내용").board().build();


    }

    @Test
    public void 댓글_생성_성공() {
        // given

        Post post1 = Post.builder().title("게시글제목").content("게시글내용").board().build();
        // when commentRepository.save(comment)

        // then
    }
}