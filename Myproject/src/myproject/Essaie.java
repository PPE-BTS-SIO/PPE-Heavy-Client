/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class Essaie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       Connecting connection = new Connecting();
       /*Famille laFamille = new Famille();
       laFamille = (Famille) connection.chargerFamille("A504");
       System.out.println(laFamille.getLibelleFamille());
       
      TypeMateriel leType = new TypeMateriel();
      leType = (TypeMateriel) connection.chargerTypeMateriel(laFamille.getCodeFamille());
      
      System.out.println("le type de la famille : " + leType.getLibelleTypeMateriel());
      
      Materiel leMateriel = new Materiel();
      ArrayList<Materiel> listMateriel = new ArrayList();
      listMateriel = (ArrayList<Materiel>) connection.chargerMateriel(leType.getReferenceInterne());
      ArrayList<ContratMaintenance> leContrat = new ArrayList();
      for (Materiel unMateriel : listMateriel){
          System.out.println(unMateriel.getNom());
          leContrat = (ArrayList<ContratMaintenance>) connection.chargerContrat(unMateriel.getNumContrat());
      }
     
    for (ContratMaintenance unContrat : leContrat){
        System.out.println(unContrat.);
    }
    */
    /*ArrayList<Client> leClient = new ArrayList();
    ArrayList<ContratMaintenance> leContrat = new ArrayList();
    ArrayList<Materiel> leMateriel = new ArrayList();
    connection.chargerDepuisBase("C1", "Client");
    leClient = connection.getLesClients();
    System.out.println(leClient.size());
    for (Client unClient : leClient){
        leContrat = unClient.getLesContrats();
        //System.out.println(leContrat.isEmpty());
        for(ContratMaintenance unContrat : leContrat){
            leMateriel = unContrat.getLesMaterielsAssures();
            for(Materiel unMateriel : leMateriel){
                System.out.println(unMateriel.getNom());
            }
        }
    }*/
    System.out.println("On commence bien");
    Client leClient = new Client();
    leClient = (Client) connection.chargerDepuisBase("C1", "Client");
    System.out.println("Le nom du client est : " + leClient.getNom());
    ArrayList<ContratMaintenance> leContrat = new ArrayList();
    ArrayList<Materiel> leMateriel = new ArrayList();
    leContrat = leClient.getLesContrats();
    for(ContratMaintenance contrat : leContrat){
        System.out.println("le nom du contrat est : " + contrat.getNumContrat());
   }
    for (Materiel unMateriel : leMateriel){
        System.out.println("le nom du materiel est : " + unMateriel.getNom());
    }
    
    }
    
}
