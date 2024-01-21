package application;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ValiderJson {
    static final String MSG_NBR_CLES_INVALIDE = "Le nombre de cles JSON est invalide ";
    static boolean clesExiste = true;
    private int position;
    GestionJson jsonObject;
    JSONObject unJsonObject;
    JSONArray unJsonArray;
    private String msgInvalideJsonObject;
    private String msgInvalideJsonArray;
    private String msgInvalide;
    public ValiderJson () {}
    public ValiderJson ( GestionJson jsonObject) {
        this.jsonObject = jsonObject;
        this.unJsonObject = jsonObject.getJsonObject();
        this.unJsonArray = jsonObject.getJsonArray();
    }

    /**
     * Cette methode permet de valider le nombre de cle dans un ObjectJson
     * @return
     */
    public boolean validerNbrClesJsonObject () {
        if (jsonObject.getJsonObject() == null || jsonObject.getJsonObject().isEmpty())
            msgInvalideJsonObject = jsonObject.getErreurFormatJson();
        else if (jsonObject.getNbrClesJsonObjet() != 3)
            msgInvalideJsonObject = MSG_NBR_CLES_INVALIDE;
        return jsonObject.getNbrClesJsonObjet() == 3;
    }

    /**
     * Cette methode permet de valider le nombre de cle dans un JsonArray
     * @return
     */
    public boolean validerNbrClesJsonArray () {
        if (jsonObject.getJsonObject() == null || jsonObject.getJsonObject().isEmpty())
            msgInvalideJsonArray = jsonObject.getErreurFormatJson();
        else if (jsonObject.getNbrClesJsonArray() != 3)
            msgInvalideJsonArray = MSG_NBR_CLES_INVALIDE + " a la position #" + position + " du tableau reclamation";
        return jsonObject.getNbrClesJsonArray() == 3;
    }

    /**
     * Cette methode permet de valider les champs d'un jsonArray
     * @return
     */
    public boolean validerJsonArray() {
        position = 1;
        for (int i = 0; i < unJsonArray.size() && clesExiste; i++) {
            clesExiste = verifierChamps(unJsonArray.getJSONObject(i),"soin", Integer.class) &&
                    verifierChamps(unJsonArray.getJSONObject(i), "date", String.class) &&
                    verifierChamps(unJsonArray.getJSONObject(i), "montant", String.class);
            msgInvalideJsonArray = msgInvalideJsonObject + " a la position #" + position + " du tableau reclamation";
            position ++;
        }
        return clesExiste && validerNbrClesJsonArray();
    }

    /**
     * Cette methode permet de valider les champs d'un jsonObject
     * @param jsonObject
     * @param nomChamps
     * @param typeChamps
     * @return
     */
    public boolean verifierChamps (JSONObject jsonObject, String nomChamps, Class<?> typeChamps) {
        boolean estValide = true;
        if (jsonObject.containsKey(nomChamps)) {
            Object valeurChamps = jsonObject.get(nomChamps);
            if (!typeChamps.isInstance(valeurChamps)) {
                estValide = false;
                msgInvalideJsonObject = "La cles " + nomChamps + " n'est pas valide ou a ete dupliquee";
            }
        } else {
            estValide = false;
            msgInvalideJsonObject = "La cles " + nomChamps + " n'existe pas";
        }
        return estValide;
    }

    /**
     * cette methode permet de valider un JsonObject
     * @return
     */
    public boolean validerJsonObject () {
        return validerNbrClesJsonObject() && verifierChamps(jsonObject.getJsonObject(), "dossier", String.class)
                && verifierChamps(jsonObject.getJsonObject(), "mois", String.class) &&
                verifierChamps(jsonObject.getJsonObject(), "reclamations", JSONArray.class);
    }

    /**
     * Cette permet de valider un Json
     * @return
     */
    public boolean validerJson () {
        return validerJsonObject() && validerJsonArray();
    }

    public String getMsgInvalide() {
        if (!validerJsonObject()) {
            msgInvalide = msgInvalideJsonObject;
        } else if (!validerJsonArray()) {
            msgInvalide = msgInvalideJsonArray;
        }
        return msgInvalide;
    }
}