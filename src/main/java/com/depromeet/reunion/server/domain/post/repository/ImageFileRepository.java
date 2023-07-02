package com.depromeet.reunion.server.domain.post.repository;

import com.depromeet.reunion.server.domain.post.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
}
