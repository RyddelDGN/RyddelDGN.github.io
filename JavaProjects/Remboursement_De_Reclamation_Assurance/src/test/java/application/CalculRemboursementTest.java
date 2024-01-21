package application;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculRemboursementTest {

    @Test
    void calculerTotal() {
        Reclamations reclamations1 = new Reclamations("1", "2022-03-01", "50.00$");
        Reclamations reclamations2 = new Reclamations("2", "2022-03-02", "75.00$");
        List<Reclamations> reclamations = new ArrayList<>();
        reclamations.add(reclamations1);
        reclamations.add(reclamations2);
        InfosReclamations infosReclamations = new InfosReclamations("1234","A","2022-01-02",reclamations);
        double total = CalculRemboursement.calculerTotal(infosReclamations);
        assertEquals(125, total, 0.001);

    }

    @Test
    void calculerMontantRembourse() {

        double montantRembourser = 100.0;
        double montantMax = 150.0;
        double[] Montant1 = {100.0, 50.0};

        double[] actualMontant = CalculRemboursement.calculerMontantRembourse(montantRembourser, montantMax);

        Assertions.assertArrayEquals(Montant1, actualMontant);
    }

    @Test
    void rembourserMassotherapie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("0", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserMassotherapie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());


    }

    @Test
    void rembourserKinesitherapie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("150", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserKinesitherapie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserOsteopathie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("100", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("100", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserOsteopathie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserPsychoIndividuelle() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("200", "2022-01-03", "300.00$"));
        listeReclam.add(new Reclamations("200", "2022-01-04", "200.00$"));
        double montantMax = 200;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserPsychoIndividuelle(listeReclam, montantMax, pourcentage);
        assertEquals("150.00$", listeReclam.get(0).getMontant());
        assertEquals("50.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserSoinsDentaire() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("325", "2022-01-04", "200.00$"));

        double pourcentage = 0.5;
        CalculRemboursement.rembourserSoinsDentaire(listeReclam, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("100.00$", listeReclam.get(1).getMontant());
    }

    @Test
    void rembourserMassotherapieSansMax() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("0", "2022-01-04", "200.00$"));
        double pourcentage = 0.5;
        CalculRemboursement.rembourserMassotherapieSansMax(listeReclam, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("100.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserKinesitherapieSansMax() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("150", "2022-01-04", "200.00$"));
        double pourcentage = 0.5;
        CalculRemboursement.rembourserKinesitherapieSansMax(listeReclam, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("100.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserNaturopathieSansMax() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("400", "2022-01-04", "200.00$"));
        double pourcentage = 0.5;
        CalculRemboursement.rembourserNaturopathieSansMax(listeReclam, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("100.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserOrthophonieSansMax() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("700", "2022-01-04", "200.00$"));
        double pourcentage = 0.5;
        CalculRemboursement.rembourserOrthophonieSansMax(listeReclam, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("100.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserNaturopathie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("400", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserNaturopathie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserChiropratie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("500", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserChiropratie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserMedecinGeneralistePriver() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("175", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("175", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserMedecinGeneralistePriver(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserPhysiotherapie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("600", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserPhysiotherapie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserOrthophonie() {
        List<Reclamations> listeReclam = new ArrayList<>();
        listeReclam.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclam.add(new Reclamations("700", "2022-01-04", "200.00$"));
        double montantMax = 50.0;
        double pourcentage = 0.5;
        CalculRemboursement.rembourserOrthophonie(listeReclam, montantMax, pourcentage);
        assertEquals("50.00$", listeReclam.get(0).getMontant());
        assertEquals("0.00$", listeReclam.get(1).getMontant());

    }

    @Test
    void rembourserContratA() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("200", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("100", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("175", "2022-01-03", "100.00$"));
        CalculRemboursement.rembourserContratA(listeReclamations);

        assertEquals("40.00$", listeReclamations.get(0).getMontant());
        assertEquals("0.00$", listeReclamations.get(1).getMontant());
        assertEquals("0.00$", listeReclamations.get(2).getMontant());
        assertEquals("0.00$", listeReclamations.get(3).getMontant());
        assertEquals("25.00$", listeReclamations.get(4).getMontant());
        assertEquals("0.00$", listeReclamations.get(5).getMontant());
        assertEquals("25.00$", listeReclamations.get(6).getMontant());
        assertEquals("35.00$", listeReclamations.get(7).getMontant());
        assertEquals("25.00$", listeReclamations.get(8).getMontant());
        assertEquals("50.00$", listeReclamations.get(9).getMontant());
    }

    @Test
    void rembourserContratC() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("200", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("100", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("175", "2022-01-03", "100.00$"));
        CalculRemboursement.rembourserContratC(listeReclamations);

        assertEquals("75.00$", listeReclamations.get(0).getMontant());
        assertEquals("90.00$", listeReclamations.get(1).getMontant());
        assertEquals("90.00$", listeReclamations.get(2).getMontant());
        assertEquals("85.00$", listeReclamations.get(3).getMontant());
        assertEquals("90.00$", listeReclamations.get(4).getMontant());
        assertEquals("90.00$", listeReclamations.get(5).getMontant());
        assertEquals("90.00$", listeReclamations.get(6).getMontant());
        assertEquals("95.00$", listeReclamations.get(7).getMontant());
        assertEquals("90.00$", listeReclamations.get(8).getMontant());
        assertEquals("90.00$", listeReclamations.get(9).getMontant());


    }

    @Test
    void rembourserContratD() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("200", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("100", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("175", "2022-01-03", "100.00$"));
        CalculRemboursement.rembourserContratD(listeReclamations);

        assertEquals("100.00$", listeReclamations.get(0).getMontant());
        assertEquals("90.00$", listeReclamations.get(1).getMontant());
        assertEquals("100.00$", listeReclamations.get(2).getMontant());
        assertEquals("100.00$", listeReclamations.get(3).getMontant());
        assertEquals("100.00$", listeReclamations.get(4).getMontant());
        assertEquals("65.00$", listeReclamations.get(5).getMontant());
        assertEquals("100.00$", listeReclamations.get(6).getMontant());
        assertEquals("75.00$", listeReclamations.get(7).getMontant());
        assertEquals("85.00$", listeReclamations.get(8).getMontant());
        assertEquals("95.00$", listeReclamations.get(9).getMontant());

    }

    @Test
    void rembourserContratE() {List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("200", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("100", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("175", "2022-01-03", "100.00$"));
        CalculRemboursement.rembourserContratE(listeReclamations);

        assertEquals("15.00$", listeReclamations.get(0).getMontant());
        assertEquals("22.00$", listeReclamations.get(1).getMontant());
        assertEquals("60.00$", listeReclamations.get(2).getMontant());
        assertEquals("15.00$", listeReclamations.get(3).getMontant());
        assertEquals("20.00$", listeReclamations.get(4).getMontant());
        assertEquals("15.00$", listeReclamations.get(5).getMontant());
        assertEquals("12.00$", listeReclamations.get(6).getMontant());
        assertEquals("25.00$", listeReclamations.get(7).getMontant());
        assertEquals("15.00$", listeReclamations.get(8).getMontant());
        assertEquals("20.00$", listeReclamations.get(9).getMontant());


    }

    @Test
    void rembourserContratB() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("200", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("100", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("0", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("175", "2022-01-03", "100.00$"));
        CalculRemboursement.rembourserContratB(listeReclamations);

        assertEquals("10.00$", listeReclamations.get(0).getMontant());
        assertEquals("70.00$", listeReclamations.get(1).getMontant());
        assertEquals("50.00$", listeReclamations.get(2).getMontant());
        assertEquals("0.00$", listeReclamations.get(3).getMontant());
        assertEquals("50.00$", listeReclamations.get(4).getMontant());
        assertEquals("0.00$", listeReclamations.get(5).getMontant());
        assertEquals("100.00$", listeReclamations.get(6).getMontant());
        assertEquals("50.00$", listeReclamations.get(7).getMontant());
        assertEquals("40.00$", listeReclamations.get(8).getMontant());
        assertEquals("75.00$", listeReclamations.get(9).getMontant());


    }

    @Test
    void calculerRemboursementTotalContratA() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));

        InfosReclamations infosReclamations = new InfosReclamations("123456", "A", "2022-01-03", listeReclamations);
        CalculRemboursement.calculerRemboursementTotal(infosReclamations);

        Assertions.assertEquals("40.00$", listeReclamations.get(0).getMontant());
        Assertions.assertEquals("0.00$", listeReclamations.get(1).getMontant());
        Assertions.assertEquals("0.00$", listeReclamations.get(2).getMontant());

    }

    @Test
    void calculerRemboursementTotalContratC() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));

        InfosReclamations infosReclamations = new InfosReclamations("123456", "C", "2022-01-03", listeReclamations);
        CalculRemboursement.calculerRemboursementTotal(infosReclamations);

        Assertions.assertEquals("75.00$", listeReclamations.get(0).getMontant());
        Assertions.assertEquals("90.00$", listeReclamations.get(1).getMontant());
        Assertions.assertEquals("90.00$", listeReclamations.get(2).getMontant());

    }

    @Test
    void calculerRemboursementTotalContratD() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));

        InfosReclamations infosReclamations = new InfosReclamations("123456", "D", "2022-01-03", listeReclamations);
        CalculRemboursement.calculerRemboursementTotal(infosReclamations);

        Assertions.assertEquals("100.00$", listeReclamations.get(0).getMontant());
        Assertions.assertEquals("90.00$", listeReclamations.get(1).getMontant());
        Assertions.assertEquals("100.00$", listeReclamations.get(2).getMontant());

    }
    @Test
    void calculerRemboursementTotalContratB() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));

        InfosReclamations infosReclamations = new InfosReclamations("123456", "B", "2022-01-03", listeReclamations);
        CalculRemboursement.calculerRemboursementTotal(infosReclamations);

        Assertions.assertEquals("10.00$", listeReclamations.get(0).getMontant());
        Assertions.assertEquals("70.00$", listeReclamations.get(1).getMontant());
        Assertions.assertEquals("50.00$", listeReclamations.get(2).getMontant());

    }
    @Test
    void calculerRemboursementTotalContratE() {
        List<Reclamations> listeReclamations = new ArrayList<>();
        listeReclamations.add(new Reclamations("600", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("700", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("300", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("150", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("500", "2022-01-03", "100.00$"));
        listeReclamations.add(new Reclamations("400", "2022-01-03", "100.00$"));

        InfosReclamations infosReclamations = new InfosReclamations("123456", "E", "2022-01-03", listeReclamations);
        CalculRemboursement.calculerRemboursementTotal(infosReclamations);

        Assertions.assertEquals("15.00$", listeReclamations.get(0).getMontant());
        Assertions.assertEquals("22.00$", listeReclamations.get(1).getMontant());
        Assertions.assertEquals("60.00$", listeReclamations.get(2).getMontant());

    }



    @Test
    void fichierSortieInvalide() {
        String message = "Le fichier de sortie est invalide.";

        JSONObject result = CalculRemboursement.fichierSortieInvalide(message);

        Assertions.assertEquals(message, result.getString("message"));
    }

    @Test
    void tableauMontant() {
        double montantRembourser = 200;
        double montantMax = 300;

        double[] result = CalculRemboursement.tableauMontant(montantRembourser,montantMax);

        Assertions.assertEquals(2, result.length);
        Assertions.assertEquals(montantRembourser, result[0]);
        Assertions.assertEquals(montantMax, result[1]);
    }
}