package com.depromeet.reunion.server.global.security.config;


import com.depromeet.reunion.server.domain.member.repository.MemberRepository;
import com.depromeet.reunion.server.global.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.depromeet.reunion.server.global.security.filter.JwtAuthenticationFilter;
import com.depromeet.reunion.server.global.security.provider.InvalidTokenAuthenticationEntryPoint;
import com.depromeet.reunion.server.global.security.provider.handler.CustomLoginFailureHandler;
import com.depromeet.reunion.server.global.security.provider.handler.CustomLoginSuccessHandler;
import com.depromeet.reunion.server.global.security.token.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;
    private final CustomLoginSuccessHandler successHandler;
    private final CustomLoginFailureHandler failureHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/v1/auth", "/v1/auth/sms/send", "/v1/auth/sms/verify", "/v1/sign-up", "/health", "/h2-console",
                        "/v1/slack/group/reject/*", "/v1/slack/group/approve/*").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().disable()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .apply(new CustomFilter())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new InvalidTokenAuthenticationEntryPoint());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public class CustomFilter extends AbstractHttpConfigurer<CustomFilter, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

            http.addFilterAfter(new CustomUsernamePasswordAuthenticationFilter(authenticationManager, objectMapper, successHandler, failureHandler), UsernamePasswordAuthenticationFilter.class);
            http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil, memberRepository), BasicAuthenticationFilter.class);

        }
    }

}
