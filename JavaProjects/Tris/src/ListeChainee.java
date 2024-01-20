public class ListeChainee <T extends Comparable <T>> {
    private Noeud <T> chaines;
    private int nombreElement;

    public ListeChainee () {
        this.chaines = null;
        nombreElement = 0;
    }

    public ListeChainee (Noeud <T> noeud) {
        chaines = noeud;
        Noeud <T> courant = chaines;
        while (courant != null) {
            nombreElement++;
            courant = courant.getSuivant();
        }
    }

    private void noeudPrecedent (Noeud <T> nouveauNoeud, Noeud <T> precedent, Noeud <T> courant) {
        if (precedent == courant) {
            nouveauNoeud.setSuivant(courant);
            chaines = nouveauNoeud;
        } else {
            nouveauNoeud.setSuivant(courant);
            precedent.setSuivant(nouveauNoeud);
        }
    }

    private boolean ajoutFin (Noeud <T> nouveauNoeud, Noeud <T> courant) {
        boolean estAjouter = false;
        Noeud<T> precedent = courant;
        courant = courant.getSuivant();
        if (courant == null) {
            precedent.setSuivant(nouveauNoeud);
            estAjouter = true;
        }
        return estAjouter;
    }

    private boolean estPresent (T element) {
        Noeud <T> courant = chaines;
        while (courant != null) {
            if (element.compareTo(courant.getElement()) == 0) return true;
            courant = courant.getSuivant();
        }
        return false;
    }

    private boolean ajoutDebut (Noeud <T> nouveauNoeud) {
        chaines = nouveauNoeud;
        return true;
    }

    public boolean ajouterElement (T element) {
        Noeud <T> nouvauNoeud = new Noeud<>(element);
        Noeud <T> courant = chaines;
        boolean estAjouter = false;
        if (chaines == null || chaines.getElement() == null) estAjouter = ajoutDebut(nouvauNoeud);
        else {
            Noeud <T> precedent = courant;
            while (courant != null && !estAjouter && !estPresent(element)) {
                if (element.compareTo(courant.getElement()) < 0) {
                    noeudPrecedent(nouvauNoeud, precedent, courant);
                    estAjouter = true;
                } else {
                    estAjouter = ajoutFin(nouvauNoeud, courant);
                    courant = courant.getSuivant();
                }
            }
        }
        if (estAjouter) nombreElement++;
        return estAjouter;
    }

    public boolean retirerElement (T element) {
        boolean estRetirer = false;
        Noeud <T> courant = chaines;
        Noeud <T> precedent = null;
        while (courant != null) {
            if (element.compareTo(courant.getElement()) == 0) {
                if (chaines.getElement() == element) chaines = chaines.getSuivant();
                else precedent.setSuivant(courant.getSuivant());
                estRetirer = true;
            }
            precedent = courant;
            courant = courant.getSuivant();
        }
        if (estRetirer) nombreElement--;
        return estRetirer;
    }



    public int getNombreElement() {
        return nombreElement;
    }

    @Override
    public String toString() {
        return "ListeChainee{" +
                "chaines=" + chaines +
                '}';
    }
}
