package com.jinius.architecture.clean.apply.domain.entity;

import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 특강 신청 테이블
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_apply",
        uniqueConstraints = {
            @UniqueConstraint(
                name = "USER_LECTURE_UNIQUE",
                columnNames = {"lectureId", "userId"})
            })
public class Apply {
    /**
     * 특강 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "apply_id")
    private UUID applyId;

    /**
     * 강의 정보 ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", unique = true, nullable = false)
    private Lecture lecture;

    /**
     * 신청 유저 ID 
     * UNIQUE
     */
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    /**
     * 버전
     */
    @Version
    private Long version;

    /**
     * 특강 일시
     */
    @Column(name = "lecture_date")
    private LocalDateTime lectureDate = LocalDateTime.of(2024, 4, 20, 13, 0, 0);

    @Builder
    public Apply(Long userId, Lecture lecture) {
        this.userId = userId;
        this.lecture = lecture;
    }
}
