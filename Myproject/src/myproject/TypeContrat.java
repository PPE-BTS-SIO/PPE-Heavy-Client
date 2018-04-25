package myproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Cette classe est une instance d'un type de contrat.
 *
 * Elle dispose des variables suivantes : ref, delaiIntervention & tauxApplicable.
 * Elle contient également une méthode statique (accessible depuis n'importe où) permettant d'accéder à la liste des instances de TypeContrat.
 * Elle contient une autre méthode statique : 'loadAllTypes' > Elle permet de récupérer tous les types de contrats présents dans la base de donnée.
 *
 * */

public class TypeContrat {
    private static ArrayList<TypeContrat> typesContrat = new ArrayList<>();
    private int ref, delaiIntervention;
    private double tauxApplicable;

    public TypeContrat(int ref, int delaiIntervention, double tauxApplicable) {
        this.ref = ref;
        this.delaiIntervention = delaiIntervention;
        this.tauxApplicable = tauxApplicable;
        typesContrat.add(this);
    }

    public static ArrayList<TypeContrat> getTypesContrat() {
        return typesContrat;
    }

    public double getTauxApplicable() {
        return tauxApplicable;
    }

    public int getDelaiIntervention() {
        return delaiIntervention;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setDelaiIntervention(int delaiIntervention) {
        this.delaiIntervention = delaiIntervention;
    }

    public void setTauxApplicable(double tauxApplicable) {
        this.tauxApplicable = tauxApplicable;
    }

    public static boolean loadAllTypes() throws SQLException {
        Connection connection = Connecting.getConnexion();
        if (connection == null) {
            return false;
        }
        System.out.println("\nRécupération des types de contrats...");
        ResultSet result = connection.createStatement().executeQuery("SELECT * FROM `type_contrat`");
        while (result.next()) {
            int ref = result.getInt("RefTypeContrat");
            int delaiIntervention = result.getInt("DelaiIntervention");
            double tauxApplicable = result.getDouble("TauxApplicable");
            new TypeContrat(ref, delaiIntervention, tauxApplicable);
        }
        System.out.println("Types de contrats récupérés : \u001B[36m" + TypeContrat.getTypesContrat().size() + "\u001B[0m");
        return true;
    }
}
