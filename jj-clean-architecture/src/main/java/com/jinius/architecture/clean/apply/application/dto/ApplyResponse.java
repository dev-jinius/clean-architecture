package com.jinius.architecture.clean.apply.application.dto;

import com.jinius.architecture.clean.apply.domain.entity.Apply;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ApplyResponse {

    private UUID applyId;
    private UUID lectureId;
    private Long userId;
    private Long version;
    private LocalDateTime lectureDate;

    public static ApplyResponse of(Apply entity) {
        return ApplyResponse.builder()
                .applyId(entity.getApplyId())
                .userId(entity.getUserId())
                .version(entity.getVersion())
                .lectureDate(entity.getLectureDate())
                .build();
    }
}
