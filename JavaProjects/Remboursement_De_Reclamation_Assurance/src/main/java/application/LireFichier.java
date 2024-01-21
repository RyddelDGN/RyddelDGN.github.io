package application;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LireFichier {
    private String contenueFichier;

    public LireFichier () {}
    public LireFichier (String cheminFichier) {
        try {contenueFichier = IOUtils.toString(Files.readString(Paths.get(cheminFichier)).getBytes(),"UTF-8");
        } catch (IOException ignored) {}
    }

    public String getContenueFichier() {
        return contenueFichier;
    }

    /**
     * Cette methode fait l'affichage d'un fichier.
     */
    public void afficherFic () {System.out.println(contenueFichier);}
}