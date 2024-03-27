package com.jinius.architecture.clean.apply.application.dto;

import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ApplyResponse {

    private UUID applyId;
    private UUID lectureId;
    private Long userId;
    private LocalDateTime lectureDate;

    public static ApplyResponse of(Apply entity) {
        return ApplyResponse.builder()
                .applyId(entity.getApplyId())
                .userId(entity.getUserId())
                .lectureDate(entity.getLectureDate())
                .build();
    }
}
