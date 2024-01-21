package application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


class GestionJsonTest {
    private GestionJson gestionJson;
    private String jsonInput;

    @BeforeEach
    void setUp() {

        jsonInput = "{\"mois\": \"2022-12\", \"reclamations\":[{\"soin\": 200, " +
                "\"date\": \"2022-12-26\", \"montant\": \"1000.50$\"}, " +
                "{\"soin\": 300, \"date\": \"2022-12-28\", \"montant\": \"200.50$\"}], \"dossier\": \"B200323\"}";

        gestionJson = new GestionJson(jsonInput);
    }

    @Test
    void getJsonObject() {
        assertNotNull(gestionJson.getJsonObject());

        gestionJson = new GestionJson(null);
        assertTrue(gestionJson.getJsonObject().isEmpty());
    }

    @Test
    void getJsonArray() {

        assertNotNull(gestionJson.getJsonArray());

        assertEquals(2, gestionJson.getJsonArray().size());
    }

    @Test
    void getErreurFormatJson() {

        assertNull(gestionJson.getErreurFormatJson());

        gestionJson = new GestionJson("{invalid json}");

        assertNotNull(gestionJson.getErreurFormatJson());
    }

    @Test
    void getNbrClesJsonObjet() {
        assertEquals(3, gestionJson.getNbrClesJsonObjet());
    }


}