package application;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class GestionJson {
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private String erreurFormatJson;
    private int nbrClesJsonArray;
    public GestionJson () {}

    public GestionJson (String element) {
        try {
            if (element == null) {
                erreurFormatJson = "Le fichier d'entrer passe comme premmier argument n'existe pas";
                System.out.println("Le fichier d'entrer passe comme premmier argument n'existe pas");
            } else jsonObject = (JSONObject) JSONSerializer.toJSON(element);
        } catch (NullPointerException|JSONException e) {
            erreurFormatJson = "Le fichier d'entrer passe comme premmier argument n'a pas un format Json " +
                    "ou il y a une erreur dans l'un des champs du fichier";
        }
    }

    /**
     * @return
     */
    public JSONObject getJsonObject() {
        if (jsonObject == null || jsonObject.isEmpty())
            jsonObject = new JSONObject();
        return jsonObject;
    }

    public JSONArray getJsonArray() {
        try {
            if (jsonObject == null || jsonObject.isEmpty()) jsonArray = new JSONArray();
            else jsonArray = jsonObject.getJSONArray("reclamations");
        } catch (Exception e) {
            jsonArray = new JSONArray();
            erreurFormatJson = "Le fichier ne contient pas le champs reclamation ou le champs est null ou vide";
        }
        return jsonArray;
    }

    public String getErreurFormatJson() {
        return erreurFormatJson;
    }

    /**
     * Cette methode permet de trouver le nombre de cle dans un JsonObject.
     * @return
     */
    public int getNbrClesJsonObjet () {
        return jsonObject.size();
    }

    /**
     * Cette methode permet de trouver le nombre de cle dans un JsonArray.
     * @return
     */
    public int getNbrClesJsonArray () {
        boolean tailleValide = true;
        for (int i = 0; i < jsonArray.size() && tailleValide; i++) {
            nbrClesJsonArray = jsonArray.getJSONObject(i).size();
            tailleValide = jsonArray.getJSONObject(i).size() == 3;
        }
        return nbrClesJsonArray;
    }
}