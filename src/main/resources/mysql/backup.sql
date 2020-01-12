-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: users
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth` (
  `auth_key` int(11) NOT NULL,
  `auth_type` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`auth_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (1,'admin',30),(31,'user',30),(35,'user',34),(38,'user',37);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_and_week`
--

DROP TABLE IF EXISTS `chore_and_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_and_week` (
  `chore_id` int(11) DEFAULT NULL,
  `week` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_and_week`
--

LOCK TABLES `chore_and_week` WRITE;
/*!40000 ALTER TABLE `chore_and_week` DISABLE KEYS */;
INSERT INTO `chore_and_week` VALUES (147,'2019-W49',116),(154,'2019-W49',117),(159,'2019-W49',118);
/*!40000 ALTER TABLE `chore_and_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_chart_term_information`
--

DROP TABLE IF EXISTS `chore_chart_term_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_chart_term_information` (
  `id` int(11) NOT NULL,
  `term_start` date DEFAULT NULL,
  `term_end` date DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  `population` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chore_chart_term_information_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_chart_term_information`
--

LOCK TABLES `chore_chart_term_information` WRITE;
/*!40000 ALTER TABLE `chore_chart_term_information` DISABLE KEYS */;
INSERT INTO `chore_chart_term_information` VALUES (127,'2020-07-01','2020-07-14',0,12),(146,'2020-01-14','2020-01-15',0,5),(149,'2020-02-01','2020-02-02',0,16),(150,'2020-01-08','2020-01-09',0,16);
/*!40000 ALTER TABLE `chore_chart_term_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_chart_weeks`
--

DROP TABLE IF EXISTS `chore_chart_weeks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_chart_weeks` (
  `id` int(11) NOT NULL,
  `week` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chore_chart_weeks_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_chart_weeks`
--

LOCK TABLES `chore_chart_weeks` WRITE;
/*!40000 ALTER TABLE `chore_chart_weeks` DISABLE KEYS */;
INSERT INTO `chore_chart_weeks` VALUES (1,'2019-W49');
/*!40000 ALTER TABLE `chore_chart_weeks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_charts`
--

DROP TABLE IF EXISTS `chore_charts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_charts` (
  `week` varchar(255) NOT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`week`),
  UNIQUE KEY `chore_charts_week_uindex` (`week`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_charts`
--

LOCK TABLES `chore_charts` WRITE;
/*!40000 ALTER TABLE `chore_charts` DISABLE KEYS */;
INSERT INTO `chore_charts` VALUES ('DEFAULT',0);
/*!40000 ALTER TABLE `chore_charts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_chore`
--

DROP TABLE IF EXISTS `chore_chore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_chore` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chore_chore_chore_chore_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_chore`
--

LOCK TABLES `chore_chore` WRITE;
/*!40000 ALTER TABLE `chore_chore` DISABLE KEYS */;
INSERT INTO `chore_chore` VALUES (1,'Dishes'),(147,'Sweep And Mop'),(152,'Tables'),(154,'Upper Quad Bath'),(159,'Super Long Name');
/*!40000 ALTER TABLE `chore_chore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_day`
--

DROP TABLE IF EXISTS `chore_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_day` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_day`
--

LOCK TABLES `chore_day` WRITE;
/*!40000 ALTER TABLE `chore_day` DISABLE KEYS */;
INSERT INTO `chore_day` VALUES (1,'Monday'),(156,'Tuesday'),(160,'Wednesday');
/*!40000 ALTER TABLE `chore_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chore_day_with_user`
--

DROP TABLE IF EXISTS `chore_day_with_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chore_day_with_user` (
  `id` int(11) NOT NULL,
  `chore_chart_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `chore_id` int(11) DEFAULT NULL,
  `day_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chore_day__with_user_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chore_day_with_user`
--

LOCK TABLES `chore_day_with_user` WRITE;
/*!40000 ALTER TABLE `chore_day_with_user` DISABLE KEYS */;
INSERT INTO `chore_day_with_user` VALUES (1,1,1,1,1);
/*!40000 ALTER TABLE `chore_day_with_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committee`
--

DROP TABLE IF EXISTS `committee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `committee` (
  `committee_entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `committee_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`committee_entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee`
--

LOCK TABLES `committee` WRITE;
/*!40000 ALTER TABLE `committee` DISABLE KEYS */;
INSERT INTO `committee` VALUES (1,'maintenance',17);
/*!40000 ALTER TABLE `committee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_and_week`
--

DROP TABLE IF EXISTS `day_and_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day_and_week` (
  `day_id` int(11) DEFAULT NULL,
  `week` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_and_week`
--

LOCK TABLES `day_and_week` WRITE;
/*!40000 ALTER TABLE `day_and_week` DISABLE KEYS */;
INSERT INTO `day_and_week` VALUES (1,'2019-W49',132);
/*!40000 ALTER TABLE `day_and_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (161);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferences`
--

DROP TABLE IF EXISTS `preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferences` (
  `user_id` int(11) DEFAULT NULL,
  `preference_id` int(11) NOT NULL,
  `week_number` varchar(255) DEFAULT NULL,
  `chores_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`preference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferences`
--

LOCK TABLES `preferences` WRITE;
/*!40000 ALTER TABLE `preferences` DISABLE KEYS */;
INSERT INTO `preferences` VALUES (1,1,'1','1,2,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0'),(3,2,'1','1,6,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0'),(17,3,'1','1,6,0,0,0,0,2,7,0,4,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0'),(18,4,'1','1,6,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0'),(5,20,'2019-W47','1,6,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0');
/*!40000 ALTER TABLE `preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_and_id`
--

DROP TABLE IF EXISTS `user_and_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_and_id` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `kappa_sigma` int(11) DEFAULT '0',
  `brother` tinyint(1) DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `big` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_and_id`
--

LOCK TABLES `user_and_id` WRITE;
/*!40000 ALTER TABLE `user_and_id` DISABLE KEYS */;
INSERT INTO `user_and_id` VALUES (30,'test','test','test',1411,1,'$2a$10$pg3sl6NXAscT7YFxjfl6YeTUJYAZmFow13jKX2ROJ5T3qsWtK/TUy',1401),(34,'bill','Bill','Fred',0,0,'$2a$10$HPL00RMeTSsJYt4PZfhHu.7HH72XdoboZwHVQjm708a.GRnOIsaP.',1450),(37,'user','bill','test',1230,1,'$2a$10$n3etVgRk1wb.mRO8y2C/v.B.c1AFrFAgSEQWn9/0cZWy4JJp2QBHe',1110);
/*!40000 ALTER TABLE `user_and_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preference`
--

DROP TABLE IF EXISTS `user_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_preference` (
  `preference_id` int(11) NOT NULL,
  `week` varchar(255) NOT NULL,
  `day_id` int(11) DEFAULT NULL,
  `chore_id` int(11) DEFAULT NULL,
  `preference_ranking` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`preference_id`),
  UNIQUE KEY `user_preference_preference_id_uindex` (`preference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preference`
--

LOCK TABLES `user_preference` WRITE;
/*!40000 ALTER TABLE `user_preference` DISABLE KEYS */;
INSERT INTO `user_preference` VALUES (1,'DEFAULT',1,1,1,NULL);
/*!40000 ALTER TABLE `user_preference` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-07 23:19:51
