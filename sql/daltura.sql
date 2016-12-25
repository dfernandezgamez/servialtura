CREATE DATABASE  IF NOT EXISTS `SERVIALTURA` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `SERVIALTURA`;
-- MySQL dump 10.13  Distrib 5.7.16, for Linux (i686)
--
-- Host: localhost    Database: SERVIALTURA
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CATEGORIA_MATERIAL`
--

DROP TABLE IF EXISTS `CATEGORIA_MATERIAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CATEGORIA_MATERIAL` (
  `idCategoriaMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCategoria` tinytext CHARACTER SET latin1,
  PRIMARY KEY (`idCategoriaMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORIA_MATERIAL`
--

LOCK TABLES `CATEGORIA_MATERIAL` WRITE;
/*!40000 ALTER TABLE `CATEGORIA_MATERIAL` DISABLE KEYS */;
INSERT INTO `CATEGORIA_MATERIAL` VALUES (1,'Fijadores'),(2,'Revestimientos'),(3,'Esmaltes'),(4,'Morteros'),(5,'Otros');
/*!40000 ALTER TABLE `CATEGORIA_MATERIAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTACTO`
--

DROP TABLE IF EXISTS `CONTACTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTACTO` (
  `idContacto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_contacto` tinytext COLLATE latin1_spanish_ci NOT NULL,
  `email_contacto` tinytext COLLATE latin1_spanish_ci,
  `direccion_contacto` tinytext COLLATE latin1_spanish_ci,
  `telefono_contacto` tinytext COLLATE latin1_spanish_ci,
  `idEmpresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idContacto`),
  KEY `contacto_pertenece_empresa_idx` (`idEmpresa`),
  CONSTRAINT `contacto_pertenece_empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `EMPRESA` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTACTO`
--

LOCK TABLES `CONTACTO` WRITE;
/*!40000 ALTER TABLE `CONTACTO` DISABLE KEYS */;
INSERT INTO `CONTACTO` VALUES (1,'Hugo',NULL,NULL,'636455092',1),(2,'Asunción Noguero',NULL,NULL,'625916547',2),(3,'Susana',NULL,NULL,'649898635',3),(4,'Tito','','','638107686',4),(5,'Vicente','','','628168175',5),(6,'Antonio','','','985321998',5),(7,'Oscar','','','656947996',NULL),(8,'Jesus','','','628290753',13),(9,'Conchita','','','630685662',13),(10,'Carlos','','','639775856',10);
/*!40000 ALTER TABLE `CONTACTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPRESA`
--

DROP TABLE IF EXISTS `EMPRESA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPRESA` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_empresa` tinytext CHARACTER SET latin1,
  `nif_empresa` tinytext CHARACTER SET latin1,
  `email_empresa` tinytext CHARACTER SET latin1,
  `telefono_empresa` tinytext CHARACTER SET latin1,
  `direccion_empresa` tinytext CHARACTER SET latin1,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPRESA`
--

LOCK TABLES `EMPRESA` WRITE;
/*!40000 ALTER TABLE `EMPRESA` DISABLE KEYS */;
INSERT INTO `EMPRESA` VALUES (1,'ADMINISTRACIÓN PRINCIPADO','','info@administracionesprincipado.com ','985130891 / 663328592','info@administracionesprincipado.com '),(2,'ADMINISTRACIÓN NOGUERO',NULL,'gestion@anoguero.com','984193023 / ','gestion@anoguero.com'),(3,'SERVICIOS IGS',NULL,'','','serviciosigs@movistar.es'),(4,'OZONO',NULL,NULL,NULL,'elidatito@msn.com'),(5,'LOPEZ Y CORTINA',NULL,NULL,NULL,'promocioneslopezycortina@gmail.com'),(6,'ASTURLA',NULL,NULL,'984050794','oscar@asturla.com'),(7,'SERVICON',NULL,NULL,NULL,'info@servicon.es'),(8,'PROMOCIONES CASCOS',NULL,NULL,'629646608 / 620866157','franciscocascos@pcascos.com'),(9,'BECAREAVIT',NULL,NULL,'629646608 / 620866157','franciscocascos@pcascos.com'),(10,'OTEAC','',NULL,NULL,'comunidades@oteac.es'),(11,'ACEBAL ADMININISTRACION','',NULL,'985342216','maria@administracionesacebal.com'),(12,'PEDRO POZUECO ADMINISTRACION','',NULL,'696533487','pozuecomanero@gmail.com'),(13,'JESÚS VIGIL ADMINISTRACION','',NULL,'985342353','vigil@jesusperezvigil.e.telefonica.net'),(14,'ANA PRIETO ADMINISTRACIÓN',NULL,NULL,'985330287 / 662146653','anaprietoalvarez@gmail.com'),(15,'ANA FERNANDEZ CASTAÑO ADMINISTRACION',NULL,NULL,'984190867','anafernandezcas@telecable.es');
/*!40000 ALTER TABLE `EMPRESA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MATERIAL`
--

DROP TABLE IF EXISTS `MATERIAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MATERIAL` (
  `idMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `nombreMaterial` tinytext CHARACTER SET latin1,
  `precioUnitario` decimal(34,2) DEFAULT NULL,
  `idCategoriaMaterial` int(11) DEFAULT NULL,
  `idUnidadMaterial` int(11) DEFAULT NULL,
  `idProveedor` int(11) DEFAULT NULL,
  `descripcionMaterial` tinytext COLLATE latin1_spanish_ci,
  PRIMARY KEY (`idMaterial`),
  KEY `material_tiene_categoria_idx` (`idCategoriaMaterial`),
  KEY `fk_SERVICIO_TIENE_UNIDAD_idx` (`idUnidadMaterial`),
  KEY `material_pertenece_proveedor_idx` (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MATERIAL`
--

LOCK TABLES `MATERIAL` WRITE;
/*!40000 ALTER TABLE `MATERIAL` DISABLE KEYS */;
INSERT INTO `MATERIAL` VALUES (1,'FIXACRYL',49.03,1,2,1,'Al agua'),(2,'DELTAFIX',33.88,1,2,1,'Al disolvente'),(3,'HYDROGRUND',31.23,1,2,1,'Para LOTUSAN'),(4,'KEIM FIXATIV',33.12,1,2,1,'Al Agua'),(5,'FIJADOR PARA SILIBLATEM',20.36,1,2,1,'');
/*!40000 ALTER TABLE `MATERIAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MATERIALES_PRESUPUESTO`
--

DROP TABLE IF EXISTS `MATERIALES_PRESUPUESTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MATERIALES_PRESUPUESTO` (
  `idPresupuesto` int(11) NOT NULL,
  `idMaterial` int(11) NOT NULL,
  `cantidad` float DEFAULT NULL,
  PRIMARY KEY (`idPresupuesto`,`idMaterial`),
  KEY `fk_SERVICIOS_PRESUPUESTO_1_idx` (`idMaterial`),
  CONSTRAINT `fk_SERVICIOS_PRESUPUESTO_1` FOREIGN KEY (`idMaterial`) REFERENCES `MATERIAL` (`idMaterial`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SERVICIOS_PRESUPUESTO_pertenece_presupuesto` FOREIGN KEY (`idPresupuesto`) REFERENCES `PRESUPUESTO` (`idPresupuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MATERIALES_PRESUPUESTO`
--

LOCK TABLES `MATERIALES_PRESUPUESTO` WRITE;
/*!40000 ALTER TABLE `MATERIALES_PRESUPUESTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `MATERIALES_PRESUPUESTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARTIDA`
--

DROP TABLE IF EXISTS `PARTIDA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PARTIDA` (
  `id_partida` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_partida` tinytext CHARACTER SET latin1,
  `importe_partida` decimal(38,2) DEFAULT NULL,
  `id_presupuesto` int(11) DEFAULT NULL,
  `descripcion_partida` mediumtext CHARACTER SET latin1,
  PRIMARY KEY (`id_partida`),
  KEY `fk_PARTIDA_1` (`id_presupuesto`),
  CONSTRAINT `fk_PARTIDA_1` FOREIGN KEY (`id_presupuesto`) REFERENCES `PRESUPUESTO` (`idPresupuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARTIDA`
--

LOCK TABLES `PARTIDA` WRITE;
/*!40000 ALTER TABLE `PARTIDA` DISABLE KEYS */;
INSERT INTO `PARTIDA` VALUES (1,'asas',6545456.00,3,'<p>assasasas</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ul>\r\n	<li>assaas</li>\r\n</ul>\r\n');
/*!40000 ALTER TABLE `PARTIDA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRESUPUESTO`
--

DROP TABLE IF EXISTS `PRESUPUESTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRESUPUESTO` (
  `idPresupuesto` int(11) NOT NULL AUTO_INCREMENT,
  `estado_presupuesto` tinytext CHARACTER SET latin1,
  `importe_presupuesto` decimal(38,2) DEFAULT NULL,
  `tiene_factura_suplidos` binary(1) DEFAULT NULL,
  `fecha_solicitud` datetime DEFAULT NULL,
  `descripcion_solicitud` tinytext CHARACTER SET latin1,
  `email_contacto` tinytext CHARACTER SET latin1,
  `persona_contacto` tinytext CHARACTER SET latin1,
  `telefono_contacto` tinytext CHARACTER SET latin1,
  `id_cliente` int(11) DEFAULT NULL,
  `numero_presupuesto` tinytext CHARACTER SET latin1,
  PRIMARY KEY (`idPresupuesto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRESUPUESTO`
--

LOCK TABLES `PRESUPUESTO` WRITE;
/*!40000 ALTER TABLE `PRESUPUESTO` DISABLE KEYS */;
INSERT INTO `PRESUPUESTO` VALUES (3,'ACEPTADO',366584.00,NULL,'2016-12-18 01:00:00','sdfsdfsd','dddddddd','dfdfd','33333',1,'PRE1612001'),(4,'RECHAZADO',9879879.00,NULL,'2016-12-18 01:00:00','sdfsdfsd','fsdfsdfds','dfdfdfd','3333',1,'PRE1612002'),(5,'EN_PREPARACION',NULL,NULL,'2016-12-18 01:00:00','adasdas','asdasd','asdasdas','asdas',NULL,'PRE1612003');
/*!40000 ALTER TABLE `PRESUPUESTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROVEEDOR`
--

DROP TABLE IF EXISTS `PROVEEDOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROVEEDOR` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_proveedor` tinytext COLLATE latin1_spanish_ci,
  `email_proveedor` tinytext COLLATE latin1_spanish_ci,
  `direccion_proveedor` tinytext COLLATE latin1_spanish_ci,
  `telefono_proveedor` tinytext COLLATE latin1_spanish_ci,
  `notas_proveedor` tinytext COLLATE latin1_spanish_ci,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROVEEDOR`
--

LOCK TABLES `PROVEEDOR` WRITE;
/*!40000 ALTER TABLE `PROVEEDOR` DISABLE KEYS */;
INSERT INTO `PROVEEDOR` VALUES (1,'PINTAVI','','','',NULL),(2,'KEIM',NULL,NULL,NULL,NULL),(3,'COLORES DEL PRINCIPADO',NULL,NULL,NULL,NULL),(4,'PRECIOS PROCOLOR EDUARDO s.l. ','','Piedras blancas','',NULL),(5,'MONCA','',NULL,NULL,NULL),(6,'TENYSOL',NULL,NULL,NULL,NULL),(7,'LUDOAVENTURA',NULL,NULL,NULL,NULL),(8,'Marmolería Valladares','info@marmoleriavalladares.com','Calle Gutiérrez Herrero, 33, Bajo 33402 AVILES, ASTURIAS','985548410',NULL),(9,'Marmolería Di-Mar','marmoleriadimar@hotmail.com','Polígono Industrial Roces 4\nCalle Jorge Juan 64\n33211 Gijón (Asturias)','985 168 229',NULL),(10,'Fermin Galan Marmoles','','Av. Cudillero, S/N 33120 PRAVIA, ASTURIAS ','985820467',NULL),(11,'Mármoles Y Granitos Insunza','','Calle LA FONTANA, 28 33120 PRAVIA, ASTURIAS','985820723',NULL),(12,'Mármoles Paz','marmolespaz@hotmail.com','Calle Alfred Nóbel, 95Polígono industrial de Porceyo\nGIJÓN  (Asturias)  33392 ','985 167 261',NULL),(13,'Marmolería Leonesa',NULL,NULL,NULL,NULL),(14,'ASTUR ESTRUCTURAS','','Avilés','985.98.60.70','Mínimo 10 días de alquier'),(15,'ANDAMIOS FM, S.L.','','Oviedo','985.21.34.24','Sin mínimo'),(16,'BRUGUER',NULL,NULL,NULL,NULL),(17,'FERMAR (ANDAMIOS)',NULL,NULL,NULL,NULL),(18,'ESGA','',NULL,NULL,NULL),(19,'ASTURIANA DE AZULEJOS',NULL,NULL,NULL,NULL),(20,'HIERROS DEL CANTÁBRICO_COMERCIAL DE LAMINADOS',NULL,NULL,NULL,NULL),(21,'MARTIN VEGA',NULL,NULL,NULL,NULL),(22,'VEGA ALQUILERES',NULL,NULL,NULL,NULL),(23,'ASA',NULL,NULL,NULL,NULL),(24,'MUNDOCOLOR',NULL,NULL,NULL,NULL),(25,'TREMOLS & PELAEZ',NULL,NULL,NULL,NULL),(26,'VIPEQ',NULL,NULL,NULL,NULL),(27,'CUESTA',NULL,NULL,NULL,NULL),(28,'GOMEZ OVIEDO',NULL,NULL,NULL,NULL),(29,'TRATAMIENTOS Y PINTURAS',NULL,NULL,NULL,NULL),(30,'GERCO',NULL,NULL,NULL,NULL),(31,'PORTELA',NULL,NULL,NULL,NULL),(32,'IMPERCANAL',NULL,NULL,NULL,NULL),(33,'BLA-TEM_COLORES PRINCIPADO',NULL,NULL,NULL,NULL),(34,'ROALDO',NULL,NULL,NULL,NULL),(35,'JOMASA',NULL,NULL,NULL,NULL),(36,'LEROY MERLÍN',NULL,NULL,NULL,NULL),(37,'CEPEDAL',NULL,NULL,NULL,NULL),(38,'CEMAT','',NULL,NULL,NULL),(39,'DRIZORO','',NULL,NULL,NULL),(40,'ALKOROSTI',NULL,NULL,NULL,' Antiguo Marcelo y Ferrao'),(41,'SIKA',NULL,NULL,NULL,NULL),(42,'Beissier. Morteros',NULL,NULL,NULL,NULL),(43,'TECNOL',NULL,NULL,NULL,NULL),(44,'TEXSA',NULL,NULL,NULL,NULL),(45,'KERAKOLL',NULL,NULL,NULL,NULL),(46,'WEBER','',NULL,NULL,NULL);
/*!40000 ALTER TABLE `PROVEEDOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROL`
--

DROP TABLE IF EXISTS `ROL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROL` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROL`
--

LOCK TABLES `ROL` WRITE;
/*!40000 ALTER TABLE `ROL` DISABLE KEYS */;
INSERT INTO `ROL` VALUES (1,'ROLE_ADMINISTRADOR'),(2,'ROLE_USUARIO');
/*!40000 ALTER TABLE `ROL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRABAJO`
--

DROP TABLE IF EXISTS `TRABAJO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRABAJO` (
  `idTrabajo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_trabajo` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `precio_unidad` float DEFAULT NULL,
  PRIMARY KEY (`idTrabajo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRABAJO`
--

LOCK TABLES `TRABAJO` WRITE;
/*!40000 ALTER TABLE `TRABAJO` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRABAJO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UNIDAD_MATERIAL`
--

DROP TABLE IF EXISTS `UNIDAD_MATERIAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UNIDAD_MATERIAL` (
  `idUnidadMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUnidad` tinytext COLLATE latin1_spanish_ci,
  `descripcionUnidad` tinytext COLLATE latin1_spanish_ci,
  PRIMARY KEY (`idUnidadMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UNIDAD_MATERIAL`
--

LOCK TABLES `UNIDAD_MATERIAL` WRITE;
/*!40000 ALTER TABLE `UNIDAD_MATERIAL` DISABLE KEYS */;
INSERT INTO `UNIDAD_MATERIAL` VALUES (1,'KG','Kilogramos'),(2,'L','Litros');
/*!40000 ALTER TABLE `UNIDAD_MATERIAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `usuario` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `idRol` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_USUARIO_1_idx` (`idRol`),
  CONSTRAINT `usuario_tiene_rol` FOREIGN KEY (`idRol`) REFERENCES `ROL` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,'david','david',1),(2,'daniel','daniel',1);
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-25 12:52:09