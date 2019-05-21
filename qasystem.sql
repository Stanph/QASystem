/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : qasystem

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-04-23 15:27:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answerID` int(11) NOT NULL AUTO_INCREMENT,
  `questionID` int(11) NOT NULL,
  `userID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `createTime` int(11) NOT NULL,
  `answer` varchar(20000) CHARACTER SET utf8 NOT NULL,
  `star` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`answerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentID` int(11) NOT NULL AUTO_INCREMENT,
  `answerID` int(11) NOT NULL,
  `userID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `toUserID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `createTime` int(11) NOT NULL,
  `comment` varchar(20000) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`commentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `questionID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `createTime` int(11) NOT NULL,
  `question` varchar(255) CHARACTER SET utf8 NOT NULL,
  `detail` varchar(20000) CHARACTER SET utf8 NOT NULL,
  `star` int(11) NOT NULL DEFAULT '0',
  `pageviews` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`questionID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for star_answer
-- ----------------------------
DROP TABLE IF EXISTS `star_answer`;
CREATE TABLE `star_answer` (
  `answerID` int(11) NOT NULL,
  `userID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `starTime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for star_question
-- ----------------------------
DROP TABLE IF EXISTS `star_question`;
CREATE TABLE `star_question` (
  `questionID` int(11) NOT NULL,
  `userID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `starTime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `userType` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
