package application;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class StatistiquesTest {

    private GestionJson gestionJson;
    private Statistiques statistiques;

    private String nomFichierStats = "statistiquesTest.json";

    @BeforeEach
    void SetUp(){
        String jsonStr = "{\"dossier\": \"D0001\", \"mois\": \"202201\", \"reclamations\": " +
                "[{\"soin\": 300, \"date\": \"2022-01-01\", \"montant\": \"100.00$\"}, " +
                "{\"soin\": 300, \"date\": \"2022-01-02\", \"montant\": \"200.00$\"}]}";
        gestionJson = new GestionJson(jsonStr);

        statistiques = new Statistiques(gestionJson);
    }

    @Test
    void trouverSoinDentaire() {
        int result = statistiques.trouverSoinDentaire(gestionJson);
        assertEquals(2, result);
    }

    @Test
    void trouverNbrSoin() {
        int result = statistiques.trouverNbrSoin(300, gestionJson);
        assertEquals(2, result);
    }

    @Test
    void trouverNbrReclamTraite() {
        int result = statistiques.trouverNbrReclamTraite();
        assertEquals(0, result);
    }

    @Test
    void trouverNbrReclamJete() {
        int result = statistiques.trouverNbrReclamJete();
        assertEquals(1, result);
    }

    @Test
    void jsonListeSoins() {
        JSONObject jsonListeSoins = statistiques.jsonListeSoins();
        assertNotNull(jsonListeSoins);
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin MASSOTHERAPIE (0)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin OSTEOPATHIE (100)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin KINESITHERAPIE (150)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin MEDECIN GENERALISTE PRIVE (175)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin PSYCHOLOGIE INDIVIDUELLE (200)"));
        assertEquals(2, jsonListeSoins.getInt("Nombre de soin DENTAIRE [300 - 399]"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin NATUROPATHIE - ACUPONCTURE (400)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin CHIROPRATIE (500)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin PHYSIOTHERAPIE (600)"));
        assertEquals(0, jsonListeSoins.getInt("Nombre de soin ORTHOPHONIE - ERGOTHERAPIE (700)"));
    }

    @Test
    void creerNvJsonStats1() {
        JSONObject jsonStats1 = statistiques.creerNvJsonStats1();
        assertNotNull(jsonStats1);
        assertEquals(0, jsonStats1.getInt("Nombre de reclamations traitees"));
        assertEquals(1, jsonStats1.getInt("Nombre de reclamations rejetees"));
        assertTrue(jsonStats1.has("Listes des Soins Valides "));
    }

    @Test
    void chercherFichier() {
        File fichier = new File(nomFichierStats);
        assertTrue(statistiques.chercherFichier(fichier.getPath()));
    }

    @Test
    void ecrireNvFichier() throws IOException {
        File fichier = new File(nomFichierStats);
        statistiques.ecrireNvFichier(fichier.getPath());
    }

    @Test
    void creerJsonFicExistant() {
        // Arrange
        JSONObject expected = new JSONObject();
        expected.put("Nombre de reclamations traitees", 5);
        expected.put("Nombre de reclamations rejetees", 2);
        expected.put("Listes des Soins Valides ", new JSONObject()
                .put("Nombre de soin MASSOTHERAPIE (0)", 3));

        JSONObject result = statistiques.creerJsonFicExistant(nomFichierStats);

        assertNotEquals(expected, result);

    }

    @Test
    void creerUnStats() {
        GestionJson gestionJson = new GestionJson();
        Statistiques stats = new Statistiques(gestionJson);
        File fichier = new File(nomFichierStats);

        stats.creerUnStats(fichier.getPath());

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("Nombre de reclamations traitees", 10);
        expectedJson.put("Nombre de reclamations rejetees", 5);
        expectedJson.put("Nombre de soin", 20);

        JSONObject actualJson = gestionJson.getJsonObject();

        assertNotEquals(expectedJson, actualJson);
    }

    @Test
    void ficStats() {
        File file = new File(nomFichierStats);
        GestionJson gestionJson = new GestionJson();

        Statistiques stats = new Statistiques(gestionJson);
        stats.ficStats(nomFichierStats);

        assertTrue(file.exists());

    }
}