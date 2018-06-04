/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 * @author Joel  / Thomas / Raphael
 */
public class TypeMateriel {
    private String referenceInterne, libelleTypeMateriel, codeFamille;
    private Famille laFamille;

    public TypeMateriel(){}
    public TypeMateriel(String referenceInterne,String libelleTypeMateriel,String codeFamille){
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
        this.codeFamille = codeFamille;
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
}
