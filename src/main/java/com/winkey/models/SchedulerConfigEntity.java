package com.winkey.models;

import com.winkey.util.Lock;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "scheduler_config")
public class SchedulerConfigEntity {

    @Id
    private Integer id;

    @Column(name = "bean_name")
    private String beanName;

    private String cron;

    @Enumerated(EnumType.STRING)
    private Lock lock_;
}
