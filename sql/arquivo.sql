use arquivo;

CREATE TABLE `expedientes` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `caja` int NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  `notas` varchar(45) DEFAULT NULL,
  `tomos` varchar(45) DEFAULT NULL,
  `juzgado` varchar(45) NOT NULL,
  `lugar` varchar(45) NOT NULL,
  `paginas` int NOT NULL,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE INDEX PK_exp ON expedientes (numExpediente, tipo, anio, juzgado);
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES ('1', 'oral','2009', '100', 'A-1-2', null, null, 'instruccion', 'archivo general', '30', 'disponible');
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES ('2', 'oral','2010', '101', 'B-1-3', null, null, 'guardia', 'archivo general', '33', 'expurgado');
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado)
VALUES ('3', 'oral','2011', '102', 'C-2-2', null, null, 'penal', 'archivo general', '22', 'transferido');

CREATE TABLE `solicitantes` (
  `idEmpleado`int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `edad` int NOT NULL,
  `cargo` varchar(45) NOT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO solicitantes (nombre, apellidos, edad, cargo) VALUES ('diego', 'perez', 30, 'secretario de juez');
INSERT INTO solicitantes (nombre, apellidos, edad, cargo) VALUES ('marta', 'garcia', 46, 'fiscal');
INSERT INTO solicitantes (nombre, apellidos, edad, cargo) VALUES ('ignacio', 'sanchez', 50, 'juez');

CREATE TABLE `prestamos` (
  `idSolicitante` INT NOT NULL,
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `juzgado` varchar(45) NOT NULL,
  `fechaPrestamo` varchar(45) NOT NULL,
  `fechaDevolucion` varchar(45) DEFAULT NULL,
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
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2009', 'oral', '70', 'A-1-2-4');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2010', 'oral', '67', 'B-1-3-4');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2011', 'oral', '78', 'C-2-2-3');


-- expedientes
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES
    (4, 'escrito', 2012, 2022, 'N-2-3-4', 'Algunas notas', 'Tomos 1-3', 'civil', 'archivo general', 10, 'transferido'),
    (5, 'escrito', 2013, 2022, 'N-2-3-4', 'Notas importantes', 'Tomos 2-4', 'penal', 'archivo general', 10, 'transferido'),
    (6, 'escrito', 2014, 2022, 'N-2-3-4', 'Sin notas', NULL, 'instruccion', 'archivo general', 10, 'disponible'),
    (7, 'oral', 2015, 2023, 'O-1-4-3', NULL, NULL, 'guardia', 'archivo general', 10, 'disponible'),
    (8, 'escrito', 2016, 2023, 'O-1-4-3', 'Notas detalladas', 'Tomos 3-6', 'civil', 'archivo general', 10, 'disponible'),
    (9, 'escrito', 2017, 2023, 'O-1-4-3', 'Notas importantes', 'Tomos 2-5', 'penal', 'archivo general', 10, 'disponible'),
    (10, 'oral', 2018, 2023, 'O-1-4-3', 'Algunas notas', 'Tomos 1-2', 'instruccion', 'archivo general', 10, 'disponible'),
    (11, 'escrito', 2019, 2024, 'P-3-2-1', NULL, NULL, 'guardia', 'archivo general', 10, 'disponible'),
    (12, 'escrito', 2020, 2024, 'P-3-2-1', 'Sin notas', 'Tomos 1-4', 'civil', 'archivo general', 10, 'expurgado'),
    (13, 'escrito', 2021, 2024, 'P-3-2-1', 'Notas detalladas', 'Tomos 2-3', 'penal', 'archivo general', 10, 'expurgado');



-- solicitantes
INSERT INTO solicitantes (nombre, apellidos, edad, cargo) 
VALUES
    ('ana', 'rodriguez', 35, 'secretario de juez'),
    ('carlos', 'lopez', 42, 'fiscal'),
    ('luisa', 'martinez', 48, 'juez'),
    ('raul', '0santos', 38, 'secretario de juez'),
    ('carmen', 'perez', 41, 'fiscal'),
    ('javier', 'gonzalez', 49, 'juez'),
    ('patricia', 'ramirez', 33, 'secretario de juez'),
    ('lucia', 'fernandez', 45, 'fiscal'),
    ('roberto', 'sanchez', 51, 'juez'),
    ('laura', 'gomez', 36, 'secretario de juez');


-- prestamos
INSERT INTO prestamos (idSolicitante, numExpediente, tipo, anio, juzgado, fechaPrestamo, fechaDevolucion) 
VALUES
    (42, 4, 'escrito', 2012, 'civil', '2023-07-25', '2023-08-10'),
    (42, 5, 'escrito', 2013, 'penal', '2023-08-05', NULL),
    (42, 6, 'escrito', 2014, 'instruccion', '2023-07-20', '2023-08-04'),
    (42, 7, 'oral', 2015, 'guardia', '2023-08-01', NULL),
    (48, 8, 'escrito', 2016, 'civil', '2023-07-30', '2023-08-15'),
    (38, 9, 'escrito', 2017, 'penal', '2023-07-15', NULL),
    (33, 10, 'oral', 2018, 'instruccion', '2023-08-02', '2023-08-17'),
    (33, 11, 'escrito', 2019, 'guardia', '2023-08-10', NULL),
    (51, 12, 'escrito', 2020, 'civil', '2023-07-22', '2023-08-07'),
    (36, 13, 'escrito', 2021, 'penal', '2023-07-18', NULL);


--historicaExpurgo

INSERT INTO historicaExpurgo (numExpediente, tipo, anio, juzgado, fechaHito) 
VALUES
    (12, 'escrito', 2020, 'civil', '2014-04-30'),
    (13, 'escrito', 2021, 'penal', '2013-03-31');



--historicaTransferencia


INSERT INTO historicaTransferencia (numExpediente, tipo, anio, juzgado, fechaHito) 
VALUES
    (4, 'escrito', 2012, 'civil', '2023-02-15'),
    (5, 'escrito', 2013, 'penal', '2023-03-20');

--cajas

INSERT INTO cajas (idCaja, anio, tipo, paginas, ubicacion) 
VALUES
    (2022, 2022, 'escrito', 30, 'N-2-3-4'),
    (2023, 2023, 'escrito', 40, 'O-1-4-3'),
    (2024, 2024, 'escrito', 30, 'P-3-2-1');

