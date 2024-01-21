package application;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * UQAM - HIVER 2023 - INF2050 - GROUPE 20 - PROJET DE SESSION.PARTIE01
 * application.InfosReclamations : Cette classe sert à representer les informations du
 * client et les implementer dans le constructeur
 * @author EQUIPE N°15
 * @version 2023-04-23
 */

public class InfosReclamations {


    private String client;
    private String contrat;
    private List<Reclamations> reclamations;
    private String date;
    private final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM");

    public InfosReclamations () {}

    /**
     * @param client les informations sur le client
     * @param contrat le numero de contrat du client
     * @param date la date de la reclamation
     * @param reclamations les informations sur les reclamations du client
     */
    public InfosReclamations(String client, String contrat, String date, List<Reclamations> reclamations) {
        this.client = client;
        this.contrat = contrat;
        this.date = date;
        this.reclamations = reclamations;
    }

    /**
     * @return client
     */
    public String getClient() {
        return client;
    }


    /**
     * @return contrat
     */
    public String getContrat() {
        return contrat;
    }


    /**
     * @return reclamations
     */
    public List<Reclamations> getReclamations() {
        return reclamations;
    }


    /**
     * @return date
     */
    public YearMonth getDate() {
        YearMonth uneDate = null;
        try {
            uneDate = YearMonth.parse(date, formatDate);
        } catch (DateTimeException ignored) {}
        return uneDate;
    }
}