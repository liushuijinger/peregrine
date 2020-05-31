DROP TABLE IF EXISTS user;

CREATE TABLE `user`
(
    `id`          int(11)          NOT NULL AUTO_INCREMENT COMMENT '唯一 id，自增',
    `name`        varchar(64)      NOT NULL COMMENT '姓名',
    `age`         tinyint unsigned NOT NULL COMMENT '年龄',
    `create_time` timestamp        NULL COMMENT '创建时间',
    `update_time` timestamp        NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户信息';

INSERT INTO user (id, name, age,create_time,update_time) VALUES
(1, '刘一', 18,now(),now()),
(2, '陈二', 20,now(),now()),
(3, '张三', 28,now(),now()),
(4, '李四', 21,now(),now()),
(5, '王五', 24,now(),now());