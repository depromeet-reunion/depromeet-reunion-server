package com.depromeet.reunion.server.domain.post.repository;

import com.depromeet.reunion.server.domain.comment.entity.Comment;
import com.depromeet.reunion.server.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoardId(Long boardId);

    List<Post> findByMemberId(Long memberId);
}