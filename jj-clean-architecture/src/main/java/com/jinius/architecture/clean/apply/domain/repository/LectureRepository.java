package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.domain.entity.Lecture;

import java.util.Optional;

public interface LectureRepository{

    Optional<Lecture> findByLectureId(ApplyRequest request);

    Lecture save(Lecture lecture);
}
