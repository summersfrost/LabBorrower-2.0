-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2023 at 01:58 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `facilitator`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(5) NOT NULL DEFAULT 0,
  `name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `password` varchar(100) CHARACTER SET latin1 NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `address` varchar(200) CHARACTER SET latin1 NOT NULL,
  `city` varchar(100) CHARACTER SET latin1 NOT NULL,
  `contact` varchar(20) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `password`, `email`, `address`, `city`, `contact`) VALUES
(0, 'Zoe Gerard Brillantes', 'ZoeGerard123', 'zoe@gmail.com', 'Dauis ', 'Panglao Bohol', '09093317262'),
(0, 'Keith Canon Roma', 'kcroma', 'kcroma@gmail.com', 'Dauis ', 'Panglao Bohol', '09093317262');

-- --------------------------------------------------------

--
-- Table structure for table `borrowed_equipment`
--

CREATE TABLE `borrowed_equipment` (
  `student_id` varchar(50) NOT NULL,
  `equipment_id` varchar(50) NOT NULL,
  `borrowed_date` date DEFAULT NULL,
  `returned_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrowed_equipment`
--

INSERT INTO `borrowed_equipment` (`student_id`, `equipment_id`, `borrowed_date`, `returned_date`) VALUES
('21-0308-733', 'screw1', '2023-05-30', '2023-06-07'),
('21-0308-733', 'screw', '2023-05-30', '2023-06-07'),
('21-0308-733', 'screw', '2023-05-30', '2023-06-07'),
('21-0308-733', 'screw', '2023-05-30', '2023-06-07'),
('21-0308-733', 'screw', '2023-05-31', '2023-06-07'),
('21-0308-733', 'screw1', '2023-05-31', '2023-06-07'),
('21-0308-733', 'screw', '2023-06-02', '2023-06-07'),
('21-0308-733', 'screw1', '2023-06-02', '2023-06-07'),
('21-0308-733', 'screw1', '2023-06-02', '2023-06-07'),
('21-0308-733', 'screw1', '2023-06-07', '2023-06-07'),
('21-0308-733', 'screw', '2023-06-07', '2023-06-07'),
('21-1598-436', 'screw1', '2023-06-07', '2023-06-07'),
('21-1598-436', 'screw', '2023-06-07', NULL),
('20-0587-750', 'screw1', '2023-06-07', '2023-06-07'),
('20-0587-750', 'Hammer1', '2023-06-07', NULL),
('20-0587-750', 'p1', '2023-06-07', '2023-06-07'),
('21-0308-733', 'p4', '2023-06-07', '2023-06-07'),
('21-1598-436', 'p1', '2023-06-07', '2023-06-07');

-- --------------------------------------------------------

--
-- Table structure for table `equipments`
--

CREATE TABLE `equipments` (
  `id` int(11) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `manufacturer` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `dateAdded` date NOT NULL,
  `isBorrowed` tinyint(1) NOT NULL,
  `isAvailable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `equipments`
--

INSERT INTO `equipments` (`id`, `refID`, `name`, `manufacturer`, `model`, `dateAdded`, `isBorrowed`, `isAvailable`) VALUES
(1, 'screw', 'Screwdriver', 'Conarco', '14-69', '2023-05-30', 1, 0),
(2, 'screw1', 'Screwdriver', 'Conarco', '14-69', '2023-05-30', 0, 1),
(12, 'p1', 'Pliers', 'Gen Av', 'p-232', '2023-06-07', 0, 1),
(13, 'p2', 'Pliers', 'Gen Av', 'p-232', '2023-06-07', 0, 1),
(14, 'p3', 'Pliers', 'Gen Av', 'p-232', '2023-06-07', 0, 1),
(15, 'Avl1', 'Av Snips (left)', 'Genav', 'avl1221', '2023-06-07', 0, 1),
(16, 'avl2', 'Av Snips (left)', 'Genav', 'avl1221', '2023-06-07', 0, 1),
(17, 'tpg1', 'Tire Pressure Gauge', 'Goodyear', 'tp21-2', '2023-06-07', 0, 1),
(18, 'tpg2', 'Tire Pressure Gauge', 'Goodyear', 'tp21-2', '2023-06-07', 0, 1),
(19, 'Hammer1', 'Hammer Rubber', 'Ub', 'Honda', '2023-06-07', 1, 0),
(20, 'p4', 'Pliers', 'Gen Av', 'Gen AV', '2023-06-07', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `facilitator`
--

CREATE TABLE `facilitator` (
  `id` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `city` varchar(100) NOT NULL,
  `contact` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facilitator`
--

INSERT INTO `facilitator` (`id`, `name`, `password`, `email`, `address`, `city`, `contact`) VALUES
(9, 'User', 'Benjie14!', 'benjie14@gmail.com', 'Tocdog Dacu', 'Loay Bohols', '09507938850'),
(17, 'Username', 'Benjie14!', 'bbq14@gmail.com', 'Cogon', 'Loay Bohol', '09507938850'),
(19, 'Zoe', 'Zoebiot123!', 'zoeg@gmail.com', 'Tawala', 'New Jersey', '09462261187');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `studentID` varchar(20) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `middleName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `studentID`, `firstName`, `middleName`, `lastName`, `extension`, `email`) VALUES
(1, '21-0308-733', 'Benjie', 'Solera', 'Juabot', '', 'benjiejuabot14@gmail.com'),
(4, '21-1598-436', 'Keith', '', 'Roma', '', 'kcroma@gmail.com'),
(5, '20-0587-750', 'Olaivar', 'Buaya', 'Olaivar', '', 'kenskie@gmail.com'),
(6, '21-0185-791', 'Kyle Zymund', 'Cachapero', 'Grapa', '', 'kzcgrapa@universityofbohol.edu.ph'),
(7, '15-1235-954', 'John Michael', 'Dayaganon', 'Conarco', '', 'jmdconarco@universityofbohol.edu.ph'),
(8, '20-0713-219', 'Don Allen', 'Tutor', 'Veloso', '', 'yongs@yongs.xyz'),
(9, '21-0676-683', 'Zoe Gerard', '', 'Brillantes', '', 'zoeg@gmail.com'),
(11, '12-2443-232', 'Juan', 'Dela', 'Cruz', '', 'as@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `equipments`
--
ALTER TABLE `equipments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_refID` (`refID`);

--
-- Indexes for table `facilitator`
--
ALTER TABLE `facilitator`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_studentID` (`studentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `equipments`
--
ALTER TABLE `equipments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `facilitator`
--
ALTER TABLE `facilitator`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
