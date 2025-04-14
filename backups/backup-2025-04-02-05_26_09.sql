/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.4.5-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: zitrone
-- ------------------------------------------------------
-- Server version	11.4.5-MariaDB-ubu2404

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `Addresses`
--

DROP TABLE IF EXISTS `Addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Addresses` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `isMain` bit(1) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `salutation` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Addresses`
--

LOCK TABLES `Addresses` WRITE;
/*!40000 ALTER TABLE `Addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `Addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Adult`
--

DROP TABLE IF EXISTS `Adult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Adult` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birdthYear` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `preferredLanguage` varchar(255) DEFAULT NULL,
  `salutation` varchar(255) DEFAULT NULL,
  `carer_id` bigint(20) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `carer` bigint(20) DEFAULT NULL,
  `carerId` bigint(20) DEFAULT NULL,
  `tamagotchiId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKdv1kjbvdd5a7oox7kh6pk5huv` (`carer_id`),
  CONSTRAINT `FKake3etx49m51849xfcrrscvii` FOREIGN KEY (`carer_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Adult`
--

LOCK TABLES `Adult` WRITE;
/*!40000 ALTER TABLE `Adult` DISABLE KEYS */;
INSERT INTO `Adult` VALUES
(199268075270145,'2025-02-27 15:20:49.853819',NULL,'2025-03-11 15:41:21.986976','media\\image\\Download_1.png',1971,'terry@gooddev.de','Arnaldo','Fuentes','015224625752','de','WITHOUT',130,'CARER_AND_FAMILY',NULL,130,210938461782016);
/*!40000 ALTER TABLE `Adult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AuditLogs`
--

DROP TABLE IF EXISTS `AuditLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `AuditLogs` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `details` text DEFAULT NULL,
  `entity` varchar(255) DEFAULT NULL,
  `entityId` bigint(20) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AuditLogs`
--

LOCK TABLES `AuditLogs` WRITE;
/*!40000 ALTER TABLE `AuditLogs` DISABLE KEYS */;
INSERT INTO `AuditLogs` VALUES
(202029159202816,'2025-03-07 10:35:42.612895',NULL,NULL,'LOG_IN','{\r\n  \"browserApplication\" : \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36\",\r\n  \"locale\" : \"de_DE\",\r\n  \"address\" : \"127.0.0.1\",\r\n  \"secureConnection\" : false,\r\n  \"windows\" : true,\r\n  \"android\" : false,\r\n  \"linux\" : false,\r\n  \"browserMinorVersion\" : 0,\r\n  \"browserMajorVersion\" : 133,\r\n  \"safari\" : false,\r\n  \"macOSX\" : false,\r\n  \"iphone\" : false,\r\n  \"firefox\" : false,\r\n  \"chromeOS\" : false,\r\n  \"chrome\" : true,\r\n  \"edge\" : false,\r\n  \"windowsPhone\" : false,\r\n  \"opera\" : false,\r\n  \"ie\" : false\r\n}',NULL,NULL,'INFO','Zitrone: Saure Zitrone (Zitrone@simone.de)'),
(202029359783936,'2025-03-07 10:36:31.580040',NULL,NULL,'LOG_IN','{\r\n  \"browserApplication\" : \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36\",\r\n  \"locale\" : \"de_DE\",\r\n  \"address\" : \"127.0.0.1\",\r\n  \"secureConnection\" : false,\r\n  \"windows\" : true,\r\n  \"android\" : false,\r\n  \"linux\" : false,\r\n  \"browserMinorVersion\" : 0,\r\n  \"browserMajorVersion\" : 133,\r\n  \"safari\" : false,\r\n  \"macOSX\" : false,\r\n  \"iphone\" : false,\r\n  \"firefox\" : false,\r\n  \"chromeOS\" : false,\r\n  \"chrome\" : true,\r\n  \"edge\" : false,\r\n  \"windowsPhone\" : false,\r\n  \"opera\" : false,\r\n  \"ie\" : false\r\n}',NULL,NULL,'INFO','Zitrone: Saure Zitrone (Zitrone@simone.de)'),
(202029522857984,'2025-03-07 10:37:11.393907',NULL,NULL,'LOG_IN','{\r\n  \"browserApplication\" : \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36\",\r\n  \"locale\" : \"de_DE\",\r\n  \"address\" : \"127.0.0.1\",\r\n  \"secureConnection\" : false,\r\n  \"windows\" : true,\r\n  \"android\" : false,\r\n  \"linux\" : false,\r\n  \"browserMinorVersion\" : 0,\r\n  \"browserMajorVersion\" : 133,\r\n  \"safari\" : false,\r\n  \"macOSX\" : false,\r\n  \"iphone\" : false,\r\n  \"firefox\" : false,\r\n  \"chromeOS\" : false,\r\n  \"chrome\" : true,\r\n  \"edge\" : false,\r\n  \"windowsPhone\" : false,\r\n  \"opera\" : false,\r\n  \"ie\" : false\r\n}',NULL,NULL,'INFO','Zitrone: Saure Zitrone (Zitrone@simone.de)');
/*!40000 ALTER TABLE `AuditLogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatGroup`
--

DROP TABLE IF EXISTS `ChatGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatGroup` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lastMessage` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatGroup`
--

LOCK TABLES `ChatGroup` WRITE;
/*!40000 ALTER TABLE `ChatGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `ChatGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatGroup_members`
--

DROP TABLE IF EXISTS `ChatGroup_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatGroup_members` (
  `ChatGroup_id` bigint(20) NOT NULL,
  `members` bigint(20) DEFAULT NULL,
  KEY `FK93svsiy87qa0ucc9ls74jjfnx` (`ChatGroup_id`),
  CONSTRAINT `FK93svsiy87qa0ucc9ls74jjfnx` FOREIGN KEY (`ChatGroup_id`) REFERENCES `ChatGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatGroup_members`
--

LOCK TABLES `ChatGroup_members` WRITE;
/*!40000 ALTER TABLE `ChatGroup_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `ChatGroup_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatGroup_seenBy`
--

DROP TABLE IF EXISTS `ChatGroup_seenBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatGroup_seenBy` (
  `ChatGroup_id` bigint(20) NOT NULL,
  `seenBy` bigint(20) DEFAULT NULL,
  KEY `FKoqpkaa4ejwl4bei8fhfrmavbt` (`ChatGroup_id`),
  CONSTRAINT `FKoqpkaa4ejwl4bei8fhfrmavbt` FOREIGN KEY (`ChatGroup_id`) REFERENCES `ChatGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatGroup_seenBy`
--

LOCK TABLES `ChatGroup_seenBy` WRITE;
/*!40000 ALTER TABLE `ChatGroup_seenBy` DISABLE KEYS */;
/*!40000 ALTER TABLE `ChatGroup_seenBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatMessage`
--

DROP TABLE IF EXISTS `ChatMessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatMessage` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `reciverId` bigint(20) DEFAULT NULL,
  `senderId` bigint(20) DEFAULT NULL,
  `senderImage` varchar(255) DEFAULT NULL,
  `senderName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatMessage`
--

LOCK TABLES `ChatMessage` WRITE;
/*!40000 ALTER TABLE `ChatMessage` DISABLE KEYS */;
INSERT INTO `ChatMessage` VALUES
(208763061547008,'2025-03-26 11:16:01.737817',NULL,NULL,'hallooo',199268075270145,130,'media\\image\\large_lemons-0718_1.png','Sauer Zitrone'),
(208763061547010,'2025-03-26 11:17:01.737000',NULL,NULL,'holaa, wie geths',130,199268075270145,NULL,'Terry tupano'),
(208763061547030,'2025-03-26 11:47:01.737000',NULL,NULL,'holaa, wie geths',130,199268075270145,NULL,'Terry tupano'),
(208767365910528,'2025-03-26 11:33:32.607781',NULL,NULL,'fghj',199268075270145,130,'media\\image\\large_lemons-0718_1.png','Sauer Zitrone'),
(209159897042944,'2025-03-27 14:10:45.404990',NULL,NULL,'cvb',199268075270145,130,'media\\image\\large_lemons-0718_1.png','Sauer Zitrone'),
(210948228833280,'2025-04-01 15:27:29.846022',NULL,NULL,'asdf\nagadgsdfg',199268075270145,130,'media\\image\\large_lemons-0718_1.png','Sauer Zitrone');
/*!40000 ALTER TABLE `ChatMessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatMessage_seenBy`
--

DROP TABLE IF EXISTS `ChatMessage_seenBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatMessage_seenBy` (
  `ChatMessage_id` bigint(20) NOT NULL,
  `seenBy` bigint(20) DEFAULT NULL,
  KEY `FKk8vgk5o3g250rexo0ssj0bwsl` (`ChatMessage_id`),
  CONSTRAINT `FKk8vgk5o3g250rexo0ssj0bwsl` FOREIGN KEY (`ChatMessage_id`) REFERENCES `ChatMessage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatMessage_seenBy`
--

LOCK TABLES `ChatMessage_seenBy` WRITE;
/*!40000 ALTER TABLE `ChatMessage_seenBy` DISABLE KEYS */;
INSERT INTO `ChatMessage_seenBy` VALUES
(208763061547008,130),
(208763061547010,130),
(208767365910528,130),
(208763061547030,130),
(209159897042944,130),
(210948228833280,130);
/*!40000 ALTER TABLE `ChatMessage_seenBy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Countries`
--

DROP TABLE IF EXISTS `Countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Countries` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Countries_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Countries`
--

LOCK TABLES `Countries` WRITE;
/*!40000 ALTER TABLE `Countries` DISABLE KEYS */;
INSERT INTO `Countries` VALUES
(63796,'2023-09-06 10:41:20.643876',130,'2024-02-22 15:04:03.576995','AD','The Word, Europe'),
(63797,'2023-09-06 10:41:20.643876',130,NULL,'AE','The Word, Asia'),
(67892,'2023-09-06 10:41:20.644426',130,NULL,'AF','The Word, Asia'),
(71988,'2023-09-06 10:41:20.645003',130,NULL,'AG','The Word, North America'),
(71989,'2023-09-06 10:41:20.645003',130,NULL,'AI','The Word, North America'),
(71990,'2023-09-06 10:41:20.645581',130,NULL,'AL','The Word, Europe'),
(71991,'2023-09-06 10:41:20.645581',130,NULL,'AM','The Word, Asia'),
(76084,'2023-09-06 10:41:20.646106',130,NULL,'AN','The Word, North America'),
(76085,'2023-09-06 10:41:20.646281',130,NULL,'AO','The Word, Africa'),
(76086,'2023-09-06 10:41:20.646281',130,NULL,'AQ','The Word, Antarctica'),
(76087,'2023-09-06 10:41:20.646281',130,NULL,'AR','The Word, South America'),
(76088,'2023-09-06 10:41:20.646830',130,NULL,'AS','The Word, Oceania'),
(76089,'2023-09-06 10:41:20.646830',130,'2023-12-20 10:17:13.356332','AT','The Word, Europe, EU'),
(80180,'2023-09-06 10:41:20.647416',130,NULL,'AU','The Word, Oceania'),
(80181,'2023-09-06 10:41:20.647416',130,NULL,'AW','The Word, North America'),
(84276,'2023-09-06 10:41:20.648546',130,NULL,'AZ','The Word, Asia'),
(88372,'2023-09-06 10:41:20.649110',130,NULL,'BA','The Word, Europe'),
(88373,'2023-09-06 10:41:20.649110',130,NULL,'BB','The Word, North America'),
(88374,'2023-09-06 10:41:20.649110',130,NULL,'BD','The Word, Asia'),
(88375,'2023-09-06 10:41:20.649668',130,'2023-12-20 10:17:29.870058','BE','The Word, Europe, EU'),
(88376,'2023-09-06 10:41:20.649668',130,NULL,'BF','The Word, Africa'),
(88377,'2023-09-06 10:41:20.649668',130,'2023-12-20 10:17:46.281936','BG','The Word, Europe, EU'),
(92468,'2023-09-06 10:41:20.650224',130,NULL,'BH','The Word, Asia'),
(92469,'2023-09-06 10:41:20.650224',130,NULL,'BI','The Word, Africa'),
(92470,'2023-09-06 10:41:20.650791',130,NULL,'BJ','The Word, Africa'),
(92471,'2023-09-06 10:41:20.650791',130,NULL,'BL','The Word, North America'),
(92472,'2023-09-06 10:41:20.650791',130,NULL,'BM','The Word, North America'),
(96564,'2023-09-06 10:41:20.651365',130,NULL,'BN','The Word, Asia'),
(96565,'2023-09-06 10:41:20.651365',130,NULL,'BO','The Word, South America'),
(96566,'2023-09-06 10:41:20.651365',130,NULL,'BR','The Word, South America'),
(96567,'2023-09-06 10:41:20.651931',130,NULL,'BS','The Word, North America'),
(96568,'2023-09-06 10:41:20.651931',130,NULL,'BT','The Word, Asia'),
(96569,'2023-09-06 10:41:20.651931',130,NULL,'BW','The Word, Africa'),
(100660,'2023-09-06 10:41:20.652493',130,NULL,'BY','The Word, Europe'),
(100661,'2023-09-06 10:41:20.652493',130,NULL,'BZ','The Word, North America'),
(100662,'2023-09-06 10:41:20.652493',130,NULL,'CA','The Word, North America'),
(104756,'2023-09-06 10:41:20.653063',130,NULL,'CC','The Word, Asia'),
(104757,'2023-09-06 10:41:20.653063',130,NULL,'CD','The Word, Africa'),
(104758,'2023-09-06 10:41:20.653063',130,NULL,'CF','The Word, Africa'),
(104759,'2023-09-06 10:41:20.653596',130,NULL,'CG','The Word, Africa'),
(104760,'2023-09-06 10:41:20.653596',130,NULL,'CH','The Word, Europe'),
(104761,'2023-09-06 10:41:20.653596',130,NULL,'CI','The Word, Africa'),
(104762,'2023-09-06 10:41:20.653596',130,NULL,'CK','The Word, Oceania'),
(104763,'2023-09-06 10:41:20.653596',130,NULL,'CL','The Word, South America'),
(104764,'2023-09-06 10:41:20.653596',130,NULL,'CM','The Word, Africa'),
(108852,'2023-09-06 10:41:20.654596',130,NULL,'CN','The Word, Asia'),
(108853,'2023-09-06 10:41:20.654596',130,NULL,'CO','The Word, South America'),
(108854,'2023-09-06 10:41:20.654596',130,NULL,'CR','The Word, North America'),
(108855,'2023-09-06 10:41:20.654596',130,NULL,'CU','The Word, North America'),
(108856,'2023-09-06 10:41:20.654596',130,NULL,'CV','The Word, Africa'),
(112948,'2023-09-06 10:41:20.655598',130,NULL,'CW','The Word, North America'),
(112949,'2023-09-06 10:41:20.655598',130,NULL,'CX','The Word, Asia'),
(112950,'2023-09-06 10:41:20.655598',130,NULL,'CY','The Word, Europe'),
(112951,'2023-09-06 10:41:20.655598',130,'2023-12-20 10:23:04.271611','CZ','The Word, Europe, EU'),
(112952,'2023-09-06 10:41:20.655598',130,'2023-12-20 10:26:18.779690','DE','The Word, Europe, EU'),
(117044,'2023-09-06 10:41:20.656598',130,NULL,'DJ','The Word, Africa'),
(117045,'2023-09-06 10:41:20.656598',130,'2023-12-20 10:24:45.618062','DK','The Word, Europe, EU'),
(117046,'2023-09-06 10:41:20.656598',130,NULL,'DM','The Word, North America'),
(117047,'2023-09-06 10:41:20.656598',130,NULL,'DO','The Word, North America'),
(117048,'2023-09-06 10:41:20.656598',130,NULL,'DZ','The Word, Africa'),
(117049,'2023-09-06 10:41:20.656598',130,NULL,'EC','The Word, South America'),
(121140,'2023-09-06 10:41:20.657598',130,'2023-12-20 10:25:32.592822','EE','The Word, Europe, EU'),
(121141,'2023-09-06 10:41:20.657598',130,NULL,'EG','The Word, Africa'),
(121142,'2023-09-06 10:41:20.657598',130,NULL,'EH','The Word, Africa'),
(121143,'2023-09-06 10:41:20.657598',130,NULL,'ER','The Word, Africa'),
(121144,'2023-09-06 10:41:20.657598',130,'2023-12-20 10:30:19.560390','ES','The Word, Europe, EU'),
(121145,'2023-09-06 10:41:20.657598',130,NULL,'ET','The Word, Africa'),
(125236,'2023-09-06 10:41:20.658598',130,'2023-12-20 10:25:48.264002','FI','The Word, Europe, EU'),
(125237,'2023-09-06 10:41:20.658598',130,NULL,'FJ','The Word, Oceania'),
(125238,'2023-09-06 10:41:20.658598',130,NULL,'FK','The Word, South America'),
(125239,'2023-09-06 10:41:20.658598',130,NULL,'FM','The Word, Oceania'),
(125240,'2023-09-06 10:41:20.658598',130,NULL,'FO','The Word, Europe'),
(129332,'2023-09-06 10:41:20.658598',130,'2023-12-20 10:26:04.450354','FR','The Word, Europe, EU'),
(129333,'2023-09-06 10:41:20.659595',130,NULL,'GA','The Word, Africa'),
(129334,'2023-09-06 10:41:20.659595',130,NULL,'GB','The Word, Europe'),
(129335,'2023-09-06 10:41:20.659595',130,NULL,'GD','The Word, North America'),
(129336,'2023-09-06 10:41:20.659595',130,NULL,'GE','The Word, Asia'),
(129337,'2023-09-06 10:41:20.659595',130,NULL,'GG','The Word, Europe'),
(133428,'2023-09-06 10:41:20.660598',130,NULL,'GH','The Word, Africa'),
(133429,'2023-09-06 10:41:20.660598',130,NULL,'GI','The Word, Europe'),
(133430,'2023-09-06 10:41:20.660598',130,NULL,'GL','The Word, North America'),
(133431,'2023-09-06 10:41:20.660598',130,NULL,'GM','The Word, Africa'),
(133432,'2023-09-06 10:41:20.660598',130,NULL,'GN','The Word, Africa'),
(133433,'2023-09-06 10:41:20.660598',130,NULL,'GQ','The Word, Africa'),
(137524,'2023-09-06 10:41:20.660598',130,'2023-12-20 10:26:36.494408','GR','The Word, Europe, EU'),
(137525,'2023-09-06 10:41:20.661598',130,NULL,'GT','The Word, North America'),
(137526,'2023-09-06 10:41:20.661598',130,NULL,'GU','The Word, Oceania'),
(137527,'2023-09-06 10:41:20.661598',130,NULL,'GW','The Word, Africa'),
(137528,'2023-09-06 10:41:20.661598',130,NULL,'GY','The Word, South America'),
(137529,'2023-09-06 10:41:20.661598',130,NULL,'HK','The Word, Asia'),
(137530,'2023-09-06 10:41:20.661598',130,NULL,'HN','The Word, North America'),
(141620,'2023-09-06 10:41:20.662598',130,'2023-12-20 10:24:22.453088','HR','The Word, Europe, EU'),
(141621,'2023-09-06 10:41:20.662598',130,NULL,'HT','The Word, North America'),
(141622,'2023-09-06 10:41:20.662598',130,'2023-12-20 10:26:50.414853','HU','The Word, Europe, EU'),
(141623,'2023-09-06 10:41:20.662598',130,NULL,'ID','The Word, Asia'),
(141624,'2023-09-06 10:41:20.662598',130,'2023-12-20 10:27:05.400039','IE','The Word, Europe, EU'),
(145716,'2023-09-06 10:41:20.663598',130,NULL,'IL','The Word, Asia'),
(145717,'2023-09-06 10:41:20.663598',130,NULL,'IM','The Word, Europe'),
(145718,'2023-09-06 10:41:20.663598',130,NULL,'IN','The Word, Asia'),
(145719,'2023-09-06 10:41:20.663598',130,NULL,'IO','The Word, Asia'),
(145720,'2023-09-06 10:41:20.663598',130,NULL,'IQ','The Word, Asia'),
(145721,'2023-09-06 10:41:20.663598',130,NULL,'IR','The Word, Asia'),
(149812,'2023-09-06 10:41:20.664598',130,NULL,'IS','The Word, Europe'),
(149813,'2023-09-06 10:41:20.664598',130,'2023-12-20 10:27:23.625014','IT','The Word, Europe, EU'),
(149814,'2023-09-06 10:41:20.664598',130,NULL,'JE','The Word, Europe'),
(149815,'2023-09-06 10:41:20.664598',130,NULL,'JM','The Word, North America'),
(149816,'2023-09-06 10:41:20.664598',130,NULL,'JO','The Word, Asia'),
(149817,'2023-09-06 10:41:20.664598',130,NULL,'JP','The Word, Asia'),
(153908,'2023-09-06 10:41:20.665598',130,NULL,'KE','The Word, Africa'),
(153909,'2023-09-06 10:41:20.665598',130,NULL,'KG','The Word, Asia'),
(153910,'2023-09-06 10:41:20.665598',130,NULL,'KH','The Word, Asia'),
(153911,'2023-09-06 10:41:20.665598',130,NULL,'KI','The Word, Oceania'),
(153912,'2023-09-06 10:41:20.665598',130,NULL,'KM','The Word, Africa'),
(153913,'2023-09-06 10:41:20.665598',130,NULL,'KN','The Word, North America'),
(158004,'2023-09-06 10:41:20.666599',130,NULL,'KP','The Word, Asia'),
(158005,'2023-09-06 10:41:20.666599',130,NULL,'KR','The Word, Asia'),
(158006,'2023-09-06 10:41:20.666599',130,NULL,'KW','The Word, Asia'),
(158007,'2023-09-06 10:41:20.666599',130,NULL,'KY','The Word, North America'),
(158008,'2023-09-06 10:41:20.666599',130,NULL,'KZ','The Word, Asia'),
(158009,'2023-09-06 10:41:20.666599',130,NULL,'LA','The Word, Asia'),
(162100,'2023-09-06 10:41:20.667598',130,NULL,'LB','The Word, Asia'),
(162101,'2023-09-06 10:41:20.667598',130,NULL,'LC','The Word, North America'),
(162102,'2023-09-06 10:41:20.667598',130,NULL,'LI','The Word, Europe'),
(162103,'2023-09-06 10:41:20.667598',130,NULL,'LK','The Word, Asia'),
(166196,'2023-09-06 10:41:20.668591',130,NULL,'LR','The Word, Africa'),
(166197,'2023-09-06 10:41:20.668591',130,NULL,'LS','The Word, Africa'),
(166198,'2023-09-06 10:41:20.668591',130,'2023-12-20 10:27:58.685182','LT','The Word, Europe, EU'),
(166199,'2023-09-06 10:41:20.668591',130,'2023-12-20 10:28:16.235064','LU','The Word, Europe, EU'),
(166200,'2023-09-06 10:41:20.668591',130,'2023-12-20 10:27:43.561386','LV','The Word, Europe, EU'),
(170292,'2023-09-06 10:41:20.669598',130,NULL,'LY','The Word, Africa'),
(170293,'2023-09-06 10:41:20.669598',130,NULL,'MA','The Word, Africa'),
(170294,'2023-09-06 10:41:20.669598',130,NULL,'MC','The Word, Europe'),
(170295,'2023-09-06 10:41:20.669598',130,NULL,'MD','The Word, Europe'),
(170296,'2023-09-06 10:41:20.669598',130,NULL,'ME','The Word, Europe'),
(170297,'2023-09-06 10:41:20.669598',130,NULL,'MF','The Word, North America'),
(174388,'2023-09-06 10:41:20.670599',130,NULL,'MG','The Word, Africa'),
(174389,'2023-09-06 10:41:20.670599',130,NULL,'MH','The Word, Oceania'),
(174390,'2023-09-06 10:41:20.670599',130,NULL,'MK','The Word, Europe'),
(174391,'2023-09-06 10:41:20.670599',130,NULL,'ML','The Word, Africa'),
(174392,'2023-09-06 10:41:20.670599',130,NULL,'MM','The Word, Asia'),
(174393,'2023-09-06 10:41:20.670599',130,NULL,'MN','The Word, Asia'),
(178484,'2023-09-06 10:41:20.671599',130,NULL,'MO','The Word, Asia'),
(178485,'2023-09-06 10:41:20.671599',130,NULL,'MP','The Word, Oceania'),
(178486,'2023-09-06 10:41:20.671599',130,NULL,'MR','The Word, Africa'),
(178487,'2023-09-06 10:41:20.671599',130,NULL,'MS','The Word, North America'),
(178488,'2023-09-06 10:41:20.671599',130,'2023-12-20 10:28:31.732620','MT','The Word, Europe, EU'),
(182580,'2023-09-06 10:41:20.671599',130,NULL,'MU','The Word, Africa'),
(182581,'2023-09-06 10:41:20.672598',130,NULL,'MV','The Word, Asia'),
(182582,'2023-09-06 10:41:20.672598',130,NULL,'MW','The Word, Africa'),
(182583,'2023-09-06 10:41:20.672598',130,NULL,'MX','The Word, North America'),
(182584,'2023-09-06 10:41:20.672598',130,NULL,'MY','The Word, Asia'),
(182585,'2023-09-06 10:41:20.672598',130,NULL,'MZ','The Word, Africa'),
(186676,'2023-09-06 10:41:20.673598',130,NULL,'NA','The Word, Africa'),
(186677,'2023-09-06 10:41:20.673598',130,NULL,'NC','The Word, Oceania'),
(186678,'2023-09-06 10:41:20.673598',130,NULL,'NE','The Word, Africa'),
(186679,'2023-09-06 10:41:20.673598',130,NULL,'NG','The Word, Africa'),
(186680,'2023-09-06 10:41:20.673598',130,NULL,'NI','The Word, North America'),
(186681,'2023-09-06 10:41:20.673598',130,'2023-12-20 10:28:47.050569','NL','The Word, Europe, EU'),
(186682,'2023-09-06 10:41:20.673598',130,NULL,'NO','The Word, Europe'),
(190772,'2023-09-06 10:41:20.674599',130,NULL,'NP','The Word, Asia'),
(190773,'2023-09-06 10:41:20.674599',130,NULL,'NR','The Word, Oceania'),
(190774,'2023-09-06 10:41:20.674599',130,NULL,'NU','The Word, Oceania'),
(190775,'2023-09-06 10:41:20.674599',130,NULL,'NZ','The Word, Oceania'),
(190776,'2023-09-06 10:41:20.674599',130,NULL,'OM','The Word, Asia'),
(190777,'2023-09-06 10:41:20.674599',130,NULL,'PA','The Word, North America'),
(194868,'2023-09-06 10:41:20.675598',130,NULL,'PE','The Word, South America'),
(194869,'2023-09-06 10:41:20.675598',130,NULL,'PF','The Word, Oceania'),
(194870,'2023-09-06 10:41:20.675598',130,NULL,'PG','The Word, Oceania'),
(194871,'2023-09-06 10:41:20.675598',130,NULL,'PH','The Word, Asia'),
(194872,'2023-09-06 10:41:20.675598',130,NULL,'PK','The Word, Asia'),
(198964,'2023-09-06 10:41:20.676594',130,'2023-12-20 10:29:04.148588','PL','The Word, Europe, EU'),
(198965,'2023-09-06 10:41:20.676594',130,NULL,'PM','The Word, North America'),
(198966,'2023-09-06 10:41:20.676594',130,NULL,'PN','The Word, Oceania'),
(198967,'2023-09-06 10:41:20.676594',130,NULL,'PR','The Word, North America'),
(198968,'2023-09-06 10:41:20.676594',130,NULL,'PS','The Word, Asia'),
(198969,'2023-09-06 10:41:20.676594',130,'2023-12-20 10:29:20.524449','PT','The Word, Europe, EU'),
(203060,'2023-09-06 10:41:20.677594',130,NULL,'PW','The Word, Oceania'),
(203061,'2023-09-06 10:41:20.677594',130,NULL,'PY','The Word, South America'),
(203062,'2023-09-06 10:41:20.677594',130,NULL,'QA','The Word, Asia'),
(203063,'2023-09-06 10:41:20.677594',130,NULL,'RE','The Word, Africa'),
(203064,'2023-09-06 10:41:20.677594',130,'2023-12-20 10:29:37.530077','RO','The Word, Europe, EU'),
(203065,'2023-09-06 10:41:20.677594',130,NULL,'RS','The Word, Europe'),
(207156,'2023-09-06 10:41:20.678594',130,NULL,'RU','The Word, Europe'),
(207157,'2023-09-06 10:41:20.678594',130,NULL,'RW','The Word, Africa'),
(207158,'2023-09-06 10:41:20.678594',130,NULL,'SA','The Word, Asia'),
(207159,'2023-09-06 10:41:20.678594',130,NULL,'SB','The Word, Oceania'),
(207160,'2023-09-06 10:41:20.678594',130,NULL,'SC','The Word, Africa'),
(207161,'2023-09-06 10:41:20.678594',130,NULL,'SD','The Word, Africa'),
(207162,'2023-09-06 10:41:20.678594',130,'2023-12-20 10:30:32.220383','SE','The Word, Europe, EU'),
(211252,'2023-09-06 10:41:20.679594',130,NULL,'SG','The Word, Asia'),
(211253,'2023-09-06 10:41:20.679594',130,NULL,'SH','The Word, Africa'),
(211254,'2023-09-06 10:41:20.679594',130,'2023-12-20 10:30:06.789741','SI','The Word, Europe, EU'),
(211255,'2023-09-06 10:41:20.679594',130,NULL,'SJ','The Word, Europe'),
(211256,'2023-09-06 10:41:20.679594',130,'2023-12-20 10:29:54.083995','SK','The Word, Europe, EU'),
(211257,'2023-09-06 10:41:20.679594',130,NULL,'SL','The Word, Africa'),
(215348,'2023-09-06 10:41:20.680593',130,NULL,'SM','The Word, Europe'),
(215349,'2023-09-06 10:41:20.680593',130,NULL,'SN','The Word, Africa'),
(215350,'2023-09-06 10:41:20.680593',130,NULL,'SO','The Word, Africa'),
(215351,'2023-09-06 10:41:20.680593',130,NULL,'SR','The Word, South America'),
(215352,'2023-09-06 10:41:20.680593',130,NULL,'SS','The Word, Africa'),
(215353,'2023-09-06 10:41:20.680593',130,NULL,'ST','The Word, Africa'),
(219444,'2023-09-06 10:41:20.681593',130,NULL,'SV','The Word, North America'),
(219445,'2023-09-06 10:41:20.681593',130,NULL,'SX','The Word, North America'),
(219446,'2023-09-06 10:41:20.681593',130,NULL,'SY','The Word, Asia'),
(219447,'2023-09-06 10:41:20.681593',130,NULL,'SZ','The Word, Africa'),
(219448,'2023-09-06 10:41:20.681593',130,NULL,'TC','The Word, North America'),
(223540,'2023-09-06 10:41:20.681593',130,NULL,'TD','The Word, Africa'),
(223541,'2023-09-06 10:41:20.682594',130,NULL,'TG','The Word, Africa'),
(223542,'2023-09-06 10:41:20.682594',130,NULL,'TH','The Word, Asia'),
(223543,'2023-09-06 10:41:20.682594',130,NULL,'TJ','The Word, Asia'),
(223544,'2023-09-06 10:41:20.682594',130,NULL,'TK','The Word, Oceania'),
(223545,'2023-09-06 10:41:20.682594',130,NULL,'TL','The Word, Oceania'),
(223546,'2023-09-06 10:41:20.682594',130,NULL,'TM','The Word, Asia'),
(227636,'2023-09-06 10:41:20.683594',130,NULL,'TN','The Word, Africa'),
(227637,'2023-09-06 10:41:20.683594',130,NULL,'TO','The Word, Oceania'),
(227638,'2023-09-06 10:41:20.683594',130,NULL,'TR','The Word, Asia'),
(227639,'2023-09-06 10:41:20.683594',130,NULL,'TT','The Word, North America'),
(227640,'2023-09-06 10:41:20.683594',130,NULL,'TV','The Word, Oceania'),
(227641,'2023-09-06 10:41:20.683594',130,NULL,'TW','The Word, Asia'),
(227642,'2023-09-06 10:41:20.683594',130,NULL,'TZ','The Word, Africa'),
(231732,'2023-09-06 10:41:20.684593',130,NULL,'UA','The Word, Europe'),
(231733,'2023-09-06 10:41:20.684593',130,NULL,'UG','The Word, Africa'),
(231734,'2023-09-06 10:41:20.684593',130,NULL,'US','The Word, North America'),
(231735,'2023-09-06 10:41:20.684593',130,NULL,'UY','The Word, South America'),
(231736,'2023-09-06 10:41:20.684593',130,NULL,'UZ','The Word, Asia'),
(231737,'2023-09-06 10:41:20.684593',130,NULL,'VA','The Word, Europe'),
(231738,'2023-09-06 10:41:20.684593',130,NULL,'VC','The Word, North America'),
(235828,'2023-09-06 10:41:20.685593',130,NULL,'VE','The Word, South America'),
(235829,'2023-09-06 10:41:20.685593',130,NULL,'VG','The Word, North America'),
(235830,'2023-09-06 10:41:20.685593',130,NULL,'VI','The Word, North America'),
(235831,'2023-09-06 10:41:20.685593',130,NULL,'VN','The Word, Asia'),
(235832,'2023-09-06 10:41:20.685593',130,NULL,'VU','The Word, Oceania'),
(235833,'2023-09-06 10:41:20.685593',130,NULL,'WF','The Word, Oceania'),
(239924,'2023-09-06 10:41:20.686594',130,NULL,'WS','The Word, Oceania'),
(239925,'2023-09-06 10:41:20.686594',130,NULL,'XK','The Word, Europe'),
(239926,'2023-09-06 10:41:20.686594',130,NULL,'YE','The Word, Asia'),
(239927,'2023-09-06 10:41:20.686594',130,NULL,'YT','The Word, Africa'),
(239928,'2023-09-06 10:41:20.686594',130,NULL,'ZA','The Word, Africa'),
(239929,'2023-09-06 10:41:20.686594',130,NULL,'ZM','The Word, Africa'),
(239930,'2023-09-06 10:41:20.686594',130,NULL,'ZW','The Word, Africa');
/*!40000 ALTER TABLE `Countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Drug`
--

DROP TABLE IF EXISTS `Drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Drug` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `imageBox` varchar(255) DEFAULT NULL,
  `imageMedicament` varchar(255) DEFAULT NULL,
  `imprint` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ndc9` varchar(255) DEFAULT NULL,
  `primaryColor` varchar(255) DEFAULT NULL,
  `refillFrom` int(11) DEFAULT NULL,
  `secundaryColor` varchar(255) DEFAULT NULL,
  `shape` varchar(255) DEFAULT NULL,
  `strength` int(11) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Drug`
--

LOCK TABLES `Drug` WRITE;
/*!40000 ALTER TABLE `Drug` DISABLE KEYS */;
INSERT INTO `Drug` VALUES
(201709941149697,'2025-03-06 12:56:48.515874',NULL,'2025-03-07 16:19:56.390511','media\\image\\istockphoto-1014846208-612x612_1.jpg','media\\image\\fototapeten-gelule-medicament_1.png','TEDx Talks','Ibuprofeno','Denmark','#FF0000',2,'#FFFFFF','CAPSULE',1,'Plantaciones.Edelman');
/*!40000 ALTER TABLE `Drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Drugs`
--

DROP TABLE IF EXISTS `Drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Drugs` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `imageBox` varchar(255) DEFAULT NULL,
  `imageMedicament` varchar(255) DEFAULT NULL,
  `imprint` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ndc9` varchar(255) DEFAULT NULL,
  `primaryColor` varchar(255) DEFAULT NULL,
  `refillFrom` int(11) DEFAULT NULL,
  `secundaryColor` varchar(255) DEFAULT NULL,
  `shape` varchar(255) DEFAULT NULL,
  `strength` int(11) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Drugs`
--

LOCK TABLES `Drugs` WRITE;
/*!40000 ALTER TABLE `Drugs` DISABLE KEYS */;
/*!40000 ALTER TABLE `Drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EndPoints`
--

DROP TABLE IF EXISTS `EndPoints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `EndPoints` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `EndPoints_ownerId` (`ownerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EndPoints`
--

LOCK TABLES `EndPoints` WRITE;
/*!40000 ALTER TABLE `EndPoints` DISABLE KEYS */;
INSERT INTO `EndPoints` VALUES
(1,'2023-09-19 11:55:27.118216',130,'2023-06-19 18:02:13.396087','','Account.copy'),
(2,'2023-09-19 11:55:27.118216',130,'2023-06-19 18:02:13.396087','','AuditLogs'),
(36,'2023-06-01 15:39:26.530000',130,'2023-06-02 10:13:40.399000','Communication\'s templates and messages ','Mailings'),
(37,'2023-06-01 15:39:26.530000',130,NULL,'','Mailing.copy'),
(38,'2023-06-01 15:39:26.530000',130,NULL,'','Mailing.edit'),
(39,'2023-06-01 15:39:26.530000',130,NULL,'','Mailing.delete'),
(40,'2023-06-01 15:39:26.530000',130,'2023-06-02 10:14:39.837000','Spende Formular Zahlungsart verwalten ','PaymentMethods'),
(41,'2023-06-01 15:39:26.530000',130,NULL,'','PaymentMethod.add'),
(42,'2023-06-01 15:39:26.530000',130,NULL,'','PaymentMethod.edit'),
(43,'2023-06-01 15:39:26.530000',130,NULL,'','PaymentMethod.delete'),
(48,'2023-06-01 15:39:26.530000',130,'2023-06-02 10:16:52.629000','Spenden Verwaltung','Donations'),
(50,'2023-06-01 15:39:26.530000',130,'2023-09-19 11:34:00.156687','','Contribution.edit'),
(51,'2023-06-01 15:39:26.530000',130,'2023-09-19 11:33:54.701266','','Contribution.delete'),
(52,'2023-06-01 15:39:26.530000',130,'2023-06-02 10:20:56.667000','Petition Verwaltung','Petitions'),
(54,'2023-06-01 15:39:26.530000',130,NULL,'','Petition.edit'),
(55,'2023-06-01 15:39:26.530000',130,NULL,'','Petition.delete'),
(56,'2023-06-01 15:39:26.530000',130,'2023-06-02 10:21:43.711000','Unterstützern Verwaltung','Supporters'),
(58,'2023-06-01 15:39:26.530000',130,NULL,'','Supporter.edit'),
(59,'2023-06-01 15:39:26.530000',130,'2023-12-12 14:31:17.962407','','Supporter.merge'),
(61,'2023-09-18 11:52:40.204295',130,NULL,'','InsightWidgets'),
(62,'2023-06-20 11:41:33.966597',130,NULL,'','InsightWidget.delete'),
(63,'2023-06-20 11:42:05.721877',130,'2023-06-20 11:42:14.598692','','InsightWidget.copy'),
(64,'2023-06-20 11:42:22.047034',130,'2023-09-19 11:34:58.418006','','InsightWidget.install'),
(65,'2023-06-20 11:41:24.224898',130,NULL,'','InsightWidget.edit'),
(938,'2023-06-19 15:17:23.202436',130,'2023-09-19 12:06:37.114950','Good Funds Users','Users'),
(939,'2023-06-19 15:17:42.675201',130,'2023-06-19 15:17:51.308985','','User.edit'),
(940,'2023-06-19 15:18:00.207385',130,'2023-06-19 15:18:07.979441','','User.delete'),
(941,'2023-12-12 14:30:53.122000',130,NULL,NULL,'User.join'),
(1938,'2023-06-19 18:01:42.920082',130,'2024-02-22 14:46:11.485783','Account administration','Accounts'),
(1939,'2023-06-19 18:01:47.105499',130,NULL,'','Account.edit'),
(1941,'2023-06-19 18:02:03.368359',130,NULL,'','Account.delete'),
(1942,'2023-06-20 09:21:53.625937',130,'2023-09-19 11:40:29.158863','Roles Verwaltung','Roles'),
(1943,'2023-06-20 09:22:03.012333',130,NULL,'','Role.edit'),
(1944,'2023-06-20 09:22:09.755469',130,NULL,'','Role.copy'),
(1945,'2023-06-20 09:22:20.756164',130,NULL,'','Role.delete'),
(1946,'2023-06-20 10:20:36.628785',130,'2023-06-02 10:46:36.758000','Campaign administration','Campaigns'),
(1947,'2023-06-20 10:20:40.927514',130,NULL,'','Campaign.edit'),
(1948,'2023-06-20 10:20:43.923430',130,NULL,'','Campaign.delete'),
(1949,'2023-06-20 10:20:58.586735',130,NULL,'','Campaign.copy'),
(1950,'2023-06-20 11:41:19.023335',130,'2023-06-02 10:16:24.249000','Spende-, Petitionsformular verwalten und umsetzen ','Widgets'),
(1951,'2023-06-20 11:41:24.224898',130,NULL,'','Widget.edit'),
(1952,'2023-06-20 11:41:33.966597',130,NULL,'','Widget.delete'),
(1953,'2023-06-20 11:42:05.721877',130,'2023-06-20 11:42:14.598692','','Widget.copy'),
(1954,'2023-06-20 11:42:22.047034',130,'2023-09-19 12:07:05.978449','','Widget.Install'),
(1955,'2023-06-20 11:42:44.604460',130,'2023-09-19 12:06:55.869185','','Widget.configure'),
(1962,'2023-06-21 11:22:34.511276',130,'2023-06-21 11:25:48.908372','','User.password.reset'),
(1963,'2023-06-20 11:42:44.604460',130,'2023-06-20 11:46:33.899563','','InsightWidget.configure'),
(1966,'2023-06-22 17:10:52.391069',130,'2023-06-22 17:11:05.271723','','Campaign.export'),
(2124,'2023-10-13 14:39:22.856901',130,NULL,'Internal comunication channel','ChatMessagesView'),
(3568,'2023-11-23 09:55:39.273664',130,NULL,'','Contribution.export'),
(3569,'2023-11-23 09:56:21.617033',130,NULL,'','Contribution.import'),
(3570,'2023-11-23 10:01:46.741594',130,NULL,'','Supporter.import'),
(3571,'2023-11-23 10:02:14.485092',130,NULL,'','Supporter.export'),
(3572,'2023-12-12 14:30:53.122971',130,NULL,'','Supporter.delete');
/*!40000 ALTER TABLE `EndPoints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Event`
--

DROP TABLE IF EXISTS `Event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Event` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `canBeMissed` bit(1) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `descritpion` varchar(255) DEFAULT NULL,
  `reminderCount` int(11) DEFAULT NULL,
  `reminderInterval` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Event`
--

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;
INSERT INTO `Event` VALUES
(203103201730560,'2025-03-10 11:26:00.026809',NULL,NULL,'\0',NULL,'is time to swallo the medicament Ibuprofeno',NULL,NULL,'PT5M','Medikamenteneinnahme','MEDICATION','MISSED',NULL),
(203164994011136,'2025-03-10 15:37:26.031680',NULL,NULL,'\0',NULL,'is time to swallo the medicament Ibuprofeno',NULL,3,'PT5M','Medikamenteneinnahme','MEDICATION','WAITING',NULL),
(203518794113024,'2025-03-11 15:37:03.010567',NULL,NULL,'\0',NULL,'is time to swallo the medicament Ibuprofeno',NULL,3,'PT5M','Medikamenteneinnahme','MEDICATION','WAITING',NULL),
(203519019429888,'2025-03-11 15:37:58.017242',NULL,NULL,'\0',NULL,'is time to swallo the medicament Ibuprofeno',NULL,3,'PT5M','Medikamenteneinnahme','MEDICATION','WAITING',NULL),
(205642357256192,'2025-03-17 15:37:51.044609',NULL,NULL,'\0',NULL,'is time to swallo the medicament Ibuprofeno',NULL,3,'PT5M','Medikamenteneinnahme','MEDICATION','WAITING',NULL),
(206350014869504,'2025-03-19 15:37:19.013809',NULL,NULL,'\0',NULL,'is time to swallo the medicament Ibuprofeno',NULL,3,'PT5M','Medikamenteneinnahme','MEDICATION','WAITING',NULL);
/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GlobalProperties`
--

DROP TABLE IF EXISTS `GlobalProperties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `GlobalProperties` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `accessTokeLifeSpan` varchar(255) DEFAULT NULL,
  `appEmail` varchar(255) DEFAULT NULL,
  `appLogo` varchar(255) DEFAULT NULL,
  `appName` varchar(255) DEFAULT NULL,
  `deeplApiKey` varchar(255) DEFAULT NULL,
  `deeplBaseUrl` varchar(255) DEFAULT NULL,
  `defaultPassword` varchar(255) DEFAULT NULL,
  `enableUserRegistration` bit(1) NOT NULL,
  `exchangeRateApiKey` varchar(255) DEFAULT NULL,
  `exchangeRateUrl` varchar(255) DEFAULT NULL,
  `externalTOTPLifeSpan` varchar(255) DEFAULT NULL,
  `forgotPassword` bit(1) NOT NULL,
  `loginActionTimeout` varchar(255) DEFAULT NULL,
  `magicLinkLifeSpan` varchar(255) DEFAULT NULL,
  `maxFailAttempts` int(11) DEFAULT NULL,
  `minPasswortLength` int(11) DEFAULT NULL,
  `confirmationCodeEmail_id` bigint(20) DEFAULT NULL,
  `inviteUserMail_id` bigint(20) DEFAULT NULL,
  `resetCredentialsMail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK1vg5idb0h04a8rt82um4tibeg` (`confirmationCodeEmail_id`),
  UNIQUE KEY `UKb8chu9fckcccruk4t9dhwh7cl` (`inviteUserMail_id`),
  UNIQUE KEY `UKlj9jshn52axyciq7qcpmxjahp` (`resetCredentialsMail_id`),
  CONSTRAINT `FK638padv04uop1d1sgvfjenq5k` FOREIGN KEY (`inviteUserMail_id`) REFERENCES `Mailings` (`id`),
  CONSTRAINT `FKm26koa259blsh7877clenx89` FOREIGN KEY (`confirmationCodeEmail_id`) REFERENCES `Mailings` (`id`),
  CONSTRAINT `FKm75bm1kv779pvfbwm1aaf3q73` FOREIGN KEY (`resetCredentialsMail_id`) REFERENCES `Mailings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GlobalProperties`
--

LOCK TABLES `GlobalProperties` WRITE;
/*!40000 ALTER TABLE `GlobalProperties` DISABLE KEYS */;
INSERT INTO `GlobalProperties` VALUES
(1,NULL,130,'2024-02-22 15:44:40.866667','P1D','info@simone.de','icons/icon.png','Zitrone','DeepL-Auth-Key 6cc154d9-29d9-0283-6b94-6ad8790a5bf6:fx','https://api-free.deepl.com/v2','SaureZitrone','','70fb432c8e9c576aef5f242626036693','http://api.exchangerate.host/','PT3M','','PT5M','PT3M',3,4,101,102,NULL);
/*!40000 ALTER TABLE `GlobalProperties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HanniLogs`
--

DROP TABLE IF EXISTS `HanniLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `HanniLogs` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `messageId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `HanniLogs_ownerId` (`ownerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HanniLogs`
--

LOCK TABLES `HanniLogs` WRITE;
/*!40000 ALTER TABLE `HanniLogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `HanniLogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HanniTasks`
--

DROP TABLE IF EXISTS `HanniTasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `HanniTasks` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `completed` bit(1) NOT NULL,
  `task` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HanniTasks`
--

LOCK TABLES `HanniTasks` WRITE;
/*!40000 ALTER TABLE `HanniTasks` DISABLE KEYS */;
INSERT INTO `HanniTasks` VALUES
(199251063382016,'2025-02-27 14:11:36.560898',NULL,NULL,'','TIP_USERS','TIP');
/*!40000 ALTER TABLE `HanniTasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `History`
--

DROP TABLE IF EXISTS `History`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `History` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `mood` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `adultId` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `History`
--

LOCK TABLES `History` WRITE;
/*!40000 ALTER TABLE `History` DISABLE KEYS */;
INSERT INTO `History` VALUES
(202117519577088,'2025-03-07 16:35:14.967661',NULL,'2025-03-10 10:16:11.051966',21,'SAD','12341234',12,199268075270145,'MANUAL'),
(203885260156928,'2025-03-12 16:28:12.257018',NULL,NULL,234,'HAPPY','2345',2345,199268075270145,'MANUAL');
/*!40000 ALTER TABLE `History` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mailings`
--

DROP TABLE IF EXISTS `Mailings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Mailings` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `audience` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isTemplate` bit(1) NOT NULL,
  `mailingName` varchar(255) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mailings`
--

LOCK TABLES `Mailings` WRITE;
/*!40000 ALTER TABLE `Mailings` DISABLE KEYS */;
INSERT INTO `Mailings` VALUES
(100,NULL,130,'2024-03-04 13:18:26.275091','INTERNAL','Email sendet when an account update is needed.','\0','Update Your Account','<h2>Update Your Account</h2>\r\n<p>Lieber {GlobalProperty.appName}-Nutzer,</p>\r\n<p>Your administrator has just requested that you update your {GlobalProperty.appName} account. Click on the link below to start this process.</p>\r\n<p><a href=\"{User.magicLink}\">link to account Update</a></p>\r\n<p>This link will expire within {User.magicLinkDuration}.</p>\r\n<p>If you are unaware that your administrator has requested this, just ignore this message and nothing will be changed.</p>\r\n<p>Mit freundlichen Gr&uuml;&szlig;en</p>\r\n<p>Das {GlobalProperty.appName}-Team</p>','{User.firstName} Update Your Account','HTML'),
(101,NULL,130,'2024-03-04 11:33:27.340855','INTERNAL','Email template for 2fa code confirmation.','','Bestätigungscode','<p>Lieber {GlobalProperty.appName}-Nutzer,\n\n<p>wir haben eine Anfrage für den Zugriff auf Ihr {GlobalProperty.appName}-Konto <b>{User.email}</b> über Ihre E-Mail-Adresse erhalten. Ihr {GlobalProperty.appName}-Bestätigungscode lautet:\n\n<h2>{User.confirmationCode}</h2>\n\n<p>Falls Sie diesen Code nicht angefordert haben, versucht möglicherweise eine andere Person, auf das GoodFunds-Konto <b>{User.email}</b> zuzugreifen. Geben Sie diesen Code nicht weiter.\n\n<p>Mit freundlichen Grüßen\n\n<p>Das GoodFunds Team','Ihre 2FA Bestätigungscode','HTML'),
(102,NULL,130,'2024-04-29 11:54:50.407516','INTERNAL','Email sendet when a user invite another user.','\0','InviteUser','Liebe/r {User.firstName}\n\nich hoffe, es geht dir gut. Ich möchte dich herzlich einladen, Teil unserer Organisation zu werden und gemeinsam an Projekten zu arbeiten, die uns am Herzen liegen. Deine Fähigkeiten und Ideen könnten einen bedeutenden Beitrag leisten. \n\nWenn du interessiert bist, klicke bitte auf den folgenden Link, um deine Teilnahme zu bestätigen: \n\n{User.invitationRequestUrl}\n\nHerzliche Grüße,\n\n{Account.contact.firstName} {Account.contact.lastName}\n{Account.name}\nE-Mail: {Account.contact.email}\nPhone: {Account.contact.phone}','Einladung zur Zusammenarbeit','TEXT'),
(123,'2023-04-12 15:40:15.730681',130,'2024-03-04 13:27:07.065599','DONATION','Test for Qute template language','\0','test',' {#if Contribution.salutation == \'MISTER\'}\nSehr geehrter Herr {Contribution.lastName},\n{#else if Contribution.salutation == \'DAME\'}\nSehr geehrte Frau {Contribution.lastName},\n{#else}\nSehr geehrte Damen und Herren,\n{/if}\n','snipset {Campaign.url}','TEXT'),
(221,NULL,130,'2024-04-29 11:53:51.729155','DONATION','E-Mail nach erfolgreicher Spende gesendet','','Danke Spende','<p>{Contribution.getGermanSalutation(\"soft\")}\n\n<p>im Namen unseres gesamten Teams möchte ich Ihnen aufrichtig für Ihre großzügige Spende danken. Ihre Unterstützung bedeutet für uns nicht nur finanzielle Hilfe, sondern auch eine ermutigende Bestätigung unserer gemeinsamen Ziele.\n\n<p>Dies ist eine Quittung für Ihre Unterlagen.\n<p>\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; background-color:#ecf0f1\">\n<tbody>\n<tr>\n<td>Currecy:</td>\n<td>{Contribution.currency}</td>\n</tr>\n<tr>\n<td>Organisation:</td>\n<td>{Account.shortName}</td>\n</tr>\n<tr>\n<td>Campagne Title:</td>\n<td>{Campaign.title}</td>\n</tr>\n<tr>\n<td>Donation Date:</td>\n<td>{Contribution.contributionDate}</td>\n</tr>\n<tr>\n<td>Your contribution ID:</td>\n<td>{Contribution.id}</td>\n</tr>\n<tr>\n<td>Frequency:</td>\n<td>{Contribution.frequency}</td>\n</tr>\n</tbody>\n</table>\n<p>\n<p>Mit Freundliche Grüße,\n\n<p>\n<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\">\n<tbody>\n<tr>\n<td style=\"text-align: center\"><img alt=\"Ansprechpartner\" src=\"{Campaign.contact.embeddedFoto}\" style=\"border-radius: 50%; height:64px; width:64px\" />\n<br>{Campaign.contact.firstName} {Campaign.contact.lastName}\n<br>Kampagnenleiter</td>\n<td>\n 🌐 {Campaign.url}\n<br>📞 <a href=\"tel:{Campaign.contact.phone}\">{Campaign.contact.phone}</a>\n<br>📧 <a href=\"mailto:{Campaign.contact.email}\">{Campaign.contact.email}</a>\n<br>💬 <a href=\"https://goodfunds.de\">Chatte mit uns</a>\n</td>\n</tr>\n</tbody>\n</table>\n','Sie spenden {Contribution.amount} für TERRY Großartig.','HTML'),
(222,NULL,130,'2024-03-04 11:33:04.284274','DONATION','E-Mail, wenn ein Spender nun ein regelmäßiger Spender ist','','Danke Mitgliedschaft','<p>{Contribution.getGermanSalutation(\"soft\")}\n\n<p>fantastisch Sie wollen Mitglied bei <b>{Campaign.title}</b> werden. Ich freue mich, dass Sie sich entschieden haben, sich als Fördermitglied dauerhaft für die Verbreitung von Freiem Wissen einzusetzen.\n\n<p>Wir haben Ihre Anfrage zur Mitgliedschaft erhalten und freuen uns außerordentlich über Ihr Interesse, Teil unserer Gemeinschaft zu werden. Ihre Bereitschaft, nicht nur finanziell beizutragen, sondern auch als Mitglied aktiv teilzunehmen, erfüllt uns mit großer Freude.\n\n<p>Was passiert nun? In Kürze erhalten Sie weitere Informationen zu den Mitgliedschaftsmodalitäten und den Vorteilen, die damit verbunden sind. Wir schätzen Ihr Engagement und freuen uns darauf, gemeinsam mit Ihnen positive Veränderungen zu bewirken.\n\n<p>Mit Freundliche Grüße,\n<p>{Campaign.contact.firstName} {Campaign.contact.lastName}\n<p><hr>\n<table border=\"0\" cellpadding=\"2\" cellspacing=\"2\">\n<tbody>\n<tr>\n<td><img alt=\"Campaign Image\" src=\"{Campaign.embeddedLogo}\" style=\"height:64px; width:64px\" /></td>\n<td>\n{Campaign.title}\n<br>{Campaign.url}\n<br>{Campaign.contact.address}\n</td>\n</tr>\n</tbody>\n</table>\n<hr>','Ihr nachhaltiges Engagement als Fördermitglied bei {Account.accountName}','HTML'),
(223,'2023-05-05 14:45:01.446976',130,'2024-04-29 11:55:28.806554','PETITION','Email gesended als 2-schritt petition verfahren','','Petition Bestetigung','<p>{Contribution.getGermanSalutation(\"formal\")}\n\n<p>wir möchten uns herzlich bei Ihnen für Ihre wertvolle Unterstützung unserer Petition bedanken. Ihr Engagement ist von großer Bedeutung und trägt maßgeblich dazu bei, positive Veränderungen herbeizuführen.\n\n<p>Um sicherzustellen, dass Ihre Stimme zählt, bitten wir Sie freundlich, die Petition zu bestätigen. Dieser Schritt ist entscheidend, um die Wirksamkeit unserer Bemühungen zu gewährleisten und sicherzustellen, dass Ihre Meinung gehört wird.\n\n<br><br>\n<a style =\"background-color: #199319;  color: white;  padding: 10px 15px;  text-decoration: none;\" href=\"{Contribution.confirmationUrl}\">Unterschrift Bestätigen</a>\n<br><br>\n\n<p>Ihre Unterstützung ist uns wichtig, und wir sind dankbar für Ihr Interesse an dieser Angelegenheit. Bitte nehmen Sie sich einen Moment Zeit, um die Bestätigung durchzuführen, damit wir gemeinsam einen positiven Einfluss erzielen können.\n\n<p><p>Vielen Dank für Ihr Verständnis und Ihre Mitarbeit.\n\n<p><p>Mit freundlichen Grüßen,\n\n<p><img alt=\"Campaign Image\" src=\"{Campaign.contact.embeddedSignature}\" style=\"height:64px; width:164px\" />\n<p><p>{Campaign.contact.lastName} {Campaign.contact.firstName}\n<br>Geschäftsführer | {Account.shortName}\n<hr>\n{Account.contact.street} {Account.contact.postcode} {Account.contact.city}\n<br>Telf: {Account.contact.phone}\n<br>EMail: {Account.contact.email}\n\n<p><hr>\n<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\">\n<tbody>\n<tr>\n<td><img alt=\"Campaign Image\" src=\"{Campaign.contact.embeddedFoto}\" style=\"height:64px; width:64px\" /></td>\n<td>\n{Campaign.contact.lastName} {Campaign.contact.firstName}\n<br>{Campaign.url}\n<br>{Campaign.contact.address}\n</td>\n</tr>\n</tbody>\n</table>\n<hr>','{Contribution.firstName}! Dank für Ihre Unterstützung und Erinnerung an die Petitionsbestätigung','HTML'),
(224,'2023-05-22 12:38:20.115389',130,'2024-03-04 13:18:06.613933','PETITION','E-Mail vorlag zum Unterschrift teilen','','Unterschriftsteilen','Hallo liebe Freunde,\n\nich hoffe, diese Nachricht erreicht euch wohlauf. Kürzlich bin ich auf eine inspirierende Spendenkampagne gestoßen, die wirklich eine positive Veränderung bewirken kann. Die Organisation setzt sich für \"{Campaign.description}\" ein und hat bereits beeindruckende Fortschritte erzielt.\n\nIch dachte, ihr würdet euch ebenfalls für dieses wichtige Anliegen begeistern. Hier ist der Link zur Kampagne: \n\n{Campaign.url}\n\nJeder Beitrag zählt! Lasst uns gemeinsam Gutes tun und diese Initiative unterstützen.\n\nLiebe Grüße,\n{Contribution.firstName} {Contribution.lastName}','Entdeckte eine beeindruckende Spendenkampagne – Teilen für einen guten Zweck','TEXT'),
(225,'2024-03-01 12:32:15.250241',130,'2024-03-01 13:24:50.813471','DONATION','Test Qute template','\0','Test-HTML','<p> {#if Contribution.salutation == \'MISTER\'}\nSehr geehrter Herr {Contribution.lastName},\n{#else if Contribution.salutation == \'DAME\'}\nSehr geehrte Frau {Contribution.lastName},\n{#else}\nSehr geehrte Damen und Herren,\n{/if}\n<p>\n<p>\n<p>{Campaign.city}\n<p>{Campaign.contact.address}\n<p>{Campaign.contact.city}\n<p>{Campaign.contact.countryName}\n<p>{Campaign.contact.country}\n<p>{Campaign.contact.email}\n<p>{Campaign.contact.embeddedFoto}\n<p>{Campaign.contact.embeddedSignature}\n<p>{Campaign.contact.firstName}\n<p>{Campaign.contact.getGermanSalutation(\"formal\")}\n<p>{Campaign.contact.getGermanSalutation(\"soft\")}\n<p>{Campaign.contact.id}\n<p>{Campaign.contact.isMain}\n<p>{Campaign.contact.lastName}\n<p>{Campaign.contact.mailSalutation}\n<p>{Campaign.contact.phone}\n<p>{Campaign.contact.postcode}\n<p>{Campaign.contact.salutation}\n<p>{Campaign.contact.street}\n<p>{Campaign.contact.title}\n<p>{Campaign.contact.type}\n<p>{Campaign.country}\n<p>{Campaign.descriptionShort}\n<p>{Campaign.description}\n<p>{Campaign.donationGoal}\n<p>{Campaign.donations}\n<p>{Campaign.image}\n<p>{Campaign.logo}\n<p>{Campaign.petitionGoal}\n<p>{Campaign.petitions}\n<p>{Campaign.postcode}\n<p>{Campaign.status}\n<p>{Campaign.street}\n<p>{Campaign.title}\n<p>{Campaign.totalDonations}\n<p>{Campaign.url}\n<p>{Campaign.youTubeVideoSrc}\n<p>{Contribution.address}\n<p>{Contribution.amount}\n<p>{Contribution.anonymousDonation}\n<p>{Contribution.authorizationId}\n<p>{Contribution.city}\n<p>{Contribution.comment}\n<p>{Contribution.company}\n<p>{Contribution.confirmationUrl}\n<p>{Contribution.contributionDate}\n<p>{Contribution.contributionType}\n<p>{Contribution.countryName}\n<p>{Contribution.country}\n<p>{Contribution.currency}\n<p>{Contribution.dedication}\n<p>{Contribution.donorAsCompany}\n<p>{Contribution.email}\n<p>{Contribution.feeAmount}\n<p>{Contribution.finalId}\n<p>{Contribution.firstName}\n<p>{Contribution.flagAndCountryName}\n<p>{Contribution.flagAndLocation}\n<p>{Contribution.frequency}\n<p>{Contribution.fullName}\n<p>{Contribution.getGermanSalutation(\"formal\")}\n<p>{Contribution.getGermanSalutation(\"soft\")}\n<p>{Contribution.goodFundsFee}\n<p>{Contribution.grossAmount}\n<p>{Contribution.internalNote}\n<p>{Contribution.lastName}\n<p>{Contribution.location}\n<p>{Contribution.orderId}\n<p>{Contribution.paymentMethod}\n<p>{Contribution.phone}\n<p>{Contribution.planID}\n<p>{Contribution.postcode}\n<p>{Contribution.prettyContributionDate}\n<p>{Contribution.salutation}\n<p>{Contribution.source}\n<p>{Contribution.statusChangeDate}\n<p>{Contribution.statusChangeNote}\n<p>{Contribution.status}\n<p>{Contribution.street}\n<p>{Contribution.subscriptionID}\n<p>{Contribution.subscriptionStatus}\n<p>{Contribution.title}\n<p>{Contribution.transactionId}\n<p>{Contribution.wantsMailing}','xxx','HTML'),
(226,'2023-09-25 11:31:04.461576',130,'2024-03-01 13:25:31.295881','DONATION','Test Qute template','\0','Test-TEXT',' {#if Contribution.salutation == \'MISTER\'}\nSehr geehrter Herr {Contribution.lastName},\n{#else if Contribution.salutation == \'DAME\'}\nSehr geehrte Frau {Contribution.lastName},\n{#else}\nSehr geehrte Damen und Herren,\n{/if}\n\n\n{Campaign.city}\n{Campaign.contact.address}\n{Campaign.contact.city}\n{Campaign.contact.countryName}\n{Campaign.contact.country}\n{Campaign.contact.email}\n{Campaign.contact.embeddedFoto}\n{Campaign.contact.embeddedSignature}\n{Campaign.contact.firstName}\n{Campaign.contact.getGermanSalutation(\"formal\")}\n{Campaign.contact.getGermanSalutation(\"soft\")}\n{Campaign.contact.id}\n{Campaign.contact.isMain}\n{Campaign.contact.lastName}\n{Campaign.contact.mailSalutation}\n{Campaign.contact.phone}\n{Campaign.contact.postcode}\n{Campaign.contact.salutation}\n{Campaign.contact.street}\n{Campaign.contact.title}\n{Campaign.contact.type}\n{Campaign.country}\n{Campaign.descriptionShort}\n{Campaign.description}\n{Campaign.donationGoal}\n{Campaign.donations}\n{Campaign.image}\n{Campaign.logo}\n{Campaign.petitionGoal}\n{Campaign.petitions}\n{Campaign.postcode}\n{Campaign.status}\n{Campaign.street}\n{Campaign.title}\n{Campaign.totalDonations}\n{Campaign.url}\n{Campaign.youTubeVideoSrc}\n{Contribution.address}\n{Contribution.amount}\n{Contribution.anonymousDonation}\n{Contribution.authorizationId}\n{Contribution.city}\n{Contribution.comment}\n{Contribution.company}\n{Contribution.confirmationUrl}\n{Contribution.contributionDate}\n{Contribution.contributionType}\n{Contribution.countryName}\n{Contribution.country}\n{Contribution.currency}\n{Contribution.dedication}\n{Contribution.donorAsCompany}\n{Contribution.email}\n{Contribution.feeAmount}\n{Contribution.finalId}\n{Contribution.firstName}\n{Contribution.flagAndCountryName}\n{Contribution.flagAndLocation}\n{Contribution.frequency}\n{Contribution.fullName}\n{Contribution.getGermanSalutation(\"formal\")}\n{Contribution.getGermanSalutation(\"soft\")}\n{Contribution.goodFundsFee}\n{Contribution.grossAmount}\n{Contribution.internalNote}\n{Contribution.lastName}\n{Contribution.location}\n{Contribution.orderId}\n{Contribution.paymentMethod}\n{Contribution.phone}\n{Contribution.planID}\n{Contribution.postcode}\n{Contribution.prettyContributionDate}\n{Contribution.salutation}\n{Contribution.source}\n{Contribution.statusChangeDate}\n{Contribution.statusChangeNote}\n{Contribution.status}\n{Contribution.street}\n{Contribution.subscriptionID}\n{Contribution.subscriptionStatus}\n{Contribution.title}\n{Contribution.transactionId}\n{Contribution.wantsMailing}','xxx','TEXT');
/*!40000 ALTER TABLE `Mailings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MessagingProvider`
--

DROP TABLE IF EXISTS `MessagingProvider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `MessagingProvider` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `accountName` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `clientIdTest` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `secretTest` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MessagingProvider`
--

LOCK TABLES `MessagingProvider` WRITE;
/*!40000 ALTER TABLE `MessagingProvider` DISABLE KEYS */;
INSERT INTO `MessagingProvider` VALUES
(203885631447040,'2025-03-12 16:29:42.904361',NULL,'2025-03-17 16:07:25.236983','630920493431987','EAAI2CZAEfF7sBOwoZB0FjyZAbIWivqhI4kDNCCdbA6Un1B1yzNCJ4XIjBM1oX9BFYvZBcR3pILlHgDmdwlkQYDPIkTZAJN0c1iGizFHKiL7aeC6TZBhQeE24KYyZBUIyw4FBitlDHBa0xlXEhp3vLsRO9yp7V8CQpdxPtYx7BkQ5ytCA3aL0y9726ZBf1EDAHPqpMBGmiCr47cAu8zphytsrAZAlr','bischofsweg 94','WHATSAPP','GoodDEV','Plantaciones'),
(206346356514816,'2025-03-19 15:22:25.862475',NULL,NULL,'zitrone-IG','555059617589837','-','INSTAGRAM','72144e43f1b1f308aeff676891d7c7e8','-');
/*!40000 ALTER TABLE `MessagingProvider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NotificationChannels`
--

DROP TABLE IF EXISTS `NotificationChannels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `NotificationChannels` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `channelName` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `clientSecret` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isEnabled` bit(1) NOT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NotificationChannels`
--

LOCK TABLES `NotificationChannels` WRITE;
/*!40000 ALTER TABLE `NotificationChannels` DISABLE KEYS */;
/*!40000 ALTER TABLE `NotificationChannels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NotificationChannels_Mailings`
--

DROP TABLE IF EXISTS `NotificationChannels_Mailings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `NotificationChannels_Mailings` (
  `NotificationChannel_id` bigint(20) NOT NULL,
  `messages_id` bigint(20) NOT NULL,
  PRIMARY KEY (`NotificationChannel_id`,`messages_id`),
  KEY `FKfy5o90ibcde063rnrdegny0j0` (`messages_id`),
  CONSTRAINT `FKb9l1jtr33xg4npgqar9iy2xyn` FOREIGN KEY (`NotificationChannel_id`) REFERENCES `NotificationChannels` (`id`),
  CONSTRAINT `FKfy5o90ibcde063rnrdegny0j0` FOREIGN KEY (`messages_id`) REFERENCES `Mailings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NotificationChannels_Mailings`
--

LOCK TABLES `NotificationChannels_Mailings` WRITE;
/*!40000 ALTER TABLE `NotificationChannels_Mailings` DISABLE KEYS */;
/*!40000 ALTER TABLE `NotificationChannels_Mailings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prescription`
--

DROP TABLE IF EXISTS `Prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prescription` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `contraindications` varchar(255) DEFAULT NULL,
  `cronString` varchar(255) DEFAULT NULL,
  `dosage` varchar(255) DEFAULT NULL,
  `indications` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `drug_id` bigint(20) DEFAULT NULL,
  `adultId` bigint(20) DEFAULT NULL,
  `calendarRemind` varchar(255) DEFAULT NULL,
  `calendarRepeat` varchar(255) DEFAULT NULL,
  `calendarWhen` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa22scrxou16p2un324pyo81tf` (`drug_id`),
  CONSTRAINT `FKovqj48ujgkpgb1kcutchfcaqr` FOREIGN KEY (`drug_id`) REFERENCES `Drug` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prescription`
--

LOCK TABLES `Prescription` WRITE;
/*!40000 ALTER TABLE `Prescription` DISABLE KEYS */;
INSERT INTO `Prescription` VALUES
(201740731912193,'2025-03-06 15:02:05.791961',NULL,'2025-03-10 15:35:21.047936','asdf','37 16 * * 1-5','2','Plantaciones',1,201709941149697,199268075270145,'PT1M','WEEK_DAY','2025-03-09 15:37:00.000000','Arnaldo');
/*!40000 ALTER TABLE `Prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Queries`
--

DROP TABLE IF EXISTS `Queries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Queries` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `partialWhere` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Queries`
--

LOCK TABLES `Queries` WRITE;
/*!40000 ALTER TABLE `Queries` DISABLE KEYS */;
/*!40000 ALTER TABLE `Queries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role_endPoints`
--

DROP TABLE IF EXISTS `Role_endPoints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role_endPoints` (
  `Role_id` bigint(20) NOT NULL,
  `endPoints` varchar(255) DEFAULT NULL,
  KEY `FK6vv5lvlyfqj3xotsgn6dkn935` (`Role_id`),
  CONSTRAINT `FK6vv5lvlyfqj3xotsgn6dkn935` FOREIGN KEY (`Role_id`) REFERENCES `Roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role_endPoints`
--

LOCK TABLES `Role_endPoints` WRITE;
/*!40000 ALTER TABLE `Role_endPoints` DISABLE KEYS */;
INSERT INTO `Role_endPoints` VALUES
(261,'Roles'),
(261,'Users'),
(261,'PaymentMethods'),
(261,'Mailings');
/*!40000 ALTER TABLE `Role_endPoints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Roles` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isTemplate` bit(1) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `selectionMethod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES
(260,'2022-12-12 16:08:45.077000',130,'2024-03-04 13:57:27.697161','Der Master Nutzer hat die vollständige Kontrolle über die Anwendung','','Master','NOT_SELECTED'),
(261,'2024-03-04 13:31:16.829670',130,'2024-03-04 13:57:34.214610','Der Rolle Nutzer kann nicht die Anwendung konfigurieren','','User','NOT_SELECTED');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tamagotchies`
--

DROP TABLE IF EXISTS `Tamagotchies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tamagotchies` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `alive` bit(1) NOT NULL,
  `bedTime` time(6) DEFAULT NULL,
  `cleanliness` int(11) NOT NULL,
  `energy` int(11) NOT NULL,
  `experience` int(11) NOT NULL,
  `happiness` int(11) NOT NULL,
  `health` int(11) NOT NULL,
  `hunger` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `sleeping` bit(1) NOT NULL,
  `weight` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tamagotchies`
--

LOCK TABLES `Tamagotchies` WRITE;
/*!40000 ALTER TABLE `Tamagotchies` DISABLE KEYS */;
INSERT INTO `Tamagotchies` VALUES
(210938461782016,'2025-04-01 14:47:45.310501',NULL,NULL,0,'\0',NULL,0,0,0,0,0,0,0,'\0',0,'media\\image\\Avatar1_1.jpeg','test');
/*!40000 ALTER TABLE `Tamagotchies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserPreferences`
--

DROP TABLE IF EXISTS `UserPreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserPreferences` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  `view` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserPreferences`
--

LOCK TABLES `UserPreferences` WRITE;
/*!40000 ALTER TABLE `UserPreferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserPreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_internalActions`
--

DROP TABLE IF EXISTS `User_internalActions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `User_internalActions` (
  `User_id` bigint(20) NOT NULL,
  `internalActions` varchar(255) DEFAULT NULL,
  KEY `FKsp9gv9f2h52xfr1pn4qujsc1k` (`User_id`),
  CONSTRAINT `FKsp9gv9f2h52xfr1pn4qujsc1k` FOREIGN KEY (`User_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_internalActions`
--

LOCK TABLES `User_internalActions` WRITE;
/*!40000 ALTER TABLE `User_internalActions` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_internalActions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `failedAttemptsCount` int(11) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `invitationRequestUrl` varchar(255) DEFAULT NULL,
  `isEmailVerified` bit(1) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  `isLive` bit(1) NOT NULL,
  `isMfaExempt` bit(1) NOT NULL,
  `isTemporalPass` bit(1) NOT NULL,
  `isTotpRegistred` bit(1) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `lastSignIn` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `preferredLanguage` varchar(255) DEFAULT NULL,
  `preferredTheme` varchar(255) DEFAULT NULL,
  `rowsPerPage` int(11) NOT NULL,
  `sessionId` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userSecret` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `messagingProviderId` bigint(20) DEFAULT NULL,
  `messagingProvider_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5djch6db448x9v67b4mb1n6q8` (`role_id`),
  UNIQUE KEY `UK7ctbk5a4d3hupe2sw11yyctte` (`messagingProvider_id`),
  KEY `User_userName` (`userName`),
  CONSTRAINT `FK93t6fsabomo6w41ksaj3009a7` FOREIGN KEY (`messagingProvider_id`) REFERENCES `MessagingProvider` (`id`),
  CONSTRAINT `FKcogjq1smjy03v5s2wfegritx6` FOREIGN KEY (`role_id`) REFERENCES `Roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES
(130,'2023-06-21 14:40:55.880581',130,'2025-04-02 15:00:40.993462','media\\image\\large_lemons-0718_1.png','Zitrone@simone.de',0,'Sauer',NULL,'','','\0','','\0','\0','Zitrone','2025-04-02 15:00:40.991426','$2a$04$M2K6kiIkfsxqvHgl18/13OROOP3N3zqMgZgDHbTfktwz7LkDt/ZBe','+4915224625752','de','light',22,-1,'VERIFIED','CARER','Zitrone','ZRQJBAVOS3NUU3ERIGL2C37RBM74ZS7O',260,84233121845248,NULL,203885631447040);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users_HanniTasks`
--

DROP TABLE IF EXISTS `Users_HanniTasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users_HanniTasks` (
  `User_id` bigint(20) NOT NULL,
  `tasks_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK8xwpuvmll4vo8w7xu7cant1nu` (`tasks_id`),
  KEY `FKebhuoluqjiockqbno9aja8snt` (`User_id`),
  CONSTRAINT `FKbym4kx330cl6ippfrf001sk72` FOREIGN KEY (`tasks_id`) REFERENCES `HanniTasks` (`id`),
  CONSTRAINT `FKebhuoluqjiockqbno9aja8snt` FOREIGN KEY (`User_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users_HanniTasks`
--

LOCK TABLES `Users_HanniTasks` WRITE;
/*!40000 ALTER TABLE `Users_HanniTasks` DISABLE KEYS */;
INSERT INTO `Users_HanniTasks` VALUES
(130,199251063382016);
/*!40000 ALTER TABLE `Users_HanniTasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users_Queries`
--

DROP TABLE IF EXISTS `Users_Queries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users_Queries` (
  `User_id` bigint(20) NOT NULL,
  `queries_id` bigint(20) NOT NULL,
  PRIMARY KEY (`User_id`,`queries_id`),
  KEY `FKs0k3ds4pe7g364bjxw6r0tclj` (`queries_id`),
  CONSTRAINT `FKrhl9tx7otlh3gmq4m09dnrwcd` FOREIGN KEY (`User_id`) REFERENCES `Users` (`id`),
  CONSTRAINT `FKs0k3ds4pe7g364bjxw6r0tclj` FOREIGN KEY (`queries_id`) REFERENCES `Queries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users_Queries`
--

LOCK TABLES `Users_Queries` WRITE;
/*!40000 ALTER TABLE `Users_Queries` DISABLE KEYS */;
/*!40000 ALTER TABLE `Users_Queries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users_Roles`
--

DROP TABLE IF EXISTS `Users_Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users_Roles` (
  `User_id` bigint(20) NOT NULL,
  `associatedRoles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`User_id`,`associatedRoles_id`),
  KEY `FKris0x2awl9i8txb3jamvdrd03` (`associatedRoles_id`),
  CONSTRAINT `FK2iuayi31a6uniqaesbullkrmm` FOREIGN KEY (`User_id`) REFERENCES `Users` (`id`),
  CONSTRAINT `FKris0x2awl9i8txb3jamvdrd03` FOREIGN KEY (`associatedRoles_id`) REFERENCES `Roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users_Roles`
--

LOCK TABLES `Users_Roles` WRITE;
/*!40000 ALTER TABLE `Users_Roles` DISABLE KEYS */;
INSERT INTO `Users_Roles` VALUES
(130,260);
/*!40000 ALTER TABLE `Users_Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users_UserPreferences`
--

DROP TABLE IF EXISTS `Users_UserPreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users_UserPreferences` (
  `User_id` bigint(20) NOT NULL,
  `preferences_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK9navt5fli919mdpfy0y63wfys` (`preferences_id`),
  KEY `FKsw6v6j3w16wt5rkro1oj8n0k` (`User_id`),
  CONSTRAINT `FK71f8moxwn50gxa2swq0uiejr8` FOREIGN KEY (`preferences_id`) REFERENCES `UserPreferences` (`id`),
  CONSTRAINT `FKsw6v6j3w16wt5rkro1oj8n0k` FOREIGN KEY (`User_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users_UserPreferences`
--

LOCK TABLES `Users_UserPreferences` WRITE;
/*!40000 ALTER TABLE `Users_UserPreferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `Users_UserPreferences` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-04-02 15:26:10
