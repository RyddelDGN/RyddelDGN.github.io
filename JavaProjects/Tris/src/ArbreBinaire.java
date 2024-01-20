public class ArbreBinaire <T extends Comparable <T>> {
    private Arbre <T> arbre;

    public ArbreBinaire () {
        this.arbre = null;
    }

    private boolean estPresent (T element) {
        Arbre <T> courant = arbre;
        while (courant != null) {
            Arbre <T> courantGauche = courant.getGauche();
            Arbre <T> courantDroite = courant.getDroite();
            if (element.compareTo(courant.getElement()) == 0) {
                return true;
            } else if (element.compareTo(courant.getElement()) < 0) {
                courant = courantGauche;
            } else {
                courant = courantDroite;
            }
        }
        return false;
    }
    public boolean ajouterElement (T element) {
        Arbre <T> nouvelArbre = new Arbre<>(element);
        boolean estAjouter = false;
        Arbre <T> courant = arbre;
        if (arbre == null || arbre.getElement() == null) {
            arbre = nouvelArbre;
            estAjouter = true;
        } else {
            while (courant != null && !estAjouter && !estPresent(element)) {
                if (element.compareTo(courant.getElement()) < 0) {
                    if (courant.getGauche() == null) {
                        courant.setGauche(nouvelArbre);
                        estAjouter = true;
                    }
                    courant = courant.getGauche();
                } else {
                    if (courant.getDroite() == null) {
                        courant.setDroite(nouvelArbre);
                        estAjouter = true;
                    }
                    courant = courant.getDroite();
                }
            }
        }
        return estAjouter;
    }

    /*public boolean retirerElement (T element) {
        boolean estRetirer = false;


        return estRetirer;
    }*/


    public boolean retirerElement(T element) {
        if (arbre == null) return false; // Rien à retirer, l'arbre est vide
        if (!estPresent(element)) return false; // L'élément n'est pas présent dans l'arbre
        arbre = retirerElement(arbre, element);
        return true;
    }

    private Arbre<T> retirerElement(Arbre<T> unArbre, T valeur) {
        if (unArbre == null) return null;
        int comparaison = valeur.compareTo(unArbre.getElement());
        if (comparaison < 0) { unArbre.setGauche(retirerElement(unArbre.getGauche(), valeur));// La valeur à supprimer est dans le sous-arbre gauche
        } else if (comparaison > 0) unArbre.setDroite(retirerElement(unArbre.getDroite(), valeur));// La valeur à supprimer est dans le sous-arbre droit
        else {
            // La valeur à supprimer est égale à l'élément actuel
            if (unArbre.getGauche() == null) return unArbre.getDroite(); // Cas 1: Pas de sous-arbre gauche
            else if (unArbre.getDroite() == null) return unArbre.getGauche(); // Cas 2: Pas de sous-arbre droit
            // Cas 3: Arbre avec deux enfants
            // Remplacez le nœud actuel par le nœud le plus petit du sous-arbre droit
            Arbre<T> nouveauNoeud = trouverNoeudMinimum(unArbre.getDroite());
            unArbre.setDroite(retirerElement(unArbre.getDroite(), nouveauNoeud.getElement()));
            // Mettez à jour les sous-arbres gauche et droit du nouveau nœud
            nouveauNoeud.setGauche(unArbre.getGauche());
            nouveauNoeud.setDroite(unArbre.getDroite());
            // Retournez le nouveau nœud
            return nouveauNoeud;
        }
        return unArbre;
    }

    private Arbre<T> trouverNoeudMinimum(Arbre<T> noeud) {
        while (noeud.getGauche() != null) {
            noeud = noeud.getGauche();
        }
        return noeud;
    }
    private T trouverMinimum(Arbre<T> noeud) {
        T minValeur = noeud.getElement();
        while (noeud.getGauche() != null) {
            minValeur = noeud.getGauche().getElement();
            noeud = noeud.getGauche();
        }
        return minValeur;
    }

    private void afficherArbre(Arbre <T> unArbre, String prefixe, boolean estDernier) {
        if (unArbre != null) {
            System.out.print(prefixe);
            System.out.print(estDernier ? "└──d " : "├──g ");
            System.out.println(unArbre.getElement());

            String nouveauPrefixe = prefixe + (estDernier ? "    " : "│   ");

            afficherArbre(unArbre.getGauche(), nouveauPrefixe, false);
            afficherArbre(unArbre.getDroite(), nouveauPrefixe, true);
        }
    }

    @Override
    public String toString() {
        afficherArbre(arbre, "", true);
        return "" ;
    }
}
