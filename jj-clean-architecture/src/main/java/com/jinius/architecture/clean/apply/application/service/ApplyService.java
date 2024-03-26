package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.domain.entity.Apply;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import com.jinius.architecture.clean.apply.domain.repository.ApplyRepository;
import com.jinius.architecture.clean.apply.domain.repository.LectureRepository;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.*;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final LectureRepository lectureRepository;
    /**
     * 특강 신청
     * @param request
     * @return
     */
    @Transactional
    public ApplyResponse apply(ApplyRequest request) {
        final int MAX_CNT = 30;

        Apply apply = applyRepository.findByUserId(request);
        Optional<Lecture> lecture = lectureRepository.findByLectureId(request);

        if(!ObjectUtils.isEmpty(apply)) throw new ApplyException(USER_APPLY_ALREADY);
        if(lecture.get().getTotalCnt() >= MAX_CNT) throw new ApplyException(OVERFLOW_MAX_CNT);
        lecture.get().addCnt();

        return ApplyResponse.of(applyRepository.save(request));
    }
}
