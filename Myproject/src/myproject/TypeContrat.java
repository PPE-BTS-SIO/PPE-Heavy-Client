package myproject;

import java.util.ArrayList;

public class TypeContrat {
    private static ArrayList<TypeContrat> typesContrat;
    private String ref, libelle, code;

    public TypeContrat(String ref, String libelle, String code) {
        this.ref = ref;
        this.libelle = libelle;
        this.code = code;
        typesContrat.add(this);
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
