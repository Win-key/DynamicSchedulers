package com.winkey.scheduler.impl;

import com.winkey.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExampleSchedulerImpl  implements Scheduler {

    @Override
    public void schedule() {
        log.info("Hey.. This is printed by dynamically scheduled tasks");
    }

}
