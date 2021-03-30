/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : intern

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 30/03/2021 23:53:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `clientid` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(100) DEFAULT NULL COMMENT '性别',
  `DOB` varchar(100) DEFAULT NULL COMMENT '生日',
  `Phone` varchar(200) DEFAULT NULL COMMENT '手机',
  `Job` varchar(200) DEFAULT NULL COMMENT '职业',
  `Other` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`clientid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
BEGIN;
INSERT INTO `client` VALUES ('0b65b6', '小吴', '男', '', '', '', '');
INSERT INTO `client` VALUES ('14dda3', '张三', '男', '1998/11/11', '18888888888', '学生', '看书');
INSERT INTO `client` VALUES ('319262', '小乔', '女', '', '', '', '');
INSERT INTO `client` VALUES ('a60df0', '晓婷', '女', '1999/02/18', '18015725553', '学生', '');
INSERT INTO `client` VALUES ('ce0991', '小菜', '男', '', '', '', '');
INSERT INTO `client` VALUES ('d5cae9', '小刚', '男', '1999/05/08', '', '', '');
INSERT INTO `client` VALUES ('e98685', '李华', '男', '', '', '', '');
INSERT INTO `client` VALUES ('f30b16', '碎花', '男', '', '', '', '');
COMMIT;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `pswd` varchar(100) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` VALUES (1, 'admin', 'admin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
