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
   FOREIGN KEY (numExpediente) REFERENCES expedientes(numExpediente),
   FOREIGN KEY (tipo) REFERENCES expedientes(tipo),
   FOREIGN KEY (anio) REFERENCES expedientes(anio),
   FOREIGN KEY (juzgado) REFERENCES expedientes(juzgado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `historica` (
  `numExpediente` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `anio` int NOT NULL,
  `juzgado` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `fechaHito` varchar(45) NOT NULL,
   FOREIGN KEY (numExpediente) REFERENCES expedientes(numExpediente),
   FOREIGN KEY (tipo) REFERENCES expedientes(tipo),
   FOREIGN KEY (anio) REFERENCES expedientes(anio),
   FOREIGN KEY (juzgado) REFERENCES expedientes(juzgado)
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
VALUES ('2009', 'oral', '70', 'A-1-2');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2010', 'oral', '67', 'B-1-3');
INSERT INTO cajas (anio, tipo, paginas, ubicacion) 
VALUES ('2011', 'oral', '78', 'C-2-2');