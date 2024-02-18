-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: dbBike
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.23.10.1

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
-- Table structure for table `Prenota`
--

DROP TABLE IF EXISTS `Prenota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prenota` (
  `email_cliente` varchar(50) NOT NULL,
  `id_bici` int NOT NULL,
  `ora_inizio_prenota` time NOT NULL,
  `ora_fine_prenota` time DEFAULT NULL,
  PRIMARY KEY (`id_bici`,`email_cliente`),
  KEY `email_cliente` (`email_cliente`),
  CONSTRAINT `Prenota_ibfk_1` FOREIGN KEY (`id_bici`) REFERENCES `Bicicletta` (`id_bici`),
  CONSTRAINT `Prenota_ibfk_2` FOREIGN KEY (`email_cliente`) REFERENCES `Cliente` (`email_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prenota`
--

LOCK TABLES `Prenota` WRITE;
/*!40000 ALTER TABLE `Prenota` DISABLE KEYS */;
INSERT INTO `Prenota` VALUES ('c1@c1.it',1,'10:00:00',NULL),('c1@c1.it',2,'10:00:00',NULL),('c1@c1.it',3,'15:00:00',NULL),('c1@c1.it',4,'10:00:00',NULL),('c1@c1.it',7,'10:00:00',NULL),('c1@c1.it',8,'10:00:00',NULL),('c1@c1.it',9,'10:00:00',NULL);
/*!40000 ALTER TABLE `Prenota` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-18 14:11:20
