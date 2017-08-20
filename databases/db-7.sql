-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 19, 2017 at 01:18 PM
-- Server version: 10.1.20-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id2588432_grouplex`
--

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`group_id`, `group_name`, `admin_id`, `created_at`, `image`) VALUES
(1, 'CS-2013', 1, '2017-03-31 11:14:27', 'cs.png'),
(2, 'Hackers', 1, '2017-03-31 11:14:44', 'hacker.jpg'),
(3, 'EC-2018', 1, '2017-06-23 07:50:45', 'ec.jpg'),
(4, 'Royals', 1, '2017-06-17 06:39:25', ''),
(5, 'Swifties', 1, '2017-06-17 06:39:30', ''),
(6, 'Gryffindor', 2, '2017-06-17 06:39:20', ''),
(7, 'GBUians', 2, '2017-06-23 07:50:51', ''),
(8, 'Lords', 2, '2017-06-23 07:50:56', ''),
(9, 'Slytherin', 2, '2017-06-23 07:51:08', ''),
(10, 'Feminists', 2, '2017-06-23 07:51:12', ''),
(11, 'Google', 3, '2017-06-23 07:51:17', ''),
(12, 'Microsoft', 3, '2017-06-23 07:51:20', ''),
(13, 'Yahoo', 3, '2017-06-23 07:51:23', ''),
(14, 'Hackathon', 3, '2017-06-17 06:47:37', ''),
(15, 'Qween', 3, '2017-06-17 06:47:01', ''),
(31, 'Oracle', 32, '2017-06-23 08:03:56', ''),
(30, 'Adobe', 3, '2017-06-23 07:57:40', ''),
(32, 'Multiverse', 3, '2017-08-19 01:55:16', '');

-- --------------------------------------------------------

--
-- Table structure for table `master`
--

CREATE TABLE `master` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master`
--

INSERT INTO `master` (`id`, `group_id`, `user_id`, `created_at`) VALUES
(1, 1, 1, '2016-09-29 22:14:33'),
(3, 2, 1, '2016-09-29 22:15:43'),
(4, 3, 1, '2017-04-02 04:46:51'),
(5, 4, 1, '2017-04-02 04:46:54'),
(6, 5, 1, '2017-06-23 07:52:54'),
(7, 6, 2, '2017-06-23 07:52:35'),
(16, 7, 2, '2017-06-23 07:53:02'),
(12, 8, 2, '2017-06-23 07:53:06'),
(19, 9, 2, '2017-06-23 07:53:13'),
(18, 10, 2, '2017-06-23 07:53:21'),
(25, 11, 3, '2017-06-17 06:50:38'),
(26, 12, 3, '2017-06-17 06:50:41'),
(27, 13, 3, '2017-06-17 06:50:46'),
(29, 14, 3, '2017-06-23 07:54:41'),
(40, 31, 32, '2017-06-23 08:03:56'),
(39, 1, 32, '2017-06-23 08:02:05'),
(38, 30, 3, '2017-06-23 07:57:40'),
(37, 15, 3, '2017-06-23 07:54:45'),
(41, 32, 3, '2017-08-19 01:55:16');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `chat` varchar(512) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`message_id`, `group_id`, `chat`, `created_at`) VALUES
(1, 1, 'Hello Everyone', '2016-09-29 22:01:17'),
(2, 1, 'Good Night', '2016-09-29 23:17:57'),
(3, 2, 'No class tomorrow', '2017-06-17 06:49:00'),
(4, 2, 'Exam', '2017-06-17 06:48:40'),
(9, 15, 'hi', '2017-08-19 01:53:49'),
(10, 30, 'd', '2017-08-19 01:54:11'),
(11, 11, 'hd', '2017-08-19 01:54:33'),
(12, 32, 'zhsh', '2017-08-19 01:55:25'),
(13, 13, 'rd', '2017-08-19 02:01:26'),
(14, 32, 'hs', '2017-08-19 02:01:38'),
(15, 32, 'hsbd', '2017-08-19 02:01:43'),
(16, 15, 'hs', '2017-08-19 02:01:50'),
(17, 30, 's', '2017-08-19 02:01:59'),
(18, 11, 's', '2017-08-19 02:02:06'),
(19, 13, 'gh', '2017-08-19 02:05:25'),
(20, 13, 'gb', '2017-08-19 02:05:32');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `full_name` varchar(256) NOT NULL,
  `verified` tinyint(1) NOT NULL DEFAULT '0',
  `otp` int(11) NOT NULL,
  `gcm_reg_id` varchar(512) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `password`, `full_name`, `verified`, `otp`, `gcm_reg_id`, `created_at`) VALUES
(1, 'sunny0rajat@gmail.com', '$2a$10$ba12aaeb87a96805aa756Oe1hQRESdKnjessL10EpZYhZCdnt4AYq', 'Rajat Saxena', 1, 386743, 'hahaha', '2017-08-19 13:10:19'),
(2, 'rishabhahuja279@gmail.com', '$2a$10$ea51a4fcd9936ab52743bORE7D.JeLtQq/68SMJMIpyI91JjNOZwi', 'Rishabh Ahuja', 0, 0, 'hahaha', '2017-03-28 14:23:27'),
(3, '', '$2a$10$ae3ef8e25c5b1d65cad39uK1z41K.59gvkuEdbRBMnwWCq1idwXR6', 'Solve Software', 1, 0, '', '2017-08-18 09:50:25'),
(32, 'emma@watson.com', '$2a$10$6d6a97cdfd62c484b0f61uAdtRTXwtQyWBHfj4Rht2.9HO1WoqW.a', 'Emma Watson', 1, 847515, '', '2017-08-18 12:56:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`group_id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- Indexes for table `master`
--
ALTER TABLE `master`
  ADD PRIMARY KEY (`id`),
  ADD KEY `group_id` (`group_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `group_id` (`group_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `master`
--
ALTER TABLE `master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
