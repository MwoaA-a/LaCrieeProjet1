-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 23 avr. 2024 à 14:45
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
-- Base de données : `bddcriee`
--
CREATE DATABASE IF NOT EXISTS `bddcriee` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `bddcriee`;

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
  `id` int NOT NULL,
  `id_lot` int NOT NULL,
  `id_type_bac` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`id_lot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `bac`
--

INSERT INTO `bac` (`id`, `id_lot`, `id_type_bac`) VALUES
(1, 20, '1'),
(1, 21, '2'),
(1, 22, '1'),
(1, 23, '1'),
(1, 24, '1'),
(1, 26, '1'),
(1, 31, '2'),
(1, 32, '1'),
(1, 33, '2'),
(1, 35, '1'),
(2, 20, '1'),
(2, 21, '2'),
(2, 22, '2'),
(2, 23, '2'),
(2, 24, '2'),
(2, 31, '1'),
(3, 20, '2'),
(3, 21, '1'),
(3, 22, '1'),
(3, 23, '1'),
(3, 24, '1');

-- --------------------------------------------------------

--
-- Structure de la table `bateau`
--

DROP TABLE IF EXISTS `bateau`;
CREATE TABLE IF NOT EXISTS `bateau` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `immatriculation` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
-- Structure de la table `detail_fac`
--

DROP TABLE IF EXISTS `detail_fac`;
CREATE TABLE IF NOT EXISTS `detail_fac` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_lot` int NOT NULL,
  `id_fac` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8mb3_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20240111103250', '2024-01-11 10:39:05', 429),
('DoctrineMigrations\\Version20240111132256', '2024-01-11 13:23:05', 74),
('DoctrineMigrations\\Version20240111134625', '2024-01-11 13:46:32', 266),
('DoctrineMigrations\\Version20240420175307', '2024-04-20 18:00:27', 207),
('DoctrineMigrations\\Version20240420183024', '2024-04-20 18:30:46', 73),
('DoctrineMigrations\\Version20240420183335', '2024-04-20 18:33:41', 81),
('DoctrineMigrations\\Version20240420183429', '2024-04-20 18:34:32', 80),
('DoctrineMigrations\\Version20240420184149', '2024-04-20 18:42:17', 45),
('DoctrineMigrations\\Version20240420184300', '2024-04-20 18:43:06', 43),
('DoctrineMigrations\\Version20240421112900', '2024-04-21 11:29:09', 251),
('DoctrineMigrations\\Version20240421121727', '2024-04-21 12:17:41', 310),
('DoctrineMigrations\\Version20240421121838', '2024-04-21 12:18:45', 52),
('DoctrineMigrations\\Version20240421122114', '2024-04-21 12:21:19', 150),
('DoctrineMigrations\\Version20240421125423', '2024-04-21 12:54:27', 261);

-- --------------------------------------------------------

--
-- Structure de la table `espece`
--

DROP TABLE IF EXISTS `espece`;
CREATE TABLE IF NOT EXISTS `espece` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nom_scientifique` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nom_court` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `espece`
--

INSERT INTO `espece` (`id`, `nom`, `nom_scientifique`, `nom_court`) VALUES
(31020, 'Turbot', 'Psetta maxima', 'TURBO'),
(31030, 'Barbue', 'Scophthalmus rhombus', 'BARBU'),
(31150, 'Plie ou carrelet', 'Pleuronectes Platessa', 'PLIE'),
(32020, 'Merlu', 'Merluccius bilinearis', 'MERLU'),
(32050, 'Cabillaud', 'Gadus Morhua Morhue', 'CABIL'),
(32130, 'Lieu Jaune de Ligne', 'Pollachius pollachius', 'LJAUL'),
(32140, 'Lieu Noir', 'Lophius Virens', 'LNOI'),
(32230, 'Lingue franche', 'Molva Molva', 'LINGU'),
(33020, 'Congre commun', 'Conger conger', 'CONGR'),
(33080, 'Saint Pierre', 'Zeus Faber', 'STPIE'),
(33090, 'Bar de Chalut', 'Dicentrarchus Labrax', 'BARCH'),
(33091, 'Bar de Ligne', 'Dicentrarchus Labrax', 'BARLI'),
(33110, 'Mérou ou cernier', 'Polyprion Americanus', 'CERNI'),
(33120, 'Mérou noir', 'Epinephelus Guaza', 'MEROU'),
(33410, 'Rouget Barbet', 'Mullus SPP', 'ROUGT'),
(33450, 'Dorade royale chalut', 'Sparus Aurata', 'DORAC'),
(33451, 'Dorade royale ligne', 'Sparus Aurata', 'DORAL'),
(33480, 'Dorade rose', 'Pagellus bogaraveo', 'DORAD'),
(33490, 'Pageot Acarne', 'Pagellus Acarne', 'PAGEO'),
(33500, 'Pageot Commun', 'Pagellus Arythrinus', 'PAGEC'),
(33580, 'Vieille', 'LabrusBergylta', 'VIEIL'),
(33730, 'Grondin gris', 'Eutrigla Gurnadus', 'GRONG'),
(33740, 'Grondin rouge', 'Aspitrigla Cuculus', 'GRONR'),
(33760, 'Baudroie', 'Lophius Piscatorus', 'BAUDR'),
(33761, 'Baudroie Maigre', 'Lophius Piscicatorius', 'BAUDM'),
(33790, 'Grondin Camard', 'Trigloporus Lastoviza', 'GRONC'),
(33800, 'Grondin Perlon', 'Trigla Lucerna', 'GRONP'),
(34150, 'Mulet', 'Mugil SPP', 'MULET'),
(35040, 'Sardine atlantique', 'Sardina Pilchardus', 'SARDI'),
(37050, 'Maquereau', 'Scomber Scombrus', 'MAQUE'),
(38150, 'Raie douce', 'Raja Montagui', 'RAIE'),
(38160, 'Raie Pastenague commune', 'Dasyatis Pastinaca', 'RAIEP'),
(42020, 'Crabe tourteau de casier', 'Cancer Pagurus', 'CRABS'),
(42021, 'Crabe tourteau de chalut', 'Cancer Pagurus', 'CRABL'),
(42040, 'Araignée de mer casier', 'Maja squinado', 'ARAIS'),
(42041, 'Araignée de mer chalut', 'Maja squinado', 'ARAIL'),
(43010, 'Homard', 'Hammarus gammorus', 'HOMAR'),
(43030, 'Langouste rouge', 'Palinurus elephas', 'LANGR'),
(44010, 'Langoustine', 'Nephrops norvegicus', 'LANGT'),
(57010, 'Seiche', 'Sepia SPP', 'SEICH'),
(57020, 'Calmar', 'Loligo SPP', 'CALAM'),
(57050, 'Poulpe', 'Octopus SPP', 'POULP');

-- --------------------------------------------------------

--
-- Structure de la table `etat_lots`
--

DROP TABLE IF EXISTS `etat_lots`;
CREATE TABLE IF NOT EXISTS `etat_lots` (
  `id` int NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `etat_lots`
--

INSERT INTO `etat_lots` (`id`, `label`) VALUES
(1, 'Non vendue'),
(2, 'Vendue'),
(3, 'Equarrissage'),
(4, 'Don');

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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`id`, `date_facturation`, `etat`) VALUES
(1, '2024-04-20', 0),
(2, '2024-04-20', 1),
(3, '2024-04-20', 1),
(4, '2024-04-20', 1),
(5, '2024-04-20', 1),
(7, '2024-04-20', 1),
(8, '2024-04-20', 1),
(9, '2024-04-20', 1),
(10, '2024-04-20', 1),
(11, '2024-04-20', 1),
(12, '2024-04-20', 1),
(13, '2024-04-20', 1),
(14, '2024-04-20', 1),
(15, '2024-04-20', 1),
(16, '2024-04-20', 1),
(17, '2024-04-20', 1),
(18, '2024-04-20', 1),
(19, '2024-04-20', 1),
(20, '2024-04-20', 1),
(21, '2024-04-20', 1),
(22, '2024-04-20', 1),
(23, '2024-04-20', 1),
(24, '2024-04-20', 1),
(25, '2024-04-20', 1),
(26, '2024-04-20', 1),
(27, '2024-04-20', 1),
(28, '2024-04-20', 1),
(29, '2024-04-20', 1),
(30, '2024-04-20', 1),
(31, '2024-04-20', 1),
(32, '2024-04-20', 1),
(33, '2024-04-20', 1),
(34, '2024-04-20', 1),
(35, '2024-04-20', 1),
(36, '2024-04-20', 1),
(37, '2024-04-20', 1),
(38, '2024-04-20', 1),
(39, '2024-04-20', 1),
(40, '2024-04-20', 1),
(41, '2024-04-20', 1),
(42, '2024-04-20', 1),
(43, '2024-04-20', 1),
(44, '2024-04-20', 1),
(45, '2024-04-20', 1),
(46, '2024-04-20', 1),
(47, '2024-04-20', 1),
(48, '2024-04-20', 1),
(49, '2024-04-20', 1),
(50, '2024-04-20', 1),
(51, '2024-04-20', 1),
(52, '2024-04-20', 1),
(53, '2024-04-20', 1),
(54, '2024-04-20', 1),
(55, '2024-04-20', 1),
(56, '2024-04-20', 1),
(57, '2024-04-20', 1),
(58, '2024-04-20', 1),
(59, '2024-04-20', 1),
(60, '2024-04-20', 1),
(61, '2024-04-20', 1),
(62, '2024-04-20', 1),
(63, '2024-04-20', 1),
(64, '2024-04-20', 1),
(65, '2024-04-20', 1),
(66, '2024-04-20', 1),
(67, '2024-04-20', 1),
(68, '2024-04-20', 1),
(69, '2024-04-20', 1),
(70, '2024-04-20', 1),
(71, '2024-04-20', 1),
(72, '2024-04-20', 1),
(73, '2024-04-20', 1),
(74, '2024-04-20', 1),
(75, '2024-04-20', 1),
(76, '2024-04-21', 1),
(77, '2024-04-23', 0);

-- --------------------------------------------------------

--
-- Structure de la table `lots`
--

DROP TABLE IF EXISTS `lots`;
CREATE TABLE IF NOT EXISTS `lots` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_peche` date NOT NULL,
  `num_bateau` int NOT NULL,
  `espece` int NOT NULL,
  `id_qualite` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_presentation` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_taille` int DEFAULT NULL,
  `code_etat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `poids_brut_lot` int DEFAULT NULL,
  `prix_plancher` int DEFAULT NULL,
  `prix_depart` int DEFAULT NULL,
  `prix_encheres_max` int DEFAULT NULL,
  `date_enchere` date DEFAULT NULL,
  `heure_debut_enchere` time DEFAULT NULL,
  `id_facture` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `lots`
--

INSERT INTO `lots` (`id`, `date_peche`, `num_bateau`, `espece`, `id_qualite`, `id_presentation`, `id_taille`, `code_etat`, `poids_brut_lot`, `prix_plancher`, `prix_depart`, `prix_encheres_max`, `date_enchere`, `heure_debut_enchere`, `id_facture`) VALUES
(1, '2024-03-28', 1, 13, NULL, NULL, NULL, '3', 132, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '2024-03-28', 3, 1, NULL, NULL, NULL, '3', 123, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '2024-03-28', 3, 2, NULL, NULL, NULL, '3', 420, NULL, NULL, NULL, NULL, NULL, NULL),
(4, '2024-04-11', 1, 12, NULL, NULL, NULL, '3', 122, NULL, NULL, NULL, NULL, NULL, NULL),
(5, '2024-04-11', 2, 1, NULL, NULL, NULL, '2', 123, NULL, NULL, NULL, NULL, NULL, NULL),
(6, '2024-04-11', 1, 33091, NULL, NULL, NULL, '2', 152, NULL, NULL, NULL, NULL, NULL, 76),
(7, '2024-04-11', 3, 2, NULL, NULL, NULL, '4', 981, NULL, NULL, NULL, NULL, NULL, NULL),
(8, '2024-04-15', 1, 2, NULL, NULL, NULL, '3', 872, NULL, NULL, NULL, NULL, NULL, NULL),
(9, '2024-04-15', 3, 1, NULL, NULL, NULL, '2', 122, NULL, NULL, NULL, NULL, NULL, NULL),
(10, '2024-04-09', 3, 33091, 'E', 'VID', 35, '2', NULL, NULL, NULL, NULL, NULL, NULL, 75),
(11, '2024-04-10', 18, 42040, 'B', 'ENT', 90, '2', NULL, NULL, NULL, NULL, NULL, NULL, 75),
(12, '2024-04-10', 17, 32130, 'B', 'VID', 20, '2', NULL, NULL, NULL, NULL, NULL, NULL, 76),
(13, '2024-04-10', 17, 57050, 'A', 'ENT', 85, '2', NULL, NULL, NULL, NULL, NULL, NULL, 76),
(14, '2024-04-10', 18, 44010, 'B', 'VID', 90, '2', NULL, NULL, NULL, NULL, NULL, NULL, 77),
(15, '2024-04-10', 16, 32020, 'B', 'ET', 65, '2', NULL, NULL, NULL, NULL, NULL, NULL, 77),
(16, '2024-04-10', 2, 42040, 'E', 'ENT', 75, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(17, '2024-04-11', 2, 33760, 'B', 'VID', 15, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(18, '2024-04-12', 17, 33500, 'B', 'ET', 70, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(19, '2024-04-14', 19, 33761, 'B', 'ET', 10, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, '2024-04-15', 16, 44010, 'B', 'ENT', 85, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(21, '2024-04-17', 1, 33091, 'B', 'ENT', 15, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, '2024-04-18', 4, 33091, 'E', 'VID', 25, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, '2024-04-23', 4, 31020, 'B', 'ENT', 15, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(25, '2024-04-23', 3, 31150, 'E', 'ET', 25, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

DROP TABLE IF EXISTS `messenger_messages`;
CREATE TABLE IF NOT EXISTS `messenger_messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `body` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `headers` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue_name` varchar(190) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `available_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `delivered_at` datetime DEFAULT NULL COMMENT '(DC2Type:datetime_immutable)',
  PRIMARY KEY (`id`),
  KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  KEY `IDX_75EA56E016BA31DB` (`delivered_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
CREATE TABLE IF NOT EXISTS `presentation` (
  `id` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL,
  `libelle` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `presentation`
--

INSERT INTO `presentation` (`id`, `libelle`) VALUES
('ENT', 'Entier'),
('ET', 'Etété'),
('VID', 'Vidé');

-- --------------------------------------------------------

--
-- Structure de la table `qualite`
--

DROP TABLE IF EXISTS `qualite`;
CREATE TABLE IF NOT EXISTS `qualite` (
  `id` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `libelle` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  `specification` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
-- Structure de la table `type_bac`
--

DROP TABLE IF EXISTS `type_bac`;
CREATE TABLE IF NOT EXISTS `type_bac` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tare` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `type_bac`
--

INSERT INTO `type_bac` (`id`, `tare`) VALUES
(1, 2.5),
(2, 4);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
