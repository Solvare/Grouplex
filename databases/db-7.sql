-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 23, 2017 at 03:30 AM
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
(32, 'Multiverse', 3, '2017-08-19 01:55:16', ''),
(33, 'varun', 3, '2017-08-19 19:27:34', ''),
(34, 'xyz', 34, '2017-08-21 06:51:54', '');

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
(41, 32, 3, '2017-08-19 01:55:16'),
(42, 33, 3, '2017-08-19 19:27:34'),
(43, 2, 3, '2017-08-19 19:30:12'),
(44, 1, 34, '2017-08-21 06:50:59'),
(45, 34, 34, '2017-08-21 06:51:54'),
(46, 1, 3, '2017-08-21 12:00:24'),
(47, 3, 3, '2017-08-21 12:00:48'),
(48, 1, 35, '2017-08-21 12:03:24');

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
(20, 13, 'gb', '2017-08-19 02:05:32'),
(21, 11, 'varun', '2017-08-19 19:28:19');

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
(1, 'sunny0rajat@gmail.com', '$2a$10$0bd430e86b141f1d29687OrwxTa97HooLQ3sn8SVtLED0tS5Li4LG', 'Rajat Saxena', 1, 623153, 'hahaha', '2017-08-22 16:41:21'),
(2, 'rishabhahuja279@gmail.com', '$2a$10$ccc32cc9b684eb4d07ab1uSzWkZxgXQHnKfTrm6xWiBvI1dn9ETX.', 'Rishabh Ahuja', 0, 787589, 'hahaha', '2017-08-21 06:53:30'),
(3, '', '$2a$10$ae3ef8e25c5b1d65cad39uK1z41K.59gvkuEdbRBMnwWCq1idwXR6', 'Solve Software', 1, 0, '', '2017-08-22 13:50:23'),
(32, 'emma@watson.com', '$2a$10$6d6a97cdfd62c484b0f61uAdtRTXwtQyWBHfj4Rht2.9HO1WoqW.a', 'Emma Watson', 0, 847515, '', '2017-08-18 12:56:32'),
(34, 'rishabhahuja2736@gmail.com', '$2a$10$a04db6f79a9555a8d585auEROyQJGnbJBhPu2fr/3K4cFA4pvVmuu', 'rishabh', 0, 0, '', '2017-08-21 06:50:28'),
(35, 'g@g.g', '$2a$10$70d024c17f67ac4aacc05ONNj.rPD3RPp7xVU/btJdjtsy8E24I0m', 'fg', 0, 0, '', '2017-08-21 12:03:18'),
(36, 'rajat.saxena035@gmail.com', '$2a$10$a8116df8262e777c17d2aOWZ5ET8rafUYpQPKa6.eW3c/oN7GUGla', 'q', 0, 0, '', '2017-08-22 17:03:57');

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
  MODIFY `group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `master`
--
ALTER TABLE `master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
