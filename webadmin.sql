-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.2.16-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 website 的数据库结构
DROP DATABASE IF EXISTS `website`;
CREATE DATABASE IF NOT EXISTS `website` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `website`;

-- 导出  表 website.company 结构
DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `name` varchar(32) NOT NULL,
  `desc` varchar(256) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `pics` varchar(128) DEFAULT NULL,
  `team_pic1` varchar(64) DEFAULT NULL,
  `team_pic2` varchar(64) DEFAULT NULL,
  `team_pic3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  website.company 的数据：~1 rows (大约)
DELETE FROM `company`;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`name`, `desc`, `creator`, `pics`, `team_pic1`, `team_pic2`, `team_pic3`) VALUES
	('111', '2222', NULL, '63,64,65,66', '63', '64', '65');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

-- 导出  表 website.design 结构
DROP TABLE IF EXISTS `design`;
CREATE TABLE IF NOT EXISTS `design` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `desc` varchar(256) DEFAULT NULL,
  `pic_ids` varchar(64) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  website.design 的数据：~0 rows (大约)
DELETE FROM `design`;
/*!40000 ALTER TABLE `design` DISABLE KEYS */;
/*!40000 ALTER TABLE `design` ENABLE KEYS */;

-- 导出  表 website.settings 结构
DROP TABLE IF EXISTS `settings`;
CREATE TABLE IF NOT EXISTS `settings` (
  `name` varchar(32) NOT NULL,
  `value` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  website.settings 的数据：~0 rows (大约)
DELETE FROM `settings`;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;

-- 导出  表 website.uploadimage 结构
DROP TABLE IF EXISTS `uploadimage`;
CREATE TABLE IF NOT EXISTS `uploadimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT 0,
  `design_id` int(11) NOT NULL DEFAULT 0,
  `path` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- 正在导出表  website.uploadimage 的数据：~6 rows (大约)
DELETE FROM `uploadimage`;
/*!40000 ALTER TABLE `uploadimage` DISABLE KEYS */;
INSERT INTO `uploadimage` (`id`, `type`, `design_id`, `path`) VALUES
	(63, 1, 0, '8-1.png'),
	(64, 1, 0, '8-2.png'),
	(65, 1, 0, '8-3.png'),
	(66, 1, 0, '8-4.png'),
	(67, 1, 0, '8-6.png'),
	(68, 1, 0, '8-5.png');
/*!40000 ALTER TABLE `uploadimage` ENABLE KEYS */;

-- 导出  表 website.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(256) NOT NULL,
  `last_login_time` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  website.user 的数据：~0 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
