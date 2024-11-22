-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-11-2024 a las 14:18:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gigagains`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `circuitos`
--

CREATE TABLE `circuitos` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `circuitos`
--

INSERT INTO `circuitos` (`ID`, `nombre`, `Estado`) VALUES
(1, 'Dia 1', 1),
(2, 'Dia 2', 1),
(3, 'Día 3', 1),
(4, 'Día 4', 1),
(5, 'Día 5', 0),
(6, 'Día 6', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `circuitos_ejercicios`
--

CREATE TABLE `circuitos_ejercicios` (
  `ID` int(11) NOT NULL,
  `circuito_id` int(11) NOT NULL,
  `ejercicio_id` int(11) NOT NULL,
  `series` varchar(10) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `circuitos_ejercicios`
--

INSERT INTO `circuitos_ejercicios` (`ID`, `circuito_id`, `ejercicio_id`, `series`, `Estado`) VALUES
(1, 1, 1, '8x3', 1),
(2, 2, 2, '10x2', 1),
(3, 1, 2, '8x3', 1),
(4, 1, 3, '12x20', 1),
(5, 2, 3, '12x2', 1),
(6, 3, 1, '12x2', 1),
(7, 3, 3, '3x3', 1),
(12, 3, 1, '8x3', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietas`
--

CREATE TABLE `dietas` (
  `ID` int(11) NOT NULL,
  `tipo` varchar(40) NOT NULL,
  `proteinas` decimal(5,2) NOT NULL,
  `carbohidratos` decimal(5,2) NOT NULL,
  `calorias` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dietas`
--

INSERT INTO `dietas` (`ID`, `tipo`, `proteinas`, `carbohidratos`, `calorias`, `Estado`) VALUES
(1, 'Volumen', 2.00, 6.00, 44, 1),
(2, 'Definición ', 2.00, 3.00, 26, 1),
(3, 'Recomposición Muscular', 1.70, 3.50, 34, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicios`
--

CREATE TABLE `ejercicios` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `visual` varchar(255) NOT NULL,
  `grupo_muscular_id` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejercicios`
--

INSERT INTO `ejercicios` (`ID`, `nombre`, `descripcion`, `visual`, `grupo_muscular_id`, `Estado`) VALUES
(1, 'Press banca plana', 'Ejercicio para el pecho', 'https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg', 1, 1),
(2, 'Remo en barra', 'Ejercicio para la espalda', 'https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg', 2, 1),
(3, 'Press Militar', 'Ejercicio para los hombros', 'https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg', 2, 1),
(4, 'Sentadilla Bulgara', 'Ejercicio para las piernas', 'static.strengthlevel.com', 3, 1),
(5, 'xdddd', 'xdxd', 'xd', 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos_musculares`
--

CREATE TABLE `grupos_musculares` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grupos_musculares`
--

INSERT INTO `grupos_musculares` (`ID`, `nombre`, `Estado`) VALUES
(1, 'Pecho Hombro y Triceps', 1),
(2, 'Espalda biceps y antebrazos', 1),
(3, 'Piernas', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `meal_completions`
--

CREATE TABLE `meal_completions` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `meal` enum('desayuno','almuerzo','cena') NOT NULL,
  `last_completed` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `meal_completions`
--

INSERT INTO `meal_completions` (`id`, `usuario_id`, `meal`, `last_completed`) VALUES
(1, 1, 'desayuno', '2024-11-16 14:12:34'),
(2, 1, 'almuerzo', '2024-11-07 13:05:18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `musculos`
--

CREATE TABLE `musculos` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `musculos`
--

INSERT INTO `musculos` (`ID`, `nombre`, `Estado`) VALUES
(1, 'Pecho', 1),
(2, 'Espalda', 1),
(3, 'Hombros', 1),
(4, 'Biceps', 1),
(5, 'Triceps', 1),
(6, 'Antebrazo', 1),
(7, 'hombro posterior', 1),
(8, 'Cuádriceps', 1),
(9, 'Gluteo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `musculos_grupos`
--

CREATE TABLE `musculos_grupos` (
  `ID` int(11) NOT NULL,
  `musculo_id` int(11) NOT NULL,
  `grupo_muscular_id` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `musculos_grupos`
--

INSERT INTO `musculos_grupos` (`ID`, `musculo_id`, `grupo_muscular_id`, `Estado`) VALUES
(1, 1, 1, 1),
(2, 3, 1, 1),
(3, 5, 1, 1),
(4, 2, 2, 1),
(5, 4, 2, 1),
(6, 6, 2, 1),
(7, 8, 3, 1),
(8, 9, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan`
--

CREATE TABLE `plan` (
  `ID` int(11) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `rutina_id` int(11) NOT NULL,
  `dieta_id` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plan`
--

INSERT INTO `plan` (`ID`, `tipo`, `rutina_id`, `dieta_id`, `Estado`) VALUES
(1, 'plan1', 1, 1, 0),
(2, 'plan 2', 1, 2, 0),
(3, 'plan 3', 1, 3, 0),
(4, 'plan 4', 2, 1, 0),
(5, 'plan 5', 2, 2, 0),
(6, 'plan 6', 2, 3, 0),
(7, 'plan 7', 3, 1, 0),
(8, 'plan 8', 3, 2, 0),
(9, 'plan 9', 3, 3, 0),
(10, 'plan1', 1, 1, 1),
(11, 'plan2', 2, 2, 1),
(12, 'plan3', 1, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `ID` int(11) NOT NULL,
  `nombre` enum('usuario','administrador','super administrador') NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`ID`, `nombre`, `Estado`) VALUES
(1, 'usuario', 1),
(2, 'administrador', 1),
(3, 'super administrador', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutinas`
--

CREATE TABLE `rutinas` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rutinas`
--

INSERT INTO `rutinas` (`ID`, `nombre`, `Estado`) VALUES
(1, 'Fuerza', 1),
(2, 'Hipertrofia', 1),
(3, 'Resistencia', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutinas_circuitos`
--

CREATE TABLE `rutinas_circuitos` (
  `ID` int(11) NOT NULL,
  `rutina_id` int(11) NOT NULL,
  `circuito_id` int(11) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rutinas_circuitos`
--

INSERT INTO `rutinas_circuitos` (`ID`, `rutina_id`, `circuito_id`, `Estado`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 1, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `fecha_de_nacimiento` date NOT NULL,
  `fecha_de_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `genero` enum('Masculino','Femenino','Otro') NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `estatura` decimal(5,2) NOT NULL,
  `peso` decimal(5,2) NOT NULL,
  `condicion_especial` varchar(255) DEFAULT NULL,
  `id_plan` int(11) DEFAULT 1,
  `rol_id` int(11) DEFAULT 1,
  `Estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID`, `nombre`, `email`, `fecha_de_nacimiento`, `fecha_de_registro`, `genero`, `contrasena`, `estatura`, `peso`, `condicion_especial`, `id_plan`, `rol_id`, `Estado`) VALUES
(1, 'David Alfredo', 'david@gmail.com', '2006-07-07', '2024-10-30 15:01:39', 'Masculino', '$2y$10$wafSeIQIUH0uPoA8Kii3hO6sZ40z7cRt/9ClreAfUza5ETBUnnq1C', 178.00, 80.00, 'ninguna', 1, 3, 1),
(2, 'Zannian', 'zannian@gmail.com', '2005-07-07', '2024-10-30 15:01:04', 'Masculino', '$2y$10$QXldkRJ9zkahwa3Y2pm3.OvqU9P5ypsI7pNHAceZtuzIl29/iETfy', 178.00, 80.00, 'ninguna', 10, 2, 1),
(4, 'Pablo', 'pablo@gmail.com', '2006-07-07', '2024-11-03 16:11:26', 'Masculino', '$2y$10$GNyeiyq9kKBKgjw7R1sAh.lEiykrUtTr.IGZaKb4oq3JZ9n3649oq', 178.00, 78.00, 'ninguna', 2, 1, 1),
(6, 'Negronda', 'juan@gmail.com', '2006-01-01', '2024-11-06 12:03:29', 'Masculino', '$2y$10$D96EH/TJJusRnsSpRMgIluYV2pzDiqrHK76khX9xrAx9d2mMNSRDi', 178.00, 72.00, 'Ninguna', 12, 1, 1),
(7, 'Victor', 'victor@gmail.com', '2005-10-11', '2024-11-07 11:41:12', 'Otro', '$2y$10$E3um/pSOkv.9RrFMakzjC.CuVs3M89IsKtf33VfZEId.XEAjvM2hy', 165.00, 66.00, 'NInguna', 1, 1, 0),
(8, 'Pablo', 'pabliño@gmail.com', '2005-07-07', '2024-11-16 12:57:18', 'Masculino', '$2y$10$ster4sgz1/mxjw.B1K9bYeh4BK33.Ie5RmyipgI/VEsOFIMln3p..', 144.00, 77.00, 'ninguna', 1, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `circuitos`
--
ALTER TABLE `circuitos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `circuitos_ejercicios`
--
ALTER TABLE `circuitos_ejercicios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `circuito_id` (`circuito_id`),
  ADD KEY `ejercicio_id` (`ejercicio_id`);

--
-- Indices de la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `ejercicios`
--
ALTER TABLE `ejercicios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `grupo_muscular_id` (`grupo_muscular_id`);

--
-- Indices de la tabla `grupos_musculares`
--
ALTER TABLE `grupos_musculares`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `meal_completions`
--
ALTER TABLE `meal_completions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario_id` (`usuario_id`,`meal`);

--
-- Indices de la tabla `musculos`
--
ALTER TABLE `musculos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `musculos_grupos`
--
ALTER TABLE `musculos_grupos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `musculo_id` (`musculo_id`),
  ADD KEY `grupo_muscular_id` (`grupo_muscular_id`);

--
-- Indices de la tabla `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `rutina_id` (`rutina_id`),
  ADD KEY `dieta_id` (`dieta_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `rutinas`
--
ALTER TABLE `rutinas`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `rutinas_circuitos`
--
ALTER TABLE `rutinas_circuitos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `rutina_id` (`rutina_id`),
  ADD KEY `circuito_id` (`circuito_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_plan` (`id_plan`),
  ADD KEY `usuario_ibfk_rol` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `circuitos`
--
ALTER TABLE `circuitos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `circuitos_ejercicios`
--
ALTER TABLE `circuitos_ejercicios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `dietas`
--
ALTER TABLE `dietas`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ejercicios`
--
ALTER TABLE `ejercicios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `grupos_musculares`
--
ALTER TABLE `grupos_musculares`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `meal_completions`
--
ALTER TABLE `meal_completions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `musculos`
--
ALTER TABLE `musculos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `musculos_grupos`
--
ALTER TABLE `musculos_grupos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `plan`
--
ALTER TABLE `plan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rutinas`
--
ALTER TABLE `rutinas`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `rutinas_circuitos`
--
ALTER TABLE `rutinas_circuitos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `circuitos_ejercicios`
--
ALTER TABLE `circuitos_ejercicios`
  ADD CONSTRAINT `circuitos_ejercicios_ibfk_1` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `circuitos_ejercicios_ibfk_2` FOREIGN KEY (`ejercicio_id`) REFERENCES `ejercicios` (`ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `ejercicios`
--
ALTER TABLE `ejercicios`
  ADD CONSTRAINT `ejercicios_ibfk_1` FOREIGN KEY (`grupo_muscular_id`) REFERENCES `grupos_musculares` (`ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `meal_completions`
--
ALTER TABLE `meal_completions`
  ADD CONSTRAINT `meal_completions_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `musculos_grupos`
--
ALTER TABLE `musculos_grupos`
  ADD CONSTRAINT `musculos_grupos_ibfk_1` FOREIGN KEY (`musculo_id`) REFERENCES `musculos` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `musculos_grupos_ibfk_2` FOREIGN KEY (`grupo_muscular_id`) REFERENCES `grupos_musculares` (`ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`rutina_id`) REFERENCES `rutinas` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `plan_ibfk_2` FOREIGN KEY (`dieta_id`) REFERENCES `dietas` (`ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `rutinas_circuitos`
--
ALTER TABLE `rutinas_circuitos`
  ADD CONSTRAINT `rutinas_circuitos_ibfk_1` FOREIGN KEY (`rutina_id`) REFERENCES `rutinas` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `rutinas_circuitos_ibfk_2` FOREIGN KEY (`circuito_id`) REFERENCES `circuitos` (`ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`ID`) ON DELETE SET NULL,
  ADD CONSTRAINT `usuario_ibfk_rol` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`ID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
