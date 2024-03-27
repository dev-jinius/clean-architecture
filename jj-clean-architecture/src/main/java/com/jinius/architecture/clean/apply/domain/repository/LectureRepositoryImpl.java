package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.LectureResponse;
import com.jinius.architecture.clean.apply.datasource.LectureJpaRepository;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import com.jinius.architecture.clean.apply.exception.ApplyErrorType;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.OVERFLOW_MAX_CNT;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public LectureResponse checkLecture(ApplyRequest request) {
        Lecture findLecture = lectureJpaRepository.findByLectureId(request.getLectureId());

        if (findLecture.getApplySeatCount() > 30) throw new ApplyException(OVERFLOW_MAX_CNT);

        return LectureResponse.of(findLecture);
    }

    @Override
    public List<LectureResponse> lectureList() {
        List<Lecture> findLectureList = lectureJpaRepository.findAll();
        return findLectureList.stream().map(lecture -> LectureResponse.of(lecture)).collect(Collectors.toList());
    }
}
