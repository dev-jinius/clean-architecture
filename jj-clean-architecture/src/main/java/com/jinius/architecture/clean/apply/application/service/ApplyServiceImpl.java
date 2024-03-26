package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.application.service.ApplyService;
import com.jinius.architecture.clean.apply.repository.ApplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;

    @Transactional
    public ApplyResponse applyLecture(ApplyRequest applyRequest) {
        final Long userId = applyRequest.getUserId();
        return null;
    }
}
