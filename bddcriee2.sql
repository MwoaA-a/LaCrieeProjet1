-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 14 avr. 2024 à 11:43
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddcriee2`
--
CREATE DATABASE IF NOT EXISTS `bddcriee2` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `bddcriee2`;

-- --------------------------------------------------------

--
-- Structure de la table `acheteur`
--

DROP TABLE IF EXISTS `acheteur`;
CREATE TABLE IF NOT EXISTS `acheteur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_acheteur` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom_acheteur` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code_postale` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ville` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `association`
--

DROP TABLE IF EXISTS `association`;
CREATE TABLE IF NOT EXISTS `association` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_assoc` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ville` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code_postale` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `bac`
--

DROP TABLE IF EXISTS `bac`;
CREATE TABLE IF NOT EXISTS `bac` (
  `id` int NOT NULL AUTO_INCREMENT,
  `IdLot` int NOT NULL,
  `idTypeBac` char(1) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`,`IdLot`),
  KEY `idTypeBac_BAC` (`idTypeBac`),
  KEY `idx_IdLot` (`IdLot`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `bac`
--

INSERT INTO `bac` (`id`, `IdLot`, `idTypeBac`) VALUES
(3, 22, 'F'),
(2, 23, 'B'),
(1, 23, 'F'),
(1, 20, 'F'),
(3, 23, 'F'),
(3, 21, 'F'),
(2, 22, 'B'),
(1, 22, 'F'),
(2, 21, 'B'),
(1, 26, 'F'),
(2, 20, 'F'),
(3, 20, 'B'),
(1, 24, 'F'),
(2, 24, 'B'),
(3, 24, 'F'),
(1, 21, 'B'),
(2, 31, 'F'),
(1, 31, 'B'),
(1, 32, 'F');

-- --------------------------------------------------------

--
-- Structure de la table `bateau`
--

DROP TABLE IF EXISTS `bateau`;
CREATE TABLE IF NOT EXISTS `bateau` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `immatriculation` varchar(255) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `bateau`
--

INSERT INTO `bateau` (`id`, `nom`, `immatriculation`) VALUES
(1, 'Altair', 'Ad 895511'),
(2, 'Macareux', 'Ad 584873'),
(3, 'Avel Ar Mor', 'Ad 584930'),
(4, 'Plujadur', 'Ad 627846'),
(5, 'Gwaien', 'Ad 730414'),
(6, 'L Estran', 'Ad 815532'),
(7, 'Le Petit Corse', 'Ad 584826'),
(8, 'Le Vorlen', 'Ad 614221'),
(9, 'Les Copains d Abord', 'Ad 584846'),
(10, 'Tu Pe Du', 'Ad 584871'),
(11, 'Korrigan', 'Ad 895472'),
(12, 'Ar Guevel', 'Ad 895479'),
(13, 'Broceliande', 'Ad 895519'),
(14, 'L Aventurier', 'Ad 584865'),
(15, 'L Oceanide', 'Ad 741312'),
(16, 'L Arche d alliance', 'Ad 584830'),
(17, 'Sirocco', 'Ad 715792'),
(18, 'Ondine', 'Ad 584772'),
(19, 'Chimere', 'Ad 895516');

-- --------------------------------------------------------

--
-- Structure de la table `espece`
--

DROP TABLE IF EXISTS `espece`;
CREATE TABLE IF NOT EXISTS `espece` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `nomScientifique` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `nomCourt` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=57051 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `espece`
--

INSERT INTO `espece` (`id`, `nom`, `nomScientifique`, `nomCourt`) VALUES
(33760, 'Baudroie', 'Lophius Piscatorus', 'BAUDR'),
(33090, 'Bar de Chalut', 'Dicentrarchus Labrax', 'BARCH'),
(33091, 'Bar de Ligne', 'Dicentrarchus Labrax', 'BARLI'),
(32130, 'Lieu Jaune de Ligne', 'Pollachius pollachius', 'LJAUL'),
(42040, 'Araignée de mer casier', 'Maja squinado', 'ARAIS'),
(42041, 'Araignée de mer chalut', 'Maja squinado', 'ARAIL'),
(43010, 'Homard', 'Hammarus gammorus', 'HOMAR'),
(43030, 'Langouste rouge', 'Palinurus elephas', 'LANGR'),
(32140, 'Lieu Noir', 'Lophius Virens', 'LNOI'),
(31020, 'Turbot', 'Psetta maxima', 'TURBO'),
(33480, 'Dorade rose', 'Pagellus bogaraveo', 'DORAD'),
(38150, 'Raie douce', 'Raja Montagui', 'RAIE'),
(33020, 'Congre commun', 'Conger conger', 'CONGR'),
(32020, 'Merlu', 'Merluccius bilinearis', 'MERLU'),
(31030, 'Barbue', 'Scophthalmus rhombus', 'BARBU'),
(31150, 'Plie ou carrelet', 'Pleuronectes Platessa', 'PLIE'),
(32050, 'Cabillaud', 'Gadus Morhua Morhue', 'CABIL'),
(32230, 'Lingue franche', 'Molva Molva', 'LINGU'),
(33080, 'Saint Pierre', 'Zeus Faber', 'STPIE'),
(33110, 'Mérou ou cernier', 'Polyprion Americanus', 'CERNI'),
(33120, 'Mérou noir', 'Epinephelus Guaza', 'MEROU'),
(33410, 'Rouget Barbet', 'Mullus SPP', 'ROUGT'),
(33450, 'Dorade royale chalut', 'Sparus Aurata', 'DORAC'),
(33451, 'Dorade royale ligne', 'Sparus Aurata', 'DORAL'),
(33490, 'Pageot Acarne', 'Pagellus Acarne', 'PAGEO'),
(33500, 'Pageot Commun', 'Pagellus Arythrinus', 'PAGEC'),
(33580, 'Vieille', 'LabrusBergylta', 'VIEIL'),
(33730, 'Grondin gris', 'Eutrigla Gurnadus', 'GRONG'),
(33740, 'Grondin rouge', 'Aspitrigla Cuculus', 'GRONR'),
(33761, 'Baudroie Maigre', 'Lophius Piscicatorius', 'BAUDM'),
(33790, 'Grondin Camard', 'Trigloporus Lastoviza', 'GRONC'),
(33800, 'Grondin Perlon', 'Trigla Lucerna', 'GRONP'),
(34150, 'Mulet', 'Mugil SPP', 'MULET'),
(35040, 'Sardine atlantique', 'Sardina Pilchardus', 'SARDI'),
(37050, 'Maquereau', 'Scomber Scombrus', 'MAQUE'),
(38160, 'Raie Pastenague commune', 'Dasyatis Pastinaca', 'RAIEP'),
(42020, 'Crabe tourteau de casier', 'Cancer Pagurus', 'CRABS'),
(42021, 'Crabe tourteau de chalut', 'Cancer Pagurus', 'CRABL'),
(44010, 'Langoustine', 'Nephrops norvegicus', 'LANGT'),
(57010, 'Seiche', 'Sepia SPP', 'SEICH'),
(57020, 'Calmar', 'Loligo SPP', 'CALAM'),
(57050, 'Poulpe', 'Octopus SPP', 'POULP');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_facturation` date NOT NULL,
  `etat` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `lot`
--

DROP TABLE IF EXISTS `lot`;
CREATE TABLE IF NOT EXISTS `lot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datePeche` date NOT NULL,
  `idBateau` int NOT NULL,
  `idEspece` int NOT NULL,
  `idTaille` int NOT NULL,
  `idPresentation` varchar(5) COLLATE latin1_general_ci NOT NULL,
  `idQualite` char(1) COLLATE latin1_general_ci NOT NULL,
  `poidsBrutLot` decimal(6,2) DEFAULT NULL,
  `prixEnchere` decimal(6,2) DEFAULT NULL,
  `dateEnchere` date DEFAULT NULL,
  `HeureDebutEnchere` datetime DEFAULT NULL,
  `prixPlancher` decimal(6,2) DEFAULT NULL,
  `prixDepart` decimal(6,2) DEFAULT NULL,
  `codeEtat` char(1) COLLATE latin1_general_ci DEFAULT NULL,
  `idFacture` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDEspece` (`idEspece`),
  KEY `IDBateaufk_PECHE` (`datePeche`,`idBateau`),
  KEY `IdTaillefk` (`idTaille`),
  KEY `IdPresentationfk` (`idPresentation`),
  KEY `IdQualitefk` (`idQualite`),
  KEY `idx_Idbateau` (`idBateau`),
  KEY `idx_datePeche` (`datePeche`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `lot`
--

INSERT INTO `lot` (`id`, `datePeche`, `idBateau`, `idEspece`, `idTaille`, `idPresentation`, `idQualite`, `poidsBrutLot`, `prixEnchere`, `dateEnchere`, `HeureDebutEnchere`, `prixPlancher`, `prixDepart`, `codeEtat`, `idFacture`) VALUES
(26, '2024-04-09', 3, 33091, 35, 'VID', 'E', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(25, '2024-04-10', 18, 42040, 90, 'ENT', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(21, '2024-04-10', 17, 32130, 20, 'VID', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, '2024-04-10', 17, 57050, 85, 'ENT', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, '2024-04-10', 18, 44010, 90, 'VID', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, '2024-04-10', 16, 32020, 65, 'ET', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, '2024-04-10', 2, 42040, 75, 'ENT', 'E', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(30, '2024-04-11', 2, 33760, 15, 'VID', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, '2024-04-12', 17, 33500, 70, 'ET', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, '2024-04-14', 19, 33761, 10, 'ET', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Déclencheurs `lot`
--
DROP TRIGGER IF EXISTS `delete_bac_on_delete_lot`;
DELIMITER $$
CREATE TRIGGER `delete_bac_on_delete_lot` AFTER DELETE ON `lot` FOR EACH ROW BEGIN
    DELETE FROM bac WHERE IdLot = OLD.id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `peche`
--

DROP TABLE IF EXISTS `peche`;
CREATE TABLE IF NOT EXISTS `peche` (
  `idDatepeche` date NOT NULL,
  `idBateau` int NOT NULL,
  PRIMARY KEY (`idDatepeche`,`idBateau`),
  KEY `idBateau` (`idBateau`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `peche`
--

INSERT INTO `peche` (`idDatepeche`, `idBateau`) VALUES
('2022-07-18', 1),
('2022-07-18', 4),
('2022-07-18', 9),
('2022-07-18', 11),
('2022-07-20', 11),
('2022-07-21', 11),
('2022-07-23', 11),
('2022-07-24', 1),
('2022-07-24', 11),
('2022-07-25', 1),
('2022-07-25', 3),
('2022-07-25', 7),
('2022-07-25', 11),
('2022-07-30', 1),
('2022-07-30', 3),
('2022-07-30', 7),
('2022-07-30', 11),
('2022-08-12', 5),
('2022-08-12', 9),
('2022-08-25', 3),
('2022-08-25', 11);

-- --------------------------------------------------------

--
-- Structure de la table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
CREATE TABLE IF NOT EXISTS `presentation` (
  `id` varchar(5) COLLATE latin1_general_ci NOT NULL,
  `libelle` varchar(255) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `presentation`
--

INSERT INTO `presentation` (`id`, `libelle`) VALUES
('ET', 'Etété'),
('ENT', 'Entier'),
('VID', 'Vidé');

-- --------------------------------------------------------

--
-- Structure de la table `qualite`
--

DROP TABLE IF EXISTS `qualite`;
CREATE TABLE IF NOT EXISTS `qualite` (
  `id` char(1) COLLATE latin1_general_ci NOT NULL,
  `libelle` varchar(255) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `qualite`
--

INSERT INTO `qualite` (`id`, `libelle`) VALUES
('A', 'glacé'),
('B', 'déclassé'),
('E', 'extra');

-- --------------------------------------------------------

--
-- Structure de la table `taille`
--

DROP TABLE IF EXISTS `taille`;
CREATE TABLE IF NOT EXISTS `taille` (
  `id` int NOT NULL AUTO_INCREMENT,
  `specification` varchar(255) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=96 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `taille`
--

INSERT INTO `taille` (`id`, `specification`) VALUES
(10, 'TAILLE de 10'),
(15, 'TAILLE de 15'),
(20, 'TAILLE de 20'),
(25, 'TAILLE de 25'),
(30, 'TAILLE de 30'),
(35, 'TAILLE de 35'),
(40, 'TAILLE de 40'),
(45, 'TAILLE de 45'),
(50, 'TAILLE de 50'),
(55, 'TAILLE de 55'),
(60, 'TAILLE de 60'),
(65, 'TAILLE de 65'),
(70, 'TAILLE de 70'),
(75, 'TAILLE de 75'),
(80, 'TAILLE de 80'),
(85, 'TAILLE de 85'),
(90, 'TAILLE de 90'),
(95, 'TAILLE de 95');

-- --------------------------------------------------------

--
-- Structure de la table `typebac`
--

DROP TABLE IF EXISTS `typebac`;
CREATE TABLE IF NOT EXISTS `typebac` (
  `id` char(1) COLLATE latin1_general_ci NOT NULL,
  `tare` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `typebac`
--

INSERT INTO `typebac` (`id`, `tare`) VALUES
('B', '2.50'),
('F', '4.00');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
