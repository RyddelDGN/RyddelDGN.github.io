package application;


import static org.junit.jupiter.api.Assertions.*;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ValiderInfosReclamationsTest {

    private static final String MSG_NUM_CLIENT_INVALIDE = "Le numero du client dans le champs dossier n'est pas valide";
    private static final String MSG_NUM_DOSSIER_INVALIDE = "Le numero du dossier est invalide";
    private static final String MSG_CAT_SOIN_INVALIDE = "La Categorie de Soin n'est pas valide pour le soin #";
    private static final String MSG_TYPE_CONTRAT_INVALIDE = "Le type du contrat dans le champs dossier est invalide ou est absent";
    private static final String MSG_DATE_NON_EGALE = "Les dates ne sont pas egales";
    private static final String MSG_MONTANT_INVALIDE = "Le montant est invalide pour le soin #";
    private static final String MSG_FORMAT_MOIS_INVALIDE = "Le format du champs mois n'est pas valide";
    private static final String MSG_FORMAT_ANNEE_INVALIDE = "Le format du champs date n'est pas valide";

    private Reclamations reclamations1 = new Reclamations("200",  "2022-12-26", "25.00$");
    private Reclamations reclamations2 = new Reclamations("300",  "2022-12-28", "25.00$");
    private Reclamations reclamations3 = new Reclamations("300",  "", "25.00$");
    private List<Reclamations> reclamations = Arrays.asList(reclamations1, reclamations2, reclamations3);
    private InfosReclamations infosReclamations = new InfosReclamations("200323", "B", "2022-12",
            reclamations);
    String jsonStr = "{\"mois\": \"2022-12\", \"reclamations\":[{\"soin\": 200, " + "\"date\": \"2022-12-26\", \"montant\": \"25.00$\"}, " +
            "{\"soin\": 200, " + "\"date\": \"2022-12-26\", \"montant\": \"25.00$\"}, " +
            "{\"soin\": 300, \"date\": \"2022-12-28\", \"montant\": \"25.00$\"}], \"dossier\": \"B200323\"}";

    private ValiderInfosReclamations validator;

    @BeforeEach
    void setUp() {
        JSONObject jsonObject = new JSONObject();
        validator = new ValiderInfosReclamations(new GestionJson(jsonStr));
    }

    @Test
    void validerNumClient() {
        boolean result = validator.validerNumClient(infosReclamations);
        assertTrue(result);
        assertNull(validator.getMsgInvalide());

    }

    @Test
    void validerTypeContrat() {
        boolean result = validator.validerTypeContrat(infosReclamations);
        assertTrue(result);
        assertNull(validator.getMsgInvalide());
    }

    @Test
    void validerDossierClient() {
        boolean result = validator.validerDossierClient(infosReclamations);
        assertTrue(result);
        assertNull(validator.getMsgInvalide());
    }

    @Test
    void validerCatSoin() {
        boolean estValide = validator.validerCatSoin(reclamations);
        assertTrue(estValide);
        assertNull(validator.getMsgInvalide());
    }

    @Test
    void validerMontantReclamations() {
        boolean estValide = validator.validerMontantReclamations(reclamations);
        assertTrue(estValide);
        assertNull(validator.getMsgInvalide());
    }

    @Test
    void validerDate() {
        boolean estValide = validator.validerDate( reclamations1.getDateReclamation() , infosReclamations.getDate());
        assertFalse(estValide);

    }

    @Test
    void validerInfosDate() {
        boolean estValide = validator.validerInfosDate(reclamations, infosReclamations, 1);
        assertFalse(estValide);
    }

    @Test
    void validerDateInfosReclam() {
        boolean estValide = validator.validerDateInfosReclam();
        assertFalse(estValide);
    }

    @Test
    void validerEgaliteDate() {
        boolean estValide = validator.validerEgaliteDate(reclamations1, infosReclamations, 1);
        assertTrue(estValide);
        estValide = validator.validerEgaliteDate(reclamations3, infosReclamations, 3);
        assertFalse(estValide);
    }

    @Test
    void getMsgInvalide() {
        InfosReclamations infosReclamationsInv = new InfosReclamations(null, "B", "2022-12",
                reclamations);
        assertFalse(validator.validerNumClient(infosReclamationsInv));
    }

    @Test
    void getInfosReclam() {
        InfosReclamations expected = new InfosReclamations("200323", "B", "2022-12",
                reclamations);
        InfosReclamations actual = validator.getInfosReclam();
    }

    @Test
    void getListeReclamation() {
        List<Reclamations> expected = Arrays.asList(reclamations1, reclamations2, reclamations3);
        List<Reclamations> actual = validator.getListeReclamation();

        assertNotEquals(expected.toString(), actual.toString());
    }

    @Test
    void validerTabReclam() {
        boolean estValide = validator.validerTabReclam();
        assertFalse(estValide);
    }

    @Test
    void validerInfosReclam() {
        boolean estValide = validator.validerInfosReclam();
        assertFalse(estValide);
    }
}
