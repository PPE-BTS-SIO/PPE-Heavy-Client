/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

}
