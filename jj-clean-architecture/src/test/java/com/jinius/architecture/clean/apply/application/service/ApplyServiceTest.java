package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.repository.ApplyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 특강 신청 단위 테스트
 */
@ExtendWith(MockitoExtension.class)
class ApplyServiceTest {

    @InjectMocks
    ApplyService sut;

    @Mock
    ApplyRepository applyRepository;

    /**
     * 1. userId로 특강 신청 요청을 하면, 특강 신청 테이블에 저장되어야 한다.
     * 2. 이미 특강 신청 테이블에 userId가 있다면, DataIntegrityViolationException 예외를 반환한다.
     * 3. 특강 신청 테이블에 30명이 초과하면 ObjectOptimisticLockingFailureException 예외를 반환한다.
     */
    @Test
    void applyLecture() {

    }

    
}