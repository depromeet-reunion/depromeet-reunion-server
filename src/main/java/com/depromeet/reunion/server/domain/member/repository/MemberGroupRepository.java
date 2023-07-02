package com.depromeet.reunion.server.domain.member.repository;

import com.depromeet.reunion.server.domain.member.model.entity.MemberGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberGroupRepository extends CrudRepository<MemberGroup, Long> {
    Optional<MemberGroup> findMemberGroupByPartAndUnit(String part, Integer unit);
}
