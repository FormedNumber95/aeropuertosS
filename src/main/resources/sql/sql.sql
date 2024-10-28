--crear schema de veterinario
CREATE SCHEMA veterinario;
--crear tabla de animales
CREATE TABLE veterinario.animales (
	ID INT auto_increment NOT NULL,
	nombre varchar(100) NULL,
	especie varchar(100) NULL,
	raza varchar(100) NULL,
	sexo varchar(10) NULL,
	edad INT NULL,
	peso FLOAT NULL,
	primera_consulta varchar(100) NULL,
	foto BLOB NULL,
	CONSTRAINT animales_pk PRIMARY KEY (ID)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;
--crear tabla de observaciones
CREATE TABLE veterinario.observaciones (
	ID INT auto_increment NOT NULL,
	observacion varchar(100) NULL,
	id_animal INT NULL,
	CONSTRAINT observaciones_pk PRIMARY KEY (ID),
	CONSTRAINT observaciones_animales_FK FOREIGN KEY (id_animal) REFERENCES veterinario.animales(ID)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;


