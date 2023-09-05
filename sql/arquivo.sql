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
CREATE INDEX PK_exp ON expedientes (numExpediente, tipo, anio, juzgado);
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES ('1', 'Oral','2009', '100', 'A-1-2', '', '', 'Instrucción', 'Archivo general', '30', 'Disponible');
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES ('2', 'Oral','2010', '101', 'B-1-3', '', '', 'Guardia', 'Archivo general', '33', 'Expurgado');
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado)
VALUES ('3', 'Oral','2011', '102', 'C-2-2', '', '', 'Penal', 'Archivo general', '22', 'Transferido');

CREATE TABLE `solicitantes` (
  `idEmpleado`int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO solicitantes (nombre, apellidos, cargo) VALUES ('diego', 'perez', 'Secretario de juez');
INSERT INTO solicitantes (nombre, apellidos, cargo) VALUES ('marta', 'garcia', 'Fiscal');
INSERT INTO solicitantes (nombre, apellidos, cargo) VALUES ('ignacio', 'sanchez', 'juez');

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
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2009', 'Oral', '70', 'A-1-2-4');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2010', 'Oral', '67', 'B-1-3-4');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2011', 'Oral', '78', 'C-2-2-3');


-- expedientes
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas, estado) 
VALUES
    (4, 'Escrito', 2012, 2022, 'N-2-3-4', 'Algunas notas', 'Tomos 1-3', 'Civil', 'Archivo general', 10, 'Transferido'),
    (5, 'Escrito', 2013, 2022, 'N-2-3-4', 'Notas importantes', 'Tomos 2-4', 'Penal', 'Archivo general', 10, 'Transferido'),
    (6, 'Escrito', 2014, 2022, 'N-2-3-4', 'Sin notas', '', 'Instrucción', 'Archivo general', 10, 'Disponible'),
    (7, 'Oral', 2015, 2023, 'O-1-4-3', '', '', 'Guardia', 'Archivo general', 10, 'Disponible'),
    (8, 'Escrito', 2016, 2023, 'O-1-4-3', 'Notas detalladas', 'Tomos 3-6', 'Civil', 'Archivo general', 10, 'Disponible'),
    (9, 'Escrito', 2017, 2023, 'O-1-4-3', 'Notas importantes', 'Tomos 2-5', 'Penal', 'Archivo general', 10, 'Disponible'),
    (10, 'Oral', 2018, 2023, 'O-1-4-3', 'Algunas notas', 'Tomos 1-2', 'Instrucción', 'Archivo general', 10, 'Disponible'),
    (11, 'Escrito', 2019, 2024, 'P-3-2-1', '', '', 'Guardia', 'Archivo general', 10, 'Disponible'),
    (12, 'Escrito', 2020, 2024, 'P-3-2-1', 'Sin notas', 'Tomos 1-4', 'Civil', 'Archivo general', 10, 'Expurgado'),
    (13, 'Escrito', 2021, 2024, 'P-3-2-1', 'Notas detalladas', 'Tomos 2-3', 'Penal', 'Archivo general', 10, 'Expurgado');



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
    (1, 4, 'Escrito', 2012, 'Civil', '2023-07-25', '2023-08-10'),
    (1, 5, 'Escrito', 2013, 'Penal', '2023-08-05', ''),
    (1, 6, 'Escrito', 2014, 'Instrucción', '2023-07-20', '2023-08-04'),
    (1, 7, 'Oral', 2015, 'Guardia', '2023-08-01', ''),
    (3, 8, 'Escrito', 2016, 'Civil', '2023-07-30', '2023-08-15'),
    (4, 9, 'Escrito', 2017, 'Penal', '2023-07-15', ''),
    (5, 10, 'Oral', 2018, 'Instrucción', '2023-08-02', '2023-08-17'),
    (5, 11, 'Escrito', 2019, 'Guardia', '2023-08-10', ''),
    (2, 12, 'Escrito', 2020, 'Civil', '2023-07-22', '2023-08-07'),
    (6, 13, 'Escrito', 2021, 'Penal', '2023-07-18', '');


-- historicaExpurgo

INSERT INTO historicaExpurgo (numExpediente, tipo, anio, juzgado, fechaHito) 
VALUES
    (12, 'Escrito', 2020, 'Civil', '2014-04-30'),
    (13, 'Escrito', 2021, 'Penal', '2013-03-31');



-- historicaTransferencia


INSERT INTO historicaTransferencia (numExpediente, tipo, anio, juzgado, fechaHito) 
VALUES
    (4, 'Escrito', 2012, 'Civil', '2023-02-15'),
    (5, 'Escrito', 2013, 'Penal', '2023-03-20');

-- cajas

INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES
    (2022, 'Escrito', 30, 'N-2-3-4'),
    (2023, 'Escrito', 40, 'O-1-4-3'),
    (2024, 'Escrito', 30, 'P-3-2-1');

