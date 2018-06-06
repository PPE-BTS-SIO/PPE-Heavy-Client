/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Joel  / Thomas / Raphael
 */
public class TypeMateriel {
    private String referenceInterne, libelleTypeMateriel, codeFamille;
    private Famille laFamille;
    private static ArrayList<TypeMateriel> lesTypesMateriels = new ArrayList<>();

    public TypeMateriel(){}
    public TypeMateriel(String referenceInterne,String libelleTypeMateriel,String codeFamille){
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
        this.codeFamille = codeFamille;
        addTypeMateriel(this);
    }

    public static void addTypeMateriel(TypeMateriel typeMateriel){
        lesTypesMateriels.add(typeMateriel);
    }
    
    public static ArrayList<TypeMateriel> getLesTypesMateriel(){
        return lesTypesMateriels;
    }

    
    public String getReferenceInterne() {
        return referenceInterne;
    }

    public String getLibelleTypeMateriel() {
        return libelleTypeMateriel;
    }

    public String getCode() {
        return codeFamille;
    }

    public TypeMateriel(String referenceInterne, String libelleTypeMateriel, String codeFamille, Famille laFamille) {
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
        this.codeFamille = codeFamille;
        this.laFamille = laFamille;
    }

    public void setLaFamille(Famille laFamille) {
        this.laFamille = laFamille;
    }

    public Famille getLaFamille() throws SQLException {
        Connecting connection = new Connecting();
        laFamille = (Famille) connection.chargerFamille(codeFamille);
        return laFamille;
    }
    
}
