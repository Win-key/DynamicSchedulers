package com.winkey.repo;

import com.winkey.models.SchedulerConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchedulerConfigRepo extends JpaRepository<SchedulerConfigEntity, Integer> {

    @Modifying
    @Query(value = "UPDATE scheduler_config SET lock_='LOCKED' WHERE id=:id AND lock_='OPEN'", nativeQuery = true)
    int acquireLock(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE scheduler_config SET lock_='OPEN' WHERE id=:id", nativeQuery = true)
    void resetLock(@Param("id") Integer id);

}
