/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : atm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001
auther                : jamesZhan

Date: 2017-12-21 15:39:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `cardId` int(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `balance` double(10,2) NOT NULL,
  `cardNum` varchar(15) NOT NULL,
  `registeredCity` int(3) NOT NULL,
  `isUsed` int(1) NOT NULL,
  PRIMARY KEY (`cardId`),
  KEY `userId` (`userId`),
  KEY `registeredCity` (`registeredCity`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `card_ibfk_2` FOREIGN KEY (`registeredCity`) REFERENCES `city` (`cityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('1', '888888', '1', '699.00', '123456789', '1', '1');
INSERT INTO `card` VALUES ('2', '888888', '1', '1050.00', '888888888', '1', '1');
INSERT INTO `card` VALUES ('3', '20171220', '2', '2000.00', '20171220', '1', '1');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `cityId` int(100) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '杭州');
INSERT INTO `city` VALUES ('2', '上海');
INSERT INTO `city` VALUES ('3', '北京');
INSERT INTO `city` VALUES ('4', '武汉');
INSERT INTO `city` VALUES ('5', '深圳');
INSERT INTO `city` VALUES ('6', '其他');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` int(2) NOT NULL,
  `managerNum` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', '20152104', '666666');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(100) NOT NULL AUTO_INCREMENT,
  `userName` varchar(5) NOT NULL,
  `gender` int(1) NOT NULL COMMENT '性别',
  `age` int(3) NOT NULL,
  `address` varchar(100) NOT NULL,
  `telNum` varchar(11) NOT NULL,
  `IDNum` varchar(18) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'james', '1', '21', '杭州师范大学仓前校区', '400823823', '330183199605303312');
INSERT INTO `user` VALUES ('2', '风', '1', '22', '杭师大下沙校区', '13852013141', '330021821814721');

-- ----------------------------
-- Table structure for workrecord
-- ----------------------------
DROP TABLE IF EXISTS `workrecord`;
CREATE TABLE `workrecord` (
  `recordId` int(3) NOT NULL,
  `cardNum` int(3) NOT NULL,
  `type` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `moneyNum` double(10,0) NOT NULL,
  `balance` double(10,0) NOT NULL,
  `workCity` int(3) NOT NULL,
  PRIMARY KEY (`recordId`),
  KEY `workCity` (`workCity`),
  KEY `cardNum` (`cardNum`),
  CONSTRAINT `workrecord_ibfk_1` FOREIGN KEY (`workCity`) REFERENCES `city` (`cityId`),
  CONSTRAINT `workrecord_ibfk_2` FOREIGN KEY (`cardNum`) REFERENCES `card` (`cardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workrecord
-- ----------------------------
INSERT INTO `workrecord` VALUES ('1', '1', '存款', '2017-12-12 15:15:36', '100', '100', '1');
INSERT INTO `workrecord` VALUES ('2', '1', '取款', '2017-11-08 15:16:25', '300', '200', '1');
INSERT INTO `workrecord` VALUES ('3', '1', '取款', '2017-12-12 18:40:18', '50', '400', '1');
INSERT INTO `workrecord` VALUES ('4', '1', '取款', '2017-12-12 19:05:17', '100', '900', '1');
INSERT INTO `workrecord` VALUES ('5', '1', '取款', '2017-12-12 19:21:50', '201', '699', '2');
INSERT INTO `workrecord` VALUES ('6', '1', '存款', '2017-12-12 19:26:01', '200', '899', '1');
INSERT INTO `workrecord` VALUES ('7', '1', '转账', '2017-12-12 19:49:28', '100', '799', '2');
INSERT INTO `workrecord` VALUES ('8', '1', '取款', '2017-12-13 13:47:54', '200', '599', '1');
INSERT INTO `workrecord` VALUES ('9', '1', '存款', '2017-12-13 13:56:44', '200', '799', '1');
INSERT INTO `workrecord` VALUES ('10', '1', '转账', '2017-12-13 14:10:34', '100', '699', '1');
