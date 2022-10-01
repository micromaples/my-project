/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.27 : Database - my-project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my-project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `my-project`;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编号',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `mail` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`code`,`name`,`sex`,`age`,`birthday`,`mail`,`created_date`,`updated_date`) values 
(1,'admin','超级管理员','男',18,'2004-01-01','micromaple@qq.com','2022-09-21 21:48:33',NULL),
(2,'user','用户','女',18,NULL,NULL,'2022-09-21 14:06:46',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
