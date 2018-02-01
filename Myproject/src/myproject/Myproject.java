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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joel
 */
public class Myproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*login start = new login();
        start.setVisible(true);
        */
        try {
        String url = "jdbc:mysql://localhost:3306/PPE";
        String username = "root";
        String password = "";
       
       //Ouverture de connexion avec MySQL tournant sur la meme machine
         Connection conn = DriverManager.getConnection(url, username, password);
         Statement state = conn.createStatement();
         //Creation de requete SQL et stockage du resultat dans result 
         ResultSet result = state.executeQuery("SELECT Num_Agence, Nom FROM agence WHERE 1");
         //Creation de metadata pour avoir info sur le nombres de colonnes, de ligne etc...
         ResultSetMetaData resultMeta = result.getMetaData();
         
         System.out.println("- Il  y a " + resultMeta.getColumnCount() + "colonnes dans cette table");
         for(int i = 1; i <= resultMeta.getColumnCount(); i++){
             System.out.println("\t *" + resultMeta.getColumnName(i)); 
         }
         System.out.println("Voici les techniciens : ");
         System.out.println("\n---------------------------------");
         
         while(result.next()){
        System.out.print("\t" + getString(result,"Num_Agence") + "\t |");
        System.out.print("\t" + getString(result, "Nom") + "\t |");
        
        System.out.println("\n---------------------------------");
      }
       result.close();
       state.close();
       } catch(Exception e ){
           e.printStackTrace();
       }
       
    }
    public static String getString(ResultSet result, String column) {
        String str = new String();
        try {
        InputStreamReader in = new InputStreamReader(result
        .getAsciiStream(column));
        while (in.ready())
        str = str + (char) in.read();
        } catch (SQLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
        return str;
        }
}
    
}
