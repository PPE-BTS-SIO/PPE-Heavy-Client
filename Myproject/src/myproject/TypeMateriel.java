/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 * @author Joel
 */
public class TypeMateriel {
    private String referenceInterne, libelleTypeMateriel, code;
    private Famille laFamille;

    public TypeMateriel(String uneReferenceInterne,String unlibelleTypeMateriel, String unCode){
        
        this.referenceInterne = uneReferenceInterne ;
        this.libelleTypeMateriel = unlibelleTypeMateriel;
        this.code = unCode;
        
    }
    
    
    
    
    public String getReferenceInterne() {
        return referenceInterne;
    }

    public String getLibelleTypeMateriel() {
        return libelleTypeMateriel;
    }

    public String getCode() {
        return code;
    }


}
