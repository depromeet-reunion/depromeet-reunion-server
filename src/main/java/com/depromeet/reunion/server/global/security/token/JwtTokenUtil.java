package com.depromeet.reunion.server.global.security.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtTokenUtil {

    private final JwtTokenConfig jwtTokenConfig;

    public String getId(String token) throws Exception {
            return jwtTokenConfig.getJwtParser().parseClaimsJws(token).getBody().get("id", String.class);
    }
}
