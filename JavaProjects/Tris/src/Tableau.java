import java.util.ArrayList;

public class Tableau {
    private static <T> boolean estNullOuVide(T[] tableau) {
        return tableau == null || tableau.length < 1;
    }
    public static <T extends Comparable <T>> T [] trierTableauStatiqueDecroissant (T[] tableau) {
        if (estNullOuVide(tableau)) return tableau;
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau.length; j++) {
                T element = tableau[i];
                if (tableau[j].compareTo(tableau[i]) < 0) {
                    tableau[i] = tableau[j];
                    tableau[j] = element;
                }
            }
        }
        return tableau;
    }

    public static <T extends Comparable <T>> T [] trierTableauStatiqueCroissant (T[] tableau) {
        if (estNullOuVide(tableau)) return tableau;
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau.length; j++) {
                T element = tableau[i];
                if (tableau[j].compareTo(tableau[i]) > 0) {
                    tableau[i] = tableau[j];
                    tableau[j] = element;
                }
            }
        }
        return tableau;
    }

    public static <T extends Comparable<T>> ArrayList<T> trierTableauDynamiqueDecroissant(ArrayList<T> tableau) {
        if (tableau == null || tableau.isEmpty()) return tableau;
        for (int i = 0; i < tableau.size(); i++) {
            for (int j = 0; j < tableau.size(); j++) {
                T element = tableau.get(i);
                if (tableau.get(j).compareTo(tableau.get(i)) < 0) {
                    tableau.set(i, tableau.get(j));
                    tableau.set(j, element);
                }
            }
        }
        return tableau;
    }

    public static <T extends Comparable<T>> ArrayList<T> trierTableauDynamiqueCroissant(ArrayList<T> tableau) {
        if (tableau == null || tableau.isEmpty()) return tableau;
        for (int i = 0; i < tableau.size(); i++) {
            for (int j = 0; j < tableau.size(); j++) {
                T element = tableau.get(i);
                if (tableau.get(j).compareTo(tableau.get(i)) > 0) {
                    tableau.set(i, tableau.get(j));
                    tableau.set(j, element);
                }
            }
        }
        return tableau;
    }
}
