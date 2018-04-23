/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Joel
 */
//note to self : modify this class so that there's a method for connecting to the database and method for select, insert etc...
class Connecting {
    private static Connection connexion = null; 
    private String url;
    private String username;
    private String password;
    private String resultat;
    
    
    
    //Constructor 
    public Connecting() throws SQLException {
        ouvrirConnexion();
    }
    //Initialise les valeurs
    private boolean ouvrirConnexion() throws SQLException {
        /*
        url = "jdbc:mysql://78.237.195.145:3306/PPE";
        username = "ppe";
        password = "ppe123JRT";
        resultat = "";
        */
        url = "jdbc:mysql://78.237.195.145:3306/PPE";
        username = "ppe";
        password = "ppe123JRT";
        resultat = "";
        //Ouverture de connexion a la base de donnée
        try {
            connexion = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException ex) {
            //Gestion d'eventuelles erreurs
            switch(ex.getErrorCode()){
                case 0:
                    JOptionPane.showMessageDialog(null, "Ne peut pas se connecter au serveur. Merci de contacter l'administrateur", "Une erreur est survenue", JOptionPane.WARNING_MESSAGE );
                    break;
                case 1045:
                    JOptionPane.showMessageDialog(null, "Username ou mot de passe invalide", "Une erreur est survenue", JOptionPane.WARNING_MESSAGE );
            }
            
            
        }/*finally{
            
            if (connexion !=null){
                try{ 
                    //Fermeture de la connexion pour ne pas sature le serveur
                    
                    System.out.println("reached finally");
                    connexion.close();
                }catch(SQLException ignore){
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
               /* }
            }
        }*/
        
        return true;
    }
    //Select Statement 
    public ResultSet Select(String Query) throws SQLException{       
       ResultSet resultFinal = null;
       //Ouvrir connexion
       if (this.ouvrirConnexion()== true){
           //creation de l'object gerant les requetes
           Statement statement = connexion.createStatement();
           resultFinal = statement.executeQuery(Query);
           System.out.println("Connection OK");
       }
        
       return resultFinal;
    }
    
    //Update 
  public void UpdateInsert(String Query) throws SQLException{
      if (this.ouvrirConnexion() == true){
        Statement statement = connexion.createStatement();
        /* Execution d'une requete d'update */
        int statut = statement.executeUpdate(Query);
      }
  }
    public void rangerDansBase(Object unObjet) throws SQLException{
        this.ouvrirConnexion();
        if (unObjet instanceof Client){
            this.UpdateInsert("INSERT INTO client(NumeroClient, Nom, Raison_Sociale, Numero_Siren, Code_APE, Addresse, Num_Telephone, Duree_Deplacement, DistanceKm, Num_Agence) VALUES ('" + ((Client) unObjet).getNumClient() + "','" + ((Client) unObjet).getNom()+ "','" + ((Client) unObjet).getRaisonSociale() + "','" + ((Client) unObjet).getSiren() + "','" + ((Client) unObjet).getCodeApe() + "," + ((Client) unObjet).getAdresse() + "," + ((Client) unObjet).getTelClient() + "," + ((Client) unObjet).getDureeDeplacement() + "," + ((Client) unObjet).getDistanceKm()+ "," + ((Client) unObjet).getNumAgence() + ")" );
           
        } 
        if (unObjet instanceof Materiel){
          this.UpdateInsert("INSERT INTO materiel(NumSerie, Nom, DateVente, DateInstallation, Prix, Emplacement, Ref, Num_contrat) VALUES ('"+ ((Materiel) unObjet).getNumSerie() + "','" + ((Materiel) unObjet).getNom() + "','" + ((Materiel) unObjet).getDateVente() + "','" + ((Materiel) unObjet).getDateInstallation() + "','" + ((Materiel) unObjet).getPrixVente() + "','" + ((Materiel) unObjet).getEmplacement() + "','" + ((Materiel) unObjet).getRef() + "','"+ ((Materiel) unObjet).getNumContrat()+"'");
    }
        if (unObjet instanceof TypeMateriel){
            this.UpdateInsert("INSERT INTO type_materiel(Ref, Libelle, Code) VALUES ('"+ ((TypeMateriel) unObjet).getReferenceInterne() + "','" + ((TypeMateriel) unObjet).getLibelleTypeMateriel() + "','" + ((TypeMateriel) unObjet).getCode() + "')");
        }
        
    }
    
public Object chargerDepuisBase(String id, String nomClasse) throws SQLException{
        //Au lieu d'utiliser l'ID du client, nous allons utiliser le nom du client 
        this.ouvrirConnexion();
        Object unObjet = null;
        ResultSet result = null;
        if (nomClasse == "Client"){
            result = this.Select("SELECT * FROM `client` WHERE Nom = '"+ ((Client) unObjet).getNom() + "'");
        }
        
       return result; 
    }
    
    /*
    public ResultSet connection(String Query) throws SQLException{
         //Ouverture de connexion avec MySQL tournant sur la meme machine
         Connection conn = DriverManager.getConnection(url, username, password);
         Statement state = conn.createStatement();
         
         ResultSet result = state.executeQuery(Query);
         /*ResultSetMetaData resultMeta = result.getMetaData();*/
         /*if (result.next()){
           resultat = result.getString(1);
           // resultat = getString(result,"Password");
         }
          System.out.println("proce :" + resultat);
                 *//*
       return result; 
    }
     */
        
    
}


