/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Joel
 */
public class ContratMaintenance {
    private String numContrat;
    private Date dateSignature, dateEcheance;
    private ArrayList<Materiel> lesMaterielsAssures = new ArrayList<>();

    public ContratMaintenance(String numContrat, Date dateSignature, Date dateEcheance) {
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
}
