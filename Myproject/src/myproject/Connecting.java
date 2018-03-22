/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

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
    private Connection connexion = null; 
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
        
        url = "jdbc:mysql://localhost:3306/ppe";
        username = "root";
        password = "";
        resultat = "";
        
        //Ouverture de connexion a la base de donnee
        try {
            connexion = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException ex) {
            //Gestion d'eventuelles erreurs
            switch(ex.getErrorCode()){
                case 0:
                    JOptionPane.showMessageDialog(null, "Ne peut pas se connecter au serveur. Contacte l'administrateur", "ERROR 0", JOptionPane.WARNING_MESSAGE );
                    break;
                case 1045:
                    JOptionPane.showMessageDialog(null, "Username ou mot de passe invalid", "ERROR 0", JOptionPane.WARNING_MESSAGE );
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


