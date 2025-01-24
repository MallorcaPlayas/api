-- MySQL dump 10.13  Distrib 9.1.0, for Linux (x86_64)
--
-- Host: localhost    Database: spring
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Dumping data for table `aggregation_types`
--

LOCK TABLES `aggregation_types` WRITE;
/*!40000 ALTER TABLE `aggregation_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `aggregation_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `beach`
--

LOCK TABLES `beach` WRITE;
/*!40000 ALTER TABLE `beach` DISABLE KEYS */;
INSERT INTO `beach` VALUES (1,'A beautiful sandy beach with clear water.','Sunny Beach'),(2,'A rocky beach with lots of cliffs.','Rocky Cove'),(3,'A peaceful secluded beach, perfect for relaxation.','Hidden Bay'),(4,'An urban beach with lots of bars and restaurants.','City Shore'),(5,'A family-friendly beach with plenty of activities for children.','Family Fun Beach'),(6,'A beach with strong winds, perfect for windsurfing.','Windy Point'),(7,'A pristine beach with clear waters and soft sand.','Crystal Beach'),(8,'A lively beach with music and beach volleyball.','Party Beach'),(9,'A remote beach with hiking trails nearby.','Explorer’s Cove'),(10,'A pet-friendly beach where dogs can play freely.','Dog’s Paradise'),(11,'A beautiful sandy beach with clear water.','Sunny Beach'),(12,'A rocky beach with lots of cliffs.','Rocky Cove'),(13,'A peaceful secluded beach, perfect for relaxation.','Hidden Bay'),(14,'An urban beach with lots of bars and restaurants.','City Shore'),(15,'A family-friendly beach with plenty of activities for children.','Family Fun Beach'),(16,'A beach with strong winds, perfect for windsurfing.','Windy Point'),(17,'A pristine beach with clear waters and soft sand.','Crystal Beach'),(18,'A lively beach with music and beach volleyball.','Party Beach'),(19,'A remote beach with hiking trails nearby.','Explorer’s Cove'),(20,'A pet-friendly beach where dogs can play freely.','Dog’s Paradise'),(21,'A beautiful sandy beach with clear water.','Sunny Beach'),(22,'A rocky beach with lots of cliffs.','Rocky Cove'),(23,'A peaceful secluded beach, perfect for relaxation.','Hidden Bay'),(24,'An urban beach with lots of bars and restaurants.','City Shore'),(25,'A family-friendly beach with plenty of activities for children.','Family Fun Beach'),(26,'A beach with strong winds, perfect for windsurfing.','Windy Point'),(27,'A pristine beach with clear waters and soft sand.','Crystal Beach'),(28,'A lively beach with music and beach volleyball.','Party Beach'),(29,'A remote beach with hiking trails nearby.','Explorer’s Cove'),(30,'A pet-friendly beach where dogs can play freely.','Dog’s Paradise');
/*!40000 ALTER TABLE `beach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `beach_has_service`
--

LOCK TABLES `beach_has_service` WRITE;
/*!40000 ALTER TABLE `beach_has_service` DISABLE KEYS */;
INSERT INTO `beach_has_service` VALUES ('12:00:00.000000','08:00:00.000000',1,1,1),('16:00:00.000000','12:00:00.000000',2,2,2),('20:00:00.000000','16:00:00.000000',3,3,3),('12:00:00.000000','08:00:00.000000',4,4,4),('16:00:00.000000','12:00:00.000000',5,5,5),('20:00:00.000000','16:00:00.000000',6,6,1),('00:00:00.000000','20:00:00.000000',7,7,2),('04:00:00.000000','00:00:00.000000',8,8,3),('08:00:00.000000','04:00:00.000000',9,9,4),('12:00:00.000000','08:00:00.000000',10,10,5),('12:00:00.000000','08:00:00.000000',1,11,1),('16:00:00.000000','12:00:00.000000',2,12,2),('20:00:00.000000','16:00:00.000000',3,13,3),('12:00:00.000000','08:00:00.000000',4,14,4),('16:00:00.000000','12:00:00.000000',5,15,5),('20:00:00.000000','16:00:00.000000',6,16,1),('00:00:00.000000','20:00:00.000000',7,17,2),('04:00:00.000000','00:00:00.000000',8,18,3),('08:00:00.000000','04:00:00.000000',9,19,4),('12:00:00.000000','08:00:00.000000',10,20,5),('12:00:00.000000','08:00:00.000000',1,21,1),('16:00:00.000000','12:00:00.000000',2,22,2),('20:00:00.000000','16:00:00.000000',3,23,3),('12:00:00.000000','08:00:00.000000',4,24,4),('16:00:00.000000','12:00:00.000000',5,25,5),('20:00:00.000000','16:00:00.000000',6,26,1),('00:00:00.000000','20:00:00.000000',7,27,2),('04:00:00.000000','00:00:00.000000',8,28,3),('08:00:00.000000','04:00:00.000000',9,29,4),('12:00:00.000000','08:00:00.000000',10,30,5);
/*!40000 ALTER TABLE `beach_has_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `beach_manager`
--

LOCK TABLES `beach_manager` WRITE;
/*!40000 ALTER TABLE `beach_manager` DISABLE KEYS */;
INSERT INTO `beach_manager` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,10,10);
/*!40000 ALTER TABLE `beach_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `beach_type`
--

LOCK TABLES `beach_type` WRITE;
/*!40000 ALTER TABLE `beach_type` DISABLE KEYS */;
INSERT INTO `beach_type` VALUES (1,1),(2,2),(3,3),(4,1),(5,2),(6,3),(7,1),(8,2),(9,3),(10,1),(1,1),(2,2),(3,3),(4,1),(5,2),(6,3),(7,1),(8,2),(9,3),(10,1),(1,1),(2,2),(3,3),(4,1),(5,2),(6,3),(7,1),(8,2),(9,3),(10,1);
/*!40000 ALTER TABLE `beach_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bill_types`
--

LOCK TABLES `bill_types` WRITE;
/*!40000 ALTER TABLE `bill_types` DISABLE KEYS */;
INSERT INTO `bill_types` VALUES (1,'Factura A'),(2,'Factura B'),(3,'Factura C'),(4,'Factura D'),(5,'Factura E'),(6,'Factura F'),(7,'Factura G'),(8,'Factura H'),(9,'Factura I'),(10,'Factura J'),(11,'Factura A'),(12,'Factura B'),(13,'Factura C'),(14,'Factura D'),(15,'Factura E'),(16,'Factura F'),(17,'Factura G'),(18,'Factura H'),(19,'Factura I'),(20,'Factura J'),(21,'Factura A'),(22,'Factura B'),(23,'Factura C'),(24,'Factura D'),(25,'Factura E'),(26,'Factura F'),(27,'Factura G'),(28,'Factura H'),(29,'Factura I'),(30,'Factura J');
/*!40000 ALTER TABLE `bill_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (100.5,'2025-01-01 10:00:00.000000',1,'1990-05-15 00:00:00.000000',1,'john.doe@example.com','Doe','Smith','johndoe'),(200.75,'2025-01-02 11:00:00.000000',2,'1985-03-10 00:00:00.000000',2,'jane.doe@example.com','Doe','Johnson','janedoe'),(150,'2025-01-03 12:00:00.000000',3,'1992-07-20 00:00:00.000000',3,'mark.jones@example.com','Jones','Taylor','markjones'),(250.5,'2025-01-04 14:00:00.000000',1,'1988-11-25 00:00:00.000000',4,'alice.smith@example.com','Smith','Brown','alicesmith'),(300.25,'2025-01-05 15:00:00.000000',4,'1995-02-05 00:00:00.000000',5,'bob.williams@example.com','Williams','Davis','bobwilliams'),(50,'2025-01-06 16:00:00.000000',5,'1993-09-12 00:00:00.000000',6,'susan.miller@example.com','Miller','Moore','susanmiller'),(75.75,'2025-01-07 17:00:00.000000',6,'1991-12-30 00:00:00.000000',7,'tom.clark@example.com','Clark','Wilson','tomclark'),(120,'2025-01-08 18:00:00.000000',2,'1990-04-20 00:00:00.000000',8,'mary.lewis@example.com','Lewis','Taylor','marylewis'),(180.1,'2025-01-09 19:00:00.000000',3,'1994-06-10 00:00:00.000000',9,'peter.martin@example.com','Martin','Hall','petermartin'),(220.4,'2025-01-10 20:00:00.000000',4,'1987-08-30 00:00:00.000000',10,'lisa.white@example.com','White','Allen','lisawhite'),(100.5,'2025-01-01 10:00:00.000000',1,'1990-05-15 00:00:00.000000',11,'john.doe@example.com','Doe','Smith','johndoe'),(200.75,'2025-01-02 11:00:00.000000',2,'1985-03-10 00:00:00.000000',12,'jane.doe@example.com','Doe','Johnson','janedoe'),(150,'2025-01-03 12:00:00.000000',3,'1992-07-20 00:00:00.000000',13,'mark.jones@example.com','Jones','Taylor','markjones'),(250.5,'2025-01-04 14:00:00.000000',1,'1988-11-25 00:00:00.000000',14,'alice.smith@example.com','Smith','Brown','alicesmith'),(300.25,'2025-01-05 15:00:00.000000',4,'1995-02-05 00:00:00.000000',15,'bob.williams@example.com','Williams','Davis','bobwilliams'),(50,'2025-01-06 16:00:00.000000',5,'1993-09-12 00:00:00.000000',16,'susan.miller@example.com','Miller','Moore','susanmiller'),(75.75,'2025-01-07 17:00:00.000000',6,'1991-12-30 00:00:00.000000',17,'tom.clark@example.com','Clark','Wilson','tomclark'),(120,'2025-01-08 18:00:00.000000',2,'1990-04-20 00:00:00.000000',18,'mary.lewis@example.com','Lewis','Taylor','marylewis'),(180.1,'2025-01-09 19:00:00.000000',3,'1994-06-10 00:00:00.000000',19,'peter.martin@example.com','Martin','Hall','petermartin'),(220.4,'2025-01-10 20:00:00.000000',4,'1987-08-30 00:00:00.000000',20,'lisa.white@example.com','White','Allen','lisawhite'),(100.5,'2025-01-01 10:00:00.000000',1,'1990-05-15 00:00:00.000000',21,'john.doe@example.com','Doe','Smith','johndoe'),(200.75,'2025-01-02 11:00:00.000000',2,'1985-03-10 00:00:00.000000',22,'jane.doe@example.com','Doe','Johnson','janedoe'),(150,'2025-01-03 12:00:00.000000',3,'1992-07-20 00:00:00.000000',23,'mark.jones@example.com','Jones','Taylor','markjones'),(250.5,'2025-01-04 14:00:00.000000',1,'1988-11-25 00:00:00.000000',24,'alice.smith@example.com','Smith','Brown','alicesmith'),(300.25,'2025-01-05 15:00:00.000000',4,'1995-02-05 00:00:00.000000',25,'bob.williams@example.com','Williams','Davis','bobwilliams'),(50,'2025-01-06 16:00:00.000000',5,'1993-09-12 00:00:00.000000',26,'susan.miller@example.com','Miller','Moore','susanmiller'),(75.75,'2025-01-07 17:00:00.000000',6,'1991-12-30 00:00:00.000000',27,'tom.clark@example.com','Clark','Wilson','tomclark'),(120,'2025-01-08 18:00:00.000000',2,'1990-04-20 00:00:00.000000',28,'mary.lewis@example.com','Lewis','Taylor','marylewis'),(180.1,'2025-01-09 19:00:00.000000',3,'1994-06-10 00:00:00.000000',29,'peter.martin@example.com','Martin','Hall','petermartin'),(220.4,'2025-01-10 20:00:00.000000',4,'1987-08-30 00:00:00.000000',30,'lisa.white@example.com','White','Allen','lisawhite');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `budget_business`
--

LOCK TABLES `budget_business` WRITE;
/*!40000 ALTER TABLE `budget_business` DISABLE KEYS */;
INSERT INTO `budget_business` VALUES (_binary '',1,1,'2025-02-28 00:00:00.000000',1,'2025-01-20 00:00:00.000000','2025-01-15 00:00:00.000000'),(_binary '\0',2,2,'2025-03-01 00:00:00.000000',2,NULL,'2025-01-18 00:00:00.000000'),(_binary '',3,3,'2025-04-01 00:00:00.000000',3,'2025-01-21 00:00:00.000000','2025-01-10 00:00:00.000000'),(_binary '\0',4,4,'2025-02-10 00:00:00.000000',4,NULL,'2025-01-19 00:00:00.000000'),(_binary '',5,5,'2025-03-15 00:00:00.000000',5,'2025-01-22 00:00:00.000000','2025-01-14 00:00:00.000000'),(_binary '\0',6,6,'2025-02-20 00:00:00.000000',6,NULL,'2025-01-13 00:00:00.000000'),(_binary '',7,7,'2025-05-01 00:00:00.000000',7,'2025-01-23 00:00:00.000000','2025-01-16 00:00:00.000000'),(_binary '\0',8,8,'2025-06-01 00:00:00.000000',8,NULL,'2025-01-17 00:00:00.000000'),(_binary '',9,9,'2025-07-01 00:00:00.000000',9,'2025-01-24 00:00:00.000000','2025-01-12 00:00:00.000000'),(_binary '\0',10,10,'2025-08-01 00:00:00.000000',10,NULL,'2025-01-11 00:00:00.000000');
/*!40000 ALTER TABLE `budget_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `budgets`
--

LOCK TABLES `budgets` WRITE;
/*!40000 ALTER TABLE `budgets` DISABLE KEYS */;
INSERT INTO `budgets` VALUES (1000.5,1),(1500.75,2),(2000,3),(1200.3,4),(950.6,5),(850.25,6),(1100.4,7),(1300.9,8),(1600.15,9),(1400,10),(1000.5,11),(1500.75,12),(2000,13),(1200.3,14),(950.6,15),(850.25,16),(1100.4,17),(1300.9,18),(1600.15,19),(1400,20),(1000.5,21),(1500.75,22),(2000,23),(1200.3,24),(950.6,25),(850.25,26),(1100.4,27),(1300.9,28),(1600.15,29),(1400,30);
/*!40000 ALTER TABLE `budgets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `business_types`
--

LOCK TABLES `business_types` WRITE;
/*!40000 ALTER TABLE `business_types` DISABLE KEYS */;
INSERT INTO `business_types` VALUES (1,'Restaurant'),(2,'Hotel'),(3,'Retail'),(4,'Service'),(5,'Entertainment'),(6,'Technology'),(7,'Consulting'),(8,'Manufacturing'),(9,'Healthcare'),(10,'Education'),(11,'Restaurant'),(12,'Hotel'),(13,'Retail'),(14,'Service'),(15,'Entertainment'),(16,'Technology'),(17,'Consulting'),(18,'Manufacturing'),(19,'Healthcare'),(20,'Education'),(21,'Restaurant'),(22,'Hotel'),(23,'Retail'),(24,'Service'),(25,'Entertainment'),(26,'Technology'),(27,'Consulting'),(28,'Manufacturing'),(29,'Healthcare'),(30,'Education');
/*!40000 ALTER TABLE `business_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `businesses`
--

LOCK TABLES `businesses` WRITE;
/*!40000 ALTER TABLE `businesses` DISABLE KEYS */;
INSERT INTO `businesses` VALUES (1,1,'123-456-7890','http://restaurant-docs.com','Delicious Diner','A fine dining experience for all'),(2,2,'987-654-3210','http://hotel-docs.com','Sunset Hotel','Comfortable stays with a scenic view'),(3,3,'555-123-4567','http://retail-docs.com','SuperMart','Your one-stop shop for all needs'),(4,4,'444-987-6543','http://service-docs.com','QuickFix','Fast and reliable service solutions'),(5,5,'333-222-1111','http://entertainment-docs.com','FunZone','Entertainment for all ages'),(6,6,'888-555-4444','http://tech-docs.com','Tech Solutions','Innovative solutions for modern businesses'),(7,7,'666-123-0987','http://consulting-docs.com','BrightConsult','Consulting for growth and success'),(8,8,'222-444-6666','http://manufacturing-docs.com','IronWorks','Top-tier manufacturing solutions'),(9,9,'111-999-8888','http://healthcare-docs.com','HealthPlus','Comprehensive healthcare services'),(10,10,'777-888-9999','http://education-docs.com','LearnMore','Quality education for the future'),(1,11,'123-456-7890','http://restaurant-docs.com','Delicious Diner','A fine dining experience for all'),(2,12,'987-654-3210','http://hotel-docs.com','Sunset Hotel','Comfortable stays with a scenic view'),(3,13,'555-123-4567','http://retail-docs.com','SuperMart','Your one-stop shop for all needs'),(4,14,'444-987-6543','http://service-docs.com','QuickFix','Fast and reliable service solutions'),(5,15,'333-222-1111','http://entertainment-docs.com','FunZone','Entertainment for all ages'),(6,16,'888-555-4444','http://tech-docs.com','Tech Solutions','Innovative solutions for modern businesses'),(7,17,'666-123-0987','http://consulting-docs.com','BrightConsult','Consulting for growth and success'),(8,18,'222-444-6666','http://manufacturing-docs.com','IronWorks','Top-tier manufacturing solutions'),(9,19,'111-999-8888','http://healthcare-docs.com','HealthPlus','Comprehensive healthcare services'),(10,20,'777-888-9999','http://education-docs.com','LearnMore','Quality education for the future'),(1,21,'123-456-7890','http://restaurant-docs.com','Delicious Diner','A fine dining experience for all'),(2,22,'987-654-3210','http://hotel-docs.com','Sunset Hotel','Comfortable stays with a scenic view'),(3,23,'555-123-4567','http://retail-docs.com','SuperMart','Your one-stop shop for all needs'),(4,24,'444-987-6543','http://service-docs.com','QuickFix','Fast and reliable service solutions'),(5,25,'333-222-1111','http://entertainment-docs.com','FunZone','Entertainment for all ages'),(6,26,'888-555-4444','http://tech-docs.com','Tech Solutions','Innovative solutions for modern businesses'),(7,27,'666-123-0987','http://consulting-docs.com','BrightConsult','Consulting for growth and success'),(8,28,'222-444-6666','http://manufacturing-docs.com','IronWorks','Top-tier manufacturing solutions'),(9,29,'111-999-8888','http://healthcare-docs.com','HealthPlus','Comprehensive healthcare services'),(10,30,'777-888-9999','http://education-docs.com','LearnMore','Quality education for the future');
/*!40000 ALTER TABLE `businesses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `businesses_horary`
--

LOCK TABLES `businesses_horary` WRITE;
/*!40000 ALTER TABLE `businesses_horary` DISABLE KEYS */;
INSERT INTO `businesses_horary` VALUES (_binary '',1,'2025-01-22 09:00:00.000000',1,1),(_binary '\0',1,'2025-01-22 18:00:00.000000',2,2),(_binary '',2,'2025-01-22 08:00:00.000000',3,3),(_binary '',3,'2025-01-22 10:00:00.000000',4,4),(_binary '\0',4,'2025-01-22 15:00:00.000000',5,5),(_binary '',5,'2025-01-22 12:00:00.000000',6,6),(_binary '',6,'2025-01-22 14:00:00.000000',7,7),(_binary '\0',7,'2025-01-22 16:00:00.000000',8,8),(_binary '',8,'2025-01-22 09:30:00.000000',9,9),(_binary '',9,'2025-01-22 11:00:00.000000',10,10);
/*!40000 ALTER TABLE `businesses_horary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
INSERT INTO `camera` VALUES (1,1,'http://example.com/camera1.jpg'),(2,2,'http://example.com/camera2.jpg'),(3,3,'http://example.com/camera3.jpg'),(4,4,'http://example.com/camera4.jpg'),(5,5,'http://example.com/camera5.jpg'),(6,6,'http://example.com/camera6.jpg'),(7,7,'http://example.com/camera7.jpg'),(8,8,'http://example.com/camera8.jpg'),(9,9,'http://example.com/camera9.jpg'),(10,10,'http://example.com/camera10.jpg');
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (5,1,'2025-01-20 12:00:00.000000',21,'2025-01-15 10:00:00.000000',1,1,'Great beach, very clean and beautiful!'),(4,1,'2025-01-22 14:00:00.000000',22,'2025-01-18 09:00:00.000000',2,2,'Nice location, but could use more amenities.'),(3,1,'2025-01-20 16:00:00.000000',23,'2025-01-19 11:00:00.000000',3,3,'Not bad, but the water is a bit cold.'),(5,1,'2025-01-21 17:00:00.000000',24,'2025-01-20 13:00:00.000000',4,4,'Amazing sunset view, a must-visit spot!'),(2,1,'2025-01-23 18:00:00.000000',25,'2025-01-22 15:00:00.000000',5,5,'The beach is too crowded, not a peaceful place.'),(4,1,'2025-01-22 13:00:00.000000',26,'2025-01-21 10:00:00.000000',6,6,'A quiet beach with good service, worth a visit.'),(3,7,'2025-01-24 19:00:00.000000',27,'2025-01-23 12:00:00.000000',2,7,'Could be cleaner, but still okay for a quick stop.'),(5,8,'2025-01-25 20:00:00.000000',28,'2025-01-24 14:00:00.000000',2,8,'A perfect spot for families, very relaxing atmosphere.'),(4,9,'2025-01-26 21:00:00.000000',29,'2025-01-25 16:00:00.000000',3,9,'Good beach, but it gets busy on weekends.'),(2,10,'2025-01-27 22:00:00.000000',30,'2025-01-26 17:00:00.000000',4,10,'The beach is nice, but the weather wasnt great.');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (5,'2025-01-20 12:00:00.000000',1,1,1,'The beach was too crowded and noisy.','Pending','Noise'),(6,'2025-01-22 14:00:00.000000',2,2,2,'The water was dirty and the service was slow.','Resolved','Water Quality'),(1,'2025-01-20 16:00:00.000000',3,3,3,'No lifeguards on duty, it felt unsafe.','Pending','Safety'),(7,'2025-01-21 17:00:00.000000',4,4,4,'The beach was beautiful, but there were too many vendors.','Resolved','Vendors'),(2,'2025-01-23 18:00:00.000000',5,5,5,'The restrooms were dirty and lacked supplies.','Pending','Restroom Conditions'),(3,'2025-01-22 13:00:00.000000',6,6,6,'There was a lot of garbage around the area.','Resolved','Cleanliness'),(7,'2025-01-24 19:00:00.000000',7,4,7,'The beach was nice, but the sand was too hot to walk on.','Pending','Beach Condition'),(8,'2025-01-25 20:00:00.000000',8,3,8,'Too many mosquitoes, it made it difficult to enjoy the visit.','Resolved','Insects'),(9,'2025-01-26 21:00:00.000000',9,5,9,'The beach was closed early and there were no signs informing visitors.','Pending','Access Hours'),(10,'2025-01-27 22:00:00.000000',10,2,10,'Rude staff at the entrance, not a pleasant experience.','Resolved','Customer Service');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `excursions`
--

LOCK TABLES `excursions` WRITE;
/*!40000 ALTER TABLE `excursions` DISABLE KEYS */;
INSERT INTO `excursions` VALUES ('2025-01-20 12:00:00.000000',1,1,'Beach excursion with a focus on local wildlife and nature trails.'),('2025-01-22 14:00:00.000000',2,2,'A boat trip to nearby islands with snorkeling and sightseeing.'),('2025-01-20 16:00:00.000000',3,3,'Historical tour of the beach area, including ancient ruins and artifacts.'),('2025-01-21 17:00:00.000000',4,4,'Guided beach walk to learn about local flora and fauna.'),('2025-01-23 18:00:00.000000',5,5,'Surfing lesson for beginners at one of the most famous beaches.'),('2025-01-22 13:00:00.000000',6,6,'Fishing trip off the coast, with a local guide teaching fishing techniques.'),('2025-01-24 19:00:00.000000',7,7,'Sunset cruise along the coastline with refreshments and snacks.'),('2025-01-25 20:00:00.000000',8,8,'Family-friendly beach excursion with games and a picnic.'),('2025-01-26 21:00:00.000000',9,9,'Kayaking adventure, exploring the nearby caves and shoreline.'),('2025-01-27 22:00:00.000000',10,10,'Yoga retreat on the beach for relaxation and mindfulness.');
/*!40000 ALTER TABLE `excursions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `excursions_ticket_details`
--

LOCK TABLES `excursions_ticket_details` WRITE;
/*!40000 ALTER TABLE `excursions_ticket_details` DISABLE KEYS */;
INSERT INTO `excursions_ticket_details` VALUES (10,50,1,1,11,1),(15,40,2,2,12,2),(8,60,3,3,13,3),(12,45,4,4,14,4),(5,70,5,5,15,5),(20,35,6,6,16,6),(7,55,7,7,17,7),(10,60,8,8,18,8),(14,50,9,9,19,9),(6,65,10,10,20,10);
/*!40000 ALTER TABLE `excursions_ticket_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `functions`
--

LOCK TABLES `functions` WRITE;
/*!40000 ALTER TABLE `functions` DISABLE KEYS */;
INSERT INTO `functions` VALUES (1,'Guide'),(2,'Driver'),(3,'Customer Support'),(4,'Photographer'),(5,'Instructor'),(6,'Lifeguard'),(7,'Boat Captain'),(8,'Receptionist'),(9,'Event Coordinator'),(10,'Admin');
/*!40000 ALTER TABLE `functions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `horaries`
--

LOCK TABLES `horaries` WRITE;
/*!40000 ALTER TABLE `horaries` DISABLE KEYS */;
INSERT INTO `horaries` VALUES ('12:00:00.000000','08:00:00.000000',1),('16:00:00.000000','12:00:00.000000',2),('20:00:00.000000','16:00:00.000000',3),('00:00:00.000000','20:00:00.000000',4),('04:00:00.000000','00:00:00.000000',5),('12:00:00.000000','08:00:00.000000',6),('16:00:00.000000','12:00:00.000000',7),('20:00:00.000000','16:00:00.000000',8),('00:00:00.000000','20:00:00.000000',9),('04:00:00.000000','00:00:00.000000',10),('12:00:00.000000','08:00:00.000000',11),('16:00:00.000000','12:00:00.000000',12),('20:00:00.000000','16:00:00.000000',13),('00:00:00.000000','20:00:00.000000',14),('04:00:00.000000','00:00:00.000000',15);
/*!40000 ALTER TABLE `horaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `organizations`
--

LOCK TABLES `organizations` WRITE;
/*!40000 ALTER TABLE `organizations` DISABLE KEYS */;
INSERT INTO `organizations` VALUES (1,'123456789','http://docs.org1.com','Organization 1'),(2,'987654321','http://docs.org2.com','Organization 2'),(3,'555123456','http://docs.org3.com','Organization 3'),(4,'123987654','http://docs.org4.com','Organization 4'),(5,'999888777','http://docs.org5.com','Organization 5'),(6,'111222333','http://docs.org6.com','Organization 6'),(7,'444555666','http://docs.org7.com','Organization 7'),(8,'333444555','http://docs.org8.com','Organization 8'),(9,'777888999','http://docs.org9.com','Organization 9'),(10,'666777888','http://docs.org10.com','Organization 10'),(11,'123456789','http://docs.org1.com','Organization 1'),(12,'987654321','http://docs.org2.com','Organization 2'),(13,'555123456','http://docs.org3.com','Organization 3'),(14,'123987654','http://docs.org4.com','Organization 4'),(15,'999888777','http://docs.org5.com','Organization 5'),(16,'111222333','http://docs.org6.com','Organization 6'),(17,'444555666','http://docs.org7.com','Organization 7'),(18,'333444555','http://docs.org8.com','Organization 8'),(19,'777888999','http://docs.org9.com','Organization 9'),(20,'666777888','http://docs.org10.com','Organization 10'),(21,'123456789','http://docs.org1.com','Organization 1'),(22,'987654321','http://docs.org2.com','Organization 2'),(23,'555123456','http://docs.org3.com','Organization 3'),(24,'123987654','http://docs.org4.com','Organization 4'),(25,'999888777','http://docs.org5.com','Organization 5'),(26,'111222333','http://docs.org6.com','Organization 6'),(27,'444555666','http://docs.org7.com','Organization 7'),(28,'333444555','http://docs.org8.com','Organization 8'),(29,'777888999','http://docs.org9.com','Organization 9'),(30,'666777888','http://docs.org10.com','Organization 10');
/*!40000 ALTER TABLE `organizations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `photos`
--

LOCK TABLES `photos` WRITE;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `point_of_interest_types`
--

LOCK TABLES `point_of_interest_types` WRITE;
/*!40000 ALTER TABLE `point_of_interest_types` DISABLE KEYS */;
INSERT INTO `point_of_interest_types` VALUES (1,'Beach'),(2,'Park'),(3,'Museum'),(4,'Restaurant'),(5,'Hotel'),(6,'Monument'),(7,'Shopping Mall'),(8,'Historical Site'),(9,'Zoo'),(10,'Theater');
/*!40000 ALTER TABLE `point_of_interest_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `points_of_interest`
--

LOCK TABLES `points_of_interest` WRITE;
/*!40000 ALTER TABLE `points_of_interest` DISABLE KEYS */;
INSERT INTO `points_of_interest` VALUES (1,1,'A beautiful sandy beach perfect for sunbathing.','Sunny Beach','http://sunnybeach.com'),(2,2,'A large park with various walking trails.','Green Park','http://greenpark.com'),(3,3,'A famous museum showcasing historical artifacts.','History Museum','http://historymuseum.com'),(4,4,'A fine dining restaurant offering gourmet cuisine.','Gourmet Bistro','http://gourmetbistro.com'),(5,5,'A luxury hotel located by the sea.','Sea View Hotel','http://seaviewhotel.com'),(6,6,'An ancient monument with a rich history.','Ancient Monument','http://ancientmonument.com'),(7,7,'A popular shopping mall with a variety of stores.','Central Mall','http://centralmall.com'),(8,8,'A historical site with significant cultural importance.','Cultural Heritage Site','http://heritagesite.com'),(9,9,'A zoo with a wide variety of animals from all over the world.','Wildlife Zoo','http://wildlifezoo.com'),(10,10,'A grand theater that hosts numerous performances.','Grand Theater','http://grandtheater.com');
/*!40000 ALTER TABLE `points_of_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `road_type`
--

LOCK TABLES `road_type` WRITE;
/*!40000 ALTER TABLE `road_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `road_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,100,'Role for beach lifeguard, ensuring safety on the beach.','Lifeguard'),(2,200,'Role for the manager in charge of park operations.','Park Manager'),(3,150,'Role for a guide in the history museum.','Museum Guide'),(4,250,'Role for the head chef in the gourmet restaurant.','Head Chef'),(5,300,'Role for the hotel receptionist welcoming guests.','Hotel Receptionist'),(6,180,'Role for the historian at the ancient monument.','Monument Historian'),(7,120,'Role for the shopping mall security guard.','Security Guard'),(8,220,'Role for the tour guide at the cultural heritage site.','Tour Guide'),(9,130,'Role for the animal keeper at the zoo.','Zoo Keeper'),(10,210,'Role for the stage manager at the theater.','Stage Manager');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (5.5,120,150,_binary '\0',1,1,'Mountain Trail'),(3.2,90,80,_binary '',2,2,'Beachfront Walk'),(12,180,200,_binary '\0',3,3,'Historic City Walk'),(8.5,150,100,_binary '',4,4,'Nature Reserve Route'),(4,110,50,_binary '\0',5,5,'River Path'),(6.3,140,180,_binary '',6,6,'Forest Loop'),(7,160,120,_binary '\0',7,7,'Cultural Heritage Walk'),(9.1,170,220,_binary '',8,8,'Scenic Vista Trail'),(11.2,200,300,_binary '\0',9,9,'Desert Expedition'),(5.8,130,90,_binary '',10,10,'Countryside Adventure');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `segment`
--

LOCK TABLES `segment` WRITE;
/*!40000 ALTER TABLE `segment` DISABLE KEYS */;
/*!40000 ALTER TABLE `segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_beach`
--

LOCK TABLES `service_beach` WRITE;
/*!40000 ALTER TABLE `service_beach` DISABLE KEYS */;
INSERT INTO `service_beach` VALUES (1,'Lifeguard'),(2,'Shower'),(3,'Sunbeds'),(4,'Bar'),(5,'Restroom'),(6,'Lifeguard'),(7,'Shower'),(8,'Sunbeds'),(9,'Bar'),(10,'Restroom'),(11,'Lifeguard'),(12,'Shower'),(13,'Sunbeds'),(14,'Bar'),(15,'Restroom');
/*!40000 ALTER TABLE `service_beach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES ('2025-01-15 10:00:00.000000',11,11,1),('2025-01-16 14:30:00.000000',12,12,2),('2025-01-17 09:00:00.000000',13,13,3),('2025-01-18 16:00:00.000000',14,14,4),('2025-01-19 11:45:00.000000',15,15,5),('2025-01-20 13:20:00.000000',16,16,6),('2025-01-21 08:15:00.000000',17,17,7),('2025-01-22 12:30:00.000000',18,18,8),('2025-01-23 10:10:00.000000',19,19,9),('2025-01-24 14:50:00.000000',20,20,10);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `type_beach`
--

LOCK TABLES `type_beach` WRITE;
/*!40000 ALTER TABLE `type_beach` DISABLE KEYS */;
INSERT INTO `type_beach` VALUES (1,'Sandy'),(2,'Rocky'),(3,'Mixed'),(4,'Urban'),(5,'Secluded'),(6,'Sandy'),(7,'Rocky'),(8,'Mixed'),(9,'Urban'),(10,'Secluded'),(11,'Sandy'),(12,'Rocky'),(13,'Mixed'),(14,'Urban'),(15,'Secluded');
/*!40000 ALTER TABLE `type_beach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_has_rol`
--

LOCK TABLES `user_has_rol` WRITE;
/*!40000 ALTER TABLE `user_has_rol` DISABLE KEYS */;
INSERT INTO `user_has_rol` VALUES ('2025-01-01','2025-12-31',1,1,1),('2025-02-01','2025-12-31',2,2,2),('2025-03-01','2025-12-31',3,3,3),('2025-04-01','2025-12-31',4,1,4),('2025-05-01','2025-12-31',5,4,5),('2025-06-01','2025-12-31',6,2,6),('2025-07-01','2025-12-31',7,3,7),('2025-08-01','2025-12-31',8,4,8),('2025-09-01','2025-12-31',9,1,9),('2025-10-01','2025-12-31',10,2,10);
/*!40000 ALTER TABLE `user_has_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_require_rol`
--

LOCK TABLES `user_require_rol` WRITE;
/*!40000 ALTER TABLE `user_require_rol` DISABLE KEYS */;
INSERT INTO `user_require_rol` VALUES (_binary '',1,1,1,'https://example.com/doc1.pdf','https://example.com/dni1.jpg'),(_binary '\0',2,2,2,'https://example.com/doc2.pdf','https://example.com/dni2.jpg'),(_binary '',3,3,3,'https://example.com/doc3.pdf','https://example.com/dni3.jpg'),(_binary '',4,1,4,'https://example.com/doc4.pdf','https://example.com/dni4.jpg'),(_binary '\0',5,4,5,'https://example.com/doc5.pdf','https://example.com/dni5.jpg'),(_binary '',6,2,6,'https://example.com/doc6.pdf','https://example.com/dni6.jpg'),(_binary '\0',7,3,7,'https://example.com/doc7.pdf','https://example.com/dni7.jpg'),(_binary '',8,4,8,'https://example.com/doc8.pdf','https://example.com/dni8.jpg'),(_binary '',9,1,9,'https://example.com/doc9.pdf','https://example.com/dni9.jpg'),(_binary '\0',10,2,10,'https://example.com/doc10.pdf','https://example.com/dni10.jpg');
/*!40000 ALTER TABLE `user_require_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users_project`
--

LOCK TABLES `users_project` WRITE;
/*!40000 ALTER TABLE `users_project` DISABLE KEYS */;
INSERT INTO `users_project` VALUES (_binary '',_binary '','1990-05-15 08:00:00.000000',1,1,'john.doe@example.com','Doe','John','password123','Smith','url_photo_1.jpg','johndoe'),(_binary '\0',_binary '','1985-11-20 08:00:00.000000',2,2,'jane.smith@example.com','Smith','Jane','password456','Doe','url_photo_2.jpg','janesmith'),(_binary '',_binary '\0','1992-02-28 08:00:00.000000',3,1,'michael.jones@example.com','Jones','Michael','password789','Taylor','url_photo_3.jpg','michaeljones'),(_binary '\0',_binary '','1988-03-10 08:00:00.000000',4,3,'emily.white@example.com','White','Emily','password101','Green','url_photo_4.jpg','emilywhite'),(_binary '',_binary '\0','1995-07-22 08:00:00.000000',5,2,'alex.martin@example.com','Martin','Alex','password112','Davis','url_photo_5.jpg','alexmartin'),(_binary '\0',_binary '','1991-09-04 08:00:00.000000',6,1,'olivia.morris@example.com','Morris','Olivia','password113','Roberts','url_photo_6.jpg','oliviamorris'),(_binary '',_binary '','1990-12-30 08:00:00.000000',7,4,'william.brown@example.com','Brown','William','password114','Walker','url_photo_7.jpg','williambrown'),(_binary '\0',_binary '','1989-08-14 08:00:00.000000',8,2,'lucy.davis@example.com','Davis','Lucy','password115','King','url_photo_8.jpg','lucydavis'),(_binary '',_binary '\0','1993-04-06 08:00:00.000000',9,3,'jack.martin@example.com','Martin','Jack','password116','Scott','url_photo_9.jpg','jackmartin'),(_binary '\0',_binary '','1994-01-18 08:00:00.000000',10,1,'sophia.jones@example.com','Jones','Sophia','password117','Miller','url_photo_10.jpg','sophiajones'),(_binary '',_binary '','1990-05-15 08:00:00.000000',11,1,'john.doe@example.com','Doe','John','password123','Smith','url_photo_1.jpg','johndoe'),(_binary '\0',_binary '','1985-11-20 08:00:00.000000',12,2,'jane.smith@example.com','Smith','Jane','password456','Doe','url_photo_2.jpg','janesmith'),(_binary '',_binary '\0','1992-02-28 08:00:00.000000',13,1,'michael.jones@example.com','Jones','Michael','password789','Taylor','url_photo_3.jpg','michaeljones'),(_binary '\0',_binary '','1988-03-10 08:00:00.000000',14,3,'emily.white@example.com','White','Emily','password101','Green','url_photo_4.jpg','emilywhite'),(_binary '',_binary '\0','1995-07-22 08:00:00.000000',15,2,'alex.martin@example.com','Martin','Alex','password112','Davis','url_photo_5.jpg','alexmartin'),(_binary '\0',_binary '','1991-09-04 08:00:00.000000',16,1,'olivia.morris@example.com','Morris','Olivia','password113','Roberts','url_photo_6.jpg','oliviamorris'),(_binary '',_binary '','1990-12-30 08:00:00.000000',17,4,'william.brown@example.com','Brown','William','password114','Walker','url_photo_7.jpg','williambrown'),(_binary '\0',_binary '','1989-08-14 08:00:00.000000',18,2,'lucy.davis@example.com','Davis','Lucy','password115','King','url_photo_8.jpg','lucydavis'),(_binary '',_binary '\0','1993-04-06 08:00:00.000000',19,3,'jack.martin@example.com','Martin','Jack','password116','Scott','url_photo_9.jpg','jackmartin'),(_binary '\0',_binary '','1994-01-18 08:00:00.000000',20,1,'sophia.jones@example.com','Jones','Sophia','password117','Miller','url_photo_10.jpg','sophiajones'),(_binary '',_binary '','1990-05-15 08:00:00.000000',21,1,'john.doe@example.com','Doe','John','password123','Smith','url_photo_1.jpg','johndoe'),(_binary '\0',_binary '','1985-11-20 08:00:00.000000',22,2,'jane.smith@example.com','Smith','Jane','password456','Doe','url_photo_2.jpg','janesmith'),(_binary '',_binary '\0','1992-02-28 08:00:00.000000',23,1,'michael.jones@example.com','Jones','Michael','password789','Taylor','url_photo_3.jpg','michaeljones'),(_binary '\0',_binary '','1988-03-10 08:00:00.000000',24,3,'emily.white@example.com','White','Emily','password101','Green','url_photo_4.jpg','emilywhite'),(_binary '',_binary '\0','1995-07-22 08:00:00.000000',25,2,'alex.martin@example.com','Martin','Alex','password112','Davis','url_photo_5.jpg','alexmartin'),(_binary '\0',_binary '','1991-09-04 08:00:00.000000',26,1,'olivia.morris@example.com','Morris','Olivia','password113','Roberts','url_photo_6.jpg','oliviamorris'),(_binary '',_binary '','1990-12-30 08:00:00.000000',27,4,'william.brown@example.com','Brown','William','password114','Walker','url_photo_7.jpg','williambrown'),(_binary '\0',_binary '','1989-08-14 08:00:00.000000',28,2,'lucy.davis@example.com','Davis','Lucy','password115','King','url_photo_8.jpg','lucydavis'),(_binary '',_binary '\0','1993-04-06 08:00:00.000000',29,3,'jack.martin@example.com','Martin','Jack','password116','Scott','url_photo_9.jpg','jackmartin'),(_binary '\0',_binary '','1994-01-18 08:00:00.000000',30,1,'sophia.jones@example.com','Jones','Sophia','password117','Miller','url_photo_10.jpg','sophiajones');
/*!40000 ALTER TABLE `users_project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-24 10:13:00
