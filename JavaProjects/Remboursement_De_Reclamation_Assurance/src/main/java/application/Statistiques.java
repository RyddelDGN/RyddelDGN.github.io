package application;

import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Statistiques {
    private static final String MSG_RECLAM_REJETER = "Nombre de reclamations rejetees";
    private static final String MSG_RECLAM_TRATER = "Nombre de reclamations traitees";
    private static final String NBR_SOIN_0 = "Nombre de soin MASSOTHERAPIE (0)";
    private static final String NBR_SOIN_100 = "Nombre de soin OSTEOPATHIE (100)";
    private static final String NBR_SOIN_150 = "Nombre de soin KINESITHERAPIE (150)";
    private static final String NBR_SOIN_175 = "Nombre de soin MEDECIN GENERALISTE PRIVE (175)";
    private static final String NBR_SOIN_200 = "Nombre de soin PSYCHOLOGIE INDIVIDUELLE (200)";
    private static final String NBR_SOIN_300 = "Nombre de soin DENTAIRE [300 - 399]";
    private static final String NBR_SOIN_400 = "Nombre de soin NATUROPATHIE - ACUPONCTURE (400)";
    private static final String NBR_SOIN_500 = "Nombre de soin CHIROPRATIE (500)";
    private static final String NBR_SOIN_600 = "Nombre de soin PHYSIOTHERAPIE (600)";
    private static final String NBR_SOIN_700 = "Nombre de soin ORTHOPHONIE - ERGOTHERAPIE (700)";
    private static final String LISTE_SOINS = "Listes des Soins Valides ";
    private JSONObject jsonStatistiques = new JSONObject();
    private int nbrReclamTraite = 0;
    private int nbrReclamJete = 0;
    private int nbrSoin = 0;
    private GestionJson gestionJson;
    ValiderInfosReclamations infos;
    public Statistiques () {}
    public Statistiques (GestionJson unJson) {
        this.gestionJson = unJson;
        infos = new ValiderInfosReclamations(unJson);
    }

    /**
     * Cette methode permet de trouver le nombre de soinDentaire dans un GestionJson
     * @param unJson
     * @return
     */
    public int trouverSoinDentaire(GestionJson unJson) {nbrSoin = 0;
        if (unJson == null || unJson.getJsonArray() == null || unJson.getJsonArray().isEmpty()) return 0;
        else {
            for (int i = 0; i<unJson.getJsonArray().size(); i++) {
                boolean soinTrouve = unJson.getJsonArray().getJSONObject(i).has("soin") &&
                        new CategorieDeSoin(unJson.getJsonArray().getJSONObject(i).getInt("soin")).soinDentaire();
                if (soinTrouve) nbrSoin++;
            }
        }
        return nbrSoin;
    }

    /**
     * Cette methode permet de trouver le nombre de numero de soin specifique dans un GestionJson
     * @param numeroSoin
     * @param unJson
     * @return
     */
    public int trouverNbrSoin (int numeroSoin, GestionJson unJson) {
        nbrSoin = 0;
        if (unJson == null || unJson.getJsonArray() == null || unJson.getJsonArray().isEmpty()) return 0;
        else {
            for (int i = 0; i<unJson.getJsonArray().size(); i++) {
                if (unJson.getJsonArray().getJSONObject(i).has("soin")
                        && unJson.getJsonArray().getJSONObject(i).getInt("soin") == numeroSoin) nbrSoin++;}
        }
        return nbrSoin;
    }

    /**
     * Cette methode permet de trouver le nombre de fois qu'une information a ete traite
     * @return
     */
    public int trouverNbrReclamTraite () {
        if (infos == null) nbrReclamTraite = 0;
        else if (infos.validerInfosReclam()) nbrReclamTraite++;
        return nbrReclamTraite;
    }
    /**
     * Cette methode permet de trouver le nombre de fois qu'une information a ete rejete
     * @return
     */
    public int trouverNbrReclamJete () {
        if (infos == null) nbrReclamJete = 0;
        else if (!infos.validerInfosReclam()) nbrReclamJete++;
        return nbrReclamJete;
    }

    /**
     * Cette methode permet de creer un JsonObject de liste de soin.
     * @return
     */
    public JSONObject jsonListeSoins () {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NBR_SOIN_0,trouverNbrSoin(0,gestionJson));
        jsonObject.put(NBR_SOIN_100, trouverNbrSoin(100,gestionJson));
        jsonObject.put(NBR_SOIN_150, trouverNbrSoin(150,gestionJson));
        jsonObject.put(NBR_SOIN_175, trouverNbrSoin(175,gestionJson));
        jsonObject.put(NBR_SOIN_200, trouverNbrSoin(200,gestionJson));
        jsonObject.put(NBR_SOIN_300, trouverSoinDentaire(gestionJson));
        jsonObject.put(NBR_SOIN_400, trouverNbrSoin(400,gestionJson));
        jsonObject.put(NBR_SOIN_500, trouverNbrSoin(500,gestionJson));
        jsonObject.put(NBR_SOIN_600, trouverNbrSoin(600,gestionJson));
        jsonObject.put(NBR_SOIN_700, trouverNbrSoin(700,gestionJson));
        return jsonObject;
    }

    /**
     * Cette methode cree un JsonObect de statistique
     * @return
     */
    public JSONObject creerNvJsonStats1() {
        jsonStatistiques.put(MSG_RECLAM_TRATER, trouverNbrReclamTraite());
        jsonStatistiques.put(MSG_RECLAM_REJETER, trouverNbrReclamJete());
        jsonStatistiques.put("Listes des Soins Valides ", jsonListeSoins());
        return jsonStatistiques;
    }

    /**
     * Cette methode permet de savoir si un fichier existe.
     * @param nomFichier
     * @return
     */
    public boolean chercherFichier (String nomFichier) {
        File fichier = new File(nomFichier);
        return fichier.exists();
    }

    /**
     * Cette methode permet d'ecrire un nouveau fichier Json
     * @param unFichier
     */
    public void ecrireNvFichier (String unFichier) {
        try {
            FileWriter fichierAEcrire = new FileWriter (unFichier);
            fichierAEcrire.write(creerNvJsonStats1().toString(4));
            fichierAEcrire.close();
        } catch (IOException ignored) {}
    }

    /**
     * Cette methode permet de modifier un fichier Json existant
     * @param unFichier
     */
    public JSONObject creerJsonFicExistant (String unFichier) {
        LireFichier lecture = new LireFichier(unFichier);
        GestionJson gestionJson = new GestionJson(lecture.getContenueFichier());
        return gestionJson.getJsonObject();
    }

    /**
     * Cette methode permet de modifier le champ de reclamation traitee dans un fichier Json existant
     * @param unFichier
     * @throws IOException
     */
    public void ecrireFic1 (String unFichier) throws IOException {
        JSONObject jsonObject = creerJsonFicExistant(unFichier);
        int cles = (int) jsonObject.remove(MSG_RECLAM_TRATER);
        jsonObject.put(MSG_RECLAM_TRATER, (cles+1));
        jsonObject.remove(LISTE_SOINS);
        jsonObject.put(LISTE_SOINS, jsonListeSoins());
        FileWriter fichierAEcrire = new FileWriter (unFichier);
        fichierAEcrire.write(jsonObject.toString(4));
        fichierAEcrire.close();
    }

    /**
     * Cette methode permet de modifier le champ de reclamation rejetee dans un fichier Json existant
     * @param unFichier
     * @throws IOException
     */
    public void ecrireFic2 (String unFichier) throws IOException {
        JSONObject jsonObject = creerJsonFicExistant(unFichier);
        int cles = (int) jsonObject.remove(MSG_RECLAM_REJETER);
        jsonObject.put(MSG_RECLAM_REJETER, (cles+1));
        JSONObject unJson = (JSONObject) jsonObject.remove(LISTE_SOINS);
        jsonObject.put(LISTE_SOINS, unJson);
        FileWriter fichierAEcrire = new FileWriter (unFichier);
        fichierAEcrire.write(jsonObject.toString(4));
        fichierAEcrire.close();
    }

    /**
     * Cette permet de creer un fichier statistique
     * @param nomFichier
     */
    public void creerUnStats (String nomFichier) {
        try {
            if (infos == null) ecrireNvFichier(nomFichier);
            else if (!infos.validerInfosReclam() && !chercherFichier(nomFichier)) ecrireNvFichier(nomFichier);
            else if (!infos.validerInfosReclam() && chercherFichier(nomFichier)) ecrireFic2(nomFichier);
            else if (infos.validerInfosReclam() && !chercherFichier(nomFichier)) ecrireNvFichier(nomFichier);
            else if (infos.validerInfosReclam() && chercherFichier(nomFichier)) ecrireFic1(nomFichier);
        } catch (IOException ignored) {}
    }

    /**
     * Cette methode appelle une autre methode qui cree un statistique
     * @param nomFichier
     */
    public void ficStats (String nomFichier) {
        creerUnStats(nomFichier);
    }
}