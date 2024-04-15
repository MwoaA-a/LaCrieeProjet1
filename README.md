# La Criée Projet 1 (Groupe-01)

Il s'agit du premier projet de deuxième année de BTS SIO option SLAM réalisé en Java 1.8 sans Framework.

## Contexte du projet :

### Contexte générale :

La criée est située sur la commune de Plouhinec, département du Finistère (29), à proximité de la Pointe du Raz sur la zone de pêche Atlantique Nord Est. Une criée est une vente aux enchères d’une marchandise par lots. La Criée de Poulgoazec est un organisme dépendant de la Chambre de Commerce et d’Industrie (CCI) de Quimper (Concessionnaire) entretenu par le Conseil Général du Finistère (Concédant). Le site géographique de vente est identifié F-29.197.500-CE sur le quartier maritime d’Audierne (AD). 

La flotte du port d’Audierne est constituée de 40 bateaux (en grande majorité de moins de 12 mètres) qui y déposent leur pêche tous les jours. On y compte également une quinzaine de bateaux « extérieurs » qui effectuent des apports de façon irrégulière. La pêche pratiquée est qualifiée de « Petite pêche » et de « Pêche côtière », pour ces pêches l’absence du bateau du port est inférieure à 24 heures et jamais supérieure à 96 heures (ce type de pêche représente 92% de la pêche métropolitaine). La CCI de Quimper gère 7 ports en Cornouaille.

### Résultat et objectifs :

Si Poulgoazec est, sans conteste, la criée la plus petite, en tonnage, du Finistère, elle affiche un bilan financier qui n'a pas à rougir
devant les places fortes que sont les criées du sud bigouden. Depuis quelques années elle a su miser sur une production de qualité,
fruit de la pêche locale traditionnelle.

[...]

Selon son directeur, la survie de la criée implique une gestion fine des ressources, à savoir :

- Le maintien d’une flotte à Audierne/Plouhinec (maintien de l'emploi local)
- La présence de ressources humaines qualifiées pour la pêche afin de garantir la qualité des produits péchés.
- Le maintien du cours moyen en assurant la présence d’acheteurs
- La gestion de la ressource en poissons et crustacés qui relève d’une problématique nationale voire internationale (respectdes périodes de fraies).

Forte de ses atouts mais aussi consciente des enjeux, la criée de Poulgoazec veille depuis plusieurs années à la modernisation deson système d’information.

- Réduction des erreurs de calcul et de saisie sur les marquages des lots
- Accélération de la mise à disposition de l’information comptable pour les acteurs (pêcheurs, acheteurs, mareyeurs, comptable)

## Annexes :

### Extrait de l'ancien Modèle relationnel concernant le processus à automatiser (sans les MàJ des nouvelles Users Stories) :

![Extrait_ancien_Modèle_relationnel](https://github.com/MwoaA-a/LaCrieeProjet1/assets/145756714/75408d79-3888-4f07-a493-c3cfa463848b)

### Extrait du modèle entité association :

![modèle_entité_association](https://github.com/MwoaA-a/LaCrieeProjet1/assets/145756714/e0d51518-44ce-4677-a506-35d89a10a578)

Le modèle suivant vient d'un projet plus récent (projet 2 en PHP sur Symfony) et ne correspond donc pas au script de base de données MySQL fourni plus bas.

![modèle_entité_association Projet 2](https://github.com/MwoaA-a/LaCrieeProjet1/assets/145756714/1483a8f9-5e13-415d-ab02-4a7834c8035a)

### Les Users Stories à réaliser (Toutes n'ont pas été réalisées) :

- En tant que vétérinaire je veux pouvoir créer une pêche du jour afin d’enregistrer les bateaux qui proposent des bacs à la vente
- En tant que vétérinaire je veux pouvoir supprimer une pêche du jour afin de corriger des erreurs éventuelles.
- En tant que vétérinaire je veux pouvoir créer un lot pour une pêche du jour afin de saisir les bacs ultérieurement.
- En tant que vétérinaire je veux pouvoir créer les bacs de poissons et les données associées pour un lot donné afin de préparer la pesée
- En tant que vétérinaire je veux pouvoir modifier les bacs de poissons et les données associées pour un lot donné afin de corriger des erreurs de saisie
- En tant que vétérinaire je veux pouvoir afficher tous les lots et les bacs de poissons pour une pêche donnée afin d’effectuer une vérification.
- En tant que vétérinaire je veux pouvoir supprimer un ou plusieurs bacs d’un lot afin de corriger une erreur.
- En tant que vétérinaire je veux pouvoir afficher la liste des lots pour vérifier la vente du jour.
- En tant que vétérinaire je veux pouvoir imprimer les étiquettes ETQP à coller sur chaque bac d’un lot.
- En tant que peseur marqueur je veux pouvoir afficher les lots pour une pêche du jour afin de pouvoir saisir les données ultérieurement.
- En tant que peseur marqueur je veux pouvoir saisir le poids des bacs pour un lot donné afin de pouvoir compléter les données destinées au mobiblock.
- En tant que peseur marqueur je veux pouvoir modifier le poids des bacs pour un lot donné du jour afin de pouvoir corriger les erreurs.
- En tant que peseur marqueur je veux pouvoir bloquer un ou plusieurs lots pour qu’ils ne puissent plus être modifiés ultérieurement. Ces lots sont prêts pour la vente.
- En tant que peseur marqueur je veux pouvoir imprimer le ticket « avant-vente » pour le déposer sur le lot.
- En tant que directeur des ventes je veux pouvoir afficher la liste des lots prêts pour la vente

## Ajout du projet :

Pour pouvoir tester le projet, il faut la base de données MySQL, et bien évidemment le projet, il y a aussi une release disponible qui utilise une base de données en ligne, donc il est uniquement nécessaire de télécharger le .jar.

### La Base de données MySQL :

[Base de données MySQL](bddcriee2.sql)
