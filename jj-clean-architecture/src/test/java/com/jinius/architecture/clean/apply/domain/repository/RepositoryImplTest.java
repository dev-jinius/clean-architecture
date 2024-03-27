package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.application.dto.LectureResponse;
import com.jinius.architecture.clean.apply.datasource.ApplyJpaRepository;
import com.jinius.architecture.clean.apply.datasource.LectureJpaRepository;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.NOT_FOUND_USER_APPLY;
import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.USER_APPLY_ALREADY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RepositoryImplTest {

    @InjectMocks
    private ApplyRepositoryImpl applySut;

    @InjectMocks
    private LectureRepositoryImpl lecSut;

    @Mock
    private ApplyJpaRepository applyJpaRepository;

    @Mock
    private LectureJpaRepository lectureJpaRepository;


    @Test
    @DisplayName("tb_lecture 테이블을 조회하여 강의 목록을 DTO로 반환한다.")
    void getLectureList() {
        //given
        List<Lecture> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Lecture(UUID.randomUUID(), "TDD", LocalDateTime.of(2024, 4, 20+i, 13,0, 0), 20));
        }
        given(lectureJpaRepository.findAll()).willReturn(list);

        //when
        List<LectureResponse> responseList = lecSut.lectureList();

        //then
        assert responseList.size() == 5;
    }

    @Test
    @DisplayName("강의 ID로 tb_lecture 테이블을 조회하여 강의 정보를 DTO로 반환한다.")
    void getLecture() {
        //given
        UUID lectureId = UUID.randomUUID();
        ApplyRequest request = new ApplyRequest();
        request.setUserId(1L);
        request.setLectureId(lectureId);
        Lecture lecture = new Lecture(lectureId, "TDD", LocalDateTime.of(2024, 4, 20, 13,0, 0), 20);
        given(lectureJpaRepository.findByLectureId(any())).willReturn(lecture);

        //when
        LectureResponse response = lecSut.checkLecture(request);

        //then
        assert response.getLectureId().equals(lectureId);
    }

    @Test
    @DisplayName("유저 ID로 tb_apply 테이블을 조회한 반환값이 null이면 ApplyException(NOT_FOUND_USER_APPLY) 예외를 반환한다.")
    void NOT_FOUND_USER_APPLY() {
        //given
        ApplyRequest request = new ApplyRequest();
        request.setUserId(1L);
        given(applyJpaRepository.findByUserId(any())).willReturn(null);

        //when
        Throwable throwable = null;
        try {
            Boolean response = applySut.checkApply(request);
        } catch (ApplyException e) {
            throwable = e;
        }

        //then
        assert throwable != null;
        assert throwable instanceof ApplyException;
        assert ((ApplyException) throwable).getApplyErrorType().equals(NOT_FOUND_USER_APPLY);
    }

    @Test
    @DisplayName("유저 ID로 tb_apply 테이블을 조회한 반환값이 있으면, ApplyException(USER_APPLY_ALREADY) 예외를 반환한다.")
    void USER_APPLY_ALREADY() {
        //given
        ApplyRequest request = new ApplyRequest();
        request.setUserId(1L);
        given(applyJpaRepository.findByUserId(any())).willReturn(Apply.builder()
                                                                        .userId(1L)
                                                                        .lecture(any())
                                                                        .build());
        //when
        Throwable throwable = null;
        try {
            Boolean response = applySut.checkApply(request);
        } catch (ApplyException e) {
            throwable = e;
        }

        //then
        assert throwable != null;
        assert throwable instanceof ApplyException;
        assert ((ApplyException) throwable).getApplyErrorType().equals(USER_APPLY_ALREADY);
    }

    @Test
    public void save() {
        Long userId = 1L;
        UUID lectureId = UUID.randomUUID();
        Lecture lecture = new Lecture(lectureId, "TDD", LocalDateTime.of(2024,4,20, 13,0,0), 20);
        Apply expectedApply = Apply.builder()
                                .userId(userId)
                                .lecture(lecture)
                                .build();
        ApplyResponse expectedResponse = ApplyResponse.of(expectedApply);

        when(applyJpaRepository.save(expectedApply)).thenReturn(expectedApply);

        ApplyResponse response = ApplyResponse.of(applyJpaRepository.save(new Apply(userId, lecture)));

        assert response != null;
    }
}