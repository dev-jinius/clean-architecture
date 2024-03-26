package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.domain.entity.Apply;

public interface ApplyRepository {
    Apply findByUserId(ApplyRequest request);

    Apply save(ApplyRequest request);
}
