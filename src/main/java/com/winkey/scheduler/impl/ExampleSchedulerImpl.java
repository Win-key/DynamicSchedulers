package com.winkey.scheduler.impl;

import com.winkey.repo.SchedulerConfigRepo;
import com.winkey.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
public class ExampleSchedulerImpl  implements Scheduler {

    @Autowired
    private SchedulerConfigRepo schedulerConfigRepo;

    @Override
    @Transactional
    public void schedule(Integer id) {
        if(acquireLock(id)){
            log.warn("Running on another instance.");
            return;
        }

        log.info("Hey.. This is printed by dynamically scheduled tasks");
        resetLock(id);
    }

    @Override
    public boolean acquireLock(Integer id) {
        int rowsUpdated = schedulerConfigRepo.acquireLock(id);
        return rowsUpdated > 0;
    }

    @Override
    public void resetLock(Integer id) {
        schedulerConfigRepo.resetLock(id);
    }

}
