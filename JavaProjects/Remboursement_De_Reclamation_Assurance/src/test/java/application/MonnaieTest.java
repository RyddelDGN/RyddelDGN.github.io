package application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonnaieTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculer() {
        int montant = 1000;
        double pourcentage = 0.20;
        double expectedValue = 2.0;
        double actualValue = Monnaie.calculer(montant, pourcentage);

        assertEquals(expectedValue, actualValue);
    }
}