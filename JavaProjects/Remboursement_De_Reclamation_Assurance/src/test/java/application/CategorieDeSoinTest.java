package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategorieDeSoinTest {

    @Test
    void soinMassotherapie() {
        int numeroSoin = 0;
        boolean expectedValue = true;

        CategorieDeSoin soin = new CategorieDeSoin(numeroSoin);
        boolean actualValue = soin.soinMassotherapie();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void soinOsteopathie() {
        int numeroSoin = 100;
        boolean expectedValue = true;

        CategorieDeSoin soin = new CategorieDeSoin(numeroSoin);
        boolean actualValue = soin.soinOsteopathie();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void soinKinesitherapie() {
        int numeroSoin = 150;
        boolean expectedValue = true;

        CategorieDeSoin soin = new CategorieDeSoin(numeroSoin);
        boolean actualValue = soin.soinKinesitherapie();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void soinMedecinGeneralistePrive() {
        int numeroSoin = 175;
        boolean expectedValue = true;
        CategorieDeSoin soin = new CategorieDeSoin(numeroSoin);
        boolean actualValue = soin.soinMedecinGeneralistePrive();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void soinPsychologie() {
        CategorieDeSoin soin = new CategorieDeSoin(200);
        assertTrue(soin.soinPsychologie());
    }

    @Test
    void soinDentaire() {
        CategorieDeSoin example = new CategorieDeSoin(350);
        assertTrue(example.soinDentaire());
    }

    @Test
    void soinNaturopathie() {
        CategorieDeSoin soin = new CategorieDeSoin(400);
        assertTrue(soin.soinNaturopathie());
    }

    @Test
    void soinChiropratie() {
        CategorieDeSoin soin = new CategorieDeSoin(500);
        assertTrue(soin.soinChiropratie());
    }

    @Test
    void soinPhysiotherapie() {
        CategorieDeSoin soin = new CategorieDeSoin(600);
        assertTrue(soin.soinPhysiotherapie());
    }

    @Test
    void soinOrthophonie() {
        CategorieDeSoin soin = new CategorieDeSoin(700);
        assertTrue(soin.soinOrthophonie());
    }

    @Test
    void estUnSoinValide() {
        CategorieDeSoin soin1 = new CategorieDeSoin(0);
        CategorieDeSoin soin2 = new CategorieDeSoin(10);
        assertTrue(soin1.estUnSoinValide());
        assertFalse(soin2.estUnSoinValide());
    }
}