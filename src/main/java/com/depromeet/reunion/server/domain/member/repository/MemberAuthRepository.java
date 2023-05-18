package com.depromeet.reunion.server.domain.member.repository;

import com.depromeet.reunion.server.domain.member.model.entity.MemberAuth;
import org.springframework.data.repository.CrudRepository;

public interface MemberAuthRepository extends CrudRepository<MemberAuth, Long> {
}
