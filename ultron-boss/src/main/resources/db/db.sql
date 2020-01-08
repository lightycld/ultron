CREATE DATABASE IF NOT EXISTS ultron DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `administrator` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(20) NOT NULL UNIQUE,
    `email` varchar(50) NULL,
    `phone` varchar(15) NOT NULL UNIQUE,
    `password` varchar (100) NOT NULL,
    `salt_key` varchar(50) NOT NULL,
    `enabled` tinyint(1) not null default 0,
    `create_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `create_user` varchar(20)
 );

CREATE TABLE `user` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR (20) NOT NULL,
	`credit_no` VARCHAR (18) NOT NULL COMMENT '二代身份证号码',
	`we_chat_id` varchar(30) NULL COMMENT '微信号',
	`email` varchar(50) NULL,
	`phone` VARCHAR (14) NOT NULL,
	`verified` tinyint(1) NOT NULL DEFAULT false comment '是否已认证',
	`create_user` VARCHAR(20) NOT NULL,
	`create_time` TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3)
) COMMENT '用户表';

CREATE TABLE `user_info` (
	`id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	`user_id` BIGINT(20) NOT NULL,
	`username` VARCHAR(20) NOT NULL,
	`we_chat_id` varchar(30) NULL COMMENT '微信号',
	`phone` varchar(14) not null,
	`email` varchar(50) null,
	`area` VARCHAR(20) NOT NULL COMMENT '地区',
	`birthday` VARCHAR(10) NOT NULL,
	`sex` TINYINT(1) NOT NULL,
	`create_user` VARCHAR(20) NOT NULL,
	`create_time` TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
) COMMENT '用户信息';