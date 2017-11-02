/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.34 : Database - crm_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL COMMENT '父菜单ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `url` varchar(256) DEFAULT NULL COMMENT '链接地址',
  `type` varchar(16) NOT NULL COMMENT '菜单类型',
  `action` varchar(16) DEFAULT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_menu` */

LOCK TABLES `sys_menu` WRITE;

insert  into `sys_menu`(`id`,`pid`,`name`,`url`,`type`,`action`) values (1,-1,'系统管理',NULL,'1',NULL),(2,1,'用户管理','/system/manage/user','2',NULL),(3,2,'用户新增','/system/users/user','3','create'),(4,2,'用户修改','/system/users/user/*','3','update'),(5,2,'用户查询','/system/users','3','read'),(6,2,'用户删除','/system/users/user/*','3','delete'),(7,1,'角色管理','/system/manage/role','2',NULL),(8,7,'角色新增','/system/roles/role','3','create'),(9,7,'角色修改','/system/roles/role/*','3','update'),(10,7,'角色查询','/system/roles','3','read'),(11,7,'角色删除','/system/roles/role/*','3','delete');

UNLOCK TABLES;

/*Table structure for table `sys_menu_role` */

DROP TABLE IF EXISTS `sys_menu_role`;

CREATE TABLE `sys_menu_role` (
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_menu_role` */

LOCK TABLES `sys_menu_role` WRITE;

insert  into `sys_menu_role`(`role_id`,`menu_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11);

UNLOCK TABLES;

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_org` */

LOCK TABLES `sys_org` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_org_user` */

DROP TABLE IF EXISTS `sys_org_user`;

CREATE TABLE `sys_org_user` (
  `org_id` bigint(20) unsigned NOT NULL COMMENT '组织ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`org_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_org_user` */

LOCK TABLES `sys_org_user` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role` */

LOCK TABLES `sys_role` WRITE;

insert  into `sys_role`(`id`,`name`) values (1,'管理员');

UNLOCK TABLES;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user` */

LOCK TABLES `sys_user` WRITE;

insert  into `sys_user`(`id`,`username`,`password`) values (1,'admin','738b6ac910c9125916567c428b7b05f0');

UNLOCK TABLES;

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user_role` */

LOCK TABLES `sys_user_role` WRITE;

insert  into `sys_user_role`(`role_id`,`user_id`) values (1,1);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
