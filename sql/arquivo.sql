use arquivo;

CREATE TABLE `arquivo.expedientes` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `caja` int NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  `notas` varchar(45) DEFAULT NULL,
  `tomos` varchar(45) DEFAULT NULL,
  `juzgado` varchar(45) NOT NULL,
  `lugar` varchar(45) NOT NULL,
  `paginas` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas) 
VALUES ('1', 'oral','2009', '100', 'A-1-2', null, null, 'instruccion', 'archivo general', '30');
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas) 
VALUES ('2', 'oral','2010', '101', 'B-1-3', null, null, 'guardia', 'archivo general', '33');
INSERT INTO expedientes (numExpediente, tipo, anio, caja, ubicacion, notas, tomos, juzgado, lugar, paginas) 
VALUES ('3', 'oral','2011', '102', 'C-2-2', null, null, 'penal', 'archivo general', '22');

CREATE TABLE `arquivo.prestamos` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `juzgado` varchar(45) NOT NULL,
  `fechaPrestamo` date NOT NULL,
  `solicitante` VARCHAR(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `arquivo.cajas` (
  `idCaja` int NOT NULL,
  `anio` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `paginas` int NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  PRIMARY KEY (`idcaja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO cajas (idcaja, anio, tipo, paginas, ubicacion) 
VALUES ('100', '2009', 'oral', '70', 'A-1-2');
INSERT INTO cajas (idcaja, anio, tipo, paginas, ubicacion) 
VALUES ('101', '2010', 'oral', '67', 'B-1-3');
INSERT INTO cajas (idcaja, anio, tipo, paginas, ubicacion) 
VALUES ('102', '2011', 'oral', '78', 'C-2-2');