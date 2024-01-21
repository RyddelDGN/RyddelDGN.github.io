package application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

class InfosReclamationsTest {
    private List<Reclamations> reclamations = Arrays.asList(new Reclamations("200",  "2022-12-26", "$1,000.50"),
            new Reclamations("300",  "2022-12-28", "$200.50"));
    private InfosReclamations infosReclamations = new InfosReclamations("John", "C", "2022-12",
            reclamations);
    @Test
    void getClient() {
        Assertions.assertEquals("John", infosReclamations.getClient());
    }

    @Test
    void getContrat() {
        Assertions.assertEquals("C", infosReclamations.getContrat());
    }

    @Test
    void getReclamations() {
        Assertions.assertIterableEquals(reclamations, infosReclamations.getReclamations());
    }

    @Test
    void getDate() {
        YearMonth expected = YearMonth.of(2022, 12);
        YearMonth actual = infosReclamations.getDate();
    }
}
