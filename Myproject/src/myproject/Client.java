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

/**
 * @author Joel  / Thomas / Raphael
 */
public class Client {

    private static ArrayList<Client> lesClients = new ArrayList<>();
    private ArrayList<Materiel> lesMateriels = new ArrayList();
    private ArrayList<ContratMaintenance> lesContrats = new ArrayList();

    public ArrayList<Materiel> getLesMateriels() {
        return lesMateriels;
    }
public String getNumContrat(){
    ArrayList<ContratMaintenance> leContrat = new ArrayList();
    
    leContrat = this.getLesContrats();
    for(ContratMaintenance contrat : leContrat){
        return contrat.getNumContrat();
   }
        return null;
}
    public void setLesMateriels(ArrayList<Materiel> lesMateriels) {
        this.lesMateriels = lesMateriels;
    }

    public ArrayList<ContratMaintenance> getLesContrats() {
        return lesContrats;
    }

    public void setLesContrat(ArrayList<ContratMaintenance> lesContrat) {
        this.lesContrats = lesContrat;
    }
    private String numClient, raisonSociale, siren, codeApe, adresse, telClient, url, logo, numAgence, nom,fax;

    public String getFax() {
        return fax;
    }
    private int dureeDeplacement, distanceKm;
   


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

    public static void addClient(Client client) {
        lesClients.add(client);
    }

    public static ArrayList<Client> getLesClients() {
        return lesClients;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumAgence() {
        return numAgence;
    }

    public void setNumAgence(String numAgence) {
        this.numAgence = numAgence;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getCodeApe() {
        return codeApe;
    }

    public void setCodeApe(String codeApe) {
        this.codeApe = codeApe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getDureeDeplacement() {
        return dureeDeplacement;
    }

    public void setDureeDeplacement(int dureeDeplacement) {
        this.dureeDeplacement = dureeDeplacement;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }

    

    public void loadContracts() throws SQLException {
        Connecting connection = new Connecting();
        if (connection == null) return;
        this.lesContrats = new ArrayList<>();
        this.lesContrats = (ArrayList<ContratMaintenance>) connection.chargerDepuisBase(this.numClient, "Contrat");
        /*PreparedStatement ps = connection.prepareStatement("SELECT * FROM `contrat` WHERE `NumeroClient` = ?");
        ps.setString(1, this.numClient);
        System.out.println("\nRécupération des contrats de l'entreprise \u001B[36m" + this.nom + "\u001B[0m...");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            int numContrat = result.getInt("Num_contrat");
            Date dateSignature = result.getDate("Date_signature");
            Date dateExpiration = result.getDate("Date_expiration");
            String typeContrat = result.getString("RefTypeContrat");
            ContratMaintenance contrat = new ContratMaintenance(numContrat, dateSignature, dateExpiration);
            this.lesContrats.add(contrat);
        }
*/
        System.out.println("Contrats récupérés : \u001B[36m" + this.lesContrats.size() + "\u001B[0m.");
    }
}
