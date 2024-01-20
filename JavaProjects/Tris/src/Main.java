import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args) {

        /*System.out.println("Hello world!");
        int [] tableau = {1,5,9,3,33,4,2,20,0};
        System.out.println("Tableau avant tri");
        for (int i = 0; i < tableau.length; i++) {
            System.out.print(tableau[i] + " ");
        }
        tableau = trierTableauStatiqueDecroissant(tableau);
        System.out.println("\nTableau apres tri decroissant");
        for (int i = 0; i < tableau.length; i++) {
            System.out.print(tableau[i] + " ");
        }

        tableau = trierTableauStatiqueCroissant(tableau);
        System.out.println("\nTableau apres tri croissant");
        for (int i = 0; i < tableau.length; i++) {
            System.out.print(tableau[i] + " ");
        }*/


        /*Integer [] tableau2 = {1,5,9,3,33,4,2,20,0};
        String [] tab = {"ba", "ca", "ad"};
        System.out.println("Tableau avant tri");
        for (int i = 0; i < tableau2.length; i++) {
            System.out.print(tableau2[i] + " ");
        }
        tableau2 = Tableau.trierTableauStatiqueDecroissant(tableau2);
        System.out.println("\nTableau apres tri decroissant");
        for (int i = 0; i < tableau2.length; i++) {
            System.out.print(tableau2[i] + " ");
        }

        System.out.println("\nTableau avant tri croissant");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        tab = Tableau.trierTableauStatiqueCroissant(tab);
        System.out.println("\nTableau apres tri croissant");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }

        ArrayList <Integer> tab2 = new ArrayList<>(List.of(1, 3, 7, 2, 5, 4));
        System.out.println("\nTableau Array avant tri decroissant");
        for (int i = 0; i < tab2.size(); i++) {
            System.out.print(tab2.get(i) + " ");
        }
        tab2 = Tableau.trierTableauStatiqueDecroissant(tab2);
        System.out.println("\nTableau Array apres tri decroissant");
        for (int i = 0; i < tab2.size(); i++) {
            System.out.print(tab2.get(i) + " ");
        }*/

/*Noeud <Integer> unNoeud = new Noeud<>(8);


        int a = 5;
        ListeChainee <Integer> listeChainee = new ListeChainee<Integer>();
        listeChainee.ajouterElement(a);
        System.out.println(listeChainee);
        listeChainee.ajouterElement(7);
        System.out.println(listeChainee);
        System.out.println(listeChainee.ajouterElement(7));
        System.out.println(listeChainee);
        System.out.println("Le nombre d'element(s) dans la liste chainee : " + listeChainee.getNombreElement());

        ListeChainee <Integer> listeChainee2 = new ListeChainee<Integer>(unNoeud);
        System.out.println("Le nombre d'element(s) dans la liste chainee2 : " + listeChainee2.getNombreElement());
        listeChainee2.ajouterElement(7);
        listeChainee2.ajouterElement(6);
        System.out.println(listeChainee2);
        System.out.println("Le nombre d'element(s) dans la liste chainee2 : " + listeChainee2.getNombreElement());
        listeChainee2.retirerElement(7);
        listeChainee2.retirerElement(6);
        listeChainee2.retirerElement(8);
        System.out.println(listeChainee2);
        System.out.println("Le nombre d'element(s) dans la liste chainee2 : " + listeChainee2.getNombreElement());*/


        ArbreBinaire <Integer> arbre1 = new ArbreBinaire<>();
//        ArbreAvl <Integer> arbre1 = new ArbreAvl<>();
        arbre1.ajouterElement(10);
//        arbre1.ajouterElement(9);
//        arbre1.ajouterElement(11);
//        arbre1.ajouterElement(12);
//        arbre1.ajouterElement(17);
//        arbre1.ajouterElement(14);
//        arbre1.ajouterElement(13);
//        arbre1.ajouterElement(15);
//        arbre1.ajouterElement(18);
//        arbre1.ajouterElement(7);
//        arbre1.ajouterElement(5);
//        arbre1.ajouterElement(6);
//        arbre1.ajouterElement(4);
//        arbre1.ajouterElement(8);


        System.out.println(arbre1);

//        System.out.println("allp");

//        arbre1.retirerElement(17);

       System.out.println(arbre1);


    }
}