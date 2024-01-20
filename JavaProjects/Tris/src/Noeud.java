public class Noeud <T extends Comparable <T>>{
    private T element;

    private Noeud <T> suivant;
    public Noeud () {
        this.element = null;
        this.suivant = null;
    }

    public Noeud (T element) {
        this.element = element;
        this.suivant = null;
    }

    public T getElement() {
        return element;
    }

    public Noeud<T> getSuivant() {
        return suivant;
    }

//    public void setElement(T element) {
//        this.element = element;
//    }

    public void setSuivant(Noeud<T> suivant) {
        this.suivant = suivant;
    }

    @Override
    public String toString() {
        return "Noeud{" +
                "element=" + element +
                ", suivant=" + suivant +
                '}';
    }
}
