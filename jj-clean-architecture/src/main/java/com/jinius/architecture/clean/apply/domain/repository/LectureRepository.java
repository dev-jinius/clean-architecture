package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.LectureResponse;

import java.util.List;

public interface LectureRepository {

    //강의 ID로 강의 정보 조회
    LectureResponse checkLecture(ApplyRequest request);

    //특강 목록 조회
    List<LectureResponse> lectureList();
}
