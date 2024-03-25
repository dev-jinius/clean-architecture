package com.jinius.architecture.clean.apply.domain.repository;

import com.jinius.architecture.clean.apply.domain.entity.Apply;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Transactional
    List<Apply> findAllByUserId(Long userId);

}
