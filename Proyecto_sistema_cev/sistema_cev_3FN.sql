DROP DATABASE IF EXISTS sistema_cev_3FN;
CREATE DATABASE sistema_cev_3FN;
USE sistema_cev_3FN;

/*tablas de localización*/

CREATE TABLE Region (
    id_region BIGINT NOT NULL AUTO_INCREMENT,
    nombre_region VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (id_region)
);

CREATE TABLE Comuna (
    id_comuna BIGINT NOT NULL AUTO_INCREMENT,
    nombre_comuna VARCHAR(45) NOT NULL,
    region_id_region BIGINT NOT NULL,
    PRIMARY KEY (id_comuna),
    FOREIGN KEY (region_id_region) REFERENCES Region (id_region)
	ON DELETE CASCADE
    ON UPDATE CASCADE    
);

CREATE TABLE ZonaTermica (
    id_zona_termica VARCHAR(12) NOT NULL,
    tipo_zona_termica VARCHAR(45) NOT NULL,
    temperatura DECIMAL(5, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
);


CREATE TABLE Direccion (
    id_direccion BIGINT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(100) NOT NULL,
    numero INT(10),
    complemento VARCHAR(45),
    latitud DECIMAL(10,8),
    longitud DECIMAL(11,8),
    comuna_id_comuna BIGINT NOT NULL,
    zona_termica_id_zona_termica VARCHAR(12) NOT NULL,  
    FOREIGN KEY (comuna_id_comuna) REFERENCES Comuna(id_comuna)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (zona_termica_id_zona_termica) REFERENCES ZonaTermica(id_zona_termica)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
    
);


/*Usuarios y Perfiles*/

CREATE TABLE Roles (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE Usuarios (
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    rut_usuario VARCHAR(12) UNIQUE NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    id_direccion BIGINT,
    telefono VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_direccion) REFERENCES Direccion(id_direccion)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE UsuarioRoles (
    usuario_rut_usuario VARCHAR(12) NOT NULL,
    rol_id_rol INT NOT NULL,
    PRIMARY KEY (usuario_rut_usuario, rol_id_rol),
    FOREIGN KEY (usuario_rut_usuario) REFERENCES Usuarios (rut_usuario)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (rol_id_rol) REFERENCES Roles (id_rol)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Cliente (
    usuario_rut_usuario VARCHAR(12) PRIMARY KEY,
    razon_social VARCHAR(45) NOT NULL,
    representante_legal VARCHAR(100),
    giro_comercial VARCHAR(60),
    fecha_registro DATETIME NOT NULL,
    FOREIGN KEY (usuario_rut_usuario) REFERENCES Usuarios (rut_usuario)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Evaluador (
    usuario_rut_usuario VARCHAR(12) PRIMARY KEY,
    nombre_evaluador VARCHAR(45) NOT NULL,
    apellidos VARCHAR(45) NOT NULL,
    profesion VARCHAR(60),
    fecha_acreditacion DATETIME NOT NULL,
    FOREIGN KEY (usuario_rut_usuario) REFERENCES Usuarios (rut_usuario)
    ON DELETE CASCADE ON UPDATE CASCADE
);

/*Proyecto y Vivienda*/

CREATE TABLE Proyecto (
    id_proyecto BIGINT PRIMARY KEY AUTO_INCREMENT,
    rol_matriz VARCHAR(12) NOT NULL,
    nombre_proyecto VARCHAR(45) NOT NULL,
    id_direccion BIGINT NOT NULL,
    numero_viviendas BIGINT,
    zona_termica_id_zona_termica BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (zona_termica_id_zona_termica) REFERENCES ZonaTermica (id_zona_termica),
    FOREIGN KEY (id_direccion) REFERENCES Direccion (id_direccion)
);

CREATE TABLE Vivienda (
    rol_vivienda VARCHAR(12) NOT NULL PRIMARY KEY,
    id_direccion BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    lote VARCHAR(12),
    manzana VARCHAR(12),
    edificio VARCHAR(20),
    piso BIGINT,
    unidad VARCHAR(12),
    orientacion VARCHAR(20),
    materialidad VARCHAR(20),
    proyecto_id_proyecto VARCHAR(12) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id_proyecto) REFERENCES Proyecto (id_proyecto),
    FOREIGN KEY (id_direccion) REFERENCES Direccion (id_direccion)
);

/*Flujo Solicitudes*/

CREATE TABLE Estado (
    id_estado BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    color VARCHAR(7),
    activo BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
   );

CREATE TABLE Solicitud (
    id_solicitud BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(50) NOT NULL,
    permiso_edificacion VARCHAR(100),
    recepcion_final VARCHAR(100),
    proyecto_id_proyecto BIGINT NOT NULL,
    estado_id_estado BIGINT NOT NULL,
    cliente_rut_usuario VARCHAR(12) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id_proyecto) REFERENCES Proyecto (id_proyecto),
    FOREIGN KEY (estado_id_estado) REFERENCES Estado (id_estado),
    FOREIGN KEY (cliente_rut_usuario) REFERENCES Cliente(usuario_rut_usuario)
);


CREATE TABLE Documento (
    id_documento BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(45) NOT NULL,
    comentarios TEXT,
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    solicitud_id_solicitud BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (solicitud_id_solicitud) REFERENCES Solicitud (id_solicitud)   
);

/*Evaluación Técnica*/

CREATE TABLE Evaluacion (
    id_evaluacion BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha_evaluacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    proyecto_id_proyecto BIGINT NOT NULL,
    evaluador_rut_usuario VARCHAR(12) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id_proyecto) REFERENCES Proyecto (id_proyecto),
    FOREIGN KEY (evaluador_rut_usuario) REFERENCES Evaluador(usuario_rut_usuario)
    ON DELETE CASCADE ON UPDATE CASCADE
);

/*Sistemas Energéticos*/

CREATE TABLE SistemaCalefaccion (
    id_sistema_calefaccion BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tipo_distribucion VARCHAR(60),
    sistema_control VARCHAR(45),
    marca VARCHAR(20),
    modelo VARCHAR(20),
    potencia_nominal_sc DECIMAL(10, 2),
    evaluacion_id_evaluacion BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion)
);

