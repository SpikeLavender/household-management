USE household;

-- 用户信息表
DROP TABLE IF EXISTS tb_user_info;
CREATE TABLE tb_user_info
(
    `id`          int(11)  NOT NULL AUTO_INCREMENT COMMENT '用户表Id',
    `username`    varchar(50)       NOT NULL COMMENT '用户名',
    `password`    varchar(50)       NOT NULL COMMENT '用户密码, MD5加密',
    `email`       varchar(50)       DEFAULT NULL,
    `phone`       varchar(20)       DEFAULT NULL,
    `question`    varchar(100)      DEFAULT NULL COMMENT '找回密码问题',
    `answer`      varchar(100)      DEFAULT NULL COMMENT '找回密码答案',
    `role`        int(4)   NOT NULL COMMENT '角色0-管理员, 1-普通用户',
    `status`      int(4)   NOT NULL COMMENT '状态0-未审核, 1-审核中, 2-审核通过, 3-审核不通过',
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
    `name`             varchar(50)    NOT NULL    COMMENT '户籍名',
    `identity_card_id` varchar(18)    NOT NULL    COMMENT '业绩等级',
    `birthday`         datetime       NOT NULL    COMMENT '出生日期',
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


# -- 产品表
# # #DROP TABLE IF EXISTS `tb_product`;
# # CREATE TABLE `tb_product`
# # (
# #     `id`          int(11)        NOT NULL AUTO_INCREMENT COMMENT '商品id',
# #     `category_id` int(11)        NOT NULL COMMENT '分类id,对应mall_category表的主键',
# #     `name`        varchar(100)   NOT NULL COMMENT '商品名称',
# #     `subtitle`    varchar(200) DEFAULT NULL COMMENT '商品副标题',
# #     `main_image`  varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
# #     `sub_images`  text COMMENT '图片地址,json格式,扩展用',
# #     `detail`      text COMMENT '商品详情',
# #     `price`       decimal(20, 2) NOT NULL COMMENT '价格,单位-元保留两位小数',
# #     `stock`       int(11)        NOT NULL COMMENT '库存数量',
# #     `status`      int(6)       DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
# #     `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# #     `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# #     PRIMARY KEY (`id`)
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
# #
# #
# # -- 支付信息表
# # #DROP TABLE IF EXISTS `tb_pay_info`;
# # CREATE TABLE `tb_pay_info`
# # (
# #     `id`              int(11)        NOT NULL AUTO_INCREMENT,
# #     `user_id`         int(11)      DEFAULT NULL COMMENT '用户id',
# #     `order_no`        varchar(64)  DEFAULT NULL COMMENT '订单号',
# #     `pay_platform`    int(10)      DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
# #     `platform_number` varchar(200) DEFAULT NULL COMMENT '支付流水号',
# #     `platform_status` varchar(20)  DEFAULT NULL COMMENT '支付状态',
# #     `pay_amount`      decimal(20, 2) NOT NULL COMMENT '支付金额',
# #     `create_time`     datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# #     `update_time`     datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# #     PRIMARY KEY (`id`),
# #     UNIQUE KEY `uqe_order_no` (`order_no`),
# #     UNIQUE KEY `uqe_platform_number` (`platform_number`)
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
# #
# #
# # -- 订单表
# # #DROP TABLE IF EXISTS `tb_order`;
# # CREATE TABLE `tb_order`
# # (
# #     `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
# #     `order_no`     varchar(64)    DEFAULT NULL COMMENT '订单号',
# #     `user_id`      int(11)        DEFAULT NULL COMMENT '用户id',
# #     `shipping_id`  int(11)        DEFAULT NULL,
# #     `payment`      decimal(20, 2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
# #     `payment_type` int(4)         DEFAULT NULL COMMENT '支付类型,1-在线支付',
# #     `postage`      int(10)        DEFAULT NULL COMMENT '运费,单位是元',
# #     `status`       int(10)        DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
# #     `payment_time` datetime       DEFAULT NULL COMMENT '支付时间',
# #     `send_time`    datetime       DEFAULT NULL COMMENT '发货时间',
# #     `end_time`     datetime       DEFAULT NULL COMMENT '交易完成时间',
# #     `close_time`   datetime       DEFAULT NULL COMMENT '交易关闭时间',
# #     `create_time`  datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# #     `update_time`  datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# #     PRIMARY KEY (`id`),
# #     UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
# #
# #
# # -- 订单明细表
# # #DROP TABLE IF EXISTS `tb_order_item`;
# # CREATE TABLE `tb_order_item`
# # (
# #     `id`                 int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
# #     `user_id`            int(11)        DEFAULT NULL,
# #     `order_no`           varchar(64)    DEFAULT NULL,
# #     `product_id`         int(11)        DEFAULT NULL COMMENT '商品id',
# #     `product_name`       varchar(100)   DEFAULT NULL COMMENT '商品名称',
# #     `product_image`      varchar(500)   DEFAULT NULL COMMENT '商品图片地址',
# #     `current_unit_price` decimal(20, 2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
# #     `quantity`           int(10)        DEFAULT NULL COMMENT '商品数量',
# #     `total_price`        decimal(20, 2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
# #     `create_time`        datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# #     `update_time`        datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# #     PRIMARY KEY (`id`),
# #     KEY `order_no_index` (`order_no`) USING BTREE,
# #     KEY `order_no_user_id_index` (`user_id`, `order_no`) USING BTREE
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
# #
# #
# # -- 收货地址表
# # #DROP TABLE IF EXISTS `tb_shipping`;
# # CREATE TABLE `tb_shipping`
# # (
# #     `id`                int(11) NOT NULL AUTO_INCREMENT,
# #     `user_id`           int(11)      DEFAULT NULL COMMENT '用户id',
# #     `is_default`        tinyint(1)   DEFAULT true COMMENT '是否默认地址',
# #     `receiver_name`     varchar(20)  DEFAULT NULL COMMENT '收货姓名',
# #     `receiver_phone`    varchar(20)  DEFAULT NULL COMMENT '收货固定电话',
# #     `receiver_mobile`   varchar(20)  DEFAULT NULL COMMENT '收货移动电话',
# #     `receiver_province` varchar(20)  DEFAULT NULL COMMENT '省份',
# #     `receiver_city`     varchar(20)  DEFAULT NULL COMMENT '城市',
# #     `receiver_district` varchar(20)  DEFAULT NULL COMMENT '区/县',
# #     `receiver_address`  varchar(200) DEFAULT NULL COMMENT '详细地址',
# #     `receiver_zip`      varchar(6)   DEFAULT NULL COMMENT '邮编',
# #     `create_time`       datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# #     `update_time`       datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# #     PRIMARY KEY (`id`)
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
# #
# #
# # -- 数据预制
# # INSERT INTO `tb_category` (`id`, `parent_id`, `name`, `status`, `sort_order`)
# # VALUES (100001, 0, '家用电器', 1, 1),
# #        (100002, 0, '数码3C', 1, 2),
# #        (100003, 0, '服装箱包', 1, 1),
# #        (100004, 0, '食品生鲜', 1, 1),
# #        (100005, 0, '酒水饮料', 1, 1),
# #        (100006, 100001, '冰箱', 1, 1),
# #        (100007, 100001, '电视', 1, 2),
# #        (100008, 100001, '洗衣机', 1, 1),
# #        (100009, 100001, '空调', 1, 1),
# #        (100010, 100001, '电热水器', 1, 1),
# #        (100011, 100002, '电脑', 1, 1),
# #        (100012, 100002, '手机', 1, 1),
# #        (100013, 100002, '平板电脑', 1, 1),
# #        (100014, 100002, '数码相机', 1, 1),
# #        (100015, 100002, '3C配件', 1, 1),
# #        (100016, 100003, '女装', 1, 1),
# #        (100017, 100003, '帽子', 1, 1),
# #        (100018, 100003, '旅行箱', 1, 1),
# #        (100019, 100003, '手提包', 1, 1),
# #        (100020, 100003, '保暖内衣', 1, 1),
# #        (100021, 100004, '零食', 1, 1),
# #        (100022, 100004, '生鲜', 1, 1),
# #        (100023, 100004, '半成品菜', 1, 1),
# #        (100024, 100004, '速冻食品', 1, 1),
# #        (100025, 100004, '进口食品', 1, 1),
# #        (100026, 100005, '白酒', 1, 1),
# #        (100027, 100005, '红酒', 1, 1),
# #        (100028, 100005, '饮料', 1, 1),
# #        (100029, 100005, '调制鸡尾酒', 1, 1),
# #        (100030, 100005, '进口洋酒', 1, 1),
# #        (100031, 100006, '进口冰箱', 1, 1),
# #        (100032, 1000031, '进口德国冰箱', 1, 1);
# #
# #
# # INSERT INTO `tb_product` (`id`, `category_id`, `name`, `subtitle`, `main_image`, `sub_images`, `detail`, `price`,
# #                           `stock`, `status`, `create_time`, `update_time`)
# # VALUES (26, 100002, 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', 'iPhone 7，现更以红色呈现。',
# #         'http://img.springboot.cn/241997c4-9e62-4824-b7f0-7425c3c28917.jpeg',
# #         '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg,b6c56eb0-1748-49a9-98dc-bcc4b9788a54.jpeg,92f17532-1527-4563-aa1d-ed01baa0f7b2.jpeg,3adbe4f7-e374-4533-aa79-cc4a98c529bf.jpeg',
# #         '<p><img alt=\"10000.jpg\" src=\"http://img.springboot.cn/00bce8d4-e9af-4c8d-b205-e6c75c7e252b.jpg\" width=\"790\" height=\"553\"><br></p><p><img alt=\"20000.jpg\" src=\"http://img.springboot.cn/4a70b4b4-01ee-46af-9468-31e67d0995b8.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"30000.jpg\" src=\"http://img.springboot.cn/0570e033-12d7-49b2-88f3-7a5d84157223.jpg\" width=\"790\" height=\"365\"><br></p><p><img alt=\"40000.jpg\" src=\"http://img.springboot.cn/50515c02-3255-44b9-a829-9e141a28c08a.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"50000.jpg\" src=\"http://img.springboot.cn/c138fc56-5843-4287-a029-91cf3732d034.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"60000.jpg\" src=\"http://img.springboot.cn/c92d1f8a-9827-453f-9d37-b10a3287e894.jpg\" width=\"790\" height=\"525\"><br></p><p><br></p><p><img alt=\"TB24p51hgFkpuFjSspnXXb4qFXa-1776456424.jpg\" src=\"http://img.springboot.cn/bb1511fc-3483-471f-80e5-c7c81fa5e1dd.jpg\" width=\"790\" height=\"375\"><br></p><p><br></p><p><img alt=\"shouhou.jpg\" src=\"http://img.springboot.cn/698e6fbe-97ea-478b-8170-008ad24030f7.jpg\" width=\"750\" height=\"150\"><br></p><p><img alt=\"999.jpg\" src=\"http://img.springboot.cn/ee276fe6-5d79-45aa-8393-ba1d210f9c89.jpg\" width=\"790\" height=\"351\"><br></p>',
# #         6999.00, 96, 1, '2000-04-13 21:45:41', '2000-04-13 21:45:41'),
# #        (27, 100006, 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', '送品牌烤箱，五一大促',
# #         'http://img.springboot.cn/ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',
# #         'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg,4bb02f1c-62d5-48cc-b358-97b05af5740d.jpeg,36bdb49c-72ae-4185-9297-78829b54b566.jpeg',
# #         '<p><img alt=\"miaoshu.jpg\" src=\"http://img.springboot.cn/9c5c74e6-6615-4aa0-b1fc-c17a1eff6027.jpg\" width=\"790\" height=\"444\"><br></p><p><img alt=\"miaoshu2.jpg\" src=\"http://img.springboot.cn/31dc1a94-f354-48b8-a170-1a1a6de8751b.jpg\" width=\"790\" height=\"1441\"><img alt=\"miaoshu3.jpg\" src=\"http://img.springboot.cn/7862594b-3063-4b52-b7d4-cea980c604e0.jpg\" width=\"790\" height=\"1442\"><img alt=\"miaoshu4.jpg\" src=\"http://img.springboot.cn/9a650563-dc85-44d6-b174-d6960cfb1d6a.jpg\" width=\"790\" height=\"1441\"><br></p>',
# #         3299.00, 99, 1, '2000-04-13 18:51:54', '2000-04-13 21:45:41'),
# #        (28, 100012, '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', 'NOVA青春版1999元',
# #         'http://img.springboot.cn/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg',
# #         '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg,13da2172-4445-4eb5-a13f-c5d4ede8458c.jpeg,58d5d4b7-58d4-4948-81b6-2bae4f79bf02.jpeg',
# #         '<p><img alt=\"11TB2fKK3cl0kpuFjSsziXXa.oVXa_!!1777180618.jpg\" src=\"http://img.springboot.cn/5c2d1c6d-9e09-48ce-bbdb-e833b42ff664.jpg\" width=\"790\" height=\"966\"><img alt=\"22TB2YP3AkEhnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.springboot.cn/9a10b877-818f-4a27-b6f7-62887f3fb39d.jpg\" width=\"790\" height=\"1344\"><img alt=\"33TB2Yyshk.hnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.springboot.cn/7d7fbd69-a3cb-4efe-8765-423bf8276e3e.jpg\" width=\"790\" height=\"700\"><img alt=\"TB2diyziB8kpuFjSspeXXc7IpXa_!!1777180618.jpg\" src=\"http://img.springboot.cn/1d7160d2-9dba-422f-b2a0-e92847ba6ce9.jpg\" width=\"790\" height=\"393\"><br></p>',
# #         1999.00, 100, 1, '2000-04-13 18:57:18', '2000-04-13 21:45:41'),
# #        (29, 100008, 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '门店机型 德邦送货',
# #         'http://img.springboot.cn/173335a4-5dce-4afd-9f18-a10623724c4e.jpeg',
# #         '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg,42b1b8bc-27c7-4ee1-80ab-753d216a1d49.jpeg,2f1b3de1-1eb1-4c18-8ca2-518934931bec.jpeg',
# #         '<p><img alt=\"1TB2WLZrcIaK.eBjSspjXXXL.XXa_!!2114960396.jpg\" src=\"http://img.springboot.cn/ffcce953-81bd-463c-acd1-d690b263d6df.jpg\" width=\"790\" height=\"920\"><img alt=\"2TB2zhOFbZCO.eBjSZFzXXaRiVXa_!!2114960396.jpg\" src=\"http://img.springboot.cn/58a7bd25-c3e7-4248-9dba-158ef2a90e70.jpg\" width=\"790\" height=\"1052\"><img alt=\"3TB27mCtb7WM.eBjSZFhXXbdWpXa_!!2114960396.jpg\" src=\"http://img.springboot.cn/2edbe9b3-28be-4a8b-a9c3-82e40703f22f.jpg\" width=\"790\" height=\"820\"><br></p>',
# #         4299.00, 100, 1, '2000-04-13 19:07:47', '2000-04-13 21:45:41');
# #
# # INSERT INTO `tb_shipping` (`id`, `user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`, `receiver_province`,
# #                            `receiver_city`, `receiver_district`, `receiver_address`, `receiver_zip`)
# # VALUES (4, 1, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049');
# #
# # INSERT INTO `tb_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `parent_id`)
# # VALUES (1, 'admin1', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', NULL, NULL, NULL, 0, 0);
# #
# # INSERT INTO `tb_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `parent_id`)
# # VALUES (2, 'admin2', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', NULL, NULL, NULL, 0, 0);
# #
# # INSERT INTO `tb_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `parent_id`)
# # VALUES (3, 'admin3', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', NULL, NULL, NULL, 0, 1);
# #
# # INSERT INTO `tb_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `parent_id`)
# # VALUES (4, 'admin4', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', NULL, NULL, NULL, 0, 1);
# #
# # INSERT INTO `tb_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `parent_id`)
# # VALUES (5, 'admin5', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', NULL, NULL, NULL, 0, 3);
# #
# # INSERT INTO `tb_user` (`id`, `username`, `password`, `email`, `phone`, `question`, `answer`, `role`, `parent_id`)
# # VALUES (6, 'admin6', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', NULL, NULL, NULL, 0, 3);
# #
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (1, '1583423427696', 1, 4, 2000.00, 1, 0, 50, NULL, NULL, '2020-03-25 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (2, '1523423427396', 2, 4, 4000.00, 1, 0, 50, NULL, NULL, '2020-03-26 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (3, '1583923427696', 3, 4, 6000.00, 1, 0, 50, NULL, NULL, '2020-03-27 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (4, '1583423227696', 3, 4, 9000.00, 1, 0, 50, NULL, NULL, '2020-03-31 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (5, '1583413427696', 4, 4, 10000.00, 1, 0, 50, NULL, NULL, '2020-03-26 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (6, '1583433427696', 5, 4, 3000.00, 1, 0, 50, NULL, NULL, '2020-03-26 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_order`(`id`, `order_no`, `user_id`, `shipping_id`, `payment`, `payment_type`, `postage`,
# #                                  `status`, `payment_time`, `send_time`, `end_time`, `close_time`)
# # VALUES (7, '1583426427696', 6, 4, 7000.00, 1, 0, 50, NULL, NULL, '2020-03-26 15:50:27', '2020-03-31 04:56:22');
# #
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (1, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (2, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (3, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (4, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (5, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (6, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');
# # INSERT INTO `natsume`.`tb_shipping`(`user_id`, `receiver_name`, `receiver_phone`, `receiver_mobile`,
# #                                     `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
# #                                     `receiver_zip`, `create_time`, `update_time`)
# # VALUES (20, '何腾蛟', '010', '18688888888', '陕西', '西安市', '碑林区', '西安交通大学', '710049', '2020-04-01 01:59:14',
# #         '2020-04-01 01:59:14');