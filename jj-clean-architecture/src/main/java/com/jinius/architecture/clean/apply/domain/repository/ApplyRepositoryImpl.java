package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.datasource.ApplyJpaRepository;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepository {

    private final ApplyJpaRepository applyJpaRepository;

    @Override
    public Apply findByUserId(ApplyRequest request) {
        return applyJpaRepository.findByUserId(request.getUserId());
    }

    @Override
    public Apply save(ApplyRequest request) {
        return null;
    }
}
