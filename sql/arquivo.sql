CREATE DATABASE arquivo;
use arquivo;

CREATE TABLE `expedientes` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `caja` int NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  `notas` varchar(45) DEFAULT '',
  `tomos` varchar(45) DEFAULT '',
  `juzgado` varchar(45) NOT NULL,
  `lugar` varchar(45) NOT NULL,
  `paginas` int NOT NULL,
  `estado` varchar(45) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `solicitantes` (
  `idEmpleado`int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `prestamos` (
  `idSolicitante` INT NOT NULL,
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `juzgado` varchar(45) NOT NULL,
  `fechaPrestamo` varchar(45) NOT NULL,
  `fechaDevolucion` varchar(45) DEFAULT '',
   FOREIGN KEY (idSolicitante) REFERENCES solicitantes(idEmpleado),
   FOREIGN KEY (numExpediente, tipo, anio, juzgado) REFERENCES expedientes(numExpediente, tipo, anio, juzgado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `historicaExpurgo` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `juzgado` varchar(45) NOT NULL,
  `fechaHito` varchar(45) NOT NULL,
   FOREIGN KEY (numExpediente, tipo, anio, juzgado) REFERENCES expedientes(numExpediente, tipo, anio, juzgado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `historicaTransferencia` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `juzgado` varchar(45) NOT NULL,
  `fechaHito` varchar(45) NOT NULL,
  FOREIGN KEY (numExpediente, tipo, anio, juzgado) REFERENCES expedientes(numExpediente, tipo, anio, juzgado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cajas` (
  `idCaja` int NOT NULL AUTO_INCREMENT,
  `anio` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `paginas` int NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  PRIMARY KEY (`idcaja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- expedientes
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES
    (4, 'Escrito', 2012, 2, 'N-2-3-4', 'Algunas notas', 'Tomos 1-3', 'Civil', 'Archivo Transferido', 10, 'transferido'),
    (5, 'Escrito', 2013, 2, 'N-2-3-4', 'Notas importantes', 'Tomos 2-4', 'Penal', 'Archivo Transferido', 10, 'transferido'),
    (6, 'Escrito', 2014, 2, 'N-2-3-4', 'Escrito', '', 'Instrucción', 'Archivo', 10, 'disponible'),
    (7, 'Oral', 2015, 3, 'O-1-4-3', '', '', 'Escrito', 'Archivo', 10, 'prestado'),
    (8, 'Escrito', 2016, 3, 'O-1-4-3', 'Escrito', 'Tomos 3-6', 'Civil', 'Archivo', 10, 'disponible'),
    (9, 'Escrito', 2017, 3, 'O-1-4-3', 'Escrito', 'Tomos 2-5', 'Penal', 'Archivo', 10, 'prestado'),
    (10, 'Oral', 2018, 3, 'O-1-4-3', 'Escrito', 'Tomos 1-2', 'Instrucción', 'Archivo', 10, 'disponible'),
    (11, 'Escrito', 2019, 1, 'P-3-2-1', 'Escrito', '', 'Guardia', 'Archivo', 10, 'prestado'),
    (12, 'Escrito', 2020, 1, 'P-3-2-1', 'Sin notas', 'Tomos 1-4', 'Civil', 'Archivo', 10, 'expurgado'),
    (13, 'Escrito', 2021, 1, 'P-3-2-1', 'Notas detalladas', 'Tomos 2-3', 'Penal', 'Archivo', 10, 'expurgado');

INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES
    (14, 'Escrito', 2022, 4, 'A-1-2-1', 'Algunas notas', 'Tomos 2-4', 'Civil', 'Archivo', 10, 'disponible'),
    (15, 'Oral', 2023, 5, 'B-2-3-1', 'Algunas notas', 'Tomos 1-3', 'Penal', 'Archivo', 10, 'prestado'),
    (16, 'Escrito', 2024, 6, 'C-1-1-2', 'Algunas notas', 'Tomos 3-6', 'Instrucción', 'Archivo', 10, 'prestado'),
    (17, 'Oral', 2022, 7, 'D-3-2-1', 'Algunas notas', '', 'Familia', 'Archivo', 10, 'prestado'),
    (18, 'Escrito', 2023, 8, 'E-1-2-2', 'Algunas notas', 'Tomos 1-2', 'Mercantil', 'Archivo', 10, 'disponible'),
    (19, 'Oral', 2024, 9, 'F-2-2-3', 'Algunas notas', 'Tomos 1-4', 'Civil', 'Archivo', 10, 'disponible'),
    (20, 'Escrito', 2022, 10, 'G-3-3-3', 'Algunas notas', 'Tomos 2-5', 'Penal', 'Archivo', 10, 'disponible'),
    (21, 'Oral', 2023, 11, 'H-1-3-3', 'Algunas notas', '', 'Laboral', 'Archivo', 10, 'disponible'),
    (22, 'Escrito', 2024, 12, 'I-2-3-4', 'Algunas notas', 'Tomos 3-6', 'Civil', 'Archivo', 10, 'disponible'),
    (23, 'Oral', 2022, 13, 'J-3-4-4', 'Algunas notas', '', 'Familia', 'Archivo', 10, 'prestado');



-- solicitantes
INSERT INTO solicitantes (nombre, apellidos, cargo) 
VALUES
    ('Ana', 'Rodríguez', 'Secretario de juez'),
    ('Carlos', 'López', 'Fiscal'),
    ('Luisa', 'Martínez', 'Juez'),
    ('Raúl', 'Santos', 'Secretario de juez'),
    ('Carmen', 'Pérez', 'Fiscal'),
    ('Javier', 'González', 'Juez'),
    ('Patricia', 'Ramírez', 'Secretario de juez'),
    ('Lucía', 'Fernandez', 'Fiscal'),
    ('Roberto', 'Sánchez', 'Juez'),
    ('Laura', 'Gómez', 'Secretario de juez');


-- prestamos
INSERT INTO prestamos (idSolicitante, numExpediente, tipo, anio, juzgado, fechaPrestamo, fechaDevolucion) 
VALUES
    (1, 6, 'Escrito', 2014, 'Instrucción', '2023-07-20', '2023-08-04'),
    (1, 7, 'Oral', 2015, 'Guardia', '2023-08-01', ''),
    (3, 8, 'Escrito', 2016, 'Civil', '2023-07-30', '2023-08-15'),
    (4, 9, 'Escrito', 2017, 'Penal', '2023-07-15', ''),
    (5, 10, 'Oral', 2018, 'Instrucción', '2023-08-02', '2023-08-17'),
    (5, 11, 'Escrito', 2019, 'Guardia', '2023-08-10', '');
INSERT INTO prestamos (idSolicitante, numExpediente, tipo, anio, juzgado, fechaPrestamo, fechaDevolucion) 
VALUES
    (5, 14, 'Escrito', 2022, 'Civil', '2023-07-01', '2023-07-15'),
    (6, 15, 'Oral', 2023, 'Penal', '2023-07-15', ''),
    (6, 16, 'Escrito', 2024, 'Instrucción', '2023-07-30', ''),
    (6, 17, 'Oral', 2022, 'Familia', '2023-08-01', ''),
    (6, 18, 'Escrito', 2023, 'Mercantil', '2023-08-10', '2023-08-25'),
    (7, 19, 'Oral', 2024, 'Civil', '2023-08-15', '2023-08-30'),
    (8, 20, 'Escrito', 2022, 'Penal', '2023-08-20', '2023-09-04'),
    (8, 21, 'Oral', 2023, 'Laboral', '2023-08-25', '2023-09-09'),
    (10, 22, 'Escrito', 2024, 'Civil', '2023-08-30', '2023-09-15'),
    (10, 23, 'Oral', 2022, 'Familia', '2023-09-04', '');


-- historicaExpurgo

INSERT INTO historicaExpurgo (numExpediente, tipo, anio, juzgado, fechaHito) 
VALUES
    (12, 'Algunas notas', 2020, 'Civil', '2014-04-30'),
    (13, 'Notas detalladas', 2021, 'Penal', '2013-03-31');


-- historicaTransferencia


INSERT INTO historicaTransferencia (numExpediente, tipo, anio, juzgado, fechaHito) 
VALUES
    (4, 'Algunas notas', 2012, 'Civil', '2023-02-15'),
    (5, 'Notas importantes', 2013, 'Penal', '2023-03-20');


-- cajas

INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES
    (2017, 'Escrito', 30, 'N-2-3-4'),
    (2018, 'Escrito', 40, 'O-1-4-3'),
    (2015, 'Escrito', 30, 'P-3-2-1');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES
    (2019, 'Oral', 10, 'A-1-2-1'),
    (2020, 'Oral', 10, 'B-2-3-1'),
    (2021, 'Escrito', 10, 'C-1-1-2'),
    (2019, 'Oral', 10, 'D-3-2-1'),
    (2020, 'Oral', 10, 'E-1-2-2'),
    (2021, 'Escrito', 10, 'F-2-2-3'),
    (2019, 'Oral', 10, 'G-3-3-3'),
    (2020, 'Oral', 10, 'H-1-3-3'),
    (2021, 'Escrito', 10, 'I-2-3-4'),
    (2019, 'Oral', 10, 'J-3-4-4');


