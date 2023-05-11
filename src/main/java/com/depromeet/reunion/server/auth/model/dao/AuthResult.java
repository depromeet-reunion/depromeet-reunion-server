package com.depromeet.reunion.server.auth.model.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Setter
@RedisHash("AUTH-RESULT")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthResult {

    @Id
    private String key;
    private String value;
    @TimeToLive
    private int expiredTime;
}
