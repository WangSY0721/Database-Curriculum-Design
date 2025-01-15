/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : todayhead

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 15/11/2024 13:01:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for at_demo_dynasty
-- ----------------------------
DROP TABLE IF EXISTS `at_demo_dynasty`;
CREATE TABLE `at_demo_dynasty`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `capital` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '首都',
  `stYear` int NULL DEFAULT NULL COMMENT '开国时间',
  `edYear` int NULL DEFAULT NULL COMMENT '亡国时间',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:禁用,1:启用)',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:未删除,1:已删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '示例_朝代表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_demo_dynasty
-- ----------------------------
INSERT INTO `at_demo_dynasty` VALUES ('1', '南宋', '111', '杭州', 1127, 1279, 11, '1', '1', '2023-12-10 15:44:11', '2023-12-10 15:44:13', '2023-12-10 15:44:15');
INSERT INTO `at_demo_dynasty` VALUES ('1733770881022332930', '北宋4', NULL, '开封', 960, 1127, 167, '1', '0', '2023-12-10 16:49:25', '2023-12-10 16:49:25', '2023-12-10 16:49:25');
INSERT INTO `at_demo_dynasty` VALUES ('1733774834560782337', '北宋1', NULL, '开封', NULL, NULL, NULL, '1', '0', '2023-12-10 17:05:07', '2023-12-20 21:40:30', '2023-12-05 17:05:07');
INSERT INTO `at_demo_dynasty` VALUES ('1733776296208613378', '北宋2', NULL, '开封', NULL, NULL, NULL, '1', '0', '2023-12-10 17:10:56', '2023-12-10 17:10:56', '2023-12-10 17:10:56');
INSERT INTO `at_demo_dynasty` VALUES ('1733776959852376065', '北宋3', NULL, '开封', NULL, NULL, NULL, '1', '1', '2023-12-10 17:13:34', '2023-12-10 17:13:34', '2023-12-10 17:13:34');
INSERT INTO `at_demo_dynasty` VALUES ('1734213364663463937', '北宋', NULL, '开封', 960, 1127, 167, '1', '1', '2023-12-11 22:07:41', '2023-12-13 22:17:39', '2023-12-11 22:07:41');
INSERT INTO `at_demo_dynasty` VALUES ('1737856076977655809', '', NULL, '', NULL, NULL, NULL, NULL, '0', '2023-12-21 23:22:31', '2023-12-21 23:22:31', '2023-12-21 23:22:31');
INSERT INTO `at_demo_dynasty` VALUES ('1737856108032282626', '22', '<p>22</p>', '22', 22, 22, 0, '1', '0', '2023-12-21 23:22:39', '2024-04-24 23:39:42', '2023-12-15 23:22:39');

-- ----------------------------
-- Table structure for at_demo_king
-- ----------------------------
DROP TABLE IF EXISTS `at_demo_king`;
CREATE TABLE `at_demo_king`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `dynastyId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '朝代Id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `miaoHao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '庙号',
  `nianHao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年号',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:禁用,1:启用)',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:已删除,1:未删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '示例_皇上表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_demo_king
-- ----------------------------
INSERT INTO `at_demo_king` VALUES ('1734224865193476098', '1733774834560782337', '赵匡胤', '<p>2222</p>', '222', '`111', '0', '0', '2023-12-18 23:05:38', '2023-12-18 23:11:50', '2023-12-18 23:05:38');
INSERT INTO `at_demo_king` VALUES ('1736765620072038402', '1733776296208613378', '333', '<p>333</p>', '333', '333', '1', '1', '2023-12-18 23:09:26', '2023-12-18 23:09:26', '2023-12-18 23:09:26');

-- ----------------------------
-- Table structure for at_users_collect
-- ----------------------------
DROP TABLE IF EXISTS `at_users_collect`;
CREATE TABLE `at_users_collect`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `usersId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户Id',
  `workId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作品Id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:启用,1:禁用)',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:未删除,1:已删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户_收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_users_collect
