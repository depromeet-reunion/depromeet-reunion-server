package com.depromeet.reunion.server.domain.member.entity;

import com.depromeet.reunion.server.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartType part;

    @Column(nullable = false)
    private String unit;

    @ColumnDefault("false")
    @Column
    protected boolean deleted;

    @Builder
    public Member(Long id, String name, PartType part, String unit) {
        this.id = id;
        this.name = name;
        this.part = part;
        this.unit = unit;
    }

}
