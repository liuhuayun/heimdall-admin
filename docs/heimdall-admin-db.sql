/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_department`
--

DROP TABLE IF EXISTS `sys_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(32) NOT NULL,
  `pid` bigint(20) NOT NULL,
  `seq_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_department`
--

LOCK TABLES `sys_department` WRITE;
/*!40000 ALTER TABLE `sys_department` DISABLE KEYS */;
INSERT INTO `sys_department` VALUES (1332653502945595393,0,'2020-11-28 19:52:09.253000',0,'2020-11-28 19:52:09.253000','',0,1,'武当山集团',0,0),(1332653564522172418,0,'2020-11-28 19:52:23.945000',0,'2020-11-28 19:52:23.945000','',0,1,'武当山餐饮分公司',1332653502945595393,0),(1332653612517593089,0,'2020-11-28 19:52:35.389000',0,'2020-11-28 19:52:35.389000','',0,1,'武当山后勤服务集团',1332653502945595393,0),(1332976506778984450,1332660082902622210,'2020-11-29 17:15:39.379000',1332660082902622210,'2020-11-29 17:15:39.379000','炼丹分公司',NULL,1,'炼丹分公司',1332653502945595393,0),(1332976545878286337,1332660082902622210,'2020-11-29 17:15:48.701000',1332660082902622210,'2020-11-29 17:15:48.701000',NULL,NULL,1,'财务部',1332653612517593089,0),(1332976573422280706,1332660082902622210,'2020-11-29 17:15:55.269000',1332660082902622210,'2020-11-29 17:15:55.269000',NULL,NULL,1,'运输队',1332653612517593089,0),(1333060399620280322,1332660082902622210,'2020-11-29 22:49:00.992000',1332660082902622210,'2020-11-29 22:49:00.992000','峨眉分舵',NULL,1,'峨眉分舵',1332653502945595393,0);
/*!40000 ALTER TABLE `sys_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_item`
--

DROP TABLE IF EXISTS `sys_dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_item` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `item_value` varchar(32) NOT NULL,
  `label` varchar(32) NOT NULL,
  `seq_no` int(11) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统字典条目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_item`
--

LOCK TABLES `sys_dict_item` WRITE;
/*!40000 ALTER TABLE `sys_dict_item` DISABLE KEYS */;
INSERT INTO `sys_dict_item` VALUES (1332957894403973122,1332660082902622210,'2020-11-29 16:01:41.842000',1332660082902622210,'2020-11-29 16:05:08.478000','',NULL,'1','男',3,1332954329350455297),(1332958804987371522,1332660082902622210,'2020-11-29 16:05:18.944000',1332660082902622210,'2020-12-05 20:17:07.074000','',NULL,'2','女',4,1332954329350455297),(1332958860901638145,1332660082902622210,'2020-11-29 16:05:32.274000',1332660082902622210,'2020-11-29 16:05:32.274000','',NULL,'0','分不清',5,1332954329350455297),(1333005417273483265,1332660082902622210,'2020-11-29 19:10:32.178000',1332660082902622210,'2020-11-29 19:10:32.178000','',NULL,'1','促销消息',1,1333005358104436737),(1333005453084450818,1332660082902622210,'2020-11-29 19:10:40.716000',1332660082902622210,'2020-11-29 19:10:40.716000','',NULL,'2','系统通知',3,1333005358104436737);
/*!40000 ALTER TABLE `sys_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m65awrgfo5up8ergsrsw7c6q5` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统字典分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1332954329350455297,1332660082902622210,'2020-11-29 15:47:31.868000',1332660082902622210,'2020-11-29 15:47:31.868000','性别',NULL,'gender'),(1333005358104436737,1332660082902622210,'2020-11-29 19:10:18.072000',1332660082902622210,'2020-11-29 19:10:18.072000','站内信分类',NULL,'messageType');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `app_host_ip` varchar(255) DEFAULT NULL,
  `app_name` varchar(255) DEFAULT NULL,
  `app_port` varchar(255) DEFAULT NULL,
  `browser_name` varchar(255) DEFAULT NULL,
  `browser_version` varchar(255) DEFAULT NULL,
  `business_type` int(11) DEFAULT NULL,
  `consuming_time` bigint(20) DEFAULT NULL,
  `exception_message` longtext,
  `method` varchar(255) DEFAULT NULL,
  `request_ip` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `request_param` varchar(255) DEFAULT NULL,
  `request_time` datetime(6) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `response_result` longtext,
  `status` int(11) DEFAULT NULL,
  `terminal_os_name` varchar(255) DEFAULT NULL,
  `terminal_type` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_location` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_message`
--

DROP TABLE IF EXISTS `sys_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_message` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `content` longtext,
  `msg_type` int(11) NOT NULL,
  `send_time` datetime(6) NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_np0die2h8fanv1jg5rxela6pn` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统通知消息内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_message`
--

LOCK TABLES `sys_message` WRITE;
/*!40000 ALTER TABLE `sys_message` DISABLE KEYS */;
INSERT INTO `sys_message` VALUES (1333006117302820865,1332660082902622210,'2020-11-29 19:13:19.078000',1332660082902622210,'2020-11-29 19:13:19.078000',NULL,NULL,'全场七折，速来抢购全场七折，速来抢购全场七折，速来抢购全场七折，速来抢购',2,'2020-11-29 19:13:19.075000',1332660082902622210,'全场七折，速来抢购');
/*!40000 ALTER TABLE `sys_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_message_box`
--

DROP TABLE IF EXISTS `sys_message_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_message_box` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `message_id` bigint(20) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `received_time` datetime(6) NOT NULL,
  `receiver_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统通知消息收发表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_message_box`
--

LOCK TABLES `sys_message_box` WRITE;
/*!40000 ALTER TABLE `sys_message_box` DISABLE KEYS */;
INSERT INTO `sys_message_box` VALUES (1333306015994638338,1332660082902622210,'2020-11-30 15:05:00.498000',1332660082902622210,'2020-11-30 15:05:00.498000',NULL,NULL,1333006117302820865,2,'2020-11-30 15:05:00.489000',1321506328853696514,1,'全场七折，速来抢购');
/*!40000 ALTER TABLE `sys_message_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `code` varchar(32) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `seq_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hfsq7ug433qblsbuvnh5e6au7` (`name`),
  UNIQUE KEY `UK_r5b7w4kya2gmxcc1asyns6odk` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1332654295924903938,0,'2020-11-28 19:55:18.312000',0,'2020-11-28 19:55:18.312000','',0,'首席执行官','CEO',1,0),(1332654322680369153,0,'2020-11-28 19:55:24.704000',0,'2020-11-28 19:55:24.704000','',0,'首席财务官','CFO',1,0),(1332654372030550017,0,'2020-11-28 19:55:36.470000',0,'2020-11-28 19:55:36.470000','',0,'首席外星人','UFO',1,2);
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post_user`
--

DROP TABLE IF EXISTS `sys_post_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post_user` (
  `user_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  KEY `FKmd76x5iv6mm65f0vmb3glpus3` (`post_id`),
  KEY `FKsn73rux5yythc9g2pdidpf6tv` (`user_id`),
  CONSTRAINT `FKmd76x5iv6mm65f0vmb3glpus3` FOREIGN KEY (`post_id`) REFERENCES `sys_post` (`id`),
  CONSTRAINT `FKsn73rux5yythc9g2pdidpf6tv` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post_user`
--

LOCK TABLES `sys_post_user` WRITE;
/*!40000 ALTER TABLE `sys_post_user` DISABLE KEYS */;
INSERT INTO `sys_post_user` VALUES (1332661471821201410,1332654322680369153),(1332661269903212545,1332654322680369153),(1332660082902622210,1332654295924903938),(1332660082902622210,1332654322680369153),(1332660082902622210,1332654372030550017),(1332660767639527426,1332654372030550017),(1332660767639527426,1332654295924903938),(1332660767639527426,1332654322680369153),(1333060149291634690,1332654295924903938),(1333060149291634690,1332654322680369153),(1333060149291634690,1332654372030550017);
/*!40000 ALTER TABLE `sys_post_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1',
  `icon` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `perm` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `res_type` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `is_affix` tinyint(1) NOT NULL DEFAULT '0',
  `is_keep_alive` tinyint(1) NOT NULL DEFAULT '0',
  `target` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0',
  `path` varchar(255) DEFAULT NULL,
  `seq_no` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_820u0b0wa65mqls1qe589hhsv` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES (1321510438613082113,NULL,'2020-10-29 01:53:35.684000',1321506328853696514,'2020-11-24 20:41:59.296000','管理系统用户\\角色资源等信息',NULL,1,'fas fa-cog',NULL,'Sys',NULL,0,1,'系统管理',NULL,0,1,NULL,'Layout',0,'/sys',1),(1321510438801825793,NULL,'2020-10-29 01:53:35.745000',1321506328853696514,'2020-11-24 20:58:14.690000','管理系统用户信息，启用禁用账户,重置密码',NULL,1,'far fa-address-card',NULL,'SysUser',NULL,1321510438613082113,2,'用户管理','',0,1,NULL,'@views/sys/user/index',0,'/sys/user',1),(1321510438801825794,1321506328853696514,'2020-10-29 01:53:35.745000',1321506328853696514,'2020-11-07 21:31:20.034000','管理系统角色，为角色分配系统权限',NULL,1,'fas fa-users',NULL,'SysRole',NULL,1321510438613082113,2,'角色管理','',0,1,NULL,'@views/sys/role/index',0,'/sys/role',2),(1321510438801825795,1321506328853696514,'2020-10-29 01:53:35.745000',1332660082902622210,'2020-11-29 23:25:48.835000','管理系统访问日志，可以查看系统访问情况',NULL,1,'fas fa-align-justify',NULL,'SysSysLog',NULL,1321510438613082113,2,'系统日志','',0,1,NULL,'@views/sys/syslog/index',0,'/sys/log',12),(1321510438801825796,1321506328853696514,'2020-10-29 01:53:35.745000',1321506328853696514,'2020-11-24 20:42:09.940000','管理系统内菜单和资源权限，设置菜单图标文字等信息，修改系统资源信息，会影响系统权限判断逻辑，修改不当，可能导致系统无法正确访问',NULL,1,'fas fa-cubes',NULL,'SysResource',NULL,1321510438613082113,2,'资源管理','',0,1,NULL,'@views/sys/resource/index',0,'/sys/resource',3),(1322514297393299458,1321506328853696514,'2020-10-31 20:22:34.280000',1321506328853696514,'2020-11-22 22:09:37.938000','',0,1,'fas fa-user-plus','','saveSysUser','sys:user:save',1321510438801825793,3,'添加用户','/sys/user/save',0,1,'',NULL,0,NULL,0),(1322514406554255361,1321506328853696514,'2020-10-31 20:23:00.309000',1321506328853696514,'2020-11-22 22:07:39.579000','',0,1,'far fa-edit','','updateSysUser','sys:user:update',1321510438801825793,3,'修改用户','/sys/user/update',0,1,'',NULL,0,NULL,0),(1324555351789527042,1321506328853696514,'2020-11-06 11:32:59.572000',1321506328853696514,'2020-11-22 22:02:23.831000','系统字典分类和字典数据管理，实现枚举值的字典化统一管理',NULL,1,'fas fa-wallet',NULL,'SysDict',NULL,1321510438613082113,2,'系统字典',NULL,0,1,NULL,'@views/sys/dict/index',0,'/sys/dict',21),(1324766365478912002,1321506328853696514,'2020-11-07 01:31:29.156000',1321506328853696514,'2020-11-22 22:08:02.648000',NULL,NULL,1,'fas fa-list-ol',NULL,'SysUserList','sys:user:list',1321510438801825793,3,'查看系统用户列表数据','/sys/user/list',0,1,NULL,'',1,'',0),(1327636209513574401,1321506328853696514,'2020-11-14 23:35:13.294000',1321506328853696514,'2020-11-22 22:02:49.316000','站内信的管理、发送、删除等',NULL,1,'fas fa-volume-up',NULL,'SysMessage',NULL,1321510438613082113,2,'站内消息',NULL,0,1,NULL,'@views/sys/message/index',0,'/sys/message',22),(1328243256491786241,1321506328853696514,'2020-11-16 15:47:24.572000',1321506328853696514,'2020-11-16 16:01:32.727000','查看系统日志列表',NULL,1,'fas fa-egg',NULL,'SysLogList','sys:log:list',1321510438801825795,3,'查看系统日志列表','/sys/log/list',0,1,NULL,'',1,'',0),(1329391155728654337,1321506328853696514,'2020-11-19 19:48:45.076000',1332660082902622210,'2020-11-29 21:58:34.998000','查看当前在线用户，强制用户下线',NULL,1,'fas fa-diagnoses',NULL,'SysOnlineUser',NULL,1321510438613082113,2,'在线用户',NULL,0,1,NULL,'@/views/sys/onlineuser/index',0,'/sys/onlineuser',21),(1329456580344688642,1321506328853696514,'2020-11-20 00:08:43.521000',1321506328853696514,'2020-11-23 01:28:31.942000',NULL,NULL,1,'fas fa-users',NULL,'SysOnlineUserList','sys:online:list',1329391155728654337,3,'查看在线用户列表','/sys/online/list',0,1,NULL,'',1,'',0),(1329460795418869762,1321506328853696514,'2020-11-20 00:25:28.473000',1321506328853696514,'2020-11-20 00:25:28.473000',NULL,NULL,1,'fas fa-egg',NULL,'SysLogDeleteBatch','sys:log:batchDelete',1321510438801825795,3,'批量删除系统日志','/sys/log/batch/delete',0,1,NULL,'',1,'',0),(1330144570763161602,1321506328853696514,'2020-11-21 21:42:33.218000',1321506328853696514,'2020-11-22 22:08:56.288000',NULL,NULL,1,'fas fa-lock',NULL,'SysUserLock','sys:user:lock',1321510438801825793,3,'锁定解锁用户','/sys/user/update/locked',0,1,NULL,'',0,'/pet',0),(1331502738885840897,1321506328853696514,'2020-11-25 15:39:25.732000',1321506328853696514,'2020-11-25 17:43:19.346000','管理组织机构层级信息',NULL,1,'far fa-calendar-alt',NULL,'SysDepartment',NULL,1321510438613082113,2,'组织机构',NULL,0,1,'_self','@/views/sys/department/index',0,'/sys/department',4),(1331578316888473601,1321506328853696514,'2020-11-25 20:39:44.933000',1332660082902622210,'2020-11-29 22:03:07.948000','工作岗位管理',NULL,1,'far fa-dizzy',NULL,'SysPost',NULL,1321510438613082113,2,'工作岗位',NULL,0,1,'_self','@views/sys/post/index',0,'/sys/post',7),(1355013542348075010,NULL,NULL,NULL,NULL,NULL,NULL,1,'fas fa-laptop-house',NULL,'Home',NULL,0,1,'首页',NULL,0,1,'_self','Layout',0,'',0),(1355013736863117314,NULL,NULL,NULL,NULL,NULL,NULL,1,'fas fa-laptop-house',NULL,'Index',NULL,1355013542348075010,2,'',NULL,1,1,'_self','@views/index/index',0,'/',0),(1355014008700153858,NULL,NULL,NULL,NULL,NULL,NULL,1,'far fa-address-card',NULL,'UserCenter',NULL,1355013542348075010,2,'用户中心',NULL,0,1,'_self','@views/profile/index',1,'/uc/profile',0);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `is_reserved` tinyint(1) NOT NULL DEFAULT '0',
  `title` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bqy406dtsr7j7d6fawi1ckyn1` (`name`),
  UNIQUE KEY `UK_8sxhg7rmhhovrs05vp18o3ui4` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1332659194490314754,NULL,'2020-11-28 20:14:46.221000',1332660082902622210,'2020-12-28 21:42:16.454000','备注',NULL,NULL,'adminstrator',1,'超级管理员'),(1332659252694671362,NULL,'2020-11-28 20:15:00.111000',1332660082902622210,'2020-12-25 20:57:36.388000','',NULL,NULL,'user',1,'普通用户'),(1332659297301094401,NULL,'2020-11-28 20:15:10.747000',1332660082902622210,'2020-12-25 20:56:35.481000','',NULL,NULL,'guest',1,'访客'),(1332996533724917762,1332660082902622210,'2020-11-29 18:35:14.175000',1332660082902622210,'2020-12-28 21:47:15.688000',NULL,NULL,'主要负责管理系统日志','logAdmin',0,'日志管理员');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_resource`
--

DROP TABLE IF EXISTS `sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  KEY `FKkj7e3cva1e2s3nsd0yghpbsnk` (`resource_id`),
  KEY `FK7urjh5xeujvp29nihwbs5b9kr` (`role_id`),
  CONSTRAINT `FK7urjh5xeujvp29nihwbs5b9kr` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKkj7e3cva1e2s3nsd0yghpbsnk` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_resource`
--

LOCK TABLES `sys_role_resource` WRITE;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;
INSERT INTO `sys_role_resource` VALUES (1332996533724917762,1321510438613082113),(1332996533724917762,1321510438801825795),(1332996533724917762,1328243256491786241),(1332996533724917762,1329460795418869762),(1332996533724917762,1355013542348075010),(1332996533724917762,1355013736863117314),(1332996533724917762,1355014008700153858),(1332659194490314754,1321510438613082113),(1332659194490314754,1321510438801825793),(1332659194490314754,1322514297393299458),(1332659194490314754,1322514406554255361),(1332659194490314754,1324766365478912002),(1332659194490314754,1330144570763161602),(1332659194490314754,1321510438801825794),(1332659194490314754,1321510438801825795),(1332659194490314754,1328243256491786241),(1332659194490314754,1329460795418869762),(1332659194490314754,1321510438801825796),(1332659194490314754,1324555351789527042),(1332659194490314754,1327636209513574401),(1332659194490314754,1329391155728654337),(1332659194490314754,1329456580344688642),(1332659194490314754,1331502738885840897),(1332659194490314754,1331578316888473601),(1332659194490314754,1355013542348075010),(1332659194490314754,1355013736863117314),(1332659194490314754,1355014008700153858),(1332659252694671362,1321510438801825795),(1332659252694671362,1355013736863117314),(1332659252694671362,1321510438613082113),(1332659252694671362,1329460795418869762),(1332659252694671362,1329456580344688642),(1332659252694671362,1355014008700153858),(1332659252694671362,1329391155728654337),(1332659252694671362,1355013542348075010),(1332659252694671362,1328243256491786241);
/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_user`
--

DROP TABLE IF EXISTS `sys_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_user` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKe853e1gbgbb6ov7al35mfd3nm` (`role_id`),
  KEY `FK1qf7bu5lhwt9y00uesmcgnbxd` (`user_id`),
  CONSTRAINT `FK1qf7bu5lhwt9y00uesmcgnbxd` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKe853e1gbgbb6ov7al35mfd3nm` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_user`
--

LOCK TABLES `sys_role_user` WRITE;
/*!40000 ALTER TABLE `sys_role_user` DISABLE KEYS */;
INSERT INTO `sys_role_user` VALUES (1332661471821201410,1332659194490314754),(1332661471821201410,1332659252694671362),(1332661269903212545,1332659194490314754),(1332660082902622210,1332659194490314754),(1332660767639527426,1332659252694671362),(1333060149291634690,1332996533724917762);
/*!40000 ALTER TABLE `sys_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_time` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `cell_phone` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_locked` tinyint(1) NOT NULL DEFAULT '0',
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `telphone` varchar(255) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`),
  KEY `FKgpcudn9q6i2xhnbngujxwgqij` (`department_id`),
  CONSTRAINT `FKgpcudn9q6i2xhnbngujxwgqij` FOREIGN KEY (`department_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1332660082902622210,NULL,'2020-11-28 20:18:18.044000',1332660082902622210,'2020-12-25 20:19:22.591000','',NULL,NULL,'/static/images/logo.png','13333333333','0',0,'超级管理员','$2a$10$x5Z/qI.zLSI70zngTbxtEet9SqIAd8S2NKZbfLnhQs7WzoeEZsCIW','超级管理员',NULL,'admin',1332653502945595393),(1332660767639527426,NULL,'2020-11-28 20:21:01.302000',1332660082902622210,'2020-11-29 18:31:44.178000','',NULL,NULL,'/static/images/logo.png','15555555555','1',0,'武当带头大哥','$2a$10$TGqvk/NmmzFnZt1Y7U4M.OS/S3AKGH7zCYv5jq8IY5kVbg5PP8I9q','张三丰',NULL,'zhangsanfeng',1332653502945595393),(1332661269903212545,NULL,'2020-11-28 20:23:01.041000',1332660082902622210,'2020-11-29 18:26:14.501000','',NULL,NULL,'/static/images/logo.png','17777777777','1',0,'无忌他爹','$2a$10$xzlTa6sewM8Lm48k.lhBwuPR319tSoHuAkTb9H8TNQy53kIgAVin.','张翠山',NULL,'zhangcuishan',1332976506778984450),(1332661471821201410,NULL,'2020-11-28 20:23:49.193000',1332660082902622210,'2020-11-29 18:32:43.623000','',NULL,NULL,'/static/images/logo.png','18888888888','1',0,'八级残废','$2a$10$tbVuJLfPlREj4OozlJdIaeHUCtvyCU5OW8tiusGQzMbT/8Aejv8bS','俞岱岩',NULL,'yudaiyan',1332653564522172418),(1333060149291634690,1332660082902622210,'2020-11-29 22:48:01.308000',1332660082902622210,'2020-12-28 21:46:10.131000',NULL,NULL,'',NULL,'19999999999','2',0,'峨眉大姐','$2a$10$jfuPisNxtuvA28z34ISoPOwyTNsKuQwCsPs6TvengmhpVIvm0FR8a','周芷若',NULL,'zhouzhiruo',1333060399620280322);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-30 11:06:37