-- ----------------------------
INSERT INTO `at_users_collect` VALUES ('1857080126156607490', '1740737903736152066', '1856719831294873602', '0', '0', '2024-11-14 23:16:20', '2024-11-14 23:16:20', '2024-11-14 23:16:20');
INSERT INTO `at_users_collect` VALUES ('1857285557500882946', '1742550907872137217', '1856719831294873602', '0', '0', '2024-11-15 12:52:38', '2024-11-15 12:52:38', '2024-11-15 12:52:38');

-- ----------------------------
-- Table structure for at_users_comment
-- ----------------------------
DROP TABLE IF EXISTS `at_users_comment`;
CREATE TABLE `at_users_comment`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `usersId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户Id',
  `workId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作品Id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:禁用,1:启用)',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:未删除,1:已删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户_评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_users_comment
-- ----------------------------
INSERT INTO `at_users_comment` VALUES ('1857082676083761154', '1740737903736152066', '1856719831294873602', '不错', '<p>确实 写的不错</p>', '0', '0', '2024-11-14 23:26:28', '2024-11-14 23:26:28', '2024-11-14 23:26:28');
INSERT INTO `at_users_comment` VALUES ('1857086341783822338', NULL, '1856719831294873602', 'dddd', '<p>dddd</p>', '0', '0', '2024-11-14 23:41:02', '2024-11-14 23:41:02', '2024-11-14 23:41:02');
INSERT INTO `at_users_comment` VALUES ('1857086375761879041', NULL, '1856719831294873602', 'dddd', '<p>ddddffff</p>', '0', '0', '2024-11-14 23:41:10', '2024-11-14 23:41:10', '2024-11-14 23:41:10');
INSERT INTO `at_users_comment` VALUES ('1857086876742131713', '1742550907872137217', '1856719831294873602', '我也评论', '<p>我也评论我也评论我也评论我也评论<br/></p>', '0', '0', '2024-11-14 23:43:09', '2024-11-14 23:43:09', '2024-11-14 23:43:09');
INSERT INTO `at_users_comment` VALUES ('1857285697691299842', '1740737903736152066', '1857285440379138049', '确实不错啊,写的不错', '<p>确实不错啊,写的不错</p>', '0', '0', '2024-11-15 12:53:12', '2024-11-15 12:53:12', '2024-11-15 12:53:12');

-- ----------------------------
-- Table structure for at_users_desc
-- ----------------------------
DROP TABLE IF EXISTS `at_users_desc`;
CREATE TABLE `at_users_desc`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `nickName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别:0:未知,1:男,1:女',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:禁用,1:启用)',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:已删除,1:未删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  `lastLoginTime` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_users_desc
-- ----------------------------
INSERT INTO `at_users_desc` VALUES ('1740737903736152066', '姓名_02112', '昵称_02112', '22@22.com', 'sha256$VCF$c14217abe8869c4da6bd5b3a14eee9ba374b000e50782aa7210c759ad582463d', NULL, '手机112', '1', '1', '0', '2023-12-29 22:13:52', '2024-11-15 12:55:46', '2023-12-29 22:13:52', '2024-11-15 12:51:05');
INSERT INTO `at_users_desc` VALUES ('1742550907872137217', '姓名_0133', '昵称_0122', '11@11.com', 'sha256$6ti$138cce9278a873e1ae57c7bdae22aa81662c8ca8ab77904522ce690794a50f94', '<p>3333333</p>', '手机11', '0', '1', '0', '2024-01-03 22:18:06', '2024-11-15 12:50:54', '2024-01-03 22:18:06', '2024-11-15 12:50:54');
INSERT INTO `at_users_desc` VALUES ('1783513651609661442', '姓名_04', '昵称_04', '44@44.com', 'sha256$VCF$c14217abe8869c4da6bd5b3a14eee9ba374b000e50782aa7210c759ad582463d', '<p>4444</p>', '44', '2', '1', '0', '2024-04-25 23:09:25', '2024-04-29 11:04:34', '2024-04-25 23:09:25', '2024-04-29 11:04:34');
INSERT INTO `at_users_desc` VALUES ('1783513808199806977', '姓名_05', '昵称_05', '55@55.com', 'sha256$Hjz$6ea78702dfd8f46bb6113cf08f3857d0fa35abf1d4101336d3887f80307965b1', '<p>555</p>', '55', '0', '1', '0', '2024-04-25 23:10:03', '2024-04-29 11:10:10', '2024-04-25 23:10:03', NULL);

-- ----------------------------
-- Table structure for at_users_mess
-- ----------------------------
DROP TABLE IF EXISTS `at_users_mess`;
CREATE TABLE `at_users_mess`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `fromUsersId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户Id(发送者)',
  `toUsersId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户Id(接收者)',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:已录入,1:发布,2:审核通过,3:审核不通过)',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:未删除,1:已删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户_私信表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_users_mess
-- ----------------------------
INSERT INTO `at_users_mess` VALUES ('1857274378858643458', '1740737903736152066', '1783513808199806977', '1111', '<p>1111</p>', '0', '0', '2024-11-15 12:08:13', '2024-11-15 12:08:13', '2024-11-15 12:08:13');
INSERT INTO `at_users_mess` VALUES ('1857275968596328449', '1740737903736152066', '1742550907872137217', '测试一下', '<p>测试一下<br/></p>', '0', '0', '2024-11-15 12:14:32', '2024-11-15 12:14:32', '2024-11-15 12:14:32');
INSERT INTO `at_users_mess` VALUES ('1857285990990589953', '1742550907872137217', '1740737903736152066', '你好.', '<p>你好.你好.你好.你好.<br/></p>', '0', '0', '2024-11-15 12:54:22', '2024-11-15 12:54:22', '2024-11-15 12:54:22');
INSERT INTO `at_users_mess` VALUES ('1857286269790171137', '1740737903736152066', '1783513651609661442', '222', '<p>2222222222222222</p>', '0', '0', '2024-11-15 12:55:28', '2024-11-15 12:55:28', '2024-11-15 12:55:28');

-- ----------------------------
-- Table structure for at_work
-- ----------------------------
DROP TABLE IF EXISTS `at_work`;
CREATE TABLE `at_work`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `usersId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工Id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态:(0:启用,1:禁用)',
  `viewCount` int NULL DEFAULT NULL COMMENT '展现量',
  `delFlag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '逻辑删除(0:未删除,1:已删除)',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pubTime` datetime NULL DEFAULT NULL COMMENT '发布时间(排序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '作品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of at_work
-- ----------------------------
INSERT INTO `at_work` VALUES ('1856719479854141442', '1742550907872137217', '测试一下', '<p>测试一下<br/></p>', '1', 1, '0', '2024-11-13 23:23:15', '2024-11-14 23:07:20', '2024-11-13 23:23:15');
INSERT INTO `at_work` VALUES ('1856719831294873602', '1740737903736152066', '测试03333111', '<p>测试0333311<br/></p>', '1', 63, '0', '2024-11-13 23:24:39', '2024-11-15 12:54:02', '2024-11-13 23:24:39');
INSERT INTO `at_work` VALUES ('1857274202878230530', '1740737903736152066', 'aaa', '<p>aaa</p>', '1', 0, '0', '2024-11-15 12:07:31', '2024-11-15 12:07:31', '2024-11-15 12:07:31');
INSERT INTO `at_work` VALUES ('1857285440379138049', '1742550907872137217', '测试——0002', '<p>测试——0002<br/></p>', '1', 4, '0', '2024-11-15 12:52:10', '2024-11-15 12:53:15', '2024-11-15 12:52:10');

SET FOREIGN_KEY_CHECKS = 1;
