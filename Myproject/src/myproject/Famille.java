/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;


import java.util.ArrayList;


/**
 * @author Joel / Raphael / Thomas
 */
public class Famille {
    private String codeFamille, libelleFamille;
    private static ArrayList<Famille> lesFamilles = new ArrayList<>();
    
    // Constructeur
    
    public Famille (String unCodeFamille , String unLibelleFamille){
        this.codeFamille = unCodeFamille; 
        this.libelleFamille = unLibelleFamille;
        
        addFamille(this);
     
    }
    
    public static void addFamille(Famille famille){
        lesFamilles.add(famille);
    }
    
    public String getCodeFamille(){
        return codeFamille;
    }

    public String getLibelleFamille(){
        return libelleFamille;
    }
    
    
}
