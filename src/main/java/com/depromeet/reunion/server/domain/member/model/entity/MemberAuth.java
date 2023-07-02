package com.depromeet.reunion.server.domain.member.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberAuth {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String phoneNumber;

    private String password;

    private LocalDateTime authDate;
}
