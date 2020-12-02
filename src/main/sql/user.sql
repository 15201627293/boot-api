   CREATE TABLE `user` (
     `id` bigint(20) NOT NULL,
     `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
     `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
     `pass_word` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
     `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '盐',
     `sex` tinyint(4) DEFAULT NULL COMMENT '性别 1男 2女',
     `head_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     `update_time` datetime DEFAULT NULL COMMENT '修改时间',
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;