package com.winkey.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
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
}
