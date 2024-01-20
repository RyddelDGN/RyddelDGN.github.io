import java.util.ArrayList;

public class Main {

    public static boolean contains_ignore_case (String firstString, String secondString) {
        int j = 0;
        boolean contient = firstString != null && secondString != null &&
                !firstString.isEmpty() && !secondString.isEmpty() &&
                firstString.length() >= secondString.length();
        for (int i = 0; contient && i < firstString.length(); i++) {
            while (j < secondString.length() && contient) {
                if (Character.toLowerCase(secondString.charAt(j)) != Character.toLowerCase(firstString.charAt(i))) {
                    j = 0;
                    contient = false;
                } else {
                    j++;
                    break;
                }
                j++;
            }
            if (j == secondString.length()) return true;
        }
        return contient;
    }
    public static char recherche_une_lettre_plus_frequente(String phrase) {
        int [] compteur = new int [52];
        char lettreFrequente = ' ';
        int valMax = 0;
        if (phrase != null && !phrase.isEmpty()) {
            for (int i = 0; i < phrase.length(); i++) {
                if (Character.isLetter(phrase.charAt(i))) {
                    compteur [((int)phrase.charAt(i) - (int)'A') % compteur.length] ++;
                }
            }
            for (int i = 0; i < compteur.length; i++) {
                if (compteur[i] > valMax) {
                    valMax = compteur[i];
                    lettreFrequente = (char)('A' + i);
                }
            }
        }
        return lettreFrequente;
    }

    public static ArrayList<Character> recherche_liste_lettre_plus_frequente (String phrase) {
        int [] compteur = new int [52];
        ArrayList<Character> lettreFrequente = new ArrayList<>();
        int valMax = 0;
        if (phrase != null && !phrase.isEmpty()) {
            for (int i = 0; i < phrase.length(); i++) {
                if (Character.isLetter(phrase.charAt(i))) {
                    compteur [((int)phrase.charAt(i) - (int)'A') % compteur.length] ++;
                }
            }
            for (int i = 0; i < compteur.length; i++) {
                if (compteur[i] > valMax) {
                    valMax = compteur[i];
                    lettreFrequente.clear();
                    lettreFrequente.add((char) ('A' + i));
                } else if (compteur[i] == valMax) {
                    lettreFrequente.add((char) ('A' + i));
                }
            }
        }
        return lettreFrequente;
    }
    public static void main(String[] args) {
        char lettrePlusFrequente = recherche_une_lettre_plus_frequente("jhhHHggjk");
        ArrayList<Character> lettreFrequentes = recherche_liste_lettre_plus_frequente("jhHHhggjk");
        boolean contient = contains_ignore_case("Mam an ", "mam");
        System.out.println("une lettre plus frequente est : " + lettrePlusFrequente);
        System.out.println("Methode contains : " + contient);
        System.out.println("Methode contains array: ");
        for (Character lettreFrequente : lettreFrequentes) {
            System.out.print(lettreFrequente + " - ");
        }
    }
}