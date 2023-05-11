package com.depromeet.reunion.server.domain.member.repository;

import com.depromeet.reunion.server.domain.member.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Query("select m from Member m join fetch m.memberAuth as auth where auth.phoneNumber = :phoneNumber")
    Optional<Member> findByPhoneNumber(String phoneNumber);

    @Query("select m from Member m join fetch m.memberGroup as group where group.unit = :unit")
    List<Member> findSameUnitMembers(int unit);
}
