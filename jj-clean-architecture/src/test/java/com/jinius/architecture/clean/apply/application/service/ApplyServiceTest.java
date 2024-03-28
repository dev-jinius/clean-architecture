package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import com.jinius.architecture.clean.apply.domain.repository.ApplyRepository;
import com.jinius.architecture.clean.apply.domain.repository.LectureRepository;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.NOT_FOUND_USER_APPLY;
import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.USER_APPLY_ALREADY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    @Mock
    private LectureRepository lectureRepository;

    /**
     * 1. userId로 특강 신청 요청을 하면, 특강 신청 테이블에 저장되어야 한다.
     * 2. 이미 특강 신청 테이블에 userId가 있다면, DataIntegrityViolationException 예외를 반환한다.
     * 3. 특강 신청 테이블에 30명이 초과하면 ObjectOptimisticLockingFailureException 예외를 반환한다.
     */

    /**
     * 특강 신청 완료 조회 - 성공
     */
    @Test
    @DisplayName("특강 신청 완료 조회 시 신청 내역이 있으면, 반환된 `USER_APPLY_ALREADY` 예외를 그대로 반환한다.")
    void USER_APPLY_ALREADY() {
        //given
        ApplyRequest request = new ApplyRequest(1L, UUID.randomUUID());

        //when
        when(applyRepository.checkApply(request)).thenThrow(new ApplyException(USER_APPLY_ALREADY));
        Boolean response = sut.getSuccess(request);

        //then
        assert response;
    }

    /**
     * 특강 신청 완료 조회 - 실패
     */
    @Test
    @DisplayName("특강 신청 완료 조회 시 신청 내역이 없으면, 반환된 `NOT_FOUND_USER_APPLY` 예외를 그대로 반환한다.")
    void getApplySuccessYnByUserId() {
        //given
        ApplyRequest request = new ApplyRequest(1L, UUID.randomUUID());

        //when
        when(applyRepository.checkApply(request)).thenThrow(new ApplyException(NOT_FOUND_USER_APPLY));
        Boolean response = sut.getSuccess(request);

        //then
        assert !response;
    }


    /**
     * 특강 신청 - 유니크 제약 조건 위반
     */
    @Test
    @DisplayName("이미 신청한 유저가 특강 신청을 하면, 예외를 반환한다.")
    void apply() {
        //given
        Long userId = 1L;
        UUID lectureId = UUID.randomUUID();
        Lecture lecture = new Lecture(lectureId, "TDD", LocalDateTime.now(), 20);
        Apply existApply = Apply.builder()
                                .userId(userId)
                                .lecture(lecture)
                                .build();

        ApplyRequest request = new ApplyRequest(userId, lectureId);
        given(applyRepository.apply(request)).willReturn(ApplyResponse.of(existApply));

        Exception exception = null;
        try {
            ApplyResponse expectApply = sut.apply(new ApplyRequest(userId, lectureId));
        } catch (DataIntegrityViolationException e) {
            exception = e;
            e.printStackTrace();
        }

        assert exception != null;

    }

    @Test
    @DisplayName("이미 신청한 유저 예외 반환")
    public void userApplyAlreay() {
        Long userId = 1L;
        UUID lecId = UUID.randomUUID();
        ApplyRequest request = new ApplyRequest(userId, lecId);
//        when(applyRepository.checkApply(request)).thenReturn(new Apply(1L, lecId));
//        when(lectureRepository.findByLectureId(request)).thenReturn(Optional.of(new Lecture(lecId, "TDD", LocalDateTime.now(), 0L)));

        sut.apply(request);

        Throwable e = null;
        try {
            sut.apply(request);
        } catch (ApplyException exception) {
            e = exception;
        }

        assert e != null;
        assert e instanceof ApplyException;
        assert e.getMessage().equals(USER_APPLY_ALREADY);
    }

}