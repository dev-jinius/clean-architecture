package com.jinius.architecture.clean.apply.datasource;

import com.jinius.architecture.clean.apply.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findByLectureId(UUID id);
}
