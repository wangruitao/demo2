/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2017-03-30 13:33:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `permission_code` varchar(255) DEFAULT NULL COMMENT '全系那编码',
  `permission_url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `disabled` int(1) DEFAULT '0' COMMENT '0 未禁用 1 禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '添加', 'add', null, '0', null, '2017-03-25 17:55:01');
INSERT INTO `t_permission` VALUES ('2', '删除', 'del', null, '0', null, '2017-03-25 17:55:01');
INSERT INTO `t_permission` VALUES ('3', '更新', 'update', null, '0', null, '2017-03-25 17:55:01');
INSERT INTO `t_permission` VALUES ('4', '查询', 'query', null, '0', null, '2017-03-25 17:55:01');
INSERT INTO `t_permission` VALUES ('5', '用户新增', 'user:add', null, '0', null, '2017-03-29 16:36:33');
INSERT INTO `t_permission` VALUES ('6', '用户编辑', 'user:edit', null, '0', null, '2017-03-25 17:55:01');
INSERT INTO `t_permission` VALUES ('7', '用户保存', 'user:save', null, '0', null, '2017-03-29 16:36:55');
INSERT INTO `t_permission` VALUES ('8', '用户更新', 'user:update', null, '0', null, '2017-03-29 16:37:07');
INSERT INTO `t_permission` VALUES ('9', '用户删除', 'user:delete', null, '0', null, '2017-03-29 16:37:30');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) NOT NULL COMMENT '角色编码',
  `remarker` varchar(255) DEFAULT NULL COMMENT '备注',
  `disabled` int(1) DEFAULT '0' COMMENT '0 未禁用 1 禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', 'admin', null, '0', null, '2017-03-29 16:45:11');
INSERT INTO `t_role` VALUES ('2', '管理员', 'manager', null, '0', null, '2017-03-29 16:45:12');
INSERT INTO `t_role` VALUES ('3', '普通用户', 'normal', null, '0', null, '2017-03-29 16:45:13');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1', '2', '2017-03-25 17:56:54', '2017-03-25 18:04:13');
INSERT INTO `t_role_permission` VALUES ('2', '1', '5', '2017-03-25 17:56:54', '2017-03-25 18:04:13');
INSERT INTO `t_role_permission` VALUES ('4', '2', '3', '2017-03-25 17:56:54', '2017-03-25 18:04:13');
INSERT INTO `t_role_permission` VALUES ('5', '2', '6', '2017-03-25 17:56:54', '2017-03-25 18:04:13');
INSERT INTO `t_role_permission` VALUES ('6', '3', '5', '2017-03-25 17:56:54', '2017-03-29 16:38:19');
INSERT INTO `t_role_permission` VALUES ('7', '3', '6', '2017-03-29 16:38:25', '2017-03-29 16:38:25');
INSERT INTO `t_role_permission` VALUES ('8', '3', '7', '2017-03-29 16:38:29', '2017-03-29 16:38:29');
INSERT INTO `t_role_permission` VALUES ('10', '3', '8', '2017-03-29 17:57:49', '2017-03-29 17:57:49');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `locked` int(1) DEFAULT '0' COMMENT '0 未锁定 1锁定',
  `disabled` int(1) DEFAULT '0' COMMENT '0 未禁用 1 禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'tom', 'tom', 'ca88ddddb6c41b56deca2fe29af7f173', 'tomdde23cf4089fb1ba87d2a2881e46508c', '0', '0', '2017-03-25 17:30:25', '2017-03-28 14:37:29');
INSERT INTO `t_user` VALUES ('2', 'jack', 'jack', '123456', null, '0', '0', '2017-03-25 17:30:25', '2017-03-25 17:30:32');
INSERT INTO `t_user` VALUES ('3', 'rose', 'rose', '123456', null, '0', '0', '2017-03-25 17:30:25', '2017-03-25 17:30:33');
INSERT INTO `t_user` VALUES ('4', '123', '123', 'a30d67ad816cf7ae9e153f6be872d377', '1233c5f04a6d2185e250c8a2fceff45177c', '0', '0', '2017-03-29 14:47:43', '2017-03-29 14:47:43');
INSERT INTO `t_user` VALUES ('5', '123', '123', 'c6353a646de4336522e0b73702cab698', '12380e8a0ffc97a2a0f0332bcafadb18e97', '0', '0', '2017-03-29 14:50:23', '2017-03-29 14:50:23');
INSERT INTO `t_user` VALUES ('6', '123', '123', '5953b7e1fb20cd7ef0e91cfae4f3fd32', '1232ea9cdbe50f7a136c1a614957c84ced1', '0', '0', '2017-03-29 14:51:39', '2017-03-29 14:51:39');
INSERT INTO `t_user` VALUES ('7', '123', '123', '5953b7e1fb20cd7ef0e91cfae4f3fd32', '1232ea9cdbe50f7a136c1a614957c84ced1', '0', '0', '2017-03-29 14:52:00', '2017-03-29 14:52:00');
INSERT INTO `t_user` VALUES ('12', 'sasds', 'fgvcv35345bcvb', '2d8b6763a135a349f2d44d95e6884c77', 'sadasdf4a515302d7ac3210ede4062c619c2642', '0', '0', '2017-03-29 16:56:30', '2017-03-29 17:58:20');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '3', '2017-03-25 17:56:24', '2017-03-29 16:38:50');
INSERT INTO `t_user_role` VALUES ('2', '1', '3', '2017-03-25 17:56:24', '2017-03-25 18:04:38');
INSERT INTO `t_user_role` VALUES ('3', '2', '2', '2017-03-25 17:56:24', '2017-03-25 18:04:38');
INSERT INTO `t_user_role` VALUES ('4', '2', '3', '2017-03-25 17:56:24', '2017-03-25 18:04:38');
INSERT INTO `t_user_role` VALUES ('5', '3', '3', '2017-03-25 17:56:24', '2017-03-25 18:04:38');
