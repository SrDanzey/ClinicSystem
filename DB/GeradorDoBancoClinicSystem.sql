CREATE DATABASE clinicSystem;

DROP TABLE IF EXISTS `paciente`;

CREATE TABLE `paciente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `sintoma` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `paciente` WRITE;
INSERT INTO `paciente` VALUES (1,'Adson Mendes Élias','231-876-123-64','Dor de cabeça e no estomago');
UNLOCK TABLES;

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `usuario` WRITE;
INSERT INTO `usuario` VALUES (1,'Douglas Guimarães dos Santos','douglas.gs2003','senha123','douglas.GS2003@gmail.com'),(2,'Rogério Gonsalves','rogerio.g1993','senha123','rogerio.g1993@gmail.com');

UNLOCK TABLES;

DROP TABLE IF EXISTS `agenda`;

CREATE TABLE `agenda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `horario` varchar(255) NOT NULL,
  `fk_idClinica_medico` int NOT NULL,
  `fk_idPaciente` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `agenda` WRITE;

INSERT INTO `agenda` VALUES (1,'02/12/2022 - 14:30',1,1);

UNLOCK TABLES;

DROP TABLE IF EXISTS `clinica`;

CREATE TABLE `clinica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `clinica` WRITE;

INSERT INTO `clinica` VALUES (1,'Super-Clinica','Rua Geral do Andrino','(+48) 9321-3214');

UNLOCK TABLES;

DROP TABLE IF EXISTS `clinica_medico`;

CREATE TABLE `clinica_medico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fk_idMedico` int NOT NULL,
  `fk_idClinica` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `clinica_medico` WRITE;

INSERT INTO `clinica_medico` VALUES (1,1,2);

UNLOCK TABLES;

DROP TABLE IF EXISTS `medico`;

CREATE TABLE `medico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `medico` WRITE;

INSERT INTO `medico` VALUES (1,'Samuel da Super Hornet');

UNLOCK TABLES;

