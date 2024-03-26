package com.jinius.architecture.clean.apply.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 강의 정보
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "lecture_id")
    private UUID lectureId;                 //PK

    @Column(name = "lecture_title", nullable = false)
    private String lectureTitle;             //강의 제목

    @Column(name = "lecture_date_time")
    private LocalDateTime lectureDateTime;  //강의 일시

    /**
     * 총 수강 신청 인원
     */
    private Long totalCnt;
    public void addCnt() {
        totalCnt++;
    }
}
