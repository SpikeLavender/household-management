USE household;

-- 用户信息表
DROP TABLE IF EXISTS tb_user_info;
CREATE TABLE tb_user_info
(
    `id`          int(11)  NOT NULL AUTO_INCREMENT COMMENT '用户表Id',
    `username`    varchar(50)       NOT NULL COMMENT '用户名',
    `password`    varchar(200)       NOT NULL COMMENT '用户密码, MD5加密',
    `email`       varchar(50)       DEFAULT NULL,
    `phone`       varchar(20)       DEFAULT NULL,
    `question`    varchar(100)      DEFAULT NULL COMMENT '找回密码问题',
    `answer`      varchar(100)      DEFAULT NULL COMMENT '找回密码答案',
    `role`        int(4)   DEFAULT '1' COMMENT '角色0-管理员, 1-普通用户',
    `status`      int(4)   DEFAULT '0' COMMENT '状态0-未审核, 1-审核中, 2-审核通过, 3-审核不通过',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 户籍信息表
DROP TABLE IF EXISTS tb_household_info;
CREATE TABLE tb_household_info
(
    `id`               int(11)        NOT NULL AUTO_INCREMENT COMMENT '户籍信息表Id',
    `user_id`          int(11)        NOT NULL    COMMENT '用户表Id',
    `name`             varchar(50)    DEFAULT NULL    COMMENT '户籍名',
    `identity_card_id` varchar(18)    DEFAULT NULL    COMMENT '身份证号',
    `birthday`         datetime       DEFAULT NULL    COMMENT '出生日期',
    `gender`           int(4)         DEFAULT '0' COMMENT '性别, 0-未知, 1-男, 2- 女',
    `address`          text                       COMMENT '家庭住址',
    `create_time`      datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    PRIMARY KEY (id),
    UNIQUE INDEX `index_user_id` (`user_id`) COMMENT '查询索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 公告信息表
DROP TABLE IF EXISTS `tb_notice_info`;
CREATE TABLE tb_notice_info
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '公告Id',
    `title`       varchar(50)      DEFAULT NULL COMMENT '公告名称',
    `detail`      text                         COMMENT '公告内容',
    `status`      int(4)           DEFAULT '1' COMMENT '类别状态1-正常, 2-已废弃',
    `sort_order`  int(4)  NOT NULL DEFAULT '1' COMMENT '数字越大，优先级越高',
    `create_time` datetime         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;