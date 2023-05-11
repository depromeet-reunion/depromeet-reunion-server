package com.depromeet.reunion.server.domain.member.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private MemberAuth memberAuth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private MemberGroup memberGroup;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

}
