# inf2050-H23-projet-equipe15
# Projet de Session - Hiver 2023
# Outils et Pratiques de Developpement Logiciel

## Pour commencer
Il s'agit d'un projet informatique du cour **INF2050 Outils et Pratiques de Developement Logiciel** pour la session d'hiver 2023 dans le cadre du BAC en Informatique & Génie Logiciel.
L'objectif est d'utiliser et de se familliariser avec les principaux outils utilisés pour le développement professionnel de logiciel moderne tel que `Git`.

## En quoi cela consiste ?

 L'application à développer est un logiciel qui calculera des remboursements de réclamations d'assurances de soins de santé.

Le logiciel ne possèdera pas d'interface utilisateur car il est destiné à être invoqué à partir d'une application web. Le contrat ne consiste donc qu'au développement du moteur de validation de l'application.


## Outils et Technologies utilisées
### Listes des technologies:
```
   Langage: JAVA
   Librairie: https://gitlab.com/sergedelil/uqam/-/tree/master/INF2050/ateliers/json-lib/lib
   
   
```


## Usage
### Presentation

Le programme devra prendre un fichier d'entrée comme argument lors de l'exécution du logiciel ainsi qu'un nom de fichier de sortie où sera placé le résultat de l'exécution du programme, dans une console.


Le programme devra ainsi respecter un certain nombre de calculs et d'exigences pour correctement générer le fichier de sortie.

1. Le fichier d'entrée, en format JSON, aura l'air de ceci:

 ```diff
 {
 "dossier": "A100323",
"mois": "2022-01",
      "reclamations": [
    {
       "soin": 100,
       "date": "2022-01-11",
       "montant": "234.00$"
    },
    {
       "soin": 200,
       "date": "2022-01-13",
       "montant": "90.00$"
   },
    {
       "soin": 334,
        "date": "2022-01-23",
       "montant": "125.00$"
    }
    ]
  }
   ```

2. Le fichier de résultat généré par le logiciel devra ressembler à ceci :

```diff
    {
    "dossier": "A100323",
    "mois": "2022-01",
    "remboursements": [
        {
        "soin": 100,
        "date": "2022-01-11",
        "montant": "58.50$"
        },
        {
        "soin": 200,
        "date": "2022-01-13",
        "montant": "22.50$"
        },
        {
        "soin": 334,
        "date": "2022-01-23",
        "montant": "0.00$"
        }
    ]
    "total": "81.00$"
    }

 ```



### Installation
Il suffit de télécharger le projet, en s'assurant d'avoir un fichier JSON valide, ainsi que les librairies necessaires à son utilisation.

Après avoir exécuter le programme, deux fichier jar seront générés dans le target. Le fichier a prendre en compte sera celui dont le nom est le même que le nom du projet "Remboursement_De_Reclamation_Assurance" .

## Contribution
##### Membres de l'equipe:
* Ryddel D. Guervens Nacius (En qualité de **SCRUM MASTER**)
* Alpha Mamadou Diallo
* Axelle Gloria Bationo
* Yvan Cabrel Layou Fongang