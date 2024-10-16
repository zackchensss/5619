-- MySQL dump 10.13  Distrib 8.4.0, for macos14 (x86_64)
--
-- Host: localhost    Database: ee_schema
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_status`
--

DROP TABLE IF EXISTS `course_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_status` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course` varchar(50) NOT NULL,
  `complete_num` int NOT NULL,
  `completion_date` date NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_status`
--

LOCK TABLES `course_status` WRITE;
/*!40000 ALTER TABLE `course_status` DISABLE KEYS */;
INSERT INTO `course_status` VALUES (1,6,'math',1,'2024-10-16');
/*!40000 ALTER TABLE `course_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_progress`
--

DROP TABLE IF EXISTS `study_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_progress` (
  `progress_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `date` date NOT NULL,
  `study_time` varchar(5) NOT NULL,
  PRIMARY KEY (`progress_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_progress`
--

LOCK TABLES `study_progress` WRITE;
/*!40000 ALTER TABLE `study_progress` DISABLE KEYS */;
INSERT INTO `study_progress` VALUES (1,4,'2024-10-16','10:00'),(2,6,'2024-10-16','11:00');
/*!40000 ALTER TABLE `study_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `grade` varchar(100) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'\'john\'','\'1231313\'','\'qewqeqwe.com\'',NULL,'2024-10-07 19:20:16','\'grade1\'',NULL,'2024-10-07 19:20:17'),(2,'\'evan\'','\'12313131\'','\'qewqeq1we.com\'',NULL,'2024-10-14 20:09:54','\'grade1\'',NULL,'2024-10-14 20:09:54'),(4,'evan','12313131','qewqeq1we.com',NULL,'2024-10-14 20:46:16','grade1',NULL,'2024-10-14 20:46:16'),(6,'evan2','12313131','qewqeq1we.com',NULL,'2024-10-14 20:49:02','grade1',NULL,'2024-10-14 20:49:02'),(7,'evan3','12313131','qewqeq1we.com',NULL,'2024-10-14 20:49:53','grade1',NULL,'2024-10-14 20:49:53');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-17  0:08:19
