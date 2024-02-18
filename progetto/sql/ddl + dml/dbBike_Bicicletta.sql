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
-- Table structure for table `Bicicletta`
--

DROP TABLE IF EXISTS `Bicicletta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bicicletta` (
  `id_bici` int NOT NULL,
  `categoria_bici` varchar(50) DEFAULT NULL,
  `prezzo_bici` int DEFAULT NULL,
  `id_parcheggio` int DEFAULT NULL,
  `disponibile` tinyint(1) DEFAULT NULL,
  `km_effettuati` int DEFAULT NULL,
  PRIMARY KEY (`id_bici`),
  KEY `id_parcheggio` (`id_parcheggio`),
  CONSTRAINT `Bicicletta_ibfk_1` FOREIGN KEY (`id_parcheggio`) REFERENCES `Parcheggio` (`id_parcheggio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bicicletta`
--

LOCK TABLES `Bicicletta` WRITE;
/*!40000 ALTER TABLE `Bicicletta` DISABLE KEYS */;
INSERT INTO `Bicicletta` VALUES (1,'corsa',500,2,0,4631),(2,'City Bike',400,5,0,6244),(3,'BMX',350,5,0,7569),(4,'Bici da corsa',700,5,0,6761),(5,'Bici pieghevole',600,5,1,2296),(6,'Bici da trekking',550,5,1,1730),(7,'Bici elettrica',800,5,0,7638),(8,'Bici per bambini',200,5,0,7695),(9,'Bici da viaggio',750,5,0,7482),(10,'Bici da trial',450,5,1,553),(12,'1',1,1,NULL,702),(13,'CORSA',500,1,NULL,682),(14,'1',1,1,NULL,1427),(31,'Mountain Bike',500,1,1,100),(32,'City Bike',300,2,1,50),(33,'Bici da Corsa',700,1,1,200),(44,'corsa',500,1,NULL,1579),(122,'1',1,1,NULL,1323),(123,'1',1,1,NULL,1657);
/*!40000 ALTER TABLE `Bicicletta` ENABLE KEYS */;
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
