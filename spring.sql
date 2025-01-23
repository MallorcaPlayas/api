-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Jan 22, 2025 at 10:30 PM
-- Server version: 9.1.0
-- PHP Version: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `aggregation_types`
--

CREATE TABLE `aggregation_types` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `beach`
--

CREATE TABLE `beach` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `beach`
--

INSERT INTO `beach` (`id`, `description`, `name`) VALUES
(1, 'A beautiful sandy beach with clear water.', 'Sunny Beach'),
(2, 'A rocky beach with lots of cliffs.', 'Rocky Cove'),
(3, 'A peaceful secluded beach, perfect for relaxation.', 'Hidden Bay'),
(4, 'An urban beach with lots of bars and restaurants.', 'City Shore'),
(5, 'A family-friendly beach with plenty of activities for children.', 'Family Fun Beach'),
(6, 'A beach with strong winds, perfect for windsurfing.', 'Windy Point'),
(7, 'A pristine beach with clear waters and soft sand.', 'Crystal Beach'),
(8, 'A lively beach with music and beach volleyball.', 'Party Beach'),
(9, 'A remote beach with hiking trails nearby.', 'Explorer’s Cove'),
(10, 'A pet-friendly beach where dogs can play freely.', 'Dog’s Paradise'),
(11, 'A beautiful sandy beach with clear water.', 'Sunny Beach'),
(12, 'A rocky beach with lots of cliffs.', 'Rocky Cove'),
(13, 'A peaceful secluded beach, perfect for relaxation.', 'Hidden Bay'),
(14, 'An urban beach with lots of bars and restaurants.', 'City Shore'),
(15, 'A family-friendly beach with plenty of activities for children.', 'Family Fun Beach'),
(16, 'A beach with strong winds, perfect for windsurfing.', 'Windy Point'),
(17, 'A pristine beach with clear waters and soft sand.', 'Crystal Beach'),
(18, 'A lively beach with music and beach volleyball.', 'Party Beach'),
(19, 'A remote beach with hiking trails nearby.', 'Explorer’s Cove'),
(20, 'A pet-friendly beach where dogs can play freely.', 'Dog’s Paradise'),
(21, 'A beautiful sandy beach with clear water.', 'Sunny Beach'),
(22, 'A rocky beach with lots of cliffs.', 'Rocky Cove'),
(23, 'A peaceful secluded beach, perfect for relaxation.', 'Hidden Bay'),
(24, 'An urban beach with lots of bars and restaurants.', 'City Shore'),
(25, 'A family-friendly beach with plenty of activities for children.', 'Family Fun Beach'),
(26, 'A beach with strong winds, perfect for windsurfing.', 'Windy Point'),
(27, 'A pristine beach with clear waters and soft sand.', 'Crystal Beach'),
(28, 'A lively beach with music and beach volleyball.', 'Party Beach'),
(29, 'A remote beach with hiking trails nearby.', 'Explorer’s Cove'),
(30, 'A pet-friendly beach where dogs can play freely.', 'Dog’s Paradise');

-- --------------------------------------------------------

--
-- Table structure for table `beach_has_service`
--

CREATE TABLE `beach_has_service` (
  `end_time` time(6) DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `beach_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `beach_has_service`
--

INSERT INTO `beach_has_service` (`end_time`, `start_time`, `beach_id`, `id`, `role_id`) VALUES
('12:00:00.000000', '08:00:00.000000', 1, 1, 1),
('16:00:00.000000', '12:00:00.000000', 2, 2, 2),
('20:00:00.000000', '16:00:00.000000', 3, 3, 3),
('12:00:00.000000', '08:00:00.000000', 4, 4, 4),
('16:00:00.000000', '12:00:00.000000', 5, 5, 5),
('20:00:00.000000', '16:00:00.000000', 6, 6, 1),
('00:00:00.000000', '20:00:00.000000', 7, 7, 2),
('04:00:00.000000', '00:00:00.000000', 8, 8, 3),
('08:00:00.000000', '04:00:00.000000', 9, 9, 4),
('12:00:00.000000', '08:00:00.000000', 10, 10, 5),
('12:00:00.000000', '08:00:00.000000', 1, 11, 1),
('16:00:00.000000', '12:00:00.000000', 2, 12, 2),
('20:00:00.000000', '16:00:00.000000', 3, 13, 3),
('12:00:00.000000', '08:00:00.000000', 4, 14, 4),
('16:00:00.000000', '12:00:00.000000', 5, 15, 5),
('20:00:00.000000', '16:00:00.000000', 6, 16, 1),
('00:00:00.000000', '20:00:00.000000', 7, 17, 2),
('04:00:00.000000', '00:00:00.000000', 8, 18, 3),
('08:00:00.000000', '04:00:00.000000', 9, 19, 4),
('12:00:00.000000', '08:00:00.000000', 10, 20, 5),
('12:00:00.000000', '08:00:00.000000', 1, 21, 1),
('16:00:00.000000', '12:00:00.000000', 2, 22, 2),
('20:00:00.000000', '16:00:00.000000', 3, 23, 3),
('12:00:00.000000', '08:00:00.000000', 4, 24, 4),
('16:00:00.000000', '12:00:00.000000', 5, 25, 5),
('20:00:00.000000', '16:00:00.000000', 6, 26, 1),
('00:00:00.000000', '20:00:00.000000', 7, 27, 2),
('04:00:00.000000', '00:00:00.000000', 8, 28, 3),
('08:00:00.000000', '04:00:00.000000', 9, 29, 4),
('12:00:00.000000', '08:00:00.000000', 10, 30, 5);

-- --------------------------------------------------------

--
-- Table structure for table `beach_manager`
--

CREATE TABLE `beach_manager` (
  `beach_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `beach_manager`
--

INSERT INTO `beach_manager` (`beach_id`, `id`, `user_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `beach_type`
--

CREATE TABLE `beach_type` (
  `beach_id` bigint NOT NULL,
  `type_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `beach_type`
--

INSERT INTO `beach_type` (`beach_id`, `type_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3),
(7, 1),
(8, 2),
(9, 3),
(10, 1),
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3),
(7, 1),
(8, 2),
(9, 3),
(10, 1),
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3),
(7, 1),
(8, 2),
(9, 3),
(10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `amount` double DEFAULT NULL,
  `amount_date` datetime(6) DEFAULT NULL,
  `bill_type_id` bigint DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_surname` varchar(255) DEFAULT NULL,
  `second_surname` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`amount`, `amount_date`, `bill_type_id`, `birth_date`, `id`, `email`, `first_surname`, `second_surname`, `user_name`) VALUES
(100.5, '2025-01-01 10:00:00.000000', 1, '1990-05-15 00:00:00.000000', 1, 'john.doe@example.com', 'Doe', 'Smith', 'johndoe'),
(200.75, '2025-01-02 11:00:00.000000', 2, '1985-03-10 00:00:00.000000', 2, 'jane.doe@example.com', 'Doe', 'Johnson', 'janedoe'),
(150, '2025-01-03 12:00:00.000000', 3, '1992-07-20 00:00:00.000000', 3, 'mark.jones@example.com', 'Jones', 'Taylor', 'markjones'),
(250.5, '2025-01-04 14:00:00.000000', 1, '1988-11-25 00:00:00.000000', 4, 'alice.smith@example.com', 'Smith', 'Brown', 'alicesmith'),
(300.25, '2025-01-05 15:00:00.000000', 4, '1995-02-05 00:00:00.000000', 5, 'bob.williams@example.com', 'Williams', 'Davis', 'bobwilliams'),
(50, '2025-01-06 16:00:00.000000', 5, '1993-09-12 00:00:00.000000', 6, 'susan.miller@example.com', 'Miller', 'Moore', 'susanmiller'),
(75.75, '2025-01-07 17:00:00.000000', 6, '1991-12-30 00:00:00.000000', 7, 'tom.clark@example.com', 'Clark', 'Wilson', 'tomclark'),
(120, '2025-01-08 18:00:00.000000', 2, '1990-04-20 00:00:00.000000', 8, 'mary.lewis@example.com', 'Lewis', 'Taylor', 'marylewis'),
(180.1, '2025-01-09 19:00:00.000000', 3, '1994-06-10 00:00:00.000000', 9, 'peter.martin@example.com', 'Martin', 'Hall', 'petermartin'),
(220.4, '2025-01-10 20:00:00.000000', 4, '1987-08-30 00:00:00.000000', 10, 'lisa.white@example.com', 'White', 'Allen', 'lisawhite'),
(100.5, '2025-01-01 10:00:00.000000', 1, '1990-05-15 00:00:00.000000', 11, 'john.doe@example.com', 'Doe', 'Smith', 'johndoe'),
(200.75, '2025-01-02 11:00:00.000000', 2, '1985-03-10 00:00:00.000000', 12, 'jane.doe@example.com', 'Doe', 'Johnson', 'janedoe'),
(150, '2025-01-03 12:00:00.000000', 3, '1992-07-20 00:00:00.000000', 13, 'mark.jones@example.com', 'Jones', 'Taylor', 'markjones'),
(250.5, '2025-01-04 14:00:00.000000', 1, '1988-11-25 00:00:00.000000', 14, 'alice.smith@example.com', 'Smith', 'Brown', 'alicesmith'),
(300.25, '2025-01-05 15:00:00.000000', 4, '1995-02-05 00:00:00.000000', 15, 'bob.williams@example.com', 'Williams', 'Davis', 'bobwilliams'),
(50, '2025-01-06 16:00:00.000000', 5, '1993-09-12 00:00:00.000000', 16, 'susan.miller@example.com', 'Miller', 'Moore', 'susanmiller'),
(75.75, '2025-01-07 17:00:00.000000', 6, '1991-12-30 00:00:00.000000', 17, 'tom.clark@example.com', 'Clark', 'Wilson', 'tomclark'),
(120, '2025-01-08 18:00:00.000000', 2, '1990-04-20 00:00:00.000000', 18, 'mary.lewis@example.com', 'Lewis', 'Taylor', 'marylewis'),
(180.1, '2025-01-09 19:00:00.000000', 3, '1994-06-10 00:00:00.000000', 19, 'peter.martin@example.com', 'Martin', 'Hall', 'petermartin'),
(220.4, '2025-01-10 20:00:00.000000', 4, '1987-08-30 00:00:00.000000', 20, 'lisa.white@example.com', 'White', 'Allen', 'lisawhite'),
(100.5, '2025-01-01 10:00:00.000000', 1, '1990-05-15 00:00:00.000000', 21, 'john.doe@example.com', 'Doe', 'Smith', 'johndoe'),
(200.75, '2025-01-02 11:00:00.000000', 2, '1985-03-10 00:00:00.000000', 22, 'jane.doe@example.com', 'Doe', 'Johnson', 'janedoe'),
(150, '2025-01-03 12:00:00.000000', 3, '1992-07-20 00:00:00.000000', 23, 'mark.jones@example.com', 'Jones', 'Taylor', 'markjones'),
(250.5, '2025-01-04 14:00:00.000000', 1, '1988-11-25 00:00:00.000000', 24, 'alice.smith@example.com', 'Smith', 'Brown', 'alicesmith'),
(300.25, '2025-01-05 15:00:00.000000', 4, '1995-02-05 00:00:00.000000', 25, 'bob.williams@example.com', 'Williams', 'Davis', 'bobwilliams'),
(50, '2025-01-06 16:00:00.000000', 5, '1993-09-12 00:00:00.000000', 26, 'susan.miller@example.com', 'Miller', 'Moore', 'susanmiller'),
(75.75, '2025-01-07 17:00:00.000000', 6, '1991-12-30 00:00:00.000000', 27, 'tom.clark@example.com', 'Clark', 'Wilson', 'tomclark'),
(120, '2025-01-08 18:00:00.000000', 2, '1990-04-20 00:00:00.000000', 28, 'mary.lewis@example.com', 'Lewis', 'Taylor', 'marylewis'),
(180.1, '2025-01-09 19:00:00.000000', 3, '1994-06-10 00:00:00.000000', 29, 'peter.martin@example.com', 'Martin', 'Hall', 'petermartin'),
(220.4, '2025-01-10 20:00:00.000000', 4, '1987-08-30 00:00:00.000000', 30, 'lisa.white@example.com', 'White', 'Allen', 'lisawhite');

-- --------------------------------------------------------

--
-- Table structure for table `bill_types`
--

CREATE TABLE `bill_types` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bill_types`
--

INSERT INTO `bill_types` (`id`, `name`) VALUES
(1, 'Factura A'),
(2, 'Factura B'),
(3, 'Factura C'),
(4, 'Factura D'),
(5, 'Factura E'),
(6, 'Factura F'),
(7, 'Factura G'),
(8, 'Factura H'),
(9, 'Factura I'),
(10, 'Factura J'),
(11, 'Factura A'),
(12, 'Factura B'),
(13, 'Factura C'),
(14, 'Factura D'),
(15, 'Factura E'),
(16, 'Factura F'),
(17, 'Factura G'),
(18, 'Factura H'),
(19, 'Factura I'),
(20, 'Factura J'),
(21, 'Factura A'),
(22, 'Factura B'),
(23, 'Factura C'),
(24, 'Factura D'),
(25, 'Factura E'),
(26, 'Factura F'),
(27, 'Factura G'),
(28, 'Factura H'),
(29, 'Factura I'),
(30, 'Factura J');

-- --------------------------------------------------------

--
-- Table structure for table `budgets`
--

CREATE TABLE `budgets` (
  `price` double DEFAULT NULL,
  `id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `budgets`
--

INSERT INTO `budgets` (`price`, `id`) VALUES
(1000.5, 1),
(1500.75, 2),
(2000, 3),
(1200.3, 4),
(950.6, 5),
(850.25, 6),
(1100.4, 7),
(1300.9, 8),
(1600.15, 9),
(1400, 10),
(1000.5, 11),
(1500.75, 12),
(2000, 13),
(1200.3, 14),
(950.6, 15),
(850.25, 16),
(1100.4, 17),
(1300.9, 18),
(1600.15, 19),
(1400, 20),
(1000.5, 21),
(1500.75, 22),
(2000, 23),
(1200.3, 24),
(950.6, 25),
(850.25, 26),
(1100.4, 27),
(1300.9, 28),
(1600.15, 29),
(1400, 30);

-- --------------------------------------------------------

--
-- Table structure for table `budget_business`
--

CREATE TABLE `budget_business` (
  `paid` bit(1) NOT NULL,
  `budget_id` bigint DEFAULT NULL,
  `business_id` bigint DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `paid_date` datetime(6) DEFAULT NULL,
  `proposal_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `budget_business`
--

INSERT INTO `budget_business` (`paid`, `budget_id`, `business_id`, `end_date`, `id`, `paid_date`, `proposal_date`) VALUES
(b'1', 1, 1, '2025-02-28 00:00:00.000000', 1, '2025-01-20 00:00:00.000000', '2025-01-15 00:00:00.000000'),
(b'0', 2, 2, '2025-03-01 00:00:00.000000', 2, NULL, '2025-01-18 00:00:00.000000'),
(b'1', 3, 3, '2025-04-01 00:00:00.000000', 3, '2025-01-21 00:00:00.000000', '2025-01-10 00:00:00.000000'),
(b'0', 4, 4, '2025-02-10 00:00:00.000000', 4, NULL, '2025-01-19 00:00:00.000000'),
(b'1', 5, 5, '2025-03-15 00:00:00.000000', 5, '2025-01-22 00:00:00.000000', '2025-01-14 00:00:00.000000'),
(b'0', 6, 6, '2025-02-20 00:00:00.000000', 6, NULL, '2025-01-13 00:00:00.000000'),
(b'1', 7, 7, '2025-05-01 00:00:00.000000', 7, '2025-01-23 00:00:00.000000', '2025-01-16 00:00:00.000000'),
(b'0', 8, 8, '2025-06-01 00:00:00.000000', 8, NULL, '2025-01-17 00:00:00.000000'),
(b'1', 9, 9, '2025-07-01 00:00:00.000000', 9, '2025-01-24 00:00:00.000000', '2025-01-12 00:00:00.000000'),
(b'0', 10, 10, '2025-08-01 00:00:00.000000', 10, NULL, '2025-01-11 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `businesses`
--

CREATE TABLE `businesses` (
  `business_type_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `documentation_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `businesses`
--

INSERT INTO `businesses` (`business_type_id`, `id`, `contact_number`, `documentation_url`, `name`, `text`) VALUES
(1, 1, '123-456-7890', 'http://restaurant-docs.com', 'Delicious Diner', 'A fine dining experience for all'),
(2, 2, '987-654-3210', 'http://hotel-docs.com', 'Sunset Hotel', 'Comfortable stays with a scenic view'),
(3, 3, '555-123-4567', 'http://retail-docs.com', 'SuperMart', 'Your one-stop shop for all needs'),
(4, 4, '444-987-6543', 'http://service-docs.com', 'QuickFix', 'Fast and reliable service solutions'),
(5, 5, '333-222-1111', 'http://entertainment-docs.com', 'FunZone', 'Entertainment for all ages'),
(6, 6, '888-555-4444', 'http://tech-docs.com', 'Tech Solutions', 'Innovative solutions for modern businesses'),
(7, 7, '666-123-0987', 'http://consulting-docs.com', 'BrightConsult', 'Consulting for growth and success'),
(8, 8, '222-444-6666', 'http://manufacturing-docs.com', 'IronWorks', 'Top-tier manufacturing solutions'),
(9, 9, '111-999-8888', 'http://healthcare-docs.com', 'HealthPlus', 'Comprehensive healthcare services'),
(10, 10, '777-888-9999', 'http://education-docs.com', 'LearnMore', 'Quality education for the future'),
(1, 11, '123-456-7890', 'http://restaurant-docs.com', 'Delicious Diner', 'A fine dining experience for all'),
(2, 12, '987-654-3210', 'http://hotel-docs.com', 'Sunset Hotel', 'Comfortable stays with a scenic view'),
(3, 13, '555-123-4567', 'http://retail-docs.com', 'SuperMart', 'Your one-stop shop for all needs'),
(4, 14, '444-987-6543', 'http://service-docs.com', 'QuickFix', 'Fast and reliable service solutions'),
(5, 15, '333-222-1111', 'http://entertainment-docs.com', 'FunZone', 'Entertainment for all ages'),
(6, 16, '888-555-4444', 'http://tech-docs.com', 'Tech Solutions', 'Innovative solutions for modern businesses'),
(7, 17, '666-123-0987', 'http://consulting-docs.com', 'BrightConsult', 'Consulting for growth and success'),
(8, 18, '222-444-6666', 'http://manufacturing-docs.com', 'IronWorks', 'Top-tier manufacturing solutions'),
(9, 19, '111-999-8888', 'http://healthcare-docs.com', 'HealthPlus', 'Comprehensive healthcare services'),
(10, 20, '777-888-9999', 'http://education-docs.com', 'LearnMore', 'Quality education for the future'),
(1, 21, '123-456-7890', 'http://restaurant-docs.com', 'Delicious Diner', 'A fine dining experience for all'),
(2, 22, '987-654-3210', 'http://hotel-docs.com', 'Sunset Hotel', 'Comfortable stays with a scenic view'),
(3, 23, '555-123-4567', 'http://retail-docs.com', 'SuperMart', 'Your one-stop shop for all needs'),
(4, 24, '444-987-6543', 'http://service-docs.com', 'QuickFix', 'Fast and reliable service solutions'),
(5, 25, '333-222-1111', 'http://entertainment-docs.com', 'FunZone', 'Entertainment for all ages'),
(6, 26, '888-555-4444', 'http://tech-docs.com', 'Tech Solutions', 'Innovative solutions for modern businesses'),
(7, 27, '666-123-0987', 'http://consulting-docs.com', 'BrightConsult', 'Consulting for growth and success'),
(8, 28, '222-444-6666', 'http://manufacturing-docs.com', 'IronWorks', 'Top-tier manufacturing solutions'),
(9, 29, '111-999-8888', 'http://healthcare-docs.com', 'HealthPlus', 'Comprehensive healthcare services'),
(10, 30, '777-888-9999', 'http://education-docs.com', 'LearnMore', 'Quality education for the future');

-- --------------------------------------------------------

--
-- Table structure for table `businesses_horary`
--

CREATE TABLE `businesses_horary` (
  `is_open` bit(1) NOT NULL,
  `business_id` bigint DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `horary_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `businesses_horary`
--

INSERT INTO `businesses_horary` (`is_open`, `business_id`, `date`, `horary_id`, `id`) VALUES
(b'1', 1, '2025-01-22 09:00:00.000000', 1, 1),
(b'0', 1, '2025-01-22 18:00:00.000000', 2, 2),
(b'1', 2, '2025-01-22 08:00:00.000000', 3, 3),
(b'1', 3, '2025-01-22 10:00:00.000000', 4, 4),
(b'0', 4, '2025-01-22 15:00:00.000000', 5, 5),
(b'1', 5, '2025-01-22 12:00:00.000000', 6, 6),
(b'1', 6, '2025-01-22 14:00:00.000000', 7, 7),
(b'0', 7, '2025-01-22 16:00:00.000000', 8, 8),
(b'1', 8, '2025-01-22 09:30:00.000000', 9, 9),
(b'1', 9, '2025-01-22 11:00:00.000000', 10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `business_types`
--

CREATE TABLE `business_types` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `business_types`
--

INSERT INTO `business_types` (`id`, `name`) VALUES
(1, 'Restaurant'),
(2, 'Hotel'),
(3, 'Retail'),
(4, 'Service'),
(5, 'Entertainment'),
(6, 'Technology'),
(7, 'Consulting'),
(8, 'Manufacturing'),
(9, 'Healthcare'),
(10, 'Education'),
(11, 'Restaurant'),
(12, 'Hotel'),
(13, 'Retail'),
(14, 'Service'),
(15, 'Entertainment'),
(16, 'Technology'),
(17, 'Consulting'),
(18, 'Manufacturing'),
(19, 'Healthcare'),
(20, 'Education'),
(21, 'Restaurant'),
(22, 'Hotel'),
(23, 'Retail'),
(24, 'Service'),
(25, 'Entertainment'),
(26, 'Technology'),
(27, 'Consulting'),
(28, 'Manufacturing'),
(29, 'Healthcare'),
(30, 'Education');

-- --------------------------------------------------------

--
-- Table structure for table `camera`
--

CREATE TABLE `camera` (
  `camera_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `camera`
--

INSERT INTO `camera` (`camera_id`, `id`, `url`) VALUES
(1, 1, 'http://example.com/camera1.jpg'),
(2, 2, 'http://example.com/camera2.jpg'),
(3, 3, 'http://example.com/camera3.jpg'),
(4, 4, 'http://example.com/camera4.jpg'),
(5, 5, 'http://example.com/camera5.jpg'),
(6, 6, 'http://example.com/camera6.jpg'),
(7, 7, 'http://example.com/camera7.jpg'),
(8, 8, 'http://example.com/camera8.jpg'),
(9, 9, 'http://example.com/camera9.jpg'),
(10, 10, 'http://example.com/camera10.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `rating` int DEFAULT NULL,
  `beach_id` bigint DEFAULT NULL,
  `edited_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `published_date` datetime(6) DEFAULT NULL,
  `route_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`rating`, `beach_id`, `edited_date`, `id`, `published_date`, `route_id`, `user_id`, `comment`) VALUES
(5, 1, '2025-01-20 12:00:00.000000', 21, '2025-01-15 10:00:00.000000', 1, 1, 'Great beach, very clean and beautiful!'),
(4, 1, '2025-01-22 14:00:00.000000', 22, '2025-01-18 09:00:00.000000', 2, 2, 'Nice location, but could use more amenities.'),
(3, 1, '2025-01-20 16:00:00.000000', 23, '2025-01-19 11:00:00.000000', 3, 3, 'Not bad, but the water is a bit cold.'),
(5, 1, '2025-01-21 17:00:00.000000', 24, '2025-01-20 13:00:00.000000', 4, 4, 'Amazing sunset view, a must-visit spot!'),
(2, 1, '2025-01-23 18:00:00.000000', 25, '2025-01-22 15:00:00.000000', 5, 5, 'The beach is too crowded, not a peaceful place.'),
(4, 1, '2025-01-22 13:00:00.000000', 26, '2025-01-21 10:00:00.000000', 6, 6, 'A quiet beach with good service, worth a visit.'),
(3, 7, '2025-01-24 19:00:00.000000', 27, '2025-01-23 12:00:00.000000', 2, 7, 'Could be cleaner, but still okay for a quick stop.'),
(5, 8, '2025-01-25 20:00:00.000000', 28, '2025-01-24 14:00:00.000000', 2, 8, 'A perfect spot for families, very relaxing atmosphere.'),
(4, 9, '2025-01-26 21:00:00.000000', 29, '2025-01-25 16:00:00.000000', 3, 9, 'Good beach, but it gets busy on weekends.'),
(2, 10, '2025-01-27 22:00:00.000000', 30, '2025-01-26 17:00:00.000000', 4, 10, 'The beach is nice, but the weather wasnt great.');

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `beach_id` bigint DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `route_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`beach_id`, `date`, `id`, `route_id`, `user_id`, `message`, `status`, `subject`) VALUES
(5, '2025-01-20 12:00:00.000000', 1, 1, 1, 'The beach was too crowded and noisy.', 'Pending', 'Noise'),
(6, '2025-01-22 14:00:00.000000', 2, 2, 2, 'The water was dirty and the service was slow.', 'Resolved', 'Water Quality'),
(1, '2025-01-20 16:00:00.000000', 3, 3, 3, 'No lifeguards on duty, it felt unsafe.', 'Pending', 'Safety'),
(7, '2025-01-21 17:00:00.000000', 4, 4, 4, 'The beach was beautiful, but there were too many vendors.', 'Resolved', 'Vendors'),
(2, '2025-01-23 18:00:00.000000', 5, 5, 5, 'The restrooms were dirty and lacked supplies.', 'Pending', 'Restroom Conditions'),
(3, '2025-01-22 13:00:00.000000', 6, 6, 6, 'There was a lot of garbage around the area.', 'Resolved', 'Cleanliness'),
(7, '2025-01-24 19:00:00.000000', 7, 4, 7, 'The beach was nice, but the sand was too hot to walk on.', 'Pending', 'Beach Condition'),
(8, '2025-01-25 20:00:00.000000', 8, 3, 8, 'Too many mosquitoes, it made it difficult to enjoy the visit.', 'Resolved', 'Insects'),
(9, '2025-01-26 21:00:00.000000', 9, 5, 9, 'The beach was closed early and there were no signs informing visitors.', 'Pending', 'Access Hours'),
(10, '2025-01-27 22:00:00.000000', 10, 2, 10, 'Rude staff at the entrance, not a pleasant experience.', 'Resolved', 'Customer Service');

-- --------------------------------------------------------

--
-- Table structure for table `excursions`
--

CREATE TABLE `excursions` (
  `creation_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `excursions`
--

INSERT INTO `excursions` (`creation_date`, `id`, `user_id`, `description`) VALUES
('2025-01-20 12:00:00.000000', 1, 1, 'Beach excursion with a focus on local wildlife and nature trails.'),
('2025-01-22 14:00:00.000000', 2, 2, 'A boat trip to nearby islands with snorkeling and sightseeing.'),
('2025-01-20 16:00:00.000000', 3, 3, 'Historical tour of the beach area, including ancient ruins and artifacts.'),
('2025-01-21 17:00:00.000000', 4, 4, 'Guided beach walk to learn about local flora and fauna.'),
('2025-01-23 18:00:00.000000', 5, 5, 'Surfing lesson for beginners at one of the most famous beaches.'),
('2025-01-22 13:00:00.000000', 6, 6, 'Fishing trip off the coast, with a local guide teaching fishing techniques.'),
('2025-01-24 19:00:00.000000', 7, 7, 'Sunset cruise along the coastline with refreshments and snacks.'),
('2025-01-25 20:00:00.000000', 8, 8, 'Family-friendly beach excursion with games and a picnic.'),
('2025-01-26 21:00:00.000000', 9, 9, 'Kayaking adventure, exploring the nearby caves and shoreline.'),
('2025-01-27 22:00:00.000000', 10, 10, 'Yoga retreat on the beach for relaxation and mindfulness.');

-- --------------------------------------------------------

--
-- Table structure for table `excursions_ticket_details`
--

CREATE TABLE `excursions_ticket_details` (
  `available_spaces` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `excursion_id` bigint DEFAULT NULL,
  `horary_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `route_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `excursions_ticket_details`
--

INSERT INTO `excursions_ticket_details` (`available_spaces`, `price`, `excursion_id`, `horary_id`, `id`, `route_id`) VALUES
(10, 50, 1, 1, 11, 1),
(15, 40, 2, 2, 12, 2),
(8, 60, 3, 3, 13, 3),
(12, 45, 4, 4, 14, 4),
(5, 70, 5, 5, 15, 5),
(20, 35, 6, 6, 16, 6),
(7, 55, 7, 7, 17, 7),
(10, 60, 8, 8, 18, 8),
(14, 50, 9, 9, 19, 9),
(6, 65, 10, 10, 20, 10);

-- --------------------------------------------------------

--
-- Table structure for table `functions`
--

CREATE TABLE `functions` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `functions`
--

INSERT INTO `functions` (`id`, `name`) VALUES
(1, 'Guide'),
(2, 'Driver'),
(3, 'Customer Support'),
(4, 'Photographer'),
(5, 'Instructor'),
(6, 'Lifeguard'),
(7, 'Boat Captain'),
(8, 'Receptionist'),
(9, 'Event Coordinator'),
(10, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `horaries`
--

CREATE TABLE `horaries` (
  `end_time` time(6) DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `horaries`
--

INSERT INTO `horaries` (`end_time`, `start_time`, `id`) VALUES
('12:00:00.000000', '08:00:00.000000', 1),
('16:00:00.000000', '12:00:00.000000', 2),
('20:00:00.000000', '16:00:00.000000', 3),
('00:00:00.000000', '20:00:00.000000', 4),
('04:00:00.000000', '00:00:00.000000', 5),
('12:00:00.000000', '08:00:00.000000', 6),
('16:00:00.000000', '12:00:00.000000', 7),
('20:00:00.000000', '16:00:00.000000', 8),
('00:00:00.000000', '20:00:00.000000', 9),
('04:00:00.000000', '00:00:00.000000', 10),
('12:00:00.000000', '08:00:00.000000', 11),
('16:00:00.000000', '12:00:00.000000', 12),
('20:00:00.000000', '16:00:00.000000', 13),
('00:00:00.000000', '20:00:00.000000', 14),
('04:00:00.000000', '00:00:00.000000', 15);

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `coordinatex` double DEFAULT NULL,
  `coordinatey` double DEFAULT NULL,
  `id` bigint NOT NULL,
  `route_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `organizations`
--

CREATE TABLE `organizations` (
  `id` bigint NOT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `documentation_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `organizations`
--

INSERT INTO `organizations` (`id`, `contact_number`, `documentation_url`, `name`) VALUES
(1, '123456789', 'http://docs.org1.com', 'Organization 1'),
(2, '987654321', 'http://docs.org2.com', 'Organization 2'),
(3, '555123456', 'http://docs.org3.com', 'Organization 3'),
(4, '123987654', 'http://docs.org4.com', 'Organization 4'),
(5, '999888777', 'http://docs.org5.com', 'Organization 5'),
(6, '111222333', 'http://docs.org6.com', 'Organization 6'),
(7, '444555666', 'http://docs.org7.com', 'Organization 7'),
(8, '333444555', 'http://docs.org8.com', 'Organization 8'),
(9, '777888999', 'http://docs.org9.com', 'Organization 9'),
(10, '666777888', 'http://docs.org10.com', 'Organization 10'),
(11, '123456789', 'http://docs.org1.com', 'Organization 1'),
(12, '987654321', 'http://docs.org2.com', 'Organization 2'),
(13, '555123456', 'http://docs.org3.com', 'Organization 3'),
(14, '123987654', 'http://docs.org4.com', 'Organization 4'),
(15, '999888777', 'http://docs.org5.com', 'Organization 5'),
(16, '111222333', 'http://docs.org6.com', 'Organization 6'),
(17, '444555666', 'http://docs.org7.com', 'Organization 7'),
(18, '333444555', 'http://docs.org8.com', 'Organization 8'),
(19, '777888999', 'http://docs.org9.com', 'Organization 9'),
(20, '666777888', 'http://docs.org10.com', 'Organization 10'),
(21, '123456789', 'http://docs.org1.com', 'Organization 1'),
(22, '987654321', 'http://docs.org2.com', 'Organization 2'),
(23, '555123456', 'http://docs.org3.com', 'Organization 3'),
(24, '123987654', 'http://docs.org4.com', 'Organization 4'),
(25, '999888777', 'http://docs.org5.com', 'Organization 5'),
(26, '111222333', 'http://docs.org6.com', 'Organization 6'),
(27, '444555666', 'http://docs.org7.com', 'Organization 7'),
(28, '333444555', 'http://docs.org8.com', 'Organization 8'),
(29, '777888999', 'http://docs.org9.com', 'Organization 9'),
(30, '666777888', 'http://docs.org10.com', 'Organization 10');

-- --------------------------------------------------------

--
-- Table structure for table `photos`
--

CREATE TABLE `photos` (
  `id` bigint NOT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `points_of_interest`
--

CREATE TABLE `points_of_interest` (
  `id` bigint NOT NULL,
  `point_of_interest_type_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `points_of_interest`
--

INSERT INTO `points_of_interest` (`id`, `point_of_interest_type_id`, `description`, `name`, `url`) VALUES
(1, 1, 'A beautiful sandy beach perfect for sunbathing.', 'Sunny Beach', 'http://sunnybeach.com'),
(2, 2, 'A large park with various walking trails.', 'Green Park', 'http://greenpark.com'),
(3, 3, 'A famous museum showcasing historical artifacts.', 'History Museum', 'http://historymuseum.com'),
(4, 4, 'A fine dining restaurant offering gourmet cuisine.', 'Gourmet Bistro', 'http://gourmetbistro.com'),
(5, 5, 'A luxury hotel located by the sea.', 'Sea View Hotel', 'http://seaviewhotel.com'),
(6, 6, 'An ancient monument with a rich history.', 'Ancient Monument', 'http://ancientmonument.com'),
(7, 7, 'A popular shopping mall with a variety of stores.', 'Central Mall', 'http://centralmall.com'),
(8, 8, 'A historical site with significant cultural importance.', 'Cultural Heritage Site', 'http://heritagesite.com'),
(9, 9, 'A zoo with a wide variety of animals from all over the world.', 'Wildlife Zoo', 'http://wildlifezoo.com'),
(10, 10, 'A grand theater that hosts numerous performances.', 'Grand Theater', 'http://grandtheater.com');

-- --------------------------------------------------------

--
-- Table structure for table `point_of_interest_types`
--

CREATE TABLE `point_of_interest_types` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `point_of_interest_types`
--

INSERT INTO `point_of_interest_types` (`id`, `name`) VALUES
(1, 'Beach'),
(2, 'Park'),
(3, 'Museum'),
(4, 'Restaurant'),
(5, 'Hotel'),
(6, 'Monument'),
(7, 'Shopping Mall'),
(8, 'Historical Site'),
(9, 'Zoo'),
(10, 'Theater');

-- --------------------------------------------------------

--
-- Table structure for table `road_type`
--

CREATE TABLE `road_type` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint NOT NULL,
  `price` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `price`, `description`, `name`) VALUES
(1, 100, 'Role for beach lifeguard, ensuring safety on the beach.', 'Lifeguard'),
(2, 200, 'Role for the manager in charge of park operations.', 'Park Manager'),
(3, 150, 'Role for a guide in the history museum.', 'Museum Guide'),
(4, 250, 'Role for the head chef in the gourmet restaurant.', 'Head Chef'),
(5, 300, 'Role for the hotel receptionist welcoming guests.', 'Hotel Receptionist'),
(6, 180, 'Role for the historian at the ancient monument.', 'Monument Historian'),
(7, 120, 'Role for the shopping mall security guard.', 'Security Guard'),
(8, 220, 'Role for the tour guide at the cultural heritage site.', 'Tour Guide'),
(9, 130, 'Role for the animal keeper at the zoo.', 'Zoo Keeper'),
(10, 210, 'Role for the stage manager at the theater.', 'Stage Manager');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `distance` double NOT NULL,
  `duration` double NOT NULL,
  `elevation` double NOT NULL,
  `is_private` bit(1) NOT NULL,
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`distance`, `duration`, `elevation`, `is_private`, `id`, `user_id`, `name`) VALUES
(5.5, 120, 150, b'0', 1, 1, 'Mountain Trail'),
(3.2, 90, 80, b'1', 2, 2, 'Beachfront Walk'),
(12, 180, 200, b'0', 3, 3, 'Historic City Walk'),
(8.5, 150, 100, b'1', 4, 4, 'Nature Reserve Route'),
(4, 110, 50, b'0', 5, 5, 'River Path'),
(6.3, 140, 180, b'1', 6, 6, 'Forest Loop'),
(7, 160, 120, b'0', 7, 7, 'Cultural Heritage Walk'),
(9.1, 170, 220, b'1', 8, 8, 'Scenic Vista Trail'),
(11.2, 200, 300, b'0', 9, 9, 'Desert Expedition'),
(5.8, 130, 90, b'1', 10, 10, 'Countryside Adventure');

-- --------------------------------------------------------

--
-- Table structure for table `segment`
--

CREATE TABLE `segment` (
  `id` bigint NOT NULL,
  `location_one_id` bigint DEFAULT NULL,
  `location_two_id` bigint DEFAULT NULL,
  `road_type_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_beach`
--

CREATE TABLE `service_beach` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `service_beach`
--

INSERT INTO `service_beach` (`id`, `name`) VALUES
(1, 'Lifeguard'),
(2, 'Shower'),
(3, 'Sunbeds'),
(4, 'Bar'),
(5, 'Restroom'),
(6, 'Lifeguard'),
(7, 'Shower'),
(8, 'Sunbeds'),
(9, 'Bar'),
(10, 'Restroom'),
(11, 'Lifeguard'),
(12, 'Shower'),
(13, 'Sunbeds'),
(14, 'Bar'),
(15, 'Restroom');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `date_purchase` datetime(6) DEFAULT NULL,
  `excursion_ticket_details_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`date_purchase`, `excursion_ticket_details_id`, `id`, `user_id`) VALUES
('2025-01-15 10:00:00.000000', 11, 11, 1),
('2025-01-16 14:30:00.000000', 12, 12, 2),
('2025-01-17 09:00:00.000000', 13, 13, 3),
('2025-01-18 16:00:00.000000', 14, 14, 4),
('2025-01-19 11:45:00.000000', 15, 15, 5),
('2025-01-20 13:20:00.000000', 16, 16, 6),
('2025-01-21 08:15:00.000000', 17, 17, 7),
('2025-01-22 12:30:00.000000', 18, 18, 8),
('2025-01-23 10:10:00.000000', 19, 19, 9),
('2025-01-24 14:50:00.000000', 20, 20, 10);

-- --------------------------------------------------------

--
-- Table structure for table `type_beach`
--

CREATE TABLE `type_beach` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `type_beach`
--

INSERT INTO `type_beach` (`id`, `name`) VALUES
(1, 'Sandy'),
(2, 'Rocky'),
(3, 'Mixed'),
(4, 'Urban'),
(5, 'Secluded'),
(6, 'Sandy'),
(7, 'Rocky'),
(8, 'Mixed'),
(9, 'Urban'),
(10, 'Secluded'),
(11, 'Sandy'),
(12, 'Rocky'),
(13, 'Mixed'),
(14, 'Urban'),
(15, 'Secluded');

-- --------------------------------------------------------

--
-- Table structure for table `users_project`
--

CREATE TABLE `users_project` (
  `private_privacy` bit(1) NOT NULL,
  `state` bit(1) NOT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `organization_id` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_surname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `second_surname` varchar(255) DEFAULT NULL,
  `url_photo` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users_project`
--

INSERT INTO `users_project` (`private_privacy`, `state`, `birthday`, `id`, `organization_id`, `email`, `first_surname`, `name`, `password`, `second_surname`, `url_photo`, `user_name`) VALUES
(b'1', b'1', '1990-05-15 08:00:00.000000', 1, 1, 'john.doe@example.com', 'Doe', 'John', 'password123', 'Smith', 'url_photo_1.jpg', 'johndoe'),
(b'0', b'1', '1985-11-20 08:00:00.000000', 2, 2, 'jane.smith@example.com', 'Smith', 'Jane', 'password456', 'Doe', 'url_photo_2.jpg', 'janesmith'),
(b'1', b'0', '1992-02-28 08:00:00.000000', 3, 1, 'michael.jones@example.com', 'Jones', 'Michael', 'password789', 'Taylor', 'url_photo_3.jpg', 'michaeljones'),
(b'0', b'1', '1988-03-10 08:00:00.000000', 4, 3, 'emily.white@example.com', 'White', 'Emily', 'password101', 'Green', 'url_photo_4.jpg', 'emilywhite'),
(b'1', b'0', '1995-07-22 08:00:00.000000', 5, 2, 'alex.martin@example.com', 'Martin', 'Alex', 'password112', 'Davis', 'url_photo_5.jpg', 'alexmartin'),
(b'0', b'1', '1991-09-04 08:00:00.000000', 6, 1, 'olivia.morris@example.com', 'Morris', 'Olivia', 'password113', 'Roberts', 'url_photo_6.jpg', 'oliviamorris'),
(b'1', b'1', '1990-12-30 08:00:00.000000', 7, 4, 'william.brown@example.com', 'Brown', 'William', 'password114', 'Walker', 'url_photo_7.jpg', 'williambrown'),
(b'0', b'1', '1989-08-14 08:00:00.000000', 8, 2, 'lucy.davis@example.com', 'Davis', 'Lucy', 'password115', 'King', 'url_photo_8.jpg', 'lucydavis'),
(b'1', b'0', '1993-04-06 08:00:00.000000', 9, 3, 'jack.martin@example.com', 'Martin', 'Jack', 'password116', 'Scott', 'url_photo_9.jpg', 'jackmartin'),
(b'0', b'1', '1994-01-18 08:00:00.000000', 10, 1, 'sophia.jones@example.com', 'Jones', 'Sophia', 'password117', 'Miller', 'url_photo_10.jpg', 'sophiajones'),
(b'1', b'1', '1990-05-15 08:00:00.000000', 11, 1, 'john.doe@example.com', 'Doe', 'John', 'password123', 'Smith', 'url_photo_1.jpg', 'johndoe'),
(b'0', b'1', '1985-11-20 08:00:00.000000', 12, 2, 'jane.smith@example.com', 'Smith', 'Jane', 'password456', 'Doe', 'url_photo_2.jpg', 'janesmith'),
(b'1', b'0', '1992-02-28 08:00:00.000000', 13, 1, 'michael.jones@example.com', 'Jones', 'Michael', 'password789', 'Taylor', 'url_photo_3.jpg', 'michaeljones'),
(b'0', b'1', '1988-03-10 08:00:00.000000', 14, 3, 'emily.white@example.com', 'White', 'Emily', 'password101', 'Green', 'url_photo_4.jpg', 'emilywhite'),
(b'1', b'0', '1995-07-22 08:00:00.000000', 15, 2, 'alex.martin@example.com', 'Martin', 'Alex', 'password112', 'Davis', 'url_photo_5.jpg', 'alexmartin'),
(b'0', b'1', '1991-09-04 08:00:00.000000', 16, 1, 'olivia.morris@example.com', 'Morris', 'Olivia', 'password113', 'Roberts', 'url_photo_6.jpg', 'oliviamorris'),
(b'1', b'1', '1990-12-30 08:00:00.000000', 17, 4, 'william.brown@example.com', 'Brown', 'William', 'password114', 'Walker', 'url_photo_7.jpg', 'williambrown'),
(b'0', b'1', '1989-08-14 08:00:00.000000', 18, 2, 'lucy.davis@example.com', 'Davis', 'Lucy', 'password115', 'King', 'url_photo_8.jpg', 'lucydavis'),
(b'1', b'0', '1993-04-06 08:00:00.000000', 19, 3, 'jack.martin@example.com', 'Martin', 'Jack', 'password116', 'Scott', 'url_photo_9.jpg', 'jackmartin'),
(b'0', b'1', '1994-01-18 08:00:00.000000', 20, 1, 'sophia.jones@example.com', 'Jones', 'Sophia', 'password117', 'Miller', 'url_photo_10.jpg', 'sophiajones'),
(b'1', b'1', '1990-05-15 08:00:00.000000', 21, 1, 'john.doe@example.com', 'Doe', 'John', 'password123', 'Smith', 'url_photo_1.jpg', 'johndoe'),
(b'0', b'1', '1985-11-20 08:00:00.000000', 22, 2, 'jane.smith@example.com', 'Smith', 'Jane', 'password456', 'Doe', 'url_photo_2.jpg', 'janesmith'),
(b'1', b'0', '1992-02-28 08:00:00.000000', 23, 1, 'michael.jones@example.com', 'Jones', 'Michael', 'password789', 'Taylor', 'url_photo_3.jpg', 'michaeljones'),
(b'0', b'1', '1988-03-10 08:00:00.000000', 24, 3, 'emily.white@example.com', 'White', 'Emily', 'password101', 'Green', 'url_photo_4.jpg', 'emilywhite'),
(b'1', b'0', '1995-07-22 08:00:00.000000', 25, 2, 'alex.martin@example.com', 'Martin', 'Alex', 'password112', 'Davis', 'url_photo_5.jpg', 'alexmartin'),
(b'0', b'1', '1991-09-04 08:00:00.000000', 26, 1, 'olivia.morris@example.com', 'Morris', 'Olivia', 'password113', 'Roberts', 'url_photo_6.jpg', 'oliviamorris'),
(b'1', b'1', '1990-12-30 08:00:00.000000', 27, 4, 'william.brown@example.com', 'Brown', 'William', 'password114', 'Walker', 'url_photo_7.jpg', 'williambrown'),
(b'0', b'1', '1989-08-14 08:00:00.000000', 28, 2, 'lucy.davis@example.com', 'Davis', 'Lucy', 'password115', 'King', 'url_photo_8.jpg', 'lucydavis'),
(b'1', b'0', '1993-04-06 08:00:00.000000', 29, 3, 'jack.martin@example.com', 'Martin', 'Jack', 'password116', 'Scott', 'url_photo_9.jpg', 'jackmartin'),
(b'0', b'1', '1994-01-18 08:00:00.000000', 30, 1, 'sophia.jones@example.com', 'Jones', 'Sophia', 'password117', 'Miller', 'url_photo_10.jpg', 'sophiajones');

-- --------------------------------------------------------

--
-- Table structure for table `user_has_rol`
--

CREATE TABLE `user_has_rol` (
  `date_begin` date DEFAULT NULL,
  `date_finish` date DEFAULT NULL,
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_has_rol`
--

INSERT INTO `user_has_rol` (`date_begin`, `date_finish`, `id`, `role_id`, `user_id`) VALUES
('2025-01-01', '2025-12-31', 1, 1, 1),
('2025-02-01', '2025-12-31', 2, 2, 2),
('2025-03-01', '2025-12-31', 3, 3, 3),
('2025-04-01', '2025-12-31', 4, 1, 4),
('2025-05-01', '2025-12-31', 5, 4, 5),
('2025-06-01', '2025-12-31', 6, 2, 6),
('2025-07-01', '2025-12-31', 7, 3, 7),
('2025-08-01', '2025-12-31', 8, 4, 8),
('2025-09-01', '2025-12-31', 9, 1, 9),
('2025-10-01', '2025-12-31', 10, 2, 10);

-- --------------------------------------------------------

--
-- Table structure for table `user_require_rol`
--

CREATE TABLE `user_require_rol` (
  `is_approved` bit(1) NOT NULL,
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `url_official_doc` varchar(255) DEFAULT NULL,
  `url_photo_dni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_require_rol`
--

INSERT INTO `user_require_rol` (`is_approved`, `id`, `role_id`, `user_id`, `url_official_doc`, `url_photo_dni`) VALUES
(b'1', 1, 1, 1, 'https://example.com/doc1.pdf', 'https://example.com/dni1.jpg'),
(b'0', 2, 2, 2, 'https://example.com/doc2.pdf', 'https://example.com/dni2.jpg'),
(b'1', 3, 3, 3, 'https://example.com/doc3.pdf', 'https://example.com/dni3.jpg'),
(b'1', 4, 1, 4, 'https://example.com/doc4.pdf', 'https://example.com/dni4.jpg'),
(b'0', 5, 4, 5, 'https://example.com/doc5.pdf', 'https://example.com/dni5.jpg'),
(b'1', 6, 2, 6, 'https://example.com/doc6.pdf', 'https://example.com/dni6.jpg'),
(b'0', 7, 3, 7, 'https://example.com/doc7.pdf', 'https://example.com/dni7.jpg'),
(b'1', 8, 4, 8, 'https://example.com/doc8.pdf', 'https://example.com/dni8.jpg'),
(b'1', 9, 1, 9, 'https://example.com/doc9.pdf', 'https://example.com/dni9.jpg'),
(b'0', 10, 2, 10, 'https://example.com/doc10.pdf', 'https://example.com/dni10.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aggregation_types`
--
ALTER TABLE `aggregation_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `beach`
--
ALTER TABLE `beach`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `beach_has_service`
--
ALTER TABLE `beach_has_service`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8h18kcqprjsryh48x4kb7ap8w` (`beach_id`),
  ADD KEY `FKcpfwadukbp41wbakfj27jx8kp` (`role_id`);

--
-- Indexes for table `beach_manager`
--
ALTER TABLE `beach_manager`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5smibi9knwgkeeg8cpkicidh1` (`beach_id`),
  ADD KEY `FK16nj24r0anpt7ijom7tqu7uux` (`user_id`);

--
-- Indexes for table `beach_type`
--
ALTER TABLE `beach_type`
  ADD KEY `FKk75g4ejndqhg27sbeup4qv4dd` (`type_id`),
  ADD KEY `FKdfc20rv24cv0ryn2phlwnkpqr` (`beach_id`);

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2xa8iwuowbaoepdwvl0426qmi` (`bill_type_id`);

--
-- Indexes for table `bill_types`
--
ALTER TABLE `bill_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `budgets`
--
ALTER TABLE `budgets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `budget_business`
--
ALTER TABLE `budget_business`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKp7btyrm4d2tjudu5qs30ujyy2` (`budget_id`),
  ADD UNIQUE KEY `UKefhowf83pmtw0rw3urd7c9v9q` (`business_id`);

--
-- Indexes for table `businesses`
--
ALTER TABLE `businesses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfr2wiupmr36mch14rw6up7rdn` (`business_type_id`);

--
-- Indexes for table `businesses_horary`
--
ALTER TABLE `businesses_horary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1klc216u4a3vy0cw6nuipkpnn` (`business_id`),
  ADD KEY `FK9b6t4nb7jinbs6xh32vp3nl9v` (`horary_id`);

--
-- Indexes for table `business_types`
--
ALTER TABLE `business_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `camera`
--
ALTER TABLE `camera`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK38897borg4uw751ei857ci6dp` (`camera_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgq6hvhh6rin69jqw6n76fnktf` (`beach_id`),
  ADD KEY `FKk3vmpmcbl2rk8skwr6u88083p` (`route_id`),
  ADD KEY `FKt35xrng6w43pf7f9gjk1wkph3` (`user_id`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt799epnaww8vmyg0o56sgo98j` (`beach_id`),
  ADD KEY `FK6fin7n6pdrqyigkolmr3mq5kb` (`route_id`),
  ADD KEY `FKeky86wbgfj0mrr0a6frp9qo9j` (`user_id`);

--
-- Indexes for table `excursions`
--
ALTER TABLE `excursions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKet7d4r2o31j7v5dsxbs8m2j6c` (`user_id`);

--
-- Indexes for table `excursions_ticket_details`
--
ALTER TABLE `excursions_ticket_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKee9i6dpxqitemv5bxn5nibhn0` (`excursion_id`),
  ADD KEY `FKge6b6bnx1la5cvqfnllyyofdg` (`horary_id`),
  ADD KEY `FKonqmqjwnim8ph9kl51dxkk2hr` (`route_id`);

--
-- Indexes for table `functions`
--
ALTER TABLE `functions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `horaries`
--
ALTER TABLE `horaries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3245r6vktc8mi2bsg54832vpv` (`route_id`);

--
-- Indexes for table `organizations`
--
ALTER TABLE `organizations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `photos`
--
ALTER TABLE `photos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `points_of_interest`
--
ALTER TABLE `points_of_interest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfwk0ll7xr6tg1bukuff7pafs5` (`point_of_interest_type_id`);

--
-- Indexes for table `point_of_interest_types`
--
ALTER TABLE `point_of_interest_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `road_type`
--
ALTER TABLE `road_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKluqabl73r1ihtb0dpkpk9uwon` (`user_id`);

--
-- Indexes for table `segment`
--
ALTER TABLE `segment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKf3albjhgcnqik9pc1mvk51fo2` (`location_one_id`),
  ADD UNIQUE KEY `UK9crmfttvh2n8xs0oynm5w4a6m` (`location_two_id`),
  ADD KEY `FKjrsppgpyayt9s15vciusa2xc3` (`road_type_id`);

--
-- Indexes for table `service_beach`
--
ALTER TABLE `service_beach`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkl8xrxofwdckgk7fwbjvrgke1` (`excursion_ticket_details_id`),
  ADD KEY `FK11kl85yktuaaedomf3v0ferll` (`user_id`);

--
-- Indexes for table `type_beach`
--
ALTER TABLE `type_beach`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_project`
--
ALTER TABLE `users_project`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKspj5g06sadwyjwrggarr2qjia` (`organization_id`);

--
-- Indexes for table `user_has_rol`
--
ALTER TABLE `user_has_rol`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkfh6kqd1cnu2m55rry3ywsrl0` (`role_id`),
  ADD KEY `FKe81p0ngniewhwcbummregs13k` (`user_id`);

--
-- Indexes for table `user_require_rol`
--
ALTER TABLE `user_require_rol`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdv8c123ajdamg0yyxq4ucsmpi` (`role_id`),
  ADD KEY `FKr0lsept34jpuuwbi6o81tvic1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aggregation_types`
--
ALTER TABLE `aggregation_types`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `beach`
--
ALTER TABLE `beach`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `beach_has_service`
--
ALTER TABLE `beach_has_service`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `beach_manager`
--
ALTER TABLE `beach_manager`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `bill_types`
--
ALTER TABLE `bill_types`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `budgets`
--
ALTER TABLE `budgets`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `budget_business`
--
ALTER TABLE `budget_business`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `businesses`
--
ALTER TABLE `businesses`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `businesses_horary`
--
ALTER TABLE `businesses_horary`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `business_types`
--
ALTER TABLE `business_types`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `camera`
--
ALTER TABLE `camera`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `excursions`
--
ALTER TABLE `excursions`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `excursions_ticket_details`
--
ALTER TABLE `excursions_ticket_details`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `functions`
--
ALTER TABLE `functions`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `horaries`
--
ALTER TABLE `horaries`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organizations`
--
ALTER TABLE `organizations`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `photos`
--
ALTER TABLE `photos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `points_of_interest`
--
ALTER TABLE `points_of_interest`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `point_of_interest_types`
--
ALTER TABLE `point_of_interest_types`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `road_type`
--
ALTER TABLE `road_type`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `segment`
--
ALTER TABLE `segment`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service_beach`
--
ALTER TABLE `service_beach`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `type_beach`
--
ALTER TABLE `type_beach`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users_project`
--
ALTER TABLE `users_project`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `user_has_rol`
--
ALTER TABLE `user_has_rol`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user_require_rol`
--
ALTER TABLE `user_require_rol`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `beach_has_service`
--
ALTER TABLE `beach_has_service`
  ADD CONSTRAINT `FK8h18kcqprjsryh48x4kb7ap8w` FOREIGN KEY (`beach_id`) REFERENCES `beach` (`id`),
  ADD CONSTRAINT `FKcpfwadukbp41wbakfj27jx8kp` FOREIGN KEY (`role_id`) REFERENCES `service_beach` (`id`);

--
-- Constraints for table `beach_manager`
--
ALTER TABLE `beach_manager`
  ADD CONSTRAINT `FK16nj24r0anpt7ijom7tqu7uux` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`),
  ADD CONSTRAINT `FK5smibi9knwgkeeg8cpkicidh1` FOREIGN KEY (`beach_id`) REFERENCES `beach` (`id`);

--
-- Constraints for table `beach_type`
--
ALTER TABLE `beach_type`
  ADD CONSTRAINT `FKdfc20rv24cv0ryn2phlwnkpqr` FOREIGN KEY (`beach_id`) REFERENCES `beach` (`id`),
  ADD CONSTRAINT `FKk75g4ejndqhg27sbeup4qv4dd` FOREIGN KEY (`type_id`) REFERENCES `type_beach` (`id`);

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `FK2xa8iwuowbaoepdwvl0426qmi` FOREIGN KEY (`bill_type_id`) REFERENCES `bill_types` (`id`);

--
-- Constraints for table `budget_business`
--
ALTER TABLE `budget_business`
  ADD CONSTRAINT `FKnpxf8th4i156vapy0l2ixhb09` FOREIGN KEY (`budget_id`) REFERENCES `budgets` (`id`),
  ADD CONSTRAINT `FKryqfet4mgrnn6qykdsn2h6d37` FOREIGN KEY (`business_id`) REFERENCES `businesses` (`id`);

--
-- Constraints for table `businesses`
--
ALTER TABLE `businesses`
  ADD CONSTRAINT `FKfr2wiupmr36mch14rw6up7rdn` FOREIGN KEY (`business_type_id`) REFERENCES `business_types` (`id`);

--
-- Constraints for table `businesses_horary`
--
ALTER TABLE `businesses_horary`
  ADD CONSTRAINT `FK1klc216u4a3vy0cw6nuipkpnn` FOREIGN KEY (`business_id`) REFERENCES `businesses` (`id`),
  ADD CONSTRAINT `FK9b6t4nb7jinbs6xh32vp3nl9v` FOREIGN KEY (`horary_id`) REFERENCES `horaries` (`id`);

--
-- Constraints for table `camera`
--
ALTER TABLE `camera`
  ADD CONSTRAINT `FK38897borg4uw751ei857ci6dp` FOREIGN KEY (`camera_id`) REFERENCES `beach` (`id`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FKgq6hvhh6rin69jqw6n76fnktf` FOREIGN KEY (`beach_id`) REFERENCES `beach` (`id`),
  ADD CONSTRAINT `FKk3vmpmcbl2rk8skwr6u88083p` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`),
  ADD CONSTRAINT `FKt35xrng6w43pf7f9gjk1wkph3` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`);

--
-- Constraints for table `complaints`
--
ALTER TABLE `complaints`
  ADD CONSTRAINT `FK6fin7n6pdrqyigkolmr3mq5kb` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`),
  ADD CONSTRAINT `FKeky86wbgfj0mrr0a6frp9qo9j` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`),
  ADD CONSTRAINT `FKt799epnaww8vmyg0o56sgo98j` FOREIGN KEY (`beach_id`) REFERENCES `beach` (`id`);

--
-- Constraints for table `excursions`
--
ALTER TABLE `excursions`
  ADD CONSTRAINT `FKet7d4r2o31j7v5dsxbs8m2j6c` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`);

--
-- Constraints for table `excursions_ticket_details`
--
ALTER TABLE `excursions_ticket_details`
  ADD CONSTRAINT `FKee9i6dpxqitemv5bxn5nibhn0` FOREIGN KEY (`excursion_id`) REFERENCES `excursions` (`id`),
  ADD CONSTRAINT `FKge6b6bnx1la5cvqfnllyyofdg` FOREIGN KEY (`horary_id`) REFERENCES `horaries` (`id`),
  ADD CONSTRAINT `FKonqmqjwnim8ph9kl51dxkk2hr` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`);

--
-- Constraints for table `locations`
--
ALTER TABLE `locations`
  ADD CONSTRAINT `FK3245r6vktc8mi2bsg54832vpv` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`);

--
-- Constraints for table `points_of_interest`
--
ALTER TABLE `points_of_interest`
  ADD CONSTRAINT `FKfwk0ll7xr6tg1bukuff7pafs5` FOREIGN KEY (`point_of_interest_type_id`) REFERENCES `point_of_interest_types` (`id`);

--
-- Constraints for table `routes`
--
ALTER TABLE `routes`
  ADD CONSTRAINT `FKluqabl73r1ihtb0dpkpk9uwon` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`);

--
-- Constraints for table `segment`
--
ALTER TABLE `segment`
  ADD CONSTRAINT `FK2a4bhasrtl70acgj9ee5sush7` FOREIGN KEY (`location_one_id`) REFERENCES `locations` (`id`),
  ADD CONSTRAINT `FKjrsppgpyayt9s15vciusa2xc3` FOREIGN KEY (`road_type_id`) REFERENCES `road_type` (`id`),
  ADD CONSTRAINT `FKnnflbx9lbdphq5ijhtbu4ijib` FOREIGN KEY (`location_two_id`) REFERENCES `locations` (`id`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `FK11kl85yktuaaedomf3v0ferll` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`),
  ADD CONSTRAINT `FKkl8xrxofwdckgk7fwbjvrgke1` FOREIGN KEY (`excursion_ticket_details_id`) REFERENCES `excursions_ticket_details` (`id`);

--
-- Constraints for table `users_project`
--
ALTER TABLE `users_project`
  ADD CONSTRAINT `FKspj5g06sadwyjwrggarr2qjia` FOREIGN KEY (`organization_id`) REFERENCES `organizations` (`id`);

--
-- Constraints for table `user_has_rol`
--
ALTER TABLE `user_has_rol`
  ADD CONSTRAINT `FKe81p0ngniewhwcbummregs13k` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`),
  ADD CONSTRAINT `FKkfh6kqd1cnu2m55rry3ywsrl0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `user_require_rol`
--
ALTER TABLE `user_require_rol`
  ADD CONSTRAINT `FKdv8c123ajdamg0yyxq4ucsmpi` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKr0lsept34jpuuwbi6o81tvic1` FOREIGN KEY (`user_id`) REFERENCES `users_project` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
