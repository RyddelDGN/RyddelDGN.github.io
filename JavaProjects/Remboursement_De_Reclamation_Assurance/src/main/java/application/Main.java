package application;

/**
 * UQAM - HIVER 2023 - INF2050 - GROUPE 20 - PROJET DE SESSION.PARTIE02
 * application.Main : Cette classe sert à l'implementation de toutes les methodes servant à executer le programme
 * de remboursement
 * @author EQUIPE N°15
 * @version 2023-03-19
 */


public class Main {
    private static final String nomFichierStats = "statistiques.json";

    /**
     * Cette methode exécute le programme avec les fichiers entrés
     */
    public static void executerProgramme(String [] arguments, String nomFichierStats)  {
        LireFichier lecture = new LireFichier(arguments[0]);
        GestionJson unJson = new GestionJson(lecture.getContenueFichier());
        ValiderInfosReclamations infos = new ValiderInfosReclamations(unJson);
        CalculRemboursement calculRemboursement = new CalculRemboursement(unJson);
        Statistiques stats = new Statistiques(unJson);
        try {
            if (infos.validerInfosReclam()) calculRemboursement.calculRemboursement(arguments[1], nomFichierStats);
            else calculRemboursement.calculRemboursementInvalide(arguments[1], infos.getMsgInvalide(), nomFichierStats);
        } catch (Exception e){creerStats(stats, nomFichierStats);}
    }

    /**
     * Cette methode cree un statistique.
     * @param statistiques
     * @param nomFichierStats
     */
    public static void creerStats (Statistiques statistiques, String nomFichierStats) {
        creerStatsVide(nomFichierStats);
        statistiques.ficStats(nomFichierStats);
    }

    /**
     * Cette methode verifie la valeur des arguments en parametre
     * @param args
     * @param nomFichierStats
     */
    public static void verifierValeurArgs (String [] args, String nomFichierStats) {
        if (args [0].equals ("-S")) {
            try { afficherStats(nomFichierStats);
            } catch (Exception e) {reinitialiserStats(nomFichierStats);}
        }
        else if (args [0].equals ("-SR")) {reinitialiserStats(nomFichierStats);}
        else System.out.println("L'arguments passes en parametre est invalide");
    }

    /**
     * Cette methode fait l'affichage d'un fichier statistique.
     * @param nomFichierStats
     */
    public static void afficherStats (String nomFichierStats) {
        LireFichier lecture = new LireFichier(nomFichierStats);
        if (lecture.getContenueFichier() == null) {
            Statistiques stats = new Statistiques();
            stats.ficStats(nomFichierStats);
            lecture = new LireFichier(nomFichierStats);
            lecture.afficherFic();
        } else lecture.afficherFic();
    }

    /**
     * Cette methode cree un fichier statistique
     * @param nomFichierStats
     */
    public static void creerStatsVide (String nomFichierStats) {
        Statistiques stats = new Statistiques();
        stats.ficStats(nomFichierStats);
    }

    /**
     * Cette methode reinitialise un fichier statistiques
     * @param nomFichierStats
     */
    public static void reinitialiserStats (String nomFichierStats){
        creerStatsVide(nomFichierStats);
        afficherStats(nomFichierStats);
    }

    /**
     * Cette methode fait la validation des arguments pour l'exécution du programme
     * @param arguments les arguments entrés pour l'exécution
     */
    public static void validerArguments(String [] arguments, String nomFichierStats) {
        if (arguments.length == 2) executerProgramme(arguments, nomFichierStats);
        else if (arguments.length == 1) verifierValeurArgs(arguments, nomFichierStats);
        else System.out.println("Le nombre d'arguments passes en parametre est invalide");
    }

    public static void main(String [] arguments) {
        validerArguments(arguments, nomFichierStats);
    }
}