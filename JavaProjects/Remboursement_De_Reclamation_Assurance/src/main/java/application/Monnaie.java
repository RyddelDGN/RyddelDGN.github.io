package application;

public class Monnaie {
    public Monnaie(){}
    public static double calculer (int montant, double pourcentage) {
        return (int) (montant * pourcentage) / 100.0;
    }
}