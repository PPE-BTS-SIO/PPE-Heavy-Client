/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.Date;

/**
 *
 * @author Joel
 */
public class Materiel {
    private int numSerie;
    private Date dateVente, dateInstallation;
    private double prixVente;
    private String emplacement, ref, numContrat;
    private String nom;
    private TypeMateriel leType;
    
    
    public String getNumContrat(){
        return numContrat;
    }
    public String getRef(){
        return ref;
    }
    public Date getDateInstallation(){
        return dateInstallation;
    }
    public String getNom(){
        return nom;
    }
    public int getNumSerie() {
        return numSerie;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public TypeMateriel getLeType() {
        return leType;
    }
    
    public String xmlMateriel(){
    return "hello";
            }
    
}
