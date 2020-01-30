CREATE DATABASE  IF NOT EXISTS `heroku_45743bf16334ae8` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `heroku_45743bf16334ae8`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: heroku_45743bf16334ae8
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(45) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `contact_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (2,'Samy',5,3),(3,'Darren',5,4),(14,'Tammy',3,2),(15,'Kingston',3,1),(16,'Zed',3,4),(18,'Test',3,5);
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL DEFAULT '0',
  `bank_account` varchar(45) DEFAULT NULL,
  `sender_id` int(11) NOT NULL DEFAULT '0',
  `recipient_id` int(11) NOT NULL DEFAULT '0',
  `transaction_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (7,20,'xxxxxxxxxxx',3,4,'2019-11-15 15:19:35'),(8,1000,'testingbankaccount',3,4,'2019-11-15 15:20:13'),(9,123,'sdfasdfasdfads',1,4,'2019-11-15 15:21:28'),(10,2891548,'testingbankaccount2',4,3,'2019-11-15 15:21:36'),(11,2891548,'testingbankaccount2',4,3,'2020-01-11 17:54:51'),(12,2891548,'testingbankaccount2',4,3,'2020-01-11 17:54:52'),(13,2891548,'testingbankaccount2',4,3,'2020-01-11 17:54:53'),(14,2891548,'testingbankaccount2',4,3,'2020-01-11 17:54:53'),(15,2891548,'testingbankaccount2',4,3,'2020-01-11 18:13:13'),(16,2891548,'testingbankaccount2',4,3,'2020-01-12 08:54:48'),(17,2891548,'testingbankaccount2',4,3,'2020-01-12 09:15:41'),(18,201,'testingbankaccount2',4,3,'2020-01-12 09:15:48'),(19,5,'testingbankaccount2',4,3,'2020-01-12 09:15:52'),(20,115,'testingbankaccount2',4,3,'2020-01-12 09:15:55'),(21,215,'testingbankaccount2',4,3,'2020-01-12 09:15:58'),(22,72,'testingbankaccount2',4,3,'2020-01-12 09:16:00'),(23,35,'testingbankaccount2',4,3,'2020-01-12 09:16:04'),(24,100,'testingbankaccount2',4,3,'2020-01-12 09:16:07'),(25,21,'testingbankaccount2',4,3,'2020-01-12 09:16:09'),(26,75,'testingbankaccount2',4,3,'2020-01-12 09:16:12'),(27,65,'testingbankaccount2',4,3,'2020-01-12 09:16:14'),(28,26,'testingbankaccount2',4,3,'2020-01-12 09:16:17'),(29,789,'testingbankaccount2',4,3,'2020-01-12 09:16:21'),(30,152,'testingbankaccount2',4,3,'2020-01-12 09:16:24'),(31,2,'testingbankaccount2',4,3,'2020-01-12 09:16:49'),(32,167,'testingbankaccount2',4,3,'2020-01-12 09:16:52'),(33,1899,'testingbankaccount2',4,3,'2020-01-12 09:16:55'),(34,1899,'testingbankaccount2',4,3,'2020-01-12 09:17:45'),(35,115,'testingbankaccount2',4,3,'2020-01-12 09:17:49'),(36,52,'testingbankaccount2',4,3,'2020-01-12 09:17:56'),(37,15,'testingbankaccount2',4,3,'2020-01-12 09:17:58'),(38,84,'testingbankaccount2',4,3,'2020-01-12 09:18:01'),(39,115,'testingbankaccount2',4,3,'2020-01-12 10:01:13');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(300) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'foo@test.com','Jeanne Tan','sdfasdfasdfasdf','12345'),(4,'bar@test.com','Keith Choi','sdfasdfasdfasdf','12345'),(5,'john3@test.com','John2','sdfasdfasdfasdf','12345');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-12 18:05:16
