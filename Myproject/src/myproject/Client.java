/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class Client {
    
    private String numClient, raisonSocial,siren,codeApe,adresse,telClient, email, numAgence, nom;

    private int dureeDeplacement, distanceKm;

    private ArrayList<Materiel> lesMateriels = new ArrayList();

    private ContratMaintenance leContrat;

    public String getNom(){
        return nom;
    }

    public String getNumAgence(){
        return numAgence;
    }

    public String getNumClient(){
        return numClient;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public String getSiren() {
        return siren;
    }

    public String getCodeApe() {
        return codeApe;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelClient() {
        return telClient;
    }

    public String getEmail() {
        return email;
    }

    public int getDureeDeplacement() {
        return dureeDeplacement;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public void setCodeApe(String codeApe) {
        this.codeApe = codeApe;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumAgence(String numAgence) {
        this.numAgence = numAgence;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDureeDeplacement(int dureeDeplacement) {
        this.dureeDeplacement = dureeDeplacement;
    }

    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }
    
    
}
