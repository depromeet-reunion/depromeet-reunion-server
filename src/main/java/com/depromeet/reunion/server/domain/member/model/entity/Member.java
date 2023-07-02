package com.depromeet.reunion.server.domain.member.model.entity;

import com.depromeet.reunion.server.domain.member.entity.PartType;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private MemberAuth memberAuth;

    @OneToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private MemberGroup memberGroup;

//    @Builder
//    public Member(Long id, String name, PartType part, String unit) {
//        this.id = id;
//        this.name = name;
//        this.part = part;
//        this.unit = unit;
//    }

}
