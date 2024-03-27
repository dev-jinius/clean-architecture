package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.datasource.ApplyJpaRepository;
import com.jinius.architecture.clean.apply.datasource.LectureJpaRepository;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.*;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepository {

    private final ApplyJpaRepository applyJpaRepository;

    private final LectureJpaRepository lectureJpaRepository;

    private static final int MAX_SEAT_SIZE = 30;


    @Override
    public Boolean checkApply(ApplyRequest request) {
        Apply findUser = applyJpaRepository.findByUserId(request.getUserId());
        if (!ObjectUtils.isEmpty(findUser)) throw new ApplyException(USER_APPLY_ALREADY);
        else throw new ApplyException(NOT_FOUND_USER_APPLY);
    }

    @Override
    public ApplyResponse apply(ApplyRequest request) {
        Lecture lecture = lectureJpaRepository.findByLectureId(request.getLectureId());

        //유저가 이미 신청했는지 체크 (DB 유니크 제약조건)
        Boolean check = checkApply(request);
        if(check) throw new ApplyException(USER_APPLY_ALREADY);

        //30명 인원이 다 찼는지 체크
        lecture.addCount();

        //특강 신청
        Apply saved = applyJpaRepository.save(Apply.builder()
                                                        .userId(request.getUserId())
                                                        .lecture(lecture)
                                                        .build());
        if(ObjectUtils.isEmpty(saved)) throw new ApplyException(FAILED_SAVE_APPLY);
        lecture.addCount();

        return ApplyResponse.of(saved);
    }
}
