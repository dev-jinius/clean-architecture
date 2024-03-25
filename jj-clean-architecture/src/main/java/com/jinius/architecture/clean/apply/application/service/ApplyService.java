package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    ApplyResponse applyLecture(ApplyRequest applyRequest) {
        final Long userId = applyRequest.getUserId();
        return null;
    }
}
