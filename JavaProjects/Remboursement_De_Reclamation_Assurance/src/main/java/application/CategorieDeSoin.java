package application;

/**
 * UQAM - HIVER 2023 - INF2050 - GROUPE 20 - PROJET DE SESSION.PARTIE01
 * application.CategorieDeSoin : Cette classe sert à recenser et verifier pour chaque categorie de soin, le numero
 *  de soin qui lui est attribué
 * @author EQUIPE N°15
 * @version 2023-02-09
 */


public class CategorieDeSoin {

    //Declaration d'attribut
    private final int numeroSoin;


    /**
     * @param numeroSoin le numero du type de soin
     */
    public CategorieDeSoin(int numeroSoin) {
        this.numeroSoin = numeroSoin;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Massotherapie
     * @return numeroSoin
     */
    public boolean soinMassotherapie () {
        return numeroSoin == 0;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Osteopathie
     * @return numeroSoin
     */
    public boolean soinOsteopathie () {
        return numeroSoin == 100;
    }
    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Kinesitherapie
     * @return numeroSoin
     */
    public boolean soinKinesitherapie () {return numeroSoin == 150;}
    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin MedecinGeneralistePrive
     * @return numeroSoin
     */
    public boolean soinMedecinGeneralistePrive () {return numeroSoin == 175;}

    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Psychologie
     * @return numeroSoin
     */
    public boolean soinPsychologie(){
        return numeroSoin == 200;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin dentaire
     * @return numeroSoin
     */
    public boolean soinDentaire() {
        return numeroSoin >= 300 && numeroSoin <= 399;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Naturopathie
     * @return numeroSoin
     */
    public boolean soinNaturopathie() {
        return numeroSoin == 400;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Chiropratie
     * @return numeroSoin
     */
    public boolean soinChiropratie () {
        return numeroSoin == 500;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Physiotherapie
     * @return numeroSoin
     */
    public boolean soinPhysiotherapie () {
        return numeroSoin == 600;
    }


    /**
     * Cette methode verifie que le numero de soin correspond à celui du soin Orthophonie
     * @return numeroSoin
     */
    public boolean soinOrthophonie() {
        return numeroSoin == 700;
    }


    /**
     * Cette methode verifie qu'un soin est valide, c'est-à-dire qu'il fait parti de la liste de soin inclue dans le
     * contrat de remboursement
     */
    public boolean estUnSoinValide () {
        return soinMassotherapie() || soinChiropratie() || soinDentaire() || soinOsteopathie() || soinPhysiotherapie()
                || soinOrthophonie() || soinNaturopathie() || soinPsychologie() || soinMedecinGeneralistePrive()
                || soinKinesitherapie();
    }
}