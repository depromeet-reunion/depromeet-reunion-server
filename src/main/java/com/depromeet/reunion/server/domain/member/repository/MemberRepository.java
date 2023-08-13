package com.depromeet.reunion.server.domain.member.repository;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m join fetch m.memberAuth as auth where auth.phoneNumber = :phoneNumber")
    Optional<Member> findByPhoneNumber(String phoneNumber);

}
