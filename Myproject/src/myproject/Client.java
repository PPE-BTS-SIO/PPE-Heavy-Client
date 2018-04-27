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
    
    private String numClient, raisonSociale, siren, codeApe, adresse, telClient, url, logo, numAgence, nom;
    private int dureeDeplacement, distanceKm;

    private ArrayList<Materiel> lesMateriels = new ArrayList();
    private ContratMaintenance leContrat;
    private static ArrayList<Client> lesClients = new ArrayList<>();
    public Client(){
    
    }
    
    public Client(
            String numClient,
            String raisonSociale,
            String siren,
            String codeApe,
            String adresse,
            String telClient,
            String url,
            String logo,
            String numAgence,
            String nom,
            int dureeDeplacement,
            int distanceKm
    ) {
        this.numClient = numClient;
        this.raisonSociale = raisonSociale;
        this.siren = siren;
        this.codeApe = codeApe;
        this.adresse = adresse;
        this.telClient = telClient;
        this.url = url;
        this.logo = logo;
        this.numAgence = numAgence;
        this.nom = nom;
        this.dureeDeplacement = dureeDeplacement;
        this.distanceKm = distanceKm;
        addClient(this);
    }

    public String getNom(){
        return nom;
    }

    public String getNumAgence(){
        return numAgence;
    }

    public String getNumClient(){
        return numClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
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

    public String getUrl() {
        return url;
    }

    public String getLogo() {
        return logo;
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

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public static void addClient(Client client) {
        lesClients.add(client);
    }

    public static ArrayList<Client> getLesClients() {
        return lesClients;
    }
}
