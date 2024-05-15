-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-05-2024 a las 21:27:32
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `secretaria_salud`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expedientes`
--

CREATE TABLE `expedientes` (
  `id_expediente` int(11) NOT NULL,
  `tipo_sangre` varchar(5) NOT NULL,
  `estatura` varchar(10) NOT NULL,
  `peso` float NOT NULL,
  `alergias` varchar(200) NOT NULL,
  `frecuencia_cardiaca` varchar(50) NOT NULL,
  `padecimientos_personales` varchar(500) NOT NULL,
  `antecedentes_hereditarios` varchar(500) NOT NULL,
  `nombre_contacto_emergencia` varchar(50) NOT NULL,
  `telefono_contacto_emergencia` varchar(20) NOT NULL,
  `id_paciente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `expedientes`
--

INSERT INTO `expedientes` (`id_expediente`, `tipo_sangre`, `estatura`, `peso`, `alergias`, `frecuencia_cardiaca`, `padecimientos_personales`, `antecedentes_hereditarios`, `nombre_contacto_emergencia`, `telefono_contacto_emergencia`, `id_paciente`) VALUES
(4, 'A-', '1.90', 75, 'no', '98', 'Hernia hiatal', 'Abuela con cancer de mama', 'Magda Ramirez Escalante', '6688293570', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

CREATE TABLE `medicos` (
  `id_medico` int(11) NOT NULL,
  `nombres` varchar(300) NOT NULL,
  `apellidopaterno` varchar(150) NOT NULL,
  `apellidomaterno` varchar(150) NOT NULL,
  `correo` varchar(300) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `cedula_profesional` varchar(8) NOT NULL,
  `especialidad_medica` varchar(200) NOT NULL,
  `lugar_trabajo_actual` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicos`
--

INSERT INTO `medicos` (`id_medico`, `nombres`, `apellidopaterno`, `apellidomaterno`, `correo`, `password`, `fecha_nacimiento`, `telefono`, `genero`, `cedula_profesional`, `especialidad_medica`, `lugar_trabajo_actual`) VALUES
(1, 'Alfredo', 'Lopez', 'Lugardo', 'alfredolopez@gmail.com', 'Alfredo123', '1990-08-15', '6442873971', 'MASCULINO', '17652833', 'Gastroenterologia', 'ISSSTESON');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id_paciente` int(11) NOT NULL,
  `nombres` varchar(300) NOT NULL,
  `apellidopaterno` varchar(150) NOT NULL,
  `apellidomaterno` varchar(150) NOT NULL,
  `correo` varchar(300) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `estado_civil` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `id_tutor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id_paciente`, `nombres`, `apellidopaterno`, `apellidomaterno`, `correo`, `password`, `fecha_nacimiento`, `telefono`, `estado_civil`, `genero`, `id_tutor`) VALUES
(7, 'Misael', 'Marchena', 'Perez', 'misaelmarchenaperez@gmail.com', 'jordan02MAR', '2003-05-27', '6442322888', 'SOLTERO', 'MASCULINO', NULL),
(8, 'Amy Marsella', 'Marchena', 'Perez', 'amy@gmail.com', 'jordan02MAR', '2011-07-27', '6441324532', 'SOLTERO', 'FEMENINO', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutores`
--

CREATE TABLE `tutores` (
  `id_tutor` int(11) NOT NULL,
  `nombres` varchar(300) NOT NULL,
  `apellidopaterno` varchar(150) NOT NULL,
  `apellidomaterno` varchar(150) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `parentesco` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tutores`
--

INSERT INTO `tutores` (`id_tutor`, `nombres`, `apellidopaterno`, `apellidomaterno`, `fecha_nacimiento`, `telefono`, `genero`, `parentesco`) VALUES
(11, 'Marcela Arely', 'Perez', 'Valenzuela', '1980-10-21', '6441523557', 'FEMENINO', 'Madre');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `expedientes`
--
ALTER TABLE `expedientes`
  ADD PRIMARY KEY (`id_expediente`),
  ADD KEY `id_paciente` (`id_paciente`);

--
-- Indices de la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`id_medico`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id_paciente`),
  ADD KEY `id_tutor` (`id_tutor`);

--
-- Indices de la tabla `tutores`
--
ALTER TABLE `tutores`
  ADD PRIMARY KEY (`id_tutor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `expedientes`
--
ALTER TABLE `expedientes`
  MODIFY `id_expediente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `medicos`
--
ALTER TABLE `medicos`
  MODIFY `id_medico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id_paciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tutores`
--
ALTER TABLE `tutores`
  MODIFY `id_tutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
