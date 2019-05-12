/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - school
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`school` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `school`;

/*Table structure for table `absentee_record` */

DROP TABLE IF EXISTS `absentee_record`;

CREATE TABLE `absentee_record` (
  `absentee_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `t_u_user` bigint(20) DEFAULT NULL,
  `absentee_date` datetime DEFAULT NULL,
  `buildingid` bigint(20) DEFAULT NULL,
  `dorid` bigint(20) DEFAULT NULL,
  `absentee_desc` varchar(255) DEFAULT NULL,
  `abs_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`absentee_id`),
  KEY `FKpl1t9vq784hyu4ugtuy6sy8c5` (`abs_user_id`),
  CONSTRAINT `FKpl1t9vq784hyu4ugtuy6sy8c5` FOREIGN KEY (`abs_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `absentee_record` */

/*Table structure for table `building` */

DROP TABLE IF EXISTS `building`;

CREATE TABLE `building` (
  `building_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_desc` varchar(255) DEFAULT NULL,
  `gender_in` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`building_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `building` */

insert  into `building`(`building_id`,`b_desc`,`gender_in`,`user_id`) values (1,'信息技术学院','女',2),(2,'机电工程学院','男',3);

/*Table structure for table `dormitory` */

DROP TABLE IF EXISTS `dormitory`;

CREATE TABLE `dormitory` (
  `dormitory_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dor_phone` varchar(255) DEFAULT NULL,
  `dor_Mnumber` int(11) DEFAULT NULL,
  `dor_number` int(11) DEFAULT NULL,
  `gender_in` varchar(255) DEFAULT NULL,
  `dormitory_name` varchar(255) DEFAULT NULL,
  `dor_buil_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dormitory_id`),
  KEY `FK6ua4ms7i2s78im7x2r4q3yq1h` (`dor_buil_id`),
  CONSTRAINT `FK6ua4ms7i2s78im7x2r4q3yq1h` FOREIGN KEY (`dor_buil_id`) REFERENCES `building` (`building_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `dormitory` */

insert  into `dormitory`(`dormitory_id`,`dor_phone`,`dor_Mnumber`,`dor_number`,`gender_in`,`dormitory_name`,`dor_buil_id`) values (1,'15072653086',6,2,'女','101',1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `usercode` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `identity` int(11) DEFAULT NULL,
  `check_in` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `building_id` bigint(20) DEFAULT NULL,
  `user_dor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKcoduuhlltesnlnhmuwkwrg4kd` (`user_dor_id`),
  CONSTRAINT `FKcoduuhlltesnlnhmuwkwrg4kd` FOREIGN KEY (`user_dor_id`) REFERENCES `dormitory` (`dormitory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`usercode`,`password`,`gender`,`phone`,`identity`,`check_in`,`state`,`building_id`,`user_dor_id`) values (1,'admin','admin','password',NULL,NULL,2,NULL,1,NULL,NULL),(2,'胡世港','11111111','admin','男','13123214141',1,NULL,1,1,NULL),(3,'桃花','22222222','123456','女','13123214142',1,NULL,1,2,NULL),(4,'马慧','20160630221','123456','女','13123214143',0,1,1,1,1),(5,'张三','20160630131','120456','女','42727173071',0,1,1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
