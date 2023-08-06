package com.depromeet.reunion.server.domain.member.repository;

import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.domain.member.model.entity.MemberGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberGroupRepository extends CrudRepository<MemberGroup, Long> {
    Optional<MemberGroup> findMemberGroupByPartAndUnit(String part, Integer unit);

    @Query("SELECT DISTINCT mg.part FROM MemberGroup mg ORDER BY mg.part ASC")
    List<String> findAllPart();

    @Query("SELECT DISTINCT mg.unit FROM MemberGroup mg ORDER BY mg.unit ASC")
    List<Long> findAllUnit();
}
