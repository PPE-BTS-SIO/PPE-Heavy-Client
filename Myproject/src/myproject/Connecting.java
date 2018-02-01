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

/**
 *
 * @author Joel
 */
class Connecting {
    private String url = "jdbc:mysql://localhost:3306/PPE";
    private String username = "root";
    private String password = "";
    private String resultat = "";
    public String connection(String Query) throws SQLException{
         //Ouverture de connexion avec MySQL tournant sur la meme machine
         Connection conn = DriverManager.getConnection(url, username, password);
         Statement state = conn.createStatement();
         
         ResultSet result = state.executeQuery(Query);
         /*ResultSetMetaData resultMeta = result.getMetaData();*/
         if (result.next()){
            //resultat = result.getString(result,"Password");
            resultat = getString(result,"Password");
         }
       return resultat; 
    }
     public  String getString(ResultSet result, String column) {
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
