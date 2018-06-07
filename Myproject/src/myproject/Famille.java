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

    /**
     *
     */
    public Famille(){}
    
    /**
     *
     * @param unCodeFamille
     * @param unLibelleFamille
     */
    public Famille (String unCodeFamille , String unLibelleFamille){
        this.codeFamille = unCodeFamille; 
        this.libelleFamille = unLibelleFamille;
        
        addFamille(this);
     
    }
    //ajoute une Famille dans la liste de famille 

    /**
     *
     * @param famille
     */
    public static void addFamille(Famille famille){
        lesFamilles.add(famille);
    }
    //GETTER

    /**
     *
     * @return
     */
    public static ArrayList<Famille> getLesFamilles(){
        return lesFamilles;
    }
    
    /**
     *
     * @return
     */
    public String getCodeFamille(){
        return codeFamille;
    }

    /**
     *
     * @return
     */
    public String getLibelleFamille(){
        return libelleFamille;
    }
    
    
}
