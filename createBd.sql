-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Дек 23 2020 г., 10:07
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `BotsCrew`
--

-- --------------------------------------------------------

--
-- Структура таблицы `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `head_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `department`
--

INSERT INTO `department` (`id`, `name`, `head_id`) VALUES
(1, 'Department 1', 1),
(2, 'OON', 1),
(3, 'plo', 2),
(4, 'dasda', 1),
(7, 'NBU', 5),
(9, 'OOM', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `department_lectors`
--

CREATE TABLE `department_lectors` (
  `department_id` bigint(20) NOT NULL,
  `lectors_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `department_lectors`
--

INSERT INTO `department_lectors` (`department_id`, `lectors_id`) VALUES
(4, 1),
(4, 2),
(7, 3),
(7, 4),
(7, 5),
(9, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `lector`
--

CREATE TABLE `lector` (
  `id` bigint(20) NOT NULL,
  `degree` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salary` int(11) NOT NULL,
  `third_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `lector`
--

INSERT INTO `lector` (`id`, `degree`, `first_name`, `last_name`, `salary`, `third_name`) VALUES
(1, 1, 'Artem', 'Ivanov', 15000, 'Petrovich'),
(2, 2, 'Andrey', 'Kuzenkoff', 14000, 'Ivanovich'),
(3, 1, 'Kolya', 'Pupkin', 10000, 'Romanovich'),
(4, 0, 'Jora', 'Belyash', 7000, 'Nikolayevich'),
(5, 1, 'Vladislav', 'Visockii', 9000, 'Petrovich'),
(6, 0, 'Oleksei', 'Petrov', 8000, 'Ivanovich');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdb1vl8mut04igtg6992oob1ce` (`head_id`);

--
-- Индексы таблицы `department_lectors`
--
ALTER TABLE `department_lectors`
  ADD PRIMARY KEY (`department_id`,`lectors_id`),
  ADD UNIQUE KEY `UK_20ngby9dod7hyech7ul2ms2wr` (`lectors_id`);

--
-- Индексы таблицы `lector`
--
ALTER TABLE `lector`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `department`
--
ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `lector`
--
ALTER TABLE `lector`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `FKdb1vl8mut04igtg6992oob1ce` FOREIGN KEY (`head_id`) REFERENCES `lector` (`id`);

--
-- Ограничения внешнего ключа таблицы `department_lectors`
--
ALTER TABLE `department_lectors`
  ADD CONSTRAINT `FKkqgq9pk0xjjb3i2oyw4xlu40m` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKrp3vnn4rpynyn3ao6t5kls5ge` FOREIGN KEY (`lectors_id`) REFERENCES `lector` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;