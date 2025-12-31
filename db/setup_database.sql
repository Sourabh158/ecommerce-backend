CREATE DATABASE  IF NOT EXISTS `ecommerce_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce_db`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_db
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK1fa36y2oqhao3wgg2rw1pi459` (`user_id`),
  CONSTRAINT `FK1fa36y2oqhao3wgg2rw1pi459` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Indore','India','Madhya Pradesh','123 Main Road','452001',3,NULL,NULL,NULL),(2,'khandwa','India','MadhyaPradesh',NULL,'450337',4,'Paras ','','Makan no. 322 , Chawadi Chowk kumthi'),(3,'khandwa','India','MadhyaPradesh',NULL,'450337',4,'Paras mahajan','','Makan no. 322 , Chawadi Chowk kumthi'),(4,'khandwa','India','MadhyaPradesh',NULL,'450337',4,'Paras','mahajan','Makan no. 322 , Chawadi Chowk kumthi'),(5,'','India','',NULL,'',4,NULL,NULL,''),(6,'','India','',NULL,'',4,NULL,NULL,''),(7,'','India','',NULL,'',4,NULL,NULL,''),(8,'','India','',NULL,'',4,NULL,NULL,''),(9,'','India','',NULL,'',4,NULL,NULL,''),(10,'','India','',NULL,'',4,NULL,NULL,''),(11,'khandwa','India','MadhyaPradesh',NULL,'450337',4,'Paras','mahajan','Makan no. 322 , Chawadi Chowk kumthi'),(12,'','India','',NULL,'',4,NULL,NULL,''),(13,'','India','',NULL,'',4,NULL,NULL,''),(14,'','India','',NULL,'',4,NULL,NULL,''),(15,'','India','',NULL,'',4,NULL,NULL,''),(16,'','India','',NULL,'',4,NULL,NULL,''),(17,'','India','',NULL,'',4,NULL,NULL,''),(18,'','India','',NULL,'',4,NULL,NULL,''),(19,'','India','',NULL,'',4,NULL,NULL,''),(20,'','India','',NULL,NULL,4,NULL,NULL,NULL),(21,'','India','',NULL,NULL,4,NULL,NULL,NULL),(22,'','India','',NULL,NULL,4,NULL,NULL,NULL),(23,'','India','',NULL,NULL,4,NULL,NULL,NULL),(24,'','India','',NULL,NULL,4,NULL,NULL,NULL),(25,'','India','',NULL,NULL,4,NULL,NULL,NULL),(26,'','India','',NULL,'',4,NULL,NULL,''),(27,'','India','',NULL,'',4,NULL,NULL,''),(28,'','India','',NULL,'',4,NULL,NULL,''),(29,'','India','',NULL,'',4,NULL,NULL,''),(30,'','India','',NULL,'',4,NULL,NULL,''),(31,'','India','',NULL,'',4,NULL,NULL,''),(32,'khandwa','India','Madhya Pradesh',NULL,'450337',4,NULL,NULL,'makan no. 322 ward no. 17'),(33,'khandwa','India','Madhya Pradesh',NULL,'450337',4,NULL,NULL,'makan no. 322 ward no. 17'),(34,'','India','',NULL,'',4,NULL,NULL,'');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `discount` double DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `cart_item_id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `total_price` double DEFAULT NULL,
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK64t7ox312pqal3p7fg9o503c2` (`user_id`),
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (0,1,1),(0,2,2),(250000,3,3),(0,4,4),(0,5,5),(0,6,6),(0,7,7),(0,8,8);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKt8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Gadgets and Devices','Electronics'),(2,'Smartphones and accessories','Mobiles'),(3,NULL,'Laptops'),(4,NULL,'Grocery'),(5,NULL,'Crocary'),(6,NULL,'Garments');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `ordered_product_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  KEY `FKocimc7dtr037rh4ls4l95nlfi` (`product_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKocimc7dtr037rh4ls4l95nlfi` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (33,45000,1,32,106),(34,45000,1,33,106);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  `razorpay_order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `UKhaujdjk1ohmeixjhnhslchrp1` (`payment_id`),
  KEY `FKhlglkvf5i60dv6dn397ethgpt` (`address_id`),
  CONSTRAINT `FK8aol9f99s97mtyhij0tvfj41f` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`),
  CONSTRAINT `FKhlglkvf5i60dv6dn397ethgpt` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (7,'hero@test.com','2025-12-17','Order Accepted !',80000,2,4,NULL),(8,'hero@test.com','2025-12-17','Order Accepted !',50000,3,5,NULL),(9,'hero@test.com','2025-12-17','Order Accepted !',50000,4,6,NULL),(10,'hero@test.com','2025-12-17','Order Accepted !',50000,5,7,NULL),(11,'hero@test.com','2025-12-17','Order Accepted !',50000,6,8,NULL),(12,'hero@test.com','2025-12-17','Order Accepted !',50000,8,9,NULL),(13,'hero@test.com','2025-12-17','Order Accepted !',50000,9,10,NULL),(14,'hero@test.com','2025-12-17','Order Accepted !',50000,10,11,NULL),(15,'hero@test.com','2025-12-17','Order Accepted !',80000,11,12,NULL),(16,'hero@test.com','2025-12-17','Order Accepted !',50000,12,13,NULL),(17,'hero@test.com','2025-12-17','Order Accepted !',50000,13,14,NULL),(18,'hero@test.com','2025-12-17','Order Accepted !',50000,14,15,NULL),(19,'hero@test.com','2025-12-17','Order Accepted !',50000,15,16,NULL),(20,'hero@test.com','2025-12-17','Order Accepted !',50000,16,17,NULL),(21,'hero@test.com','2025-12-18','Order Accepted !',50000,17,18,NULL),(22,'hero@test.com','2025-12-25','Order Accepted !',50000,18,19,NULL),(23,'hero@test.com','2025-12-26','Order Accepted !',130000,19,20,NULL),(24,'hero@test.com','2025-12-27','Order Accepted !',1,20,21,NULL),(25,'hero@test.com','2025-12-27','Order Accepted !',150,24,22,NULL),(26,'hero@test.com','2025-12-27','Order Accepted !',150,25,23,NULL),(27,'hero@test.com','2025-12-27','Order Accepted !',15000,26,24,NULL),(28,'hero@test.com','2025-12-27','Order Accepted !',60000,27,25,NULL),(29,'hero@test.com','2025-12-27','Order Accepted !',15000,28,26,NULL),(30,'hero@test.com','2025-12-27','Order Accepted !',50000,29,27,NULL),(31,'hero@test.com','2025-12-31','Order Accepted !',50000,31,28,NULL),(32,'hero@test.com','2025-12-31','Order Accepted !',45000,32,29,NULL),(33,'hero@test.com','2025-12-31','Order Accepted !',45000,34,30,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `pg_payment_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,250000,'COD','Pending',NULL),(2,500000,'COD','Pending',NULL),(3,250000,'RAZORPAY','Success','pay_Test123456'),(4,80000,'COD','Pending',NULL),(5,50000,'COD','Pending',NULL),(6,50000,'COD','Pending',NULL),(7,50000,'COD','Pending',NULL),(8,50000,'COD','Pending',NULL),(9,50000,'COD','Pending',NULL),(10,50000,'COD','Pending',NULL),(11,50000,'COD','Pending',NULL),(12,80000,'COD','Pending',NULL),(13,50000,'COD','Pending',NULL),(14,50000,'COD','Pending',NULL),(15,50000,'COD','Pending',NULL),(16,50000,'COD','Pending',NULL),(17,50000,'COD','Pending',NULL),(18,50000,'COD','Pending',NULL),(19,50000,'COD','Pending',NULL),(20,130000,'COD','Pending',NULL),(21,1,'ONLINE',NULL,NULL),(22,150,'COD','Pending',NULL),(23,150,'COD','Pending',NULL),(24,15000,'COD','Pending',NULL),(25,60000,'COD','Pending',NULL),(26,15000,'COD','Pending',NULL),(27,50000,'COD','Pending',NULL),(28,50000,'ONLINE',NULL,NULL),(29,45000,'ONLINE',NULL,NULL),(30,45000,'COD','Pending',NULL);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `special_price` double DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (0,45000,98,45000,2,106,'','images/iPhone 14.jpeg','iphone 14'),(0,60999,100,60999,3,107,'','images/AsusLaptop.jpeg','Asus Laptop'),(0,2000,10,2000,6,110,'','images/Clothes_converted.jpg','Talwinder Clothes'),(0,300,1,300,4,111,'','images\\Food.jpg','Food');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `role_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(1,2),(1,3),(1,4),(2,4),(1,5),(1,8);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(120) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'vallabha_secure','secure@example.com','$2a$10$HYP9RgHlWFDqvo9u65Pv5exWOR9rvV71H7iESKytOlWCZ3ZFUXm6m'),(2,'final_user','final@test.com','$2a$10$DAiWqwgt4ZoGUnFlH.AHH.uW4O31qQSSdFRMhOQ8LzH3NFL5nbM.m'),(3,'SOURABH','SOURABH@test.com','$2a$10$Eun74w/owvEG.6j0WSEyHeM8z2vnjF2Y6Ah79KT7UflZ0fk.hl2Mq'),(4,'superman','hero@test.com','$2a$10$EwbaR.ugZpye2idsMpVTaehWOaAhl./FzQqVrdNMfwXceyOU0AHZK'),(5,'paras','superman','$2a$10$lh72ad3Ibf.ZcVbv6zHPnuyMeC3jM90SnLTYLEEmq87BowgTu//rC'),(6,'hello','superman@123','$2a$10$koIjEUYJqCLGtDLyVAYseeYM0f58iUZlarDuyzb94Joj4fba4mOV.'),(7,'parass','parasmahajan157@gmail.com','$2a$10$VjAC9kxLdNbe7H2.Z1kVVuXyCFKzNxIWnOwtc/ofNmhN.L4JuxR2u'),(8,'dummy','dummy@123','$2a$10$c1GEZlkkv3x.eA.swTO8XeO2S7mPCTfdMDPibT2X3k.cp3MlCto2K');
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

-- Dump completed on 2025-12-31 23:01:23
