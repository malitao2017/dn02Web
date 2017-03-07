/*
Navicat MySQL Data Transfer

Source Server         : 本机连接测试邮件导入功能
Source Server Version : 50024
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50024
File Encoding         : 65001

Date: 2015-09-11 17:23:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_emp`
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `salary` double default NULL,
  `age` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('1', '小黑', '1212', '23');
INSERT INTO `t_emp` VALUES ('3', '小兰', '3000', '21');
INSERT INTO `t_emp` VALUES ('4', '小绿', '4000', '20');
INSERT INTO `t_emp` VALUES ('5', 'lg', '3000', '21');
INSERT INTO `t_emp` VALUES ('10', '凤飞飞', '444', '33');
INSERT INTO `t_emp` VALUES ('12', '是你么', '321', '11');
INSERT INTO `t_emp` VALUES ('14', '333范德萨的', '33', '33');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `age` int(11) default NULL,
  `gendar` char(1) default NULL,
  `brithday` date default NULL,
  `salary` double default NULL,
  `info` text,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_user
-- ----------------------------
