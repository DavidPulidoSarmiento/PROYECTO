-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 05, 2024 at 04:42 PM
-- Server version: 8.0.39
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gigagains`
--

-- --------------------------------------------------------

--
-- Table structure for table `circuitos`
--

CREATE TABLE `circuitos` (
  `ID` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `circuitos`
--

INSERT INTO `circuitos` (`ID`, `nombre`) VALUES
(1, 'Dia 1'),
(2, 'Dia 2'),
(3, 'Día 3'),
(4, 'Día 4'),
(5, 'Día 5');

-- --------------------------------------------------------

--
-- Table structure for table `circuitos_ejercicios`
--

CREATE TABLE `circuitos_ejercicios` (
  `ID` int NOT NULL,
  `circuito_id` int NOT NULL,
  `ejercicio_id` int NOT NULL,
  `series` varchar(10) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `circuitos_ejercicios`
--

INSERT INTO `circuitos_ejercicios` (`ID`, `circuito_id`, `ejercicio_id`, `series`) VALUES
(1, 1, 1, '8x3'),
(2, 2, 2, '10x2'),
(3, 1, 2, '8x3'),
(4, 1, 3, '12x2');

-- --------------------------------------------------------

--
-- Table structure for table `dietas`
--

CREATE TABLE `dietas` (
  `ID` int NOT NULL,
  `tipo` enum('V','D','R') COLLATE utf8mb4_general_ci NOT NULL,
  `proteinas` decimal(5,2) NOT NULL,
  `carbohidratos` decimal(5,2) NOT NULL,
  `calorias` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dietas`
--

INSERT INTO `dietas` (`ID`, `tipo`, `proteinas`, `carbohidratos`, `calorias`) VALUES
(1, 'V', '2.00', '1.00', 200),
(2, 'D', '3.00', '1.00', 150),
(3, 'R', '2.00', '1.50', 150);

-- --------------------------------------------------------

--
-- Table structure for table `ejercicios`
--

CREATE TABLE `ejercicios` (
  `ID` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_general_ci,
  `visual` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `grupo_muscular_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ejercicios`
--

INSERT INTO `ejercicios` (`ID`, `nombre`, `descripcion`, `visual`, `grupo_muscular_id`) VALUES
(1, 'Press banca plana', 'Ejercicio para el pecho', 'https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg', 1),
(2, 'Remo en barra', 'Ejercicio para la espalda', 'https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg', 2),
(3, 'Press Militar', 'Ejercicio para los hombros', 'static.strengthlevel.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `grupos_musculares`
--

CREATE TABLE `grupos_musculares` (
  `ID` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grupos_musculares`
--

INSERT INTO `grupos_musculares` (`ID`, `nombre`) VALUES
(1, 'Pecho Hombro y Triceps'),
(2, 'Espalda biceps y antebrazos');

-- --------------------------------------------------------

--
-- Table structure for table `musculos`
--

CREATE TABLE `musculos` (
  `ID` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `musculos`
--

INSERT INTO `musculos` (`ID`, `nombre`) VALUES
(1, 'Pecho'),
(2, 'Espalda'),
(3, 'Hombros'),
(4, 'Biceps'),
(5, 'Triceps'),
(6, 'Antebrazo'),
(7, 'hombro posterior');

-- --------------------------------------------------------

--
-- Table structure for table `musculos_grupos`
--

CREATE TABLE `musculos_grupos` (
  `ID` int NOT NULL,
  `musculo_id` int NOT NULL,
  `grupo_muscular_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `musculos_grupos`
--

INSERT INTO `musculos_grupos` (`ID`, `musculo_id`, `grupo_muscular_id`) VALUES
(1, 1, 1),
(2, 3, 1),
(3, 5, 1),
(4, 2, 2),
(5, 4, 2),
(6, 6, 2);

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `ID` int NOT NULL,
  `tipo` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `rutina_id` int NOT NULL,
  `dieta_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` (`ID`, `tipo`, `rutina_id`, `dieta_id`) VALUES
(1, 'plan1', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `ID` int NOT NULL,
  `nombre` enum('usuario','administrador','super administrador') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`ID`, `nombre`) VALUES
(1, 'usuario'),
(2, 'administrador'),
(3, 'super administrador');

-- --------------------------------------------------------

--
-- Table structure for table `rutinas`
--

CREATE TABLE `rutinas` (
  `ID` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rutinas`
--

INSERT INTO `rutinas` (`ID`, `nombre`) VALUES
(1, 'Fuerza'),
(2, 'Hipertrofia'),
(3, 'Resistencia');

-- --------------------------------------------------------

--
-- Table structure for table `rutinas_circuitos`
--

CREATE TABLE `rutinas_circuitos` (
  `ID` int NOT NULL,
  `rutina_id` int NOT NULL,
  `circuito_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rutinas_circuitos`
--

INSERT INTO `rutinas_circuitos` (`ID`, `rutina_id`, `circuito_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `ID` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_de_nacimiento` date NOT NULL,
  `fecha_de_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `genero` enum('Masculino','Femenino','Otro') COLLATE utf8mb4_general_ci NOT NULL,
  `contraseña` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `estatura` decimal(5,2) NOT NULL,
  `peso` decimal(5,2) NOT NULL,
  `condicion_especial` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_plan` int DEFAULT NULL,
  `rol_id` int DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`ID`, `nombre`, `email`, `fecha_de_nacimiento`, `fecha_de_registro`, `genero`, `contraseña`, `estatura`, `peso`, `condicion_especial`, `id_plan`, `rol_id`) VALUES
(1, 'David', 'david@gmail.com', '2006-07-07', '2024-10-30 15:01:39', 'Masculino', '$2y$10$wafSeIQIUH0uPoA8Kii3hO6sZ40z7cRt/9ClreAfUza5ETBUnnq1C', '178.00', '80.00', 'ninguna', 1, 3),
(2, 'Zannian', 'zannian@gmail.com', '2005-07-07', '2024-10-30 15:01:04', 'Masculino', '$2y$10$QXldkRJ9zkahwa3Y2pm3.OvqU9P5ypsI7pNHAceZtuzIl29/iETfy', '178.00', '80.00', 'ninguna', 1, 2),
(4, 'Pablo', 'pablo@gmail.com', '2006-07-07', '2024-11-03 16:11:26', 'Masculino', '$2y$10$GNyeiyq9kKBKgjw7R1sAh.lEiykrUtTr.IGZaKb4oq3JZ9n3649oq', '178.00', '78.00', 'ninguna', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `circuitos`
--
ALTER TABLE `circuitos`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `circuitos_ejercicios`
--
ALTER TABLE `circuitos_ejercicios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `circuito_id` (`circuito_id`),
  ADD KEY `ejercicio_id` (`ejercicio_id`);

--
-- Indexes for table `dietas`
--
ALTER TABLE `dietas`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ejercicios`
--
ALTER TABLE `ejercicios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `grupo_muscular_id` (`grupo_muscular_id`);

--
-- Indexes for table `grupos_musculares`
--
ALTER TABLE `grupos_musculares`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `musculos`
--
ALTER TABLE `musculos`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `musculos_grupos`
--
ALTER TABLE `musculos_grupos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `musculo_id` (`musculo_id`),
  ADD KEY `grupo_muscular_id` (`grupo_muscular_id`);

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `rutina_id` (`rutina_id`),
  ADD KEY `dieta_id` (`dieta_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `rutinas`
--
ALTER TABLE `rutinas`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `rutinas_circuitos`
--
ALTER TABLE `rutinas_circuitos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `rutina_id` (`rutina_id`),
  ADD KEY `circuito_id` (`circuito_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_plan` (`id_plan`),
  ADD KEY `usuario_ibfk_rol` (`rol_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `circuitos`
--
ALTER TABLE `circuitos`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `circuitos_ejercicios`
--
ALTER TABLE `circuitos_ejercicios`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `dietas`
--
ALTER TABLE `dietas`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ejercicios`
--
ALTER TABLE `ejercicios`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `grupos_musculares`
--
ALTER TABLE `grupos_musculares`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `musculos`
--
ALTER TABLE `musculos`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `musculos_grupos`
--
ALTER TABLE `musculos_grupos`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `plan`
--
ALTER TABLE `plan`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rutinas`
--
ALTER TABLE `rutinas`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rutinas_circuitos`
--
ALTER TABLE `rutinas_circuitos`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `circuitos_ejercicios`
--
ALTER TABLE `circuitos_ejercicios`
  ADD CONSTRAINT `circuitos_ejercicios_ibfk_1` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `circuitos_ejercicios_ibfk_2` FOREIGN KEY (`ejercicio_id`) REFERENCES `ejercicios` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `ejercicios`
--
ALTER TABLE `ejercicios`
  ADD CONSTRAINT `ejercicios_ibfk_1` FOREIGN KEY (`grupo_muscular_id`) REFERENCES `grupos_musculares` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `musculos_grupos`
--
ALTER TABLE `musculos_grupos`
  ADD CONSTRAINT `musculos_grupos_ibfk_1` FOREIGN KEY (`musculo_id`) REFERENCES `musculos` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `musculos_grupos_ibfk_2` FOREIGN KEY (`grupo_muscular_id`) REFERENCES `grupos_musculares` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`rutina_id`) REFERENCES `rutinas` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `plan_ibfk_2` FOREIGN KEY (`dieta_id`) REFERENCES `dietas` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `rutinas_circuitos`
--
ALTER TABLE `rutinas_circuitos`
  ADD CONSTRAINT `rutinas_circuitos_ibfk_1` FOREIGN KEY (`rutina_id`) REFERENCES `rutinas` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `rutinas_circuitos_ibfk_2` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`ID`) ON DELETE SET NULL,
  ADD CONSTRAINT `usuario_ibfk_rol` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`ID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
