package com.depromeet.reunion.server.domain.member.model.entity;

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

    @Enumerated(EnumType.STRING)
    private MemberStatus status = MemberStatus.READY;

    @Enumerated(EnumType.STRING)
    private MemberScreeningStatus screeningStatus = MemberScreeningStatus.SUBMITTED;

    @OneToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private MemberAuth memberAuth;

    @OneToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private MemberGroup memberGroup;

    public void setScreeningStatusApproved() {
        if (status == MemberStatus.READY && screeningStatus != MemberScreeningStatus.SUBMITTED) {
            return;
        }
        this.status = MemberStatus.MEMBER;
        this.screeningStatus = MemberScreeningStatus.APPROVED;
    }

    public void setScreeningStatusRejected() {
        if (screeningStatus != MemberScreeningStatus.SUBMITTED) {
            return;
        }
        this.screeningStatus = MemberScreeningStatus.REJECTED;
    }

}
