package com.jinius.architecture.clean.apply.application.dto;

import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyRequest {
    private Long userId;
    private UUID lectureId;

    public Apply toEntity(Lecture lecture) {
        return Apply.builder()
                .userId(this.userId)
                .lecture(lecture)
                .build();
    }
}
