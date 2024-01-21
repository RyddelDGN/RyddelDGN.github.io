package application;

import java.time.DateTimeException;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

/**
 * UQAM - HIVER 2023 - INF2050 - GROUPE 20 - PROJET DE SESSION.PARTIE01
 * application.Reclamations : Cette classe sert à representer les informations des
 * reclamations et les implementer dans le constructeur
 * @author EQUIPE N°15
 * @version 2023-02-09
 */


public class Reclamations {

    //Declarations des attributs
//    static final String MSG_FORMAT_ANNEE_INVALIDE = "Le format de l'annee reclamee n'est pas valide";
    private String dateReclamation;
    private String montant;
    private String soin;

    public Reclamations () {}
    /**
     * @param soin le type de soin
     * @param dateReclamation la date de reclamation
     * @param montant le montant payé
     */
    public Reclamations(String soin, String dateReclamation, String montant) {
        this.soin = soin;
        this.dateReclamation = dateReclamation;
        this.montant = montant;
    }


    /**
     * @return dateReclamation
     */
    public LocalDate getDateReclamation() {
        LocalDate uneDate = null;
        try {
            uneDate = LocalDate.parse(dateReclamation, ISO_LOCAL_DATE);
        } catch (DateTimeException ignored) {
        }
        return uneDate;
    }


    /**
     * @return montant
     */
    public String getMontant() {
        return montant;
    }

    public int transform () {
        montant = montant.replace(".","");
        montant = montant.replace(",","");
        montant = montant.replace("$","");
        return Integer.parseInt(montant);
    }

    /**
     * @param montant
     */
    public void setMontant(String montant) {
        this.montant = montant;
    }


    /**
     * @return montantArgent
     */
    public double getMontantArgent(){
        double value;
        String line = getMontant();
        value = Double.parseDouble(line.substring(0,line.length() - 1).replace(",","."));
        return value;
    }


    /**
     * @return soin
     */
    public String getSoin() {
        return soin;
    }

    /**
     * @return numeroSoin
     */
    public int getNumeroSoin () {
        return Integer.parseInt(getSoin());
    }

    /**
     * @return dateReclamString
     */
    public String getDateReclamString() {
        return dateReclamation;
    }
}