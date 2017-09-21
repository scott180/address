/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : address

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2017-09-21 14:36:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for addresslist
-- ----------------------------
DROP TABLE IF EXISTS `addresslist`;
CREATE TABLE `addresslist` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) default NULL,
  `name` varchar(255) collate utf8_unicode_ci default NULL,
  `tel` varchar(255) collate utf8_unicode_ci default NULL,
  `email` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of addresslist
-- ----------------------------
INSERT INTO `addresslist` VALUES ('63', '59', '1', '18522221111', '1@qq.com');
INSERT INTO `addresslist` VALUES ('64', '59', '12', '18522221111', '1@qq.com');
INSERT INTO `addresslist` VALUES ('65', '58', 'dsd', '18522221111', '11sa@qq.com');
INSERT INTO `addresslist` VALUES ('66', '59', '11112å', '18522221111', '1@qq.com');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '合肥');
INSERT INTO `city` VALUES ('2', '南京');
INSERT INTO `city` VALUES ('3', '上海');
INSERT INTO `city` VALUES ('4', '北京');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_unicode_ci default NULL,
  `cityId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('57', '去', '1');
INSERT INTO `user` VALUES ('58', '是', '1');
INSERT INTO `user` VALUES ('59', '是的', '1');
