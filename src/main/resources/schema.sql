DROP TABLE IF EXISTS scheduler_config;

CREATE TABLE scheduler_config (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    bean_name VARCHAR(50) NOT NULL,
    cron VARCHAR(50) NOT NULL
);