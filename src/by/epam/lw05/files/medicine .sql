-- phpMyAdmin SQL Dump
-- version 4.1.9
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Май 15 2014 г., 23:15
-- Версия сервера: 5.6.16-log
-- Версия PHP: 5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `medicine`
--

-- --------------------------------------------------------

--
-- Структура таблицы `analog`
--

CREATE TABLE IF NOT EXISTS `analog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `analog_medicine_id` bigint(20) NOT NULL,
  `medicine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_analog_id` (`id`),
  KEY `analog_medicine_id` (`analog_medicine_id`),
  KEY `medicine_id` (`medicine_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `analog`
--

INSERT INTO `analog` (`id`, `analog_medicine_id`, `medicine_id`) VALUES
(1, 9, 10),
(2, 9, 11),
(3, 1, 2),
(4, 1, 3),
(5, 1, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `consistence`
--

CREATE TABLE IF NOT EXISTS `consistence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_consistence_id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `consistence`
--

INSERT INTO `consistence` (`id`, `name`) VALUES
(1, 'pill'),
(2, 'capsule'),
(3, 'drops'),
(4, 'ointment'),
(5, 'syrup');

-- --------------------------------------------------------

--
-- Структура таблицы `dosage`
--

CREATE TABLE IF NOT EXISTS `dosage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` bigint(20) DEFAULT NULL,
  `is_after_food` tinyint(1) DEFAULT NULL,
  `frequency_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_dosage_id` (`id`),
  KEY `frequency_id` (`frequency_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=9 ;

--
-- Дамп данных таблицы `dosage`
--

INSERT INTO `dosage` (`id`, `number`, `is_after_food`, `frequency_id`) VALUES
(1, 3, 0, 1),
(2, 15, 0, 3),
(3, 1, 1, 1),
(4, 2, 0, 1),
(5, 4, 1, 2),
(6, 3, 0, 2),
(7, 20, 1, 3),
(8, 5, 0, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `frequency`
--

CREATE TABLE IF NOT EXISTS `frequency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_frequency_id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `frequency`
--

INSERT INTO `frequency` (`id`, `name`) VALUES
(1, 'perDay'),
(2, 'perWeek'),
(3, 'perMonth');

-- --------------------------------------------------------

--
-- Структура таблицы `medicine`
--

CREATE TABLE IF NOT EXISTS `medicine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `company` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `groupp` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `consistence_id` bigint(20) DEFAULT NULL,
  `dosage_id` bigint(20) DEFAULT NULL,
  `package_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_medicine_id` (`id`),
  UNIQUE KEY `UQ_medicine_name` (`name`),
  KEY `consistence_id` (`consistence_id`),
  KEY `dosage_id` (`dosage_id`),
  KEY `package_id` (`package_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=14 ;

--
-- Дамп данных таблицы `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `company`, `groupp`, `consistence_id`, `dosage_id`, `package_id`) VALUES
(1, 'azaktam', 'bayer', 'antibiotiks', 1, 2, 3),
(2, 'iruksol', 'Abbott Laboratories', 'antibiotiks', 1, 1, 1),
(3, 'claritromycinum', 'Abbott Laboratories', 'antibiotiks', 1, 2, 4),
(4, 'ciprofloksacin', 'bayer', 'antibiotiks', 1, 2, 3),
(5, 'cefotaksim', 'Faran Laboratories', 'antibiotiks', 1, 1, 3),
(6, 'pipperacylinum', 'Italfarmaco', 'antibiotiks', 1, 2, 3),
(7, 'nemozol', 'Italfarmaco', 'antihelmintic', 4, 1, 1),
(8, 'pirantel', 'Italfarmaco', 'antihelmintic', 4, 2, 3),
(9, 'acyclovir', 'Italfarmaco', 'antiviral', 2, 6, 3),
(10, 'zovyraks', 'Italfarmaco', 'antiviral', 2, 3, 3),
(11, 'arbydol', 'Italfarmaco', 'antiviral', 2, 6, 3),
(12, 'acece', 'Italfarmaco', 'antiviral', 5, 3, 3),
(13, 'vyzomitin', 'Italfarmaco', 'antiviral', 3, 8, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `package`
--

CREATE TABLE IF NOT EXISTS `package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `price` float DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_package_id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `package`
--

INSERT INTO `package` (`id`, `type`, `price`, `number`) VALUES
(1, 'box', 40, 10),
(2, 'box', 65, 15),
(3, 'box', 100, 5),
(4, 'box', 5, 10),
(5, 'box', 18, 8),
(6, 'box', 48, 30);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `analog`
--
ALTER TABLE `analog`
  ADD CONSTRAINT `FK_analog_medicine` FOREIGN KEY (`analog_medicine_id`) REFERENCES `medicine` (`id`),
  ADD CONSTRAINT `FK_analog_medicine2` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`);

--
-- Ограничения внешнего ключа таблицы `dosage`
--
ALTER TABLE `dosage`
  ADD CONSTRAINT `FK_dosage_frequency` FOREIGN KEY (`frequency_id`) REFERENCES `frequency` (`id`);

--
-- Ограничения внешнего ключа таблицы `medicine`
--
ALTER TABLE `medicine`
  ADD CONSTRAINT `FK_medicine_consistence` FOREIGN KEY (`consistence_id`) REFERENCES `consistence` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_medicine_dosage` FOREIGN KEY (`dosage_id`) REFERENCES `dosage` (`id`),
  ADD CONSTRAINT `FK_medicine_package` FOREIGN KEY (`package_id`) REFERENCES `package` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
