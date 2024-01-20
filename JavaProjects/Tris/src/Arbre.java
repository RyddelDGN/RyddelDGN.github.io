public class Arbre <T extends Comparable <T> >{

    private T element;
    private int equilibre;
    private Arbre <T> gauche;
    private Arbre <T> droite;

    public Arbre () {
        this.element = null;
        this.gauche = null;
        this.droite = null;
    }

    public Arbre (T element) {
        this.element = element;
        this.gauche = null;
        this.droite = null;
        this.equilibre = 0;
    }

    public T getElement() {
        return element;
    }

    public Arbre<T> getGauche() {
        return gauche;
    }

    public Arbre<T> getDroite() {
        return droite;
    }

    public void setEquilibre(int equilibre) {
        this.equilibre = equilibre;
    }

    public int getEquilibre() {
        return equilibre;
    }

    public void setGauche(Arbre<T> gauche) {
        this.gauche = gauche;
    }

    public void setElement(T element) {
        this.element = element;
    }

    //    public void setArbre(Arbre<T> arbre) {
//        this.getClass() = arbre;
//    }

    public void setDroite(Arbre<T> droite) {
        this.droite = droite;
    }

    @Override
    public String toString() {
        return "Arbre{" +
                "element=" + element +
                ", gauche=" + gauche +
                ", droite=" + droite +
                '}';
    }
}