CREATE TABLE SistemaVentilacion (
    id_sistema_ventilacion BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_sistema_ventilacion VARCHAR(45),
    potencia_ventilador DECIMAL(10, 2),
    potencia_recuperador DECIMAL(10, 2),
    consumo_energia DECIMAL(10, 2),
    marca VARCHAR(20),
    modelo VARCHAR(20),
    potencia_nominal_sv DECIMAL(10, 2),
    evaluacion_id_evaluacion BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion)
);

CREATE TABLE SistemaACS (
    id_sistema_acs BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_distribucion VARCHAR(45),
    sistema_encendido VARCHAR(20),
    correccion_distribucion VARCHAR(45),
    tiene_estanque VARCHAR(12) NOT NULL,
    marca VARCHAR(20),
    modelo VARCHAR(20),
    potencia_nominal_acs DECIMAL(10, 2),
    evaluacion_id_evaluacion BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion)
);

CREATE TABLE SistemaSolarCalefaccion (
    id_sistema_solar_calefaccion BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_distribucion_solar VARCHAR(60),
    sistema_aporte VARCHAR(45),
    tipo_colector VARCHAR(20),
    marca VARCHAR(20),
    modelo VARCHAR(20),
    superficie_total_ssc DECIMAL(10, 2),
    evaluacion_id_evaluacion BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion)
);

CREATE TABLE SistemaSolarIluminacion (
    id_sistema_solar_iluminacion BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_distribucion_iluminacion VARCHAR(60),
    numero_paneles BIGINT,
    marca VARCHAR(20),
    modelo VARCHAR(20),
    superficie_total_ssi DECIMAL(10, 2),
    evaluacion_id_evaluacion BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion)
);

CREATE TABLE Informe (
    id_informe BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha_generacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    evaluacion_id_evaluacion BIGINT,
    id_sistema_calefaccion BIGINT,
    id_sistema_ventilacion BIGINT,
    id_sistema_acs BIGINT,
    id_sistema_solar_calefaccion BIGINT,
    id_sistema_solar_iluminacion BIGINT,
    resumen TEXT,
    recomendaciones TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion),
    FOREIGN KEY (id_sistema_calefaccion) REFERENCES SistemaCalefaccion(id_sistema_calefaccion),
    FOREIGN KEY (id_sistema_ventilacion) REFERENCES SistemaVentilacion(id_sistema_ventilacion),
    FOREIGN KEY (id_sistema_acs) REFERENCES SistemaACS(id_sistema_acs),
    FOREIGN KEY (id_sistema_solar_calefaccion) REFERENCES SistemaSolarCalefaccion(id_sistema_solar_calefaccion),
    FOREIGN KEY (id_sistema_solar_iluminacion) REFERENCES SistemaSolarIluminacion(id_sistema_solar_iluminacion)
);

CREATE TABLE Etiqueta (
    id_etiqueta BIGINT PRIMARY KEY AUTO_INCREMENT,
    calificacion ENUM('A+', 'A', 'B', 'C', 'D', 'E', 'F', 'G') NOT NULL,
    fecha_emision DATETIME DEFAULT CURRENT_TIMESTAMP,
    evaluacion_id_evaluacion VARCHAR(12) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluacion_id_evaluacion) REFERENCES Evaluacion (id_evaluacion)   
);

/* POBLADO DE TABLAS */

/* POBLAR TABLA REGION */
INSERT INTO Region (id_region, nombre_region) VALUES (13, 'Region Metropolitana');

/* POBLAR TABLA COMUNA */
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14113, 'Renca', 13);
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14127, 'Conchali', 13);
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14114, 'Quilicura', 13);
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14201, 'Colina', 13);
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14156, 'Cerro_Navia', 13);
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14101, 'Santiago', 13);
INSERT INTO Comuna (id_comuna, nombre_comuna, region_id_region) VALUES (14105, 'Estacion Central', 13);


/* POBLAR TABLA Direccion */
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (1, 'Manuel Balmaceda', 440, NULL, -33.4378, -70.6504, 14113, 'Zona A');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (2, 'Calle Norte', 200, NULL, -33.4437, -70.6691, 14127, 'Zona B');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (3, 'Avenida Marcoleta', 330, NULL, -33.4475, -70.6728, 14114, 'Zona C');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (4, 'Calle Este', 400, NULL, -33.4503, -70.6876, 14201, 'Zona A');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (5, 'Francsico Errazuriz', 780, NULL, -33.4561, -70.6908, 14156, 'Zona B');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (6, 'Avenida Libertador', 100, NULL, -33.4608, -70.7025, 14101, 'Zona C');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (7, 'Calle Central', 50, 'Depto 101', -33.4687, -70.7103, 14105, 'Zona A');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (8, 'Avenida Siempre Viva', 742, NULL, -33.4725, -70.7251, 14113, 'Zona B');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (9, 'Pasaje La Paz', 15, NULL, -33.4783, -70.7389, 14127, 'Zona C');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (10, 'Plaza Mayor', 20, NULL, -33.4842, -70.7516, 14114, 'Zona A');
INSERT INTO Direccion (id_direccion, calle, numero, complemento, latitud, longitud, comuna_id_comuna, zona_termica_id_zona_termica) VALUES (11, 'General Freire', 450, NULL, -33.4880, -70.7604, 14113, 'Zona B');

/* POBLAR TABLA ZonaTermica */
INSERT INTO ZonaTermica (id_zona_termica, tipo_zona_termica, temperatura) VALUES ('D', 'mediterraneo', 20.5);
INSERT INTO ZonaTermica (id_zona_termica, tipo_zona_termica, temperatura) VALUES ('A', 'mediterraneo', 18.0);
INSERT INTO ZonaTermica (id_zona_termica, tipo_zona_termica, temperatura) VALUES ('C', 'mediterraneo', 18.0);
INSERT INTO ZonaTermica (id_zona_termica, tipo_zona_termica, temperatura) VALUES ('H', 'mediterraneo', 24.0);
INSERT INTO ZonaTermica (id_zona_termica, tipo_zona_termica, temperatura) VALUES ('B', 'mediterraneo', 18.0);

/* POBLAR TABLA Roles */
INSERT INTO Roles (id_rol, nombre) VALUES (1, 'ROLE_ADMIN');
INSERT INTO Roles (id_rol, nombre) VALUES (2, 'ROLE_USER');

/* POBLAR TABLA USUARIOS */
INSERT INTO Usuarios (id_usuario, rut_usuario, email, user_password, id_direccion, telefono) VALUES
(1, '76576465-3', 'jhon.sol@comercialsol.cl', 'hash123$', 1, '+56232523135'),
(2, '76668501-3', 'fqueirolo@gestionql.cl', 'hash678$', 2, '+56222335508'),
(3, '76648633-9', 'psat.integra@gmail.com', 'hash789$', 3, '+56212433550'),
(4, '76633659-1', 'avanceurbano2011@gmail.com', 'hash901$', 4, '+562268923529'),
(5, '76876172-8', 'xleon@proactiva-chile.cl', 'hash567', 5, '+56227852927'),
(6, '8133620-2', 'ximena.alvarez@example.com', 'evalpass1', 6, '+56911112222'), -- Evaluador 1
(7, '16355132-2', 'valeria.razeto@example.com', 'evalpass2', 7, '+56933334444'), -- Evaluador 2
(8, '13026382-8', 'tomas.saxton@example.com', 'evalpass3', 8, '+56955556666'), -- Evaluador 3
(9, '12506210-5', 'manuel.arjelle@example.com', 'evalpass4', 9, '+56977778888'), -- Evaluador 4
(10, '8665941-7', 'gonzalo.orrego@example.com', 'evalpass5', 10, '+56999990000'), -- Evaluador 5
(11, '9354576-1', 'stranskyvera@gmail.com', 'hash1234567', 11, '+56226695585');



/* POBLAR TABLA UsuarioRoles */
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('76576465-3', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('76668501-3', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('76648633-9', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('76633659-1', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('76876172-8', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('8133620-2', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('16355132-2', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('13026382-8', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('12506210-5', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('8665941-7', 2);
INSERT INTO UsuarioRoles (usuario_rut_usuario, rol_id_rol) VALUES ('9354576-1', 1);


/* POBLAR TABLA CLIENTE */
INSERT INTO Cliente (usuario_rut_usuario, razon_social, representante_legal, fecha_registro ) VALUES
('76576465-3', 'Asesoria e Ingenieria Sol SPA', 'Pedro Rojas Morales', '2025-01-20');
INSERT INTO Cliente (usuario_rut_usuario, razon_social, representante_legal, fecha_registro ) VALUES
('76668501-3', 'QL SPA', 'Jose Moraga Gonzalez', '2020-07-15');
INSERT INTO Cliente (usuario_rut_usuario, razon_social, representante_legal, fecha_registro ) VALUES
('76648633-9', 'P.S.A.T. INTEGRA SPA', 'Jorge Morales Cortes', '2018-09-26' );
INSERT INTO Cliente (usuario_rut_usuario, razon_social, representante_legal, fecha_registro ) VALUES
('76633659-1', 'AVANCE URBANO SPA', 'Daniel Perez Cepeda', '2019-03-01' );
INSERT INTO Cliente (usuario_rut_usuario, razon_social, representante_legal, fecha_registro ) VALUES
('76876172-8', 'PROACTIVA CHILE SPA', 'Hugo Rojas Perez', '2024-03-17');

/* POBLAR TABLA EVALUADOR */
INSERT INTO Evaluador (usuario_rut_usuario, nombre_evaluador, apellidos, profesion, fecha_acreditacion) VALUES
('8133620-2', 'Ximena', 'Alvarez Moraga', 'Constructor Civil', '2023-10-25');
INSERT INTO Evaluador (usuario_rut_usuario, nombre_evaluador, apellidos, profesion, fecha_acreditacion) VALUES
('16355132-2', 'Valeria', 'Razeto Caceres', 'Ingeniero Mecanico', '2024-02-15');
INSERT INTO Evaluador (usuario_rut_usuario, nombre_evaluador, apellidos, profesion, fecha_acreditacion) VALUES
('13026382-8', 'Tomas', 'Saxton Barrios', 'Arquitecto', '2023-02-10');
INSERT INTO Evaluador (usuario_rut_usuario, nombre_evaluador, apellidos, profesion, fecha_acreditacion) VALUES
('12506210-5', 'Manuel', 'Arjelle Gallardo', 'Ingeniero Civil', '2023-11-03');
INSERT INTO Evaluador (usuario_rut_usuario, nombre_evaluador, apellidos, profesion, fecha_acreditacion) VALUES
('8665941-7', 'Gonzalo', 'Orrego Rodriguez', 'Ingeniero Electrico', '2024-01-05');


/* POBLAR TABLA PROYECTO */
INSERT INTO proyecto (id_proyecto, rol_matriz, nombre_proyecto, id_direccion, numero_viviendas, zona_termica_id_zona_termica) VALUES
('RM001', '560-480', 'Edificio Solar', 1,  100, 'D'),
('RM002', '400-030', 'Condominio Verde', 2, 200, 'D'),
('RM003', '300-020', 'Conjunto Tierra del Fuego II', 3, 200, 'D'),
('RM004', '500-200', 'Residencial Parque', 4, 150, 'H'),
('RM005', '200-010', 'Mirador Alto', 5, 96, 'D');



/* POBLAR TABLA VIVIENDA */
INSERT INTO vivienda (rol_vivienda, id_direccion, tipo, estado, lote, manzana, edificio, piso, unidad, orientacion, materialidad, proyecto_id_proyecto) VALUES
('560-501', 1, 'Departamento', 'usado', 'Lote 01', 'Manzana A', 'Torre A', 2, 12, 'Norte', 'Hormigon', 'RM001'),
('400-062', 2, 'Casa', 'nuevo', 'Lote 02', 'Manzana 5', 'Edificio 1', 1, 5, 'Sur', 'Albañilería', 'RM002'),
('300-080', 3, 'Casa', 'usado', 'Lote 08', 'Manzana B', 'Edificio 2', 3, 8, 'Oriente', 'Acero galvanizado', 'RM003'),
('500-230', 4, 'Casa', 'nuevo', 'Lote 04', 'Manzana 9', 'Torre 3', 5, 10, 'Poniente', 'Albañilería', 'RM004'),
('200-090', 5, 'Departamento', 'nuevo', 'Lote 10', 'Manzana C', 'Torre 06', 3, 32, 'Poniente', 'Hormigon', 'RM005');


/* POBLAR TABLA ESTADO */
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Pendiente', 'La solicitud está pendiente de revisión.', '#FFC107', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('En Revisión', 'La solicitud está siendo revisada por un evaluador.', '#17A2B8', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Duc. Incompleta', 'La solicitud tiene documentación incompleta y requiere ser subsanada.', '#DC3545', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('En Evaluación', 'La solicitud está en proceso de evaluación técnica.', '#28A745', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('En Cálculo', 'Se están realizando los cálculos energéticos correspondientes.', '#007BFF', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Informe Preliminar', 'Se ha generado un informe preliminar para revisión.', '#FFC107', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Completada', 'La solicitud ha sido completada y está lista para la emisión de la etiqueta.', '#28A745', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Rechazada', 'La solicitud ha sido rechazada debido a incumplimientos normativos.', '#DC3545', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Cancelada', 'La solicitud ha sido cancelada por el solicitante.', '#6C757D', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('En Corrección', 'La solicitud requiere correcciones en la información o documentación.', '#FFC107', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Suspendida', 'La solicitud ha sido suspendida temporalmente.', '#6C757D', true);
INSERT INTO Estado (nombre_estado, descripcion, color, activo) VALUES ('Pre-aprobada', 'La solicitud ha sido pre-aprobada y está en espera de la emisión de la etiqueta.', '#28A745', true);

/* POBLAR TABLA SOLICITUD */
INSERT INTO Solicitud (id_solicitud, fecha_ingreso, tipo, permiso_edificacion, recepcion_final, proyecto_id_proyecto, estado_id_estado, cliente_rut_usuario) VALUES
('S001-D', '2025-01-17', 'Calificación', '12-A del 12-10-2010', '17 del 30-05-2012', 'RM001', 1, '76576465-3'),
('S002-C', '2024-02-12', 'Precalificación', '03 del 19-10-2024', 'En trámite', 'RM002', 2, '76668501-3'),
('S003-C', '2024-10-03', 'Calificación', '10 del 02-05-2009', '17 del 15-10-2009', 'RM003', 7, '76648633-9'),
('S004-C', '2024-11-06', 'Calificación', '07-08-2024', 'En trámite', 'RM004', 4, '76633659-1'),
('S005-D', '2024-11-01', 'Calificación', '24 del 19-10-2024', 'En trámite', 'RM005', 1, '76876172-8');



/* POBLAR TABLA DOCUMENTO */
INSERT INTO documento (id_documento, tipo, fecha_ingreso, solicitud_id_solicitud) VALUES
('DP001', 'plano', '2025-01-20', 'S001-D'),
('CC003', 'certificado', '2024-05-12', 'S002-C'),
('CETC010', 'Especificaciones tecnicas', '2024-05-12', 'S003-C'),
('CP001', 'plano', '2024-11-06', 'S004-C'),
('DP012', 'plano', '2025-01-15', 'S005-D');


/* POBLAR TABLA EVALUACION */
INSERT INTO Evaluacion (id_evaluacion, fecha_evaluacion, proyecto_id_proyecto, evaluador_rut_usuario) VALUES
('EVRM001', '2025-03-20', 'RM001', '8133620-2'),
('EVRM002', '2025-05-15', 'RM002', '16355132-2'),
('EVRM003', '2025-04-10', 'RM003', '13026382-8'),
('EVRM004', '2025-02-07', 'RM004', '12506210-5'),
('EVRM005', '2025-01-30', 'RM005', '8665941-7');

/* POBLAR TABLA SISTEMA CALEFACCION (kw) */
INSERT INTO SistemaCalefaccion (id_sistema_calefaccion, tipo_distribucion, sistema_control, marca, modelo, potencia_nominal_sc, evaluacion_id_evaluacion) VALUES
('SCEVRM001', 'sistema unitario autónomo', 'control automatico', 'Daikin', 'AT200', '2000.00', 'EVRM001'),
('SCEVRM002', 'Vivienda unifamiliar con sistema centralizado', 'control automatico', 'Bosch', 'BC300', '2500.00', 'EVRM002'),
('SCEVRM003', 'Vivienda unifamiliar con sistema centralizado', 'control manual', 'Baxi', 'BX100', '1800.00', 'EVRM003'),
('SCEVRM004', 'Sistema de calefaccion distrital', 'control manual', 'Vaillant', 'VL500', '3500.00', 'EVRM004'),
('SCEVRM005', 'Edificio con sistema centralizado', 'control automatico', 'Junkers', 'JK400', '3000.00', 'EVRM005');


/* POBLAR TABLA SISTEMA DE VENTILACION (watts, kw, kw) */
INSERT INTO SistemaVentilacion (id_sistema_ventilacion, tipo_sistema_ventilacion, potencia_ventilador, potencia_recuperador, consumo_energia, marca, modelo, potencia_nominal_sv, evaluacion_id_evaluacion) VALUES
('SVEVRM001', 'Ventilación mecánica', 250.00, 500.00, 750, 'Daikin', 'VRV-IV', 800.00, 'EVRM001'),
('SVEVRM002', 'Ventilación natural', NULL, NULL, 0, 'N/A', 'N/A', NULL, 'EVRM002'),
('SVEVRM003', 'Ventilación híbrida', 300.00, 200.00, 500, 'Mitsubishi', 'Lossnay', 600.00, 'EVRM003'),
('SVEVRM004', 'Ventilación forzada', 400.00, 450.00, 850, 'Carrier', 'Infinity', 900.00, 'EVRM004'),
('SVEVRM005', 'Ventilación con recuperación', 350.00, 600.00, 950, 'Panasonic', 'Econavi', 950.00, 'EVRM005');

/* POBLAR TABLA SISTEMA ACS (kw) */
INSERT INTO SistemaACS (id_sistema_acs, tipo_distribucion, sistema_encendido, correccion_distribucion, tiene_estanque, marca, modelo, potencia_nominal_acs, evaluacion_id_evaluacion) VALUES
('ACSEVRM001', 'Indirecto', 'Automático', 'Red de caneria con aislacion', 'Sí', 'Vaillant', 'ecoTEC plus', 24.00, 'EVRM001'),
('ACSEVRM002', 'Directo', 'Manual', 'Red de caneria sin aislacion', 'No', 'Ariston', 'Lydos', 18.50, 'EVRM002'),
('ACSEVRM003', 'Directo', 'Automático', 'Red de caneria sin aislacion', 'Sí', 'Ferroli', 'Domina', 30.00, 'EVRM003'),
('ACSEVRM004', 'Directo', 'Manual', 'Red de caneria sin aislacion', 'No', 'Baxi', 'Ninguno', 15.00, 'EVRM004'),
('ACSEVRM005', 'Indirecto', 'Automático', 'Red de caneria con aislacion', 'Sí', 'Rheem', 'Prestige', 22.50, 'EVRM005');

/* POBLAR TABLA SISTEMA SOLAR CALEFACCION Y ACS (m2) */
INSERT INTO SistemaSolarCalefaccion (id_sistema_solar_calefaccion, tipo_distribucion_solar, sistema_aporte, tipo_colector, marca, modelo, superficie_total_ssc, evaluacion_id_evaluacion) VALUES
('SSCEVRM001', 'Sistema Colectivo', 'ACS', 'Plano', 'Baxi', 'Solar 200', 4.20, 'EVRM001'),
('SSCEVRM002', 'Sistema Individual', 'ACS', 'Tubos de Vacío', 'Junkers', 'FCC-2S', 8.40 , 'EVRM002'),
('SSCEVRM003', 'Sistema Individual', 'De calefacción', 'Plano Selectivo', 'Vaillant', 'VFK 145 V', 6.30, 'EVRM003'),
('SSCEVRM004', 'Sistema Individual', 'De calefacción', 'Heat Pipe', 'Bosch', 'FKC-2S', 10.50, 'EVRM004'),
('SSCEVRM005', 'Sistema Colectivo', 'ACS', 'Plano', 'Viessmann', 'Vitosol 200-F', 5.60, 'EVRM005');

/* POBLAR TABLA SISTEMA SOLAR ILUMINACION (m2) */
INSERT INTO SistemaSolarIluminacion (id_sistema_solar_iluminacion, tipo_distribucion_iluminacion, numero_paneles, marca, modelo, superficie_total_ssi, evaluacion_id_evaluacion) VALUES
('SSISCEVRM001', 'sistema colectivo', 8, 'SunPower', 'SPR-X22-360', 13.20, 'EVRM001' ),
('SSISCEVRM002', 'sistema individual', 12, 'LG', 'NeON-R', 19.60, 'EVRM002'), 
('SSISCEVRM003', 'sistema individual', 10, 'Jinko Solar', 'Tiger Pro', 16.33, 'EVRM003'), 
('SSISCEVRM004', 'sistema individual', 14, 'Canadian Solar', 'HiKu', 22.89, 'EVRM004'), 
('SSISCEVRM005', 'sistema colectivo', 8, 'LONGi Solar', 'Hi-MO 4', 13.20, 'EVRM005');

/* POBLAR TABLA INFORME (kw, kw, kw, m2, m2) */
INSERT INTO informe (id_informe, fecha_generacion, evaluacion_id_evaluacion, id_sistema_calefaccion, id_sistema_ventilacion, id_sistema_acs, id_sistema_solar_calefaccion, id_sistema_solar_iluminacion, resumen, recomendaciones) VALUES
('INRM001', '2025-03-20', 'EVRM001', 'SCEVRM001', 'SVEVRM001', 'ACSEVRM001', 'SSCEVRM001', 'SSISCEVRM001', 'Proyecto cumple clase A+', 'Mantener Los recintos bien cerrados'),
('INRM002', '2025-05-16', 'EVRM002', 'SCEVRM002', 'SVEVRM002', 'ACSEVRM002', 'SSCEVRM002', 'SSISCEVRM002', 'Proyecto Clase A', 'Añadir sensores en los Pasillos'),
('INRM003', '2025-04-11', 'EVRM003', 'SCEVRM003', 'SVEVRM003', 'ACSEVRM003', 'SSCEVRM003', 'SSISCEVRM003', 'Proyecto Clase B', 'Mejorar aislamiento de Cubierta'),
('INRM004', '2025-02-07', 'EVRM004', 'SCEVRM004', 'SVEVRM004', 'ACSEVRM004', 'SSCEVRM004', 'SSISCEVRM004', 'Proyecto Clse C', 'Revisar Puentes térmicos ebn fachada'),
('INRM005', '2025-01-30', 'EVRM005', 'SCEVRM005', 'SVEVRM005', 'ACSEVRM005', 'SSCEVRM005', 'SSISCEVRM005', 'Proyecto Clase D', 'Agregar colectores Solares Adicionales');

/* POBLAR TABLA ETIQUETA */
INSERT INTO etiqueta (id_etiqueta, calificacion, fecha_emision, evaluacion_id_evaluacion) VALUES
('ETRM001', 'A+', '2025-03-21', 'EVRM001'),
('ETRM002', 'A', '2025-05-16', 'EVRM002'),
('ETRM003', 'B', '2025-04-11', 'EVRM003'),
('ETRM004', 'C', '2025-02-08', 'EVRM004'),
('ETRM005', 'D', '2025-01-31', 'EVRM005');


