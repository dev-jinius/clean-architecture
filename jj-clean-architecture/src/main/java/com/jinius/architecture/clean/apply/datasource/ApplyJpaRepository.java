package com.jinius.architecture.clean.apply.datasource;

import com.jinius.architecture.clean.apply.domain.entity.Apply;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplyJpaRepository extends JpaRepository<Apply, Long> {

     Apply findByUserId(Long userId);

     Apply save(Apply apply);

//     @Lock(LockModeType.OPTIMISTIC)
//     @Query(value = "select a from Apply a where a.applyId = :applyId")
//     Apply findByApplyIdLock(Long applyId);
}
