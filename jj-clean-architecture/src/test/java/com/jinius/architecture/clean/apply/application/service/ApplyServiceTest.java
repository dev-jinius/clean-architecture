package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.repository.ApplyRepository;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.USER_APPLY_ALREADY;
import static org.mockito.Mockito.when;

/**
 * 특강 신청 단위 테스트
 */
@ExtendWith(MockitoExtension.class)
public class ApplyServiceTest {

    @InjectMocks
    private ApplyService sut;

    @Mock
    private ApplyRepository applyRepository;

    /**
     * 1. userId로 특강 신청 요청을 하면, 특강 신청 테이블에 저장되어야 한다.
     * 2. 이미 특강 신청 테이블에 userId가 있다면, DataIntegrityViolationException 예외를 반환한다.
     * 3. 특강 신청 테이블에 30명이 초과하면 ObjectOptimisticLockingFailureException 예외를 반환한다.
     */
    @Test
    @DisplayName("유저 없음 반환")
    public void apply() {
        Long userId = 1L;
        UUID lecId = UUID.randomUUID();

        ApplyRequest request = new ApplyRequest(userId, lecId);
        Apply apply = applyRepository.findByUserId(request);
        assert apply == null;
//        ApplyResponse result = sut.apply(request);
    }
    @Test
    @DisplayName("이미 신청한 유저 예외 반환")
    public void userApplyAlreay() {
        Long userId = 1L;
        UUID lecId = UUID.randomUUID();
        ApplyRequest request = new ApplyRequest(userId, lecId);
        when(applyRepository.findByUserId(request)).thenReturn(null);

        Throwable e = null;
        try {
            ApplyResponse result = sut.apply(request);
        } catch (ApplyException exception) {
            e = exception;
        }

        assert e != null;
        assert e instanceof ApplyException;
        assert e.getMessage().equals(USER_APPLY_ALREADY);
    }
}