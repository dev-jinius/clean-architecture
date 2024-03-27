package com.jinius.architecture.clean.apply.application.service;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.application.dto.LectureResponse;
import com.jinius.architecture.clean.apply.domain.repository.ApplyRepository;
import com.jinius.architecture.clean.apply.domain.repository.LectureRepository;
import com.jinius.architecture.clean.apply.exception.ApplyErrorType;
import com.jinius.architecture.clean.apply.exception.ApplyException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.NOT_FOUND_USER_APPLY;
import static com.jinius.architecture.clean.apply.exception.ApplyErrorType.USER_APPLY_ALREADY;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final LectureRepository lectureRepository;


    /**
     * 특강 신청
     * @param request
     * @return ApplyResponse
     */
    @Transactional
    public ApplyResponse apply(ApplyRequest request) {

        //유저가 이미 신청했는지 체크 (+DB 유니크 제약조건)
        Boolean check = getSuccess(request);
        if(check) throw new ApplyException(USER_APPLY_ALREADY);

        //30명 인원이 다 찼는지 체크
        LectureResponse seatCheck = lectureRepository.checkLecture(request);

        //특강 신청
        return applyRepository.apply(request);
    }

    /**
     * 특강 신청 완료 조회
     * @param request
     * @return ApplyResponse
     */
    @Transactional
    public Boolean getSuccess(ApplyRequest request) {
        try {
            Boolean check = applyRepository.checkApply(request);
        } catch (ApplyException e) {
            if (e.getApplyErrorType().equals(USER_APPLY_ALREADY)) return true;

            return false;
        }
        return false;
    }

    /**
     * 특강 목록 조회
     * @return ApplyResponse
     */
    public List<LectureResponse> getLectureList() {
        return lectureRepository.lectureList();
    }
}
