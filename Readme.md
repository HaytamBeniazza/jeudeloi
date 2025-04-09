# Nouvelle version du Jeu de l'Oie (v2)

Cette version repose une modélisation différente, avec
- des tests unitaires ;
- une intégration continue ;
- la génération de la documentation Javadoc ;
- la vérification de la qualité de code avec SonarQube ;
- un DAO JDBC avec sauvegarde d'une session de jeu ;
- une architecture de type MVP.

## Reste à faire

Ce projet présente des limites :
- le DAO est incomplet ;
- la case Prison avec changement de l'état du joueur n'est pas fait ;
- type de cases limités ;
- interaction avec les joueurs limitées.