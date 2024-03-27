package com.jinius.architecture.clean.apply.domain.entity;

import com.jinius.architecture.clean.apply.exception.ApplyErrorType;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.OVERFLOW_MAX_CNT;

/**
 * 강의 정보
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    private int applySeatCount;    //수강 신청 카운트(최대 30)

    public void addCount() {
        if (this.applySeatCount >= 30) throw new ApplyException(OVERFLOW_MAX_CNT);
        applySeatCount++;
    }
}
