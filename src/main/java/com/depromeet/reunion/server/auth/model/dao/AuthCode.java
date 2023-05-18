package com.depromeet.reunion.server.auth.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash("AUTH-CODE")
@Builder
@AllArgsConstructor
public class AuthCode {

    @Id
    private String key;
    private String value;
    @TimeToLive
    private int expiredTime;

}
