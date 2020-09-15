CREATE DATABASE  IF NOT EXISTS `dollars_bank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dollars_bank`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: dollars_bank
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_number` char(17) NOT NULL,
  `type` char(16) NOT NULL,
  `date_opened` datetime NOT NULL,
  `date_closed` datetime DEFAULT NULL,
  `interest_rate` double NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`account_number`),
  UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('000111222','SAVINGS_ACCOUNT','1989-10-15 14:00:00',NULL,0.0009,10000),('01234560','CHECKING_ACCOUNT','1985-03-15 13:00:00','1999-09-01 08:30:00',0.0006,0),('11223344','SAVINGS_ACCOUNT','1980-07-09 11:00:00',NULL,0.0009,1500),('12345678','SAVINGS_ACCOUNT','1999-09-01 09:00:00',NULL,0.0009,300),('123456789','CHECKING_ACCOUNT','1999-09-01 09:00:00',NULL,0.0006,500),('333444555','CHECKING_ACCOUNT','1989-10-15 14:00:00',NULL,0.0006,1000),('55667788','CHECKING_ACCOUNT','1980-07-09 11:00:00',NULL,0.0006,500),('91234569','SAVINGS_ACCOUNT','1985-03-15 13:00:00',NULL,0.0009,300);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` char(2) NOT NULL,
  `zip_code` varchar(10) NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'34 Erwin Park Rd','Montclair','NJ','07042'),(2,'1329 Carroll Ave','Los Angeles','CA','90026');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `user_id` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` char(16) NOT NULL,
  `address_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('dude420','Surfer-Br0','Oscar','Brown','oscar.brown@gmail.com','908-445-0424',1),('flowergirl1','lily_of_the_valley99','Prudence','Halliwell','prudence.halliwell@gmail.com','830-775-9185',2),('violet99','Hello!55','Laura','Brown','laura.brown@gmail.com','732-367-5260',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_account`
--

DROP TABLE IF EXISTS `customer_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_account` (
  `customer_account_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(50) NOT NULL,
  `account_id` char(17) NOT NULL,
  PRIMARY KEY (`customer_account_id`),
  UNIQUE KEY `customer_account_id_UNIQUE` (`customer_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_account`
--

LOCK TABLES `customer_account` WRITE;
/*!40000 ALTER TABLE `customer_account` DISABLE KEYS */;
INSERT INTO `customer_account` VALUES (1,'dude420','12345678'),(2,'dude420','123456789'),(3,'violet99','12345678'),(4,'violet99','123456789'),(5,'violet99','11223344'),(6,'violet99','55667788'),(7,'dude420','91234569'),(8,'dude420','01234560'),(9,'flowergirl1','000111222'),(10,'flowergirl1','333444555');
/*!40000 ALTER TABLE `customer_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `amount` double NOT NULL,
  `type` varchar(50) NOT NULL,
  `transaction_desc` varchar(200) NOT NULL,
  `customer_id` varchar(50) NOT NULL,
  `account_id` char(17) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE KEY `transaction_id_UNIQUE` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'1989-10-15 14:00:00',9000,'DEPOSIT','Initial Deposit','flowergirl1','000111222'),(2,'1989-10-15 14:00:00',2000,'DEPOSIT','Initial Deposit','flowergirl1','333444555'),(3,'1999-09-01 09:00:00',800,'DEPOSIT','Initial Deposit','dude420','12345678'),(4,'1999-09-01 09:00:00',500,'DEPOSIT','Initial Deposit','dude420','123456789'),(5,'1985-03-15 13:00:00',1100,'DEPOSIT','Initial Deposit','dude420','91234569'),(6,'1985-03-15 13:00:00',500,'DEPOSIT','Initial Deposit','dude420','01234560'),(7,'1980-07-09 11:00:00',1000,'DEPOSIT','Initial Deposit','violet99','11223344'),(8,'1980-07-09 11:00:00',500,'DEPOSIT','Initial Deposit','violet99','55667788'),(9,'1989-10-20 10:00:00',1000,'TRANSFER','Transfer from account 333444555 to 000111222','flowergirl1','000111222'),(10,'1989-10-20 10:00:00',-1000,'TRANSFER','Transfer from account 333444555 to 000111222','flowergirl1','333444555'),(11,'2001-11-27 09:00:00',500,'TRANSFER','Transfer from account 12345678 to 11223344','violet99','11223344'),(12,'2001-11-27 09:00:00',-500,'TRANSFER','Transfer from account 12345678 to 11223344','violet99','12345678'),(13,'1999-09-01 08:30:00',-500,'WITHDRAW','Closing account 01234560','dude420','01234560'),(14,'1999-09-01 08:30:00',-800,'WITHDRAW','Withdrawing funds of $800.00','dude420','91234569');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-15 15:50:24
