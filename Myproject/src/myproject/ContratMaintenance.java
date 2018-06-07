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
 * @author Joel / Thomas / Raphael
 */

public class ContratMaintenance {

    private String numContrat;
    private Date dateSignature, dateEcheance;
    private ArrayList<Materiel> lesMaterielsAssures = new ArrayList();
public ContratMaintenance(){
    //Pour initialisation sans mettre les valeurs directement
}

    public ContratMaintenance(String numContrat, Date dateSignature, Date dateEcheance, ArrayList<Materiel> lesMaterielsAssures) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature;
        this.dateEcheance = dateEcheance;
        this.lesMaterielsAssures = lesMaterielsAssures;
    }
//GETTER AND SETTER 
    public String getNumContrat() {
        return numContrat;
    }
    public int getJourRestants() {
        Date currentDate = new Date();
        long diff = dateEcheance.getTime() - currentDate.getTime();
        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public ArrayList<Materiel> getLesMaterielsAssures() throws SQLException {
        this.loadMaterials();
        return lesMaterielsAssures;
    }
    //CONSTRUCTOR
    public ContratMaintenance(String numContrat, Date dateSignature, Date dateEcheance) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature;
        this.dateEcheance = dateEcheance;
    }

    
    //determine si le contrat est valide en fonction de la date 
    public boolean estValide() {
        Date currentDate = new Date();
        return currentDate.after(dateSignature) && currentDate.before(dateEcheance);
    }
    //Ajoute un Materiel a la liste de materiel sous contrat 
    public void ajouterMateriel(Materiel unMateriel) {
        if (!dateSignature.before(unMateriel.getDateInstallation())) {
            System.out.println("La date d'installation du matériel doit être supérieure à la date de signature du contrat!");
            return;
        }
        this.lesMaterielsAssures.add(unMateriel);
    }
    //Remplie la liste de materiel
    public void loadMaterials() throws SQLException {
        Connecting connection = new Connecting();
        
        lesMaterielsAssures = (ArrayList<Materiel>) connection.chargerMaterielAssure(String.valueOf(this.numContrat));
        
        System.out.println("Matériels récupérés : \u001B[36m" + this.lesMaterielsAssures.size() + "\u001B[0m.");
    }
    
    

}
