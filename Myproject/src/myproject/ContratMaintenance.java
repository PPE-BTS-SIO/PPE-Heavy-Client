/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Joel
 */

public class ContratMaintenance {

    private int numContrat;
    private Date dateSignature, dateEcheance;
    private ArrayList<Materiel> lesMaterielsAssures;
public ContratMaintenance(){
    //Pour initialisation sans mettre les valeurs directement
}

    public ContratMaintenance(int numContrat, Date dateSignature, Date dateEcheance, ArrayList<Materiel> lesMaterielsAssures) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature;
        this.dateEcheance = dateEcheance;
        this.lesMaterielsAssures = lesMaterielsAssures;
    }

    public int getNumContrat() {
        return numContrat;
    }
    public ContratMaintenance(int numContrat, Date dateSignature, Date dateEcheance) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature;
        this.dateEcheance = dateEcheance;
    }

    public int getJourRestants() {
        Date currentDate = new Date();
        long diff = dateEcheance.getTime() - currentDate.getTime();
        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public boolean estValide() {
        Date currentDate = new Date();
        return currentDate.after(dateSignature) && currentDate.before(dateEcheance);
    }

    public void ajouterMateriel(Materiel unMateriel) {
        if (!dateSignature.before(unMateriel.getDateInstallation())) {
            System.out.println("La date d'installation du matériel doit être supérieure à la date de signature du contrat!");
            return;
        }
        this.lesMaterielsAssures.add(unMateriel);
    }

    public ArrayList<Materiel> getLesMaterielsAssures() {
        return lesMaterielsAssures;
    }

    public void loadMaterials() throws SQLException {
        Connecting connection = new Connecting();
        
        if (connection == null) return;
        
        lesMaterielsAssures = (ArrayList<Materiel>) connection.chargerDepuisBase(String.valueOf(this.numContrat), "Materiel");
        
        System.out.println("Matériels récupérés : \u001B[36m" + this.lesMaterielsAssures.size() + "\u001B[0m.");
    }
    //Fonction pour avoir les jours avant fin contrat
    public int getNbrJourAvantEcheance() {
        //si object n'est pas sur contrat retourne 0
        if (dateSignature != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(dateSignature); //Utilise la date de vente 
            c.add(Calendar.DATE, 365); // la date de vente + 1 ans
            Date DateEcheance = c.getTime();
            if (dateSignature.after(DateEcheance)) {
                int diffJour = (int) (DateEcheance.getTime() - dateSignature.getTime()) / (1000 * 60 * 60 * 24);
                return diffJour; //retourne la difference 
            } else {
                return 0;
            }
        }
        return 0;
    }

}
