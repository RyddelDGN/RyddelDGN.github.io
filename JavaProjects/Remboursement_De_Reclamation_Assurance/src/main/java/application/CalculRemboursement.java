package application;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * UQAM - HIVER 2023 - INF2050 - GROUPE 20 - PROJET DE SESSION.PARTIE01
 * application.CalculRemboursement : Cette classe sert à faire les calculs des reclamations du
 * client et genere le fichier contenant les calculs de reclamations.
 * @author EQUIPE N°15
 * @version 2023-04-23
 */
public class CalculRemboursement {
    Statistiques stats;
    GestionJson json;
    ValiderInfosReclamations infos;
    static double montantRembourse;

    static double [] montant = new double[2];
    public CalculRemboursement() {}

    public CalculRemboursement(GestionJson json) {
        this.json = json;
        infos = new ValiderInfosReclamations(json);
        stats = new Statistiques(json);
    }

    /**
     * Cette metho de calcul le total des montants qu'on a rembourses
     * @param infosReclamations
     * @return
     */
    public static double calculerTotal (InfosReclamations infosReclamations) {
        double total = 0;
        for (Reclamations listeRemboursement : infosReclamations.getReclamations()) {
            total += listeRemboursement.getMontantArgent();
        }
        return total;
    }

    /**
     * Cette methode calcule le montant à rembourser d'un soin
     * @param montantRembourser le montant a rembourser
     * @param montantMax le montant maximum qu'on peut rembourser
     * @return montant
     */
    public static double [] calculerMontantRembourse(double montantRembourser, double montantMax){
        if (montantRembourser < montantMax) {
            montantMax = montantMax - montantRembourser;
        } else if (montantRembourser >= montantMax) {
            montantRembourser = montantMax;
            montantMax = 0;
        }
        montant = tableauMontant(montantRembourser,montantMax);
        return montant;
    }


    /**
     * Cette methode fait le remboursement de la reclamation pour le soin massothérapie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserMassotherapie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinMassotherapie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Kinesitherapie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam le tableau des reclamations
     * @param montantMax le montant max
     * @param pourcentage le pourcentage a rembourser
     */
    public static void rembourserKinesitherapie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinKinesitherapie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Osteopathie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserOsteopathie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinOsteopathie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Psychologie Individuelle si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserPsychoIndividuelle(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinPsychologie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }


    /**
     * Cette methode fait le remboursement de la reclamation pour le soin dentaire sans montant maximum
     * @param listeReclam la liste des reclamations
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserSoinsDentaire(List<Reclamations> listeReclam, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (new CategorieDeSoin(reclamations.getNumeroSoin()).soinDentaire()) {
                montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
                reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
            }
        }
    }


    /**
     * Cette methode fait le remboursement de la reclamation pour le soin massotherapie sans montant maximum
     * @param listeReclam la liste des reclamations
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserMassotherapieSansMax(List<Reclamations> listeReclam, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (new CategorieDeSoin(reclamations.getNumeroSoin()).soinMassotherapie()) {
                montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
                reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
            }
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Kinesitherapie sans montant maximum
     * @param listeReclam tableau des reclamations
     * @param pourcentage le pourcentage a rembourser
     */
    public static void rembourserKinesitherapieSansMax(List<Reclamations> listeReclam, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (new CategorieDeSoin(reclamations.getNumeroSoin()).soinKinesitherapie()) {
                montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
                reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
            }
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin naturopathie sans montant maximum
     * @param listeReclam la liste des reclamations
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserNaturopathieSansMax(List<Reclamations> listeReclam, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (new CategorieDeSoin(reclamations.getNumeroSoin()).soinNaturopathie()) {
                montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
                reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
            }
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin orthophonie sans montant maximum
     * @param listeReclam la liste des reclamations
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserOrthophonieSansMax(List<Reclamations> listeReclam, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (new CategorieDeSoin(reclamations.getNumeroSoin()).soinOrthophonie()) {
                montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
                reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
            }
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Naturopathie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserNaturopathie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinNaturopathie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Chiropratie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserChiropratie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinChiropratie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }

    /**
     * Cette methode fait le remboursement de la reclamation pour le soin MedecinGeneralistePriver si le montant
     * maximum pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserMedecinGeneralistePriver(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinMedecinGeneralistePrive()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }


    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Physiotherapie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserPhysiotherapie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinPhysiotherapie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }
    /**
     * Cette methode fait le remboursement de la reclamation pour le soin Orthophonie si le montant maximum
     * pouvant etre rembourser est atteint
     * @param listeReclam la liste des reclamations
     * @param montantMax le montant maximum pouvant etre remboursé
     * @param pourcentage le pourcentage de remboursement pour le soin
     */
    public static void rembourserOrthophonie(List<Reclamations> listeReclam, double montantMax, double pourcentage) {
        for (Reclamations reclamations : listeReclam) {
            if (!new CategorieDeSoin(reclamations.getNumeroSoin()).soinOrthophonie()) continue;
            montantRembourse = Monnaie.calculer(reclamations.transform(),pourcentage);
            double [] montant = calculerMontantRembourse(montantRembourse,montantMax);
            montantRembourse = montant [0];
            montantMax = montant[1];
            reclamations.setMontant(String.format("%.2f", montantRembourse) + "$");
        }
    }

    /**
     * Cette methode crée un tableau contenant la liste de tous les remboursements presents dans le fichier Json
     * @param listeRemboursements la liste des remboursements
     * @return jsonArray
     */
    public static JSONArray creationTabJson(List<Reclamations> listeRemboursements) {
        JSONObject jsonObject = new JSONObject(); JSONArray jsonArray = new JSONArray();
        for (Reclamations listeRemboursement : listeRemboursements) {
            jsonObject.put("soin", listeRemboursement.getNumeroSoin());
            jsonObject.put("date", listeRemboursement.getDateReclamString());
            jsonObject.put("montant", listeRemboursement.getMontant());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * Cette methode montre la validité du fichier de sortie Json
     * @param infosReclamations les informations des reclamations
     * @return jsonObject
     */
    public static JSONObject fichierSortieValide(InfosReclamations infosReclamations)  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dossier",infosReclamations.getContrat() + infosReclamations.getClient());
        jsonObject.put("mois",infosReclamations.getDate() + "");
        jsonObject.put("remboursements", creationTabJson(infosReclamations.getReclamations()));
        jsonObject.put("Total",String.format("%.2f", calculerTotal(infosReclamations)) + "$");
        return jsonObject;
    }

    /**
     * Cette methode fait correspondre chaque pourcentage de remboursement au
     * soin qui lui est attribué dans le contrat A
     * @param listeReclamations la liste des reclamations du contrat
     */
    public static void rembourserContratA(List<Reclamations> listeReclamations) {
        rembourserMassotherapieSansMax(listeReclamations, 0.25);
        rembourserOsteopathie(listeReclamations,250.00,0.35);
        rembourserPsychoIndividuelle(listeReclamations,250.0,0.25);
        rembourserSoinsDentaire(listeReclamations,0);
        rembourserNaturopathieSansMax(listeReclamations, 0);
        rembourserChiropratie(listeReclamations,150.0,0.25);
        rembourserPhysiotherapie(listeReclamations,300.0,0.40);
        rembourserOrthophonieSansMax(listeReclamations, 0);
        rembourserKinesitherapieSansMax(listeReclamations,0);
        rembourserMedecinGeneralistePriver(listeReclamations,200.0,0.50);
    }
    /**
     * Cette methode fait correspondre chaque pourcentage de remboursement au
     * soin qui lui est attribué dans le contrat C
     * @param listeReclamations la liste des reclamations du contrat
     */
    public static void rembourserContratC(List<Reclamations> listeReclamations) {
        rembourserMassotherapieSansMax(listeReclamations, 0.90);
        rembourserOsteopathie(listeReclamations,250.00,0.95);
        rembourserPsychoIndividuelle(listeReclamations,250.0,0.90);
        rembourserSoinsDentaire(listeReclamations,0.90);
        rembourserNaturopathieSansMax(listeReclamations, 0.90);
        rembourserChiropratie(listeReclamations,150.0,0.90);
        rembourserPhysiotherapie(listeReclamations,300.0,0.75);
        rembourserOrthophonieSansMax(listeReclamations, 0.90);
        rembourserKinesitherapieSansMax(listeReclamations,0.85);
        rembourserMedecinGeneralistePriver(listeReclamations,200.0,0.90);
    }

    /**
     * Cette methode fait correspondre chaque pourcentage de remboursement au
     * soin qui lui est attribué dans le contrat D
     * @param listeReclamations la liste des reclamations du contrat
     */
    public static void rembourserContratD(List<Reclamations> listeReclamations) {
        rembourserMassotherapie(listeReclamations,85.0, 1);
        rembourserOsteopathie(listeReclamations,75.0, 1);
        rembourserPsychoIndividuelle(listeReclamations,100.0, 1);
        rembourserSoinsDentaire(listeReclamations,1);
        rembourserNaturopathie(listeReclamations,65.0, 1);
        rembourserChiropratie(listeReclamations,150.0,1);
        rembourserPhysiotherapie(listeReclamations,100.0, 1);
        rembourserOrthophonie(listeReclamations,90.0, 1);
        rembourserKinesitherapie(listeReclamations,150.0,1);
        rembourserMedecinGeneralistePriver(listeReclamations,200.0,0.95);
    }

    /**
     * Cette methode fait correspondre chaque pourcentage de remboursement au
     * soin qui lui est attribué dans le contrat E
     * @param listeReclamations la liste des reclamations du contrat
     */
    public static void rembourserContratE(List<Reclamations> listeReclamations) {
        rembourserMassotherapieSansMax(listeReclamations, 0.15);
        rembourserOsteopathie(listeReclamations,75.0, 0.25);
        rembourserPsychoIndividuelle(listeReclamations,250.0, 0.12);
        rembourserSoinsDentaire(listeReclamations,0.60);
        rembourserNaturopathie(listeReclamations,15.0, 0.25);
        rembourserChiropratie(listeReclamations,20.0, 0.30);
        rembourserPhysiotherapie(listeReclamations,300.0, 0.15);
        rembourserOrthophonieSansMax(listeReclamations, 0.22);
        rembourserKinesitherapieSansMax(listeReclamations,0.15);
        rembourserMedecinGeneralistePriver(listeReclamations,20,0.25);
    }
    /**
     * Cette methode fait correspondre chaque pourcentage de remboursement au
     * soin qui lui est attribué dans le contrat B
     * @param listeReclamations la liste des reclamations du contrat
     */
    public static void rembourserContratB(List<Reclamations> listeReclamations) {
        rembourserMassotherapie(listeReclamations,40.0, 0.50);
        rembourserOsteopathie(listeReclamations,50.0, 0.50);
        rembourserPsychoIndividuelle(listeReclamations,250.0, 1);
        rembourserSoinsDentaire(listeReclamations,0.50);
        rembourserNaturopathieSansMax(listeReclamations,0);
        rembourserChiropratie(listeReclamations,50.0, 0.50);
        rembourserPhysiotherapie(listeReclamations,300.0, 0.100);
        rembourserOrthophonieSansMax(listeReclamations,0.70);
        rembourserKinesitherapieSansMax(listeReclamations,0);
        rembourserMedecinGeneralistePriver(listeReclamations,200,0.75);
    }

    /**
     * Cette methode calcule le remboursement des soins d'un contrat précis
     * @param infosReclam les informations des soins
     */
    public static InfosReclamations calculerRemboursementTotal(InfosReclamations infosReclam) {
        switch (infosReclam.getContrat()) {
            case "A" -> rembourserContratA(infosReclam.getReclamations());
            case "B" -> rembourserContratB(infosReclam.getReclamations());
            case "C" -> rembourserContratC(infosReclam.getReclamations());
            case "D" -> rembourserContratD(infosReclam.getReclamations());
            case "E" -> rembourserContratE(infosReclam.getReclamations());
        }
        return infosReclam;
    }

    /**
     * Cette methode fait le calcul des remboursements si le fichier d'entre est valide.
     * @param fichierSortie
     * @param fichierStats
     */
    public void calculRemboursement (String fichierSortie, String fichierStats) {
        try {
            InfosReclamations unInfo = calculerRemboursementTotal(infos.getInfosReclam());
            ecrireFichierRemboursement(fichierSortie, unInfo);
            stats.ficStats(fichierStats);
        } catch (IOException ignored) {}
    }
    /**
     * Cette methode fait le calcul des remboursements si le fichier d'entre est invalide.
     * @param fichierSortie
     * @param fichierStats
     */
    public void calculRemboursementInvalide (String fichierSortie, String message, String fichierStats) {
        try {
            ecrireFichier(fichierSortie, message);
            stats.ficStats(fichierStats);
        } catch (IOException ignored) {}
    }

    /**
     * Cette methode ecrit le fichier de remboursement lorsque le fichier en entree est valide
     * @param fichierSortie
     * @param infosReclam
     * @throws IOException
     */
    public static void ecrireFichierRemboursement(String fichierSortie, InfosReclamations infosReclam)
            throws IOException {
        FileWriter fichierAEcrire = new FileWriter (fichierSortie);
        JSONObject unJson = fichierSortieValide(infosReclam);
        fichierAEcrire.write(unJson.toString(2,-1));
        fichierAEcrire.close();
    }

    /**
     * Cette methode ecrit un fichier de sortie contenant un message lorsque le fichier en entree est invalide
     * @param fichierSortie le fichier ou on ecrit
     */
    public void ecrireFichier(String fichierSortie, String message) throws IOException {
        FileWriter fichierAEcrire = new FileWriter (fichierSortie);
        JSONObject unJson = fichierSortieInvalide(message);
        fichierAEcrire.write(unJson.toString(4));
        fichierAEcrire.close();
    }

    /**
     * Cette methode montre l'invalidité du fichier de sortie Json par ses données
     * @return jsonObject
     */
    public static JSONObject fichierSortieInvalide(String message)  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        return jsonObject;
    }

    /**
     * Cette methode cree un tableau qui contiendra le montant rembourser et le montant maximum
     * @param montantRembourser Le montant qu'on va rembourser
     * @param montantMax Le montant maximum
     * @return tabMontant
     */
    public static double [] tableauMontant(double montantRembourser, double montantMax){
        double [] tabMontant = new double [2];
        tabMontant [0] = montantRembourser;
        tabMontant [1] = montantMax;
        return tabMontant;
    }
}