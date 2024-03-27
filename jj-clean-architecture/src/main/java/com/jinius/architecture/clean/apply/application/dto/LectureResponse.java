package com.jinius.architecture.clean.apply.application.dto;

import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LectureResponse {
    private UUID lectureId;
    private String lectureTitle;
    private LocalDateTime lectureDateTime;

    public static LectureResponse of(Lecture entity) {
        return LectureResponse.builder()
                .lectureId(entity.getLectureId())
                .lectureTitle(entity.getLectureTitle())
                .lectureDateTime(entity.getLectureDateTime())
                .build();
    }
}
