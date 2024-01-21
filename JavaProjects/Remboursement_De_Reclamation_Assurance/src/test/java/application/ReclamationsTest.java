package application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReclamationsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void transform() {
        String montant = "$1,000.50";
        int expectedValue = 100050;

        Reclamations converter = new Reclamations("200",  "2024-02-26", montant);
        int actualValue = converter.transform();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getDateReclamationTest () {
        Reclamations reclamations = new Reclamations("200", "2024-02-26", "200.00S");
        assertEquals("2024-02-26", reclamations.getDateReclamation().toString());
    }

    @Test
    void getMontantArgentTest () {
        Reclamations reclamations = new Reclamations("200", "2024-02-26", "200.00S");
        assertEquals(200.0, reclamations.getMontantArgent());
    }
}