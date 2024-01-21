package application;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValiderJsonTest {
    private GestionJson gestionJson;
    private ValiderJson validerJson;

    private GestionJson gestionJson2;
    private ValiderJson validerJson2;

    @BeforeEach
    void setUp() throws Exception {

        String jsonStr = "{\"dossier\": \"123456\", \"mois\": \"01-2022\", \"reclamations\": [{\"soin\": 100, \"date\": "+
                "\"01-01-2022\", \"montant\": \"50.00$\"}, " +
                "{\"soin\": 200, \"date\": \"01-02-2022\", \"montant\": \"100.00$\"}, {\"soin\": 300, \"date\": " +
                "\"01-03-2022\", \"montant\": \"150.00$\"}]}";
        String jsonStr2 = "{\"dossier\": \"D0001\", \"mois\": \"202201\", \"reclamations\": [{\"soin\": 100, \"date\": "
                + " \"2022-01-01\", \"montant\": \"100.00$\"}, {\"soin\": 200, \"date\": \"2022-01-02\", \"montant\": \"200.00$\"}]}";

        gestionJson = new GestionJson(jsonStr);
        validerJson = new ValiderJson(gestionJson);

        gestionJson2 = new GestionJson(jsonStr2);
        validerJson2 = new ValiderJson(gestionJson2);
    }

    @Test
    void validerNbrClesJsonObject() {
        assertTrue(validerJson.validerNbrClesJsonObject());

        String jsonStr = "{\"dossier\": \"A123456\", \"mois\": \"2022-01\", \"reclamations\": " +
                "[{\"soin\": 100, \"date\": \"2022-01-01\", \"montant\": \"50.00$\"}, " +
                "{\"soin\": 200, \"date\": \"2022-01-02\", \"montant\": \"100.00$\"}, " +
                "{\"soin\": 300, \"date\": \"2022-01-03\", \"montant\": \"150.00$\"}], \"extra\": \"1\"}";
        gestionJson = new GestionJson(jsonStr);
        validerJson = new ValiderJson(gestionJson);

        assertFalse(validerJson.validerNbrClesJsonObject());
    }

    @Test
    void validerNbrClesJsonArray() {
        assertTrue(validerJson.validerNbrClesJsonArray());

        String jsonStr = "{\"mois\": \"2022-01\", \"reclamations\": " +
                "[{\"soin\": 100, \"date\": \"2022-01-01\", \"montant\": \"50.00$\"}, {\"soin\": 200, \"date\": " +
                "\"2022-01-02\"}], \"dossier\": \"D123456\"}";
        gestionJson = new GestionJson(jsonStr);
        validerJson = new ValiderJson(gestionJson);

        assertFalse(validerJson.validerNbrClesJsonArray());
    }

    @Test
    void validerJsonArray() {
        assertFalse(validerJson2.validerJsonArray(), "JSON array ne devrait pas etre valide");
    }

    @Test
    void verifierChamps() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("soin", 100);
        jsonObject.put("date", "2022-01-01");
        jsonObject.put("montant", "100.00");

        assertTrue(validerJson2.verifierChamps(jsonObject, "soin", Integer.class), "Champ 'soin' devrait etre valid");
        assertTrue(validerJson2.verifierChamps(jsonObject, "date", String.class), "Champ 'date' devrait etre valid");
        assertTrue(validerJson2.verifierChamps(jsonObject, "montant", String.class), "Champ 'montant' devrait etre valid");
    }

    @Test
    void validerJsonObject() {
        assertTrue(validerJson2.validerJsonObject(), "JSON object devrait etre valide");
        assertNull(validerJson2.getMsgInvalide(), "message invalide devrait etre null");
    }

    @Test
    void validerJson() {
        assertTrue(validerJson2.validerJson(), "JSON devrait valid");
        assertNull(validerJson2.getMsgInvalide(), "message invalide devrait etre null");
    }

    @Test
    void getMsgInvalide() {
        JSONArray jsonArray = gestionJson.getJsonArray();
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        jsonObject.remove("soin");
        assertFalse(validerJson.validerJsonArray());

        assertEquals("La cles soin n'existe pas a la position #1 du tableau reclamation", validerJson.getMsgInvalide());
    }
}
