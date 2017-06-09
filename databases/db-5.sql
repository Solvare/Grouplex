
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 09, 2017 at 03:25 AM
-- Server version: 10.1.24-MariaDB
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u255854698_grplx`
--

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(100) NOT NULL,
  PRIMARY KEY (`group_id`),
  KEY `admin_id` (`admin_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`group_id`, `group_name`, `admin_id`, `created_at`, `image`) VALUES
(1, 'CS-2013', 1, '2017-03-31 11:14:27', 'cs.png'),
(2, 'Hackers', 1, '2017-03-31 11:14:44', 'hacker.jpg'),
(3, 'EC-2018', 2, '2017-03-31 11:14:34', 'ec.jpg'),
(12, 'Royals', 1, '2017-04-03 14:49:01', ''),
(13, 'Swifties', 1, '2017-04-03 18:32:54', ''),
(4, 'Gryffindor', 2, '2017-04-02 04:42:46', ''),
(14, 'GBUians', 1, '2017-04-04 08:48:17', ''),
(15, 'asd', 1, '2017-04-06 20:28:30', ''),
(16, 'Slytherin', 1, '2017-04-09 01:35:59', ''),
(17, 'Feminists', 3, '2017-04-09 01:49:21', ''),
(18, 'Google', 1, '2017-04-12 19:56:40', ''),
(19, 'Microsoft', 1, '2017-04-12 19:56:40', ''),
(20, 'Yahoo', 1, '2017-04-12 19:57:39', ''),
(21, 'Hacker', 3, '2017-04-13 12:54:06', '');

-- --------------------------------------------------------

--
-- Table structure for table `master`
--

CREATE TABLE IF NOT EXISTS `master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `master`
--

INSERT INTO `master` (`id`, `group_id`, `user_id`, `created_at`) VALUES
(1, 1, 1, '2016-09-29 22:14:33'),
(3, 2, 1, '2016-09-29 22:15:43'),
(4, 3, 1, '2017-04-02 04:46:51'),
(5, 4, 1, '2017-04-02 04:46:54'),
(6, 1, 2, '2017-04-02 04:47:11'),
(7, 2, 2, '2017-04-02 04:47:05'),
(16, 4, 2, '2017-04-02 04:47:21'),
(12, 3, 2, '2017-04-01 13:33:27'),
(19, 12, 1, '2017-04-03 14:49:01'),
(18, 4, 3, '2017-04-02 04:50:30'),
(17, 1, 3, '2017-04-02 04:49:42'),
(20, 13, 1, '2017-04-03 18:32:54'),
(21, 14, 1, '2017-04-04 08:48:17'),
(22, 15, 1, '2017-04-06 20:28:30'),
(23, 16, 1, '2017-04-09 01:35:59'),
(24, 17, 3, '2017-04-09 01:49:21'),
(25, 21, 3, '2017-04-13 12:54:06');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `chat` varchar(512) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`),
  KEY `group_id` (`group_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`message_id`, `group_id`, `chat`, `created_at`) VALUES
(1, 1, 'Hello Everyone', '2016-09-29 22:01:17'),
(2, 1, 'Good Night', '2016-09-29 23:17:57'),
(7, 1, 'No class tomorrow', '2016-09-30 08:17:43'),
(8, 2, 'Exam', '2016-09-30 08:19:40');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `full_name` varchar(256) NOT NULL,
  `gcm_reg_id` varchar(512) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `password`, `full_name`, `gcm_reg_id`, `created_at`) VALUES
(1, 'rajat24saxena@gmail.com', '$2a$10$ea51a4fcd9936ab52743bORE7D.JeLtQq/68SMJMIpyI91JjNOZwi', 'Rajat Saxena', 'hahaha', '2017-03-28 14:23:23'),
(2, 'rishabhahuja279@gmail.com', '$2a$10$ea51a4fcd9936ab52743bORE7D.JeLtQq/68SMJMIpyI91JjNOZwi', 'Rishabh Ahuja', 'hahaha', '2017-03-28 14:23:27'),
(3, '1', '$2a$10$5edcd602023c8a187dab5O6XMUgUrf54XLnCtm1Uw0ETeUB.1Ze2a', '', '', '2017-04-06 17:28:24'),
(4, 'eded@dfrf.ef', 'edwdwdw', 'Rdeded deded', '', '2017-01-05 17:25:04'),
(5, 'rajat@gmail.com', '', '', '', '2017-01-05 17:29:44'),
(6, 'rishabhahuja279999@g.com', '123456789', 'fgh the', '', '2017-01-05 19:41:17'),
(7, 'c@c.com', '123456789', 'risg', '', '2017-01-05 20:08:28'),
(8, '123', '123', '123', '', '2017-01-05 21:12:44'),
(9, '1234', '1234', '1234', '', '2017-01-05 21:21:05'),
(10, '256', '256', '256', '', '2017-01-05 21:44:24'),
(11, '2568u5tcv', '256cvbn', '123456789', '', '2017-01-05 21:44:56'),
(12, 'rt@g.com', '12345678', 'rt', '', '2017-01-05 22:01:42'),
(13, 'gbu@gbu.com', '123456789', 'rishabh', '', '2017-01-05 22:03:24'),
(14, 'ry@g.com', '123456789', 'ry', '', '2017-01-05 22:18:27'),
(15, 'fgg@gd.com', 'qwertyuiop', 'yehjnj', '', '2017-01-05 23:31:12'),
(16, 'rajat35@gmail.com', '123456', 'Rajat Saxena', '', '2017-01-06 04:18:17'),
(17, 'yoyo@gmail.com', '1234512345', 'yoyo', '', '2017-03-16 14:56:20'),
(18, 'emma@watson.com', '$2a$10$65909242ed791c3dd9b48uGqYEZcDUlS/UKzCziVfKPMa/HjnjCVO', 'Emma Watson', '', '2017-04-02 04:35:37'),
(19, 'ff@s.d', '$2a$10$7e88bff7254e9f3a828aau0Soil2rSaqqli299Wps8d7duujMA7lW', 'Rajat', '', '2017-04-03 14:50:21'),
(25, 'g@g.g', '$2a$10$ee793b0b1d3849ad0a20fuL0xksoLOq64C4DJOD3hySlRBMv2BpdW', 'raga', '', '2017-04-09 04:39:50'),
(24, 'gsg@ss.d', '$2a$10$15dd8beb59885d5363e38uqCRFfntJOk0kf7Qed249MGdhMCh0VGa', 'raga', '', '2017-04-09 04:39:29');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
