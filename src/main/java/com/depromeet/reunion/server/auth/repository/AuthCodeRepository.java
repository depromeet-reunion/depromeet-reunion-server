package com.depromeet.reunion.server.auth.repository;

import com.depromeet.reunion.server.auth.model.dao.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {
}
