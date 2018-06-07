/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.*;

/**
 *
 * @author Joel
 */
public class ConnectingTest {
    
    public ConnectingTest() {
    }

      

    @Test
    public void testSelect() throws Exception {
        System.out.println("Select");
        String Query = "SELECT * From Contract";
        Connecting instance = new Connecting();
        ResultSet expResult = null;
        ResultSet result = instance.Select(Query);
        assertNotEquals(expResult, result);
        //Devrait me renvoye quelque chose. pas null
    }

    //N'a pas mis dans les test rangerDansBase et UpdateInsert parce que l'on peut le lancer qu'une seul fois apres il faut changer la requete sql

    

    @Test
    public void testChargerFamille() throws Exception {
        System.out.println("chargerFamille");
        String id = "A508";
        Connecting instance = new Connecting();
        Object expResult = null;
        Object result = instance.chargerFamille(id);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testChargerTypeMateriel() throws Exception {
        System.out.println("chargerTypeMateriel");
        String id = "PC";
        Connecting instance = new Connecting();
        Object expResult = null;
        Object result = instance.chargerTypeMateriel(id);
        assertNotEquals(expResult, result);
       //Si c'est pas nul c'est que sa charge 
    }

    @Test
    public void testChargerMaterielAssure() throws Exception {
        System.out.println("chargerMaterielAssure");
        String id = "1";
        Connecting instance = new Connecting();
        Object expResult = null;
        Object result = instance.chargerMaterielAssure(id);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testChargerMateriel() throws Exception {
        System.out.println("chargerMateriel");
        String id = "C1";
        Connecting instance = new Connecting();
        Object expResult = null;
        Object result = instance.chargerMateriel(id);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testChargerContrat() throws Exception {
        System.out.println("chargerContrat");
        String id = "C1";
        Connecting instance = new Connecting();
        Object expResult = null;
        Object result = instance.chargerContrat(id);
        assertNotEquals(expResult, result);
        
    }

    @Test
    public void testChargerDepuisBase() throws Exception {
        System.out.println("chargerDepuisBase");
        String id = "C1";
        String nomClasse = "Client";
        Connecting instance = new Connecting();
        Object expResult = null;
        Client result = (Client) instance.chargerDepuisBase(id, nomClasse);
        
        if (result.getNom() != "Exxon Mobil"){
        fail("N'a pas le bon client");
    }
        ArrayList<ContratMaintenance> contrat = result.getLesContrats();
        if (contrat.size() <=0 ){
            fail("N'a pas remplie la table de contrat de maintenance");
        }
        ArrayList<Materiel> materiel = result.getLesMateriels();
        if (materiel.size() <= 0 ){
             fail("N'a pas remplie la table de materel");
        }
        
    }

    private void fail(String na_pas_le_bon_client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void assertNotEquals(Object expResult, Object result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
