package application;

import net.sf.json.JSONObject;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ValiderInfosReclamations {
    static final String MSG_NUM_CLIENT_INVALIDE = "Le numero du client dans le champs dossier n'est pas valide";
    static final String MSG_NUM_DOSSIER_INVALIDE = "Le numero du dossier est invalide";
    static final String MSG_CAT_SOIN_INVALIDE = "La Categorie de Soin n'est pas valide pour le soin #";
    static final String MSG_TYPE_CONTRAT_INVALIDE = "Le type du contrat dans le champs dossier est invalide ou est absent";
    static final String MSG_DATE_NON_EGALE = "Les dates ne sont pas egales";
    static final String MSG_MONTANT_INVALIDE = "Le montant est invalide pour le soin #";
    static final String MSG_FORMAT_MOIS_INVALIDE = "Le format du champs mois n'est pas valide";
    static final String MSG_FORMAT_ANNEE_INVALIDE = "Le format du champs date n'est pas valide";

    static boolean estUnMontantValide = true;
    GestionJson jsonObject;
    ValiderJson json;

    private String msgInvalide;
    private InfosReclamations infosReclam;
//    public

    public ValiderInfosReclamations() {}
    public ValiderInfosReclamations(GestionJson jsonObject) {
        this.jsonObject = jsonObject;
        this.json = new ValiderJson(jsonObject);
    }

    private static final Predicate<String> estNullouVide = (x) -> x == null || x.isEmpty();

    /**
     * Cette methode verifie si le type de contrat est valide
     */
    private static final Predicate<String> contratValide = (x) -> x.equals("A") || x.equals("B") ||
            x.equals("C") || x.equals("D") || x.equals("E");

    /**
     * Cette methode verifie que la taille du numero de client est la bonne
     */
    private static final Predicate <String> tailleNumClient = (x) -> x.length() == 6;

    /**
     * Cette methode verfie que le montant est valide
     */
    private static final Predicate <String > validerMontant = (x) -> x.length() >= 5;

    /**
     * Cette methode que la valeur est bel et bien un decimal
     */
    private static final Predicate<String> validerDecimal = (x) -> x.charAt(0) == '.' || x.charAt(0) == ',' &&
            Character.isDigit(x.charAt(1))
            && Character.isDigit(x.charAt(2));

    /**
     * Cette methode verifie que le signe de dollar est bien present à la fin du montant
     */
    private static final Predicate <String> signeDollarPresent = (x) -> x.charAt(x.length() - 1) == '$';


    /**
     * Cette methode verifie qu'il s'agit d'un chiffre valide
     */
    private static final Predicate <String> estUnChiffre = (x) -> {
        boolean estValide = estNullouVide.negate().test(x);
        if (estValide){
            for (int i = 0; i < x.length() && estValide; i++) {
                estValide = Character.isDigit(x.charAt(i));
            }
        }
        return estValide;
    };

    /**
     * Cette methode permet de verifier si le numero de client est valide
     * @param infosReclamations
     * @return
     */
    public boolean validerNumClient (InfosReclamations infosReclamations) {
        boolean estValide = estNullouVide.negate().and(tailleNumClient).and(estUnChiffre).
                test(infosReclamations.getClient());
        if (!estValide) {
            if ((infosReclamations.getContrat() + infosReclamations.getClient()).length() > 7)
                msgInvalide = MSG_NUM_DOSSIER_INVALIDE;
            else msgInvalide = (MSG_NUM_CLIENT_INVALIDE);
        }
        return estValide;
    }

    /**
     * Cette methode permet de verifier si le type de contrat est valide
     * @param infosReclamations
     * @return
     */
    public boolean validerTypeContrat(InfosReclamations infosReclamations) {
        boolean estValide = contratValide.test(infosReclamations.getContrat());
        if (!estValide){
            if ((infosReclamations.getContrat() + infosReclamations.getClient()).length() > 7)
                msgInvalide = MSG_NUM_DOSSIER_INVALIDE;
            else msgInvalide = (MSG_TYPE_CONTRAT_INVALIDE);
        }
        return estValide;
    }

    /**
     * Cette methode permet de verifier si le numero de dossier client est valide
     * @param infosReclamations
     * @return
     */
    public boolean validerDossierClient(InfosReclamations infosReclamations) {
        return validerNumClient(infosReclamations) && validerTypeContrat(infosReclamations);
    }

    /**
     * Cette methode permet de verifier si la categorie de soin est valide
     * @param listeReclam
     * @return
     */
    public boolean validerCatSoin(List <Reclamations> listeReclam) {
        boolean estUnSoinValide = true;
        for (int i = 0; i<listeReclam.size() && estUnSoinValide; i++) {
            CategorieDeSoin categorieDeSoin = new CategorieDeSoin(listeReclam.get(i).getNumeroSoin());
            estUnSoinValide = categorieDeSoin.estUnSoinValide();
            if (!estUnSoinValide) { msgInvalide =  (MSG_CAT_SOIN_INVALIDE + (i+1) + " du tableau de Reclamation");}
        }
        return estUnSoinValide && !listeReclam.isEmpty();
    }

    /**
     * Cette methode permet de verifier si le montant est valide
     * @param uneListe
     * @return
     */
    public boolean validerMontantReclamations(List <Reclamations> uneListe){
        for (int i = 0; i<uneListe.size() && estUnMontantValide; i++) {
            String sousChaines = uneListe.get(i).getMontant().substring(uneListe.get(i).getMontant().length()-4);
            String autreSousChaines = uneListe.get(i).getMontant().substring(0, uneListe.get(i).getMontant().length()-4);
            estUnMontantValide = signeDollarPresent.test(uneListe.get(i).getMontant()) && validerMontant.test(uneListe.get(i).getMontant())
                    && validerDecimal.test(sousChaines) && estUnChiffre.test(autreSousChaines);
            if (!estUnMontantValide) msgInvalide = MSG_MONTANT_INVALIDE + (i+1) + " du tableau de Reclamation";
        }
        return estUnMontantValide && !uneListe.isEmpty();
    }
    /**
     * Cette methode valide le format des dates et valide l'egalite des mois dans le fichier Json entre
     * @param dateConsommer La date dans le tableau de reclamation
     * @param datereclamer la date d'infosReclamation
     * @return memeMois
     */
    public boolean validerDate (LocalDate dateConsommer, YearMonth datereclamer) {
        return dateConsommer == null || datereclamer == null;
    }

    public boolean validerInfosDate(List<Reclamations> reclamations, InfosReclamations infosReclamations, int position){
        boolean dateValide = false;
        position = 1;
        for (Reclamations reclamation : reclamations) {
            dateValide = validerEgaliteDate(reclamation,infosReclamations, position);
            position++;
            if (!dateValide) break;
        }
        return dateValide;
    }

    /**
     *
     * @return
     */
    public boolean validerDateInfosReclam () {
        int position = 0;
        return validerInfosDate(getListeReclamation(),getInfosReclam(),position);
    }

    /**
     * Cette methode permet de verifier l'egalite des dates
     * @param reclamations
     * @param infosReclamations
     * @param position
     * @return
     */
    public boolean validerEgaliteDate (Reclamations reclamations, InfosReclamations infosReclamations, int position) {
        boolean dateValide = false;
        if (!validerDate(reclamations.getDateReclamation(),infosReclamations.getDate())) {
            dateValide = (reclamations.getDateReclamation().getYear() == infosReclamations.getDate().getYear()) &&
                    (reclamations.getDateReclamation().getMonth() == infosReclamations.getDate().getMonth());
            if (!dateValide) msgInvalide = (MSG_DATE_NON_EGALE + " a la position #" + (position) + " du tableau de Reclamation");
        } else if (infosReclamations.getDate() == null) msgInvalide = (MSG_FORMAT_MOIS_INVALIDE);
        else if (reclamations.getDateReclamation() == null) msgInvalide = (MSG_FORMAT_ANNEE_INVALIDE+ " a la position #"
                + position  + " du tableau de Reclamation");
        return dateValide;
    }

    /**
     *
     * @return
     */
    public String getMsgInvalide() {
        if (!json.validerJson())
            msgInvalide = json.getMsgInvalide();
        return msgInvalide;
    }

    /**
     * Cette methode permet de trouver une InfosReclamations
     * @return
     */
    public InfosReclamations getInfosReclam() {
        infosReclam = new InfosReclamations();
        if (json.validerJson())
            infosReclam = new InfosReclamations(jsonObject.getJsonObject().getString("dossier").substring(1),
                    jsonObject.getJsonObject().getString("dossier").substring(0,1),
                    jsonObject.getJsonObject().getString("mois"),
                    getListeReclamation());
        return infosReclam;
    }

    /**
     * Cette methode permet de recuperer une liste de reclamation
     * @return
     */
    public List<Reclamations> getListeReclamation() {
        List <Reclamations> listeReclamation = new ArrayList<>();
        if (json.validerJson()) {
            for (int i = 0; i<jsonObject.getJsonArray().size(); i++) {
                JSONObject unJsonObject = jsonObject.getJsonArray().getJSONObject(i);
                Reclamations uneReclamation = new Reclamations(unJsonObject.getString("soin"),
                        unJsonObject.getString("date"), unJsonObject.getString("montant"));
                listeReclamation.add(uneReclamation);
            }
        }
        return listeReclamation;
    }

    /**
     * Cette methode permet de valider un tableau de reclamation
     * @return
     */
    public boolean validerTabReclam () {
        return validerCatSoin(getListeReclamation()) && validerDateInfosReclam()
                && validerMontantReclamations(getListeReclamation());
    }

    /**
     * Cette methode permet de valider une InformationReclamation
     * @return
     */
    public boolean validerInfosReclam () {
        return validerDossierClient(getInfosReclam()) && validerTabReclam();
    }
}