-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 03 avr. 2023 à 20:37
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionachat`
--

-- --------------------------------------------------------

--
-- Structure de la table `acheter`
--

CREATE TABLE `acheter` (
  `codeclient` smallint(1) NOT NULL,
  `codeproduit` smallint(1) NOT NULL,
  `codeachat` int(11) NOT NULL,
  `qte` int(2) NOT NULL,
  `dateachat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `acheter`
--

INSERT INTO `acheter` (`codeclient`, `codeproduit`, `codeachat`, `qte`, `dateachat`) VALUES
(59, 1, 109, 100, '2023-04-03 12:24:13'),
(59, 1, 134, 30, '2023-04-03 12:04:58'),
(59, 3, 3, 30, '2023-03-31 11:13:37'),
(59, 3, 135, 30, '2023-04-03 16:35:51'),
(60, 1, 10, 30, '2023-04-03 12:49:00'),
(60, 3, 136, 30, '2023-04-03 16:36:03');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `codeclient` smallint(1) NOT NULL,
  `nom` varchar(128) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `sexe` char(2) NOT NULL,
  `login` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `datenaiss` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`codeclient`, `nom`, `email`, `sexe`, `login`, `password`, `datenaiss`) VALUES
(59, ' ange', NULL, 'F', 'manuella', '12345', NULL),
(60, 'manager', 'manager@test', 'M', 'manager', '123456', '2023-04-19 12:24:44');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `codeproduit` smallint(1) NOT NULL,
  `label` varchar(128) NOT NULL,
  `pu` decimal(10,2) NOT NULL,
  `qtestock` int(2) NOT NULL,
  `qteseuil` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`codeproduit`, `label`, `pu`, `qtestock`, `qteseuil`) VALUES
(1, 'sucre', '1500.00', 20, 10),
(3, 'bonbon djindja', '800.00', 20, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `acheter`
--
ALTER TABLE `acheter`
  ADD PRIMARY KEY (`codeclient`,`codeproduit`,`codeachat`),
  ADD KEY `fk_acheter_produit` (`codeproduit`),
  ADD KEY `Icodeachat` (`codeachat`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`codeclient`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`codeproduit`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `acheter`
--
ALTER TABLE `acheter`
  MODIFY `codeachat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `codeclient` smallint(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `codeproduit` smallint(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `acheter`
--
ALTER TABLE `acheter`
  ADD CONSTRAINT `fk_acheter_client` FOREIGN KEY (`codeclient`) REFERENCES `client` (`codeclient`),
  ADD CONSTRAINT `fk_acheter_produit` FOREIGN KEY (`codeproduit`) REFERENCES `produit` (`codeproduit`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
