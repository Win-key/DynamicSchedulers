package com.winkey.config;

import com.winkey.models.SchedulerConfigEntity;
import com.winkey.repo.SchedulerConfigRepo;
import com.winkey.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

@Configuration
public class SchedulerConfig {

    // Todo future usage
    private static Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();

    private SchedulerConfigRepo schedulerConfigRepo;
    private ApplicationContext applicationContext;
    private TaskScheduler taskScheduler;

    @Autowired
    public SchedulerConfig(SchedulerConfigRepo schedulerConfigRepo, ApplicationContext applicationContext, TaskScheduler taskScheduler) {
        this.schedulerConfigRepo = schedulerConfigRepo;
        this.applicationContext = applicationContext;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void configSchedules(){
        List<SchedulerConfigEntity> configs = schedulerConfigRepo.findAll();

        for (SchedulerConfigEntity config : configs) {
            Scheduler scheduler = (Scheduler) applicationContext.getBean(config.getBeanName());
            if(Objects.isNull(futureMap.get(config.getBeanName()))) {
                ScheduledFuture<?> schedule = taskScheduler.schedule(()->{
                    scheduler.schedule(config.getId());
                }, new CronTrigger(config.getCron()));
                futureMap.put(config.getBeanName(), schedule);
            }
        }
    }

}
