package com.depromeet.reunion.server.auth.model.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Setter
@RedisHash("AUTH-CODE")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthCode {

    @Id
    private String key;
    private String value;
    @TimeToLive
    private int expiredTime;

}
