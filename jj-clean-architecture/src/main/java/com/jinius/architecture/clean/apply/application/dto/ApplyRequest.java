package com.jinius.architecture.clean.apply.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ApplyRequest {
    private Long userId;
    private UUID lectureId;
}
