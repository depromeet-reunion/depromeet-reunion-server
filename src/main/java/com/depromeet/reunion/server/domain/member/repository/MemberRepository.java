package com.depromeet.reunion.server.domain.member.repository;

import com.depromeet.reunion.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
