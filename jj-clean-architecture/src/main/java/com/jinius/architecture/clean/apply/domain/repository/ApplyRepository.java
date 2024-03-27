package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;

import java.util.Optional;

public interface ApplyRepository {

    //특강 신청 조회
    Boolean checkApply(ApplyRequest request);

    //특강 신청
    ApplyResponse apply(ApplyRequest request);
}
