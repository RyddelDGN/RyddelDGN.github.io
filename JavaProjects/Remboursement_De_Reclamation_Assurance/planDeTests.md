# INF2050-H23-projet-equipe15
# Projet de Session - Hiver 2023
# Outils et Pratiques de Developpement Logiciel
# Plan de tests


## Exigences fonctionnelles (concernant le sprint 1)

EF-001 : Prendre une fichier d'entrée comme argument

EF-002 : Spécifier le fichier de sortie à la console

EF-003 : Vérifier que le numero client est obligatoirement composé de 6 chiffres

EF-004 : Vérifier que les contrats valides sont seulement A, B, C et D 

EF-005 : Vérifier que les reclamations sont pour un même mois et sous le format AAAA-MM

EF-006 : Vérifier que la date de soumission est sous format AAAA-MM-JJ et valide

EF-007 : Vérifier que le signe $ est présent à la fin des montants

EF-008 : Vérifier que le numero de soin est valide

EF-009 : Générer le fichier de sortie avec un message d'erreur s'il y'a des données invalides


## Suites et cas de tests

EF-001 : ST-001 : Tester que le fichier d'entrée est pris comme argument

EF-002 : ST-002 : Tester que le fichier de sortie est spécifié à la console

EF-003 : ST-003 : Tester la composition du numero client

EF-004 : ST-004 : Tester les contrats valides

EF-005 : ST-005 : Tester le format valide et le mois des reclamations

EF-006 : ST-006 : Tester le format de la date de soumission

EF-007 : ST-007 : Tester la présence du signe $ à la fin des montants

EF-008 : ST-008 : Tester que le numero de soin est valide

EF-009 : ST-009 : Tester la génération du fichier de sortie avec message d'erreur

## Cas de tests

ST-001 : CT-001 : Entrer un fichier JSON valide 

                *Précondition : Le fichier doit correspondre aux caractéristiques
                d'un fichier JSON valide

                *Sortie attendue : le systeme doit prendre compte du fichier en argument

                *Priorité : Haute

ST-002 : CT-002 : Entrer un fichier JSON valide 

                *Précondition : Le fichier doit correspondre aux caractéristiques
                d'un fichier JSON valide

                *Sortie attendue : le systeme doit spécifier un fichier de sortie à la console

                *Priorité : Haute

ST-003 : CT-003 : Entrer un numero client invalide (ex: 098)

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit refuser le numero de client 

                *Priorité : Haute

ST-004 : CT-004 : Entrer une reclamation de contrat P

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit refuser le type de contrat 
                
                *Priorité : Haute

ST-005 : CT-005 : Entrer une reclamation de mois different

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit refuser la reclamation du mois différent 
                
                *Priorité : Haute

ST-006 : CT-006 : Entrer une date invalide (ex: 2023-02-31)

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit refuser les reclamations de cette date
                
                *Priorité : Moyenne

ST-007 : CT-007 : Entrer un montant pour une reclamation

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit ajouter à chaque affichage de somme le signe $
                
                *Priorité : Moyenne

ST-008 : CT-009 : Entrer un numero de soin invalide 

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit refuser la reclamation de ce soin
                
                *Priorité : Haute

ST-010 : CT-010 : Entrer une donnée invalide dans le fichier d'entrée

                *Précondition : Le fichier d'entrée doit avoir un format valide

                *Sortie attendue : le systeme doit afficher un message d'erreur dans le fichier de sortie
                
                *Priorité : Haute




## Exigences fonctionnelles (concernant le sprint 2)

EF-010 : Remplacer les propriétés Client et Contrat par la propriété Dossier

EF-011 : Vérifier que la propriété Dossier contient la lettre du contrat et le numero client

EF-012 : Vérifier que dans le fichier de sortie la propriété dossier a la meme valeur que celle du fichier d'entrée

EF-013 : Ajouter un nouveau type de contrat , le type E

EF-014 : Ajouter 2 nouveaux soins

EF-015 : Changer les pourcentages et maximum mensuel de certains soins

EF-016 : Faire des messages d'erreurs significatifs dans le fichier de sortie

EF-017 : Gérer si une propriété JSON manque au fichier d'entrée

EF-018 : Faire supporter la virgule dans le montant dans le fichier d'entrée

EF-019 : S'assurer que le fichier de sortie ne supporte que des points dans le montant

EF-020 : Ajout de la propriété total dans le fichier de sortie

EF-021 : Faire les manipulations de montant que dans la classe Monnaie


## Suites et cas de tests

EF-010 : ST-010 : Tester que Dossier est présent plutôt que client et contrat

EF-011 : ST-011 : Tester la composition de la fonction dossier

EF-012 : ST-012 : Tester la valeur de dossier à l'entrée et à la sortie

EF-013 : ST-013 : Tester l'ajout du type de contrat E

EF-014 : ST-014 : Tester l'ajout des soins Kinésithérapie et Médecin généraliste privé

EF-015 : ST-015 : Tester les nouvelles valeurs de pourcentages de remboursement et de maximum mensuel

EF-016 : ST-016 : Tester l'affichage des bons messages d'erreur selon les données invalides

EF-017 : ST-017 : Tester la présence de toutes les propriétés du fichier JSON d'entrée

EF-018 : ST-018 : Tester le support de la virgule dans le montant au fichier d'entrée

EF-019 : ST-019 : Tester le support unique des points dans le montant au fichier de sortie

EF-020 : ST-020 : Tester l'ajout de la propriété total dans le fichier de sortie

EF-021 : ST-021 : Tester que les calculs soient fait que dans la classe Monnaie

## Cas de tests

ST-010 : CT-010 : Entrer les propriétés client et contrat dans le fichier d'entrée 

                *Précondition : Le fichier doit avoir un format valide 

                *Sortie attendue : Erreur:"la propriété dossier est manquante"

                *Priorité : Haute

ST-011 : CT-011 : Entrer une valeur de dossier invalide (ex: j903)
 
                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : Erreur:"le numero de dossier est invalide"

                *Priorité : Haute

ST-012 : CT-012 : Entrer une valeur valide de dossier (ex: A110232)

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : Le fichier de sortie doit avoir la meme valeur de dossier

                *Priorité : Haute

ST-013 : CT-013 : Faire une réclamation pour un contrat E

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : le systeme doit faire le traitement pour les reclamations de contrat E

                *Priorité : Haute

ST-014 : CT-014 : Faire une reclamation du soin kinésithérapie

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : le systeme doit faire le traitement pour la reclamation de ce soin

                *Priorité : Haute

CT-015 : Faire une reclamation du soin médecin généraliste privé

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : le systeme doit faire le traitement pour la reclamation de ce soin

                *Priorité : Haute

ST-015 : CT-016 : Entrer une valeur invalide de soin

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : Erreur:"le numero de soin est invalide"

                *Priorité : Moyenne

ST-016 : CT-017 : Entrer un nombre invalide de proprieté

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : Erreur, selon si il ya une clé en plus ou en moins

                *Priorité : Haute

ST-017 : CT-018 : Entrer un montant avec virgule dans le fichier d'entrée

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : Le systeme doit pouvoir faire les calculs normalement

                *Priorité : Moyenne

ST-018 : CT-019 : Entrer un fichier JSON valide 

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : la priopriété total doit contenir le total de remboursement

                *Priorité : Moyenne

ST-019 : CT-020 : Entrer un fichier JSON valide 

                *Précondition : le fichier doit avoir un format valide

                *Sortie attendue : les calculs doivent se faire uniquement dans la classe monnaie

                *Priorité : Moyenne




## Exigences fonctionnelles (concernant le sprint 3)

EF-022 : Considerer le maximum mensuel de certains soins

EF-023 : Faire une accumulation de statistiques

EF-024 : Afficher les statistiques

EF-025 : Réinitialiser les statisques

## Suites et cas de tests

EF-022 : ST-020 : Tester que le remboursement des soins ne dépasse jamais le maximum mensuel

EF-023 : ST-021 : Tester que l'accumulation des statistiques incrémentes les bonnes valeurs

EF-024 : ST-022 : Tester que la fonction -S affiche les statistiques

EF-025 : ST-023 : Tester que la fonction -SR réinitialise les statistiques

## Cas de tests

ST-020 : CT-021 : Rembourser une somme de 300.00$ pour le soin 100

                *Précondition : Le fichier JSON d'entrée comporte une demande de reclamation de 300.00$ pour le soin numero 100

                *Sortie attendue : Le systeme ne rembourse que 250.00$ de cette reclamation

                *Priorité : Haute

ST-021 : CT-022 : Accumuler le nombre de reclamations valides traitées

                *Précondition : Le fichier JSON d'entrée comporte 4 reclamations valides

                *Sortie attendue : Le systeme traite les 4 reclamations et incremente le compteur de reclamations valides traitées de 4

                *Priorité : Moyenne

 CT-023 : Accumuler le nombre de reclamations réjetées

                *Précondition : Le fichier JSON d'entrée contient 2 reclamations non valides

                *Sortie attendue : Le systeme rejette les 2 reclamations et incremente le compteur de reclamations rejetées de 2

                *Priorité : Moyenne

ST-022 : CT-024 : Afficher les statistiques avec l'option -S

                *Précondition : Le fichier JSON d'entrée contient des reclamations, certaines valides et d'autres invalides

                *Sortie attendue : Le logiciel avec en paramètre l'option "-S" va générer un fichier contenant les statistiques du fichier JSON

                *Priorité : Moyenne

CT-025 : Réinitialiser les statistiques avec l'option -SR

                *Précondition : Le logiciel devra déja contenir des statistiques de reclamations ayant déja été traitées ou réjetées

                *Sortie attendue : Le fichier de statistiques devient vierge lorsqu'on passe en parametre l'option "-SR"

                *Priorité : Moyenne