package com.depromeet.reunion.server.domain.post.repository;

import com.depromeet.reunion.server.domain.post.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
