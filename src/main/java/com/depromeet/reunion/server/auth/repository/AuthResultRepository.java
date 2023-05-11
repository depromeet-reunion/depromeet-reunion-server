package com.depromeet.reunion.server.auth.repository;

import com.depromeet.reunion.server.auth.model.dao.AuthResult;
import org.springframework.data.repository.CrudRepository;

public interface AuthResultRepository extends CrudRepository<AuthResult, String> {
}
