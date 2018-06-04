/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;


/**
 * @author Joel Joel  / Thomas / Raphael
 */

class Connecting {
    private static Connection connexion = null;
    private String url;
    private String username;
    private String password;
    private String resultat;

    //Initialisation d'ArrayList pour stockage d'information 
    //sur client, contrat et materiel
    private ArrayList<Client> lesClients= new ArrayList<>();
    private ArrayList<ContratMaintenance> lesContrats= new ArrayList<>();
    private ArrayList<Materiel> lesMaterielsAssures = new ArrayList<>();

    //Renvoie la liste des clients 
    public ArrayList<Client> getLesClients() {
        return lesClients;
    }
    
    //Renvoie la liste des contrats de mainteance
    public ArrayList<ContratMaintenance> getLesContrats() {
        return lesContrats;
    }
    
    //Renvoie la liste des materiels 
    public ArrayList<Materiel> getLesMaterielsAssures() {
        return lesMaterielsAssures;
    }

    //Constructor 
    public Connecting() throws SQLException {
        //si la connexion n'est pas encore ouvert, on l'ouvre
        //sinon on utilise la connexion deja ouverte
        if (connexion == null) {
          ouvrirConnexion();  
        }else{
            getConnexion();
        }
        
    }
    //Renvoie la connexion
    public static Connection getConnexion() {
        return connexion;
    }

    //Initialise les valeurs
    private boolean ouvrirConnexion() throws SQLException {

        url = "jdbc:mysql://78.237.195.145:3306/FINAL_PPE";
        username = "ppe";
        password = "ppe123JRT";
        resultat = "";
        //Ouverture de connexion a la base de donnée
        try {
            connexion = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException ex) {
            //Gestion d'eventuelles erreurs
            switch (ex.getErrorCode()) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Ne peut pas se connecter au serveur. Merci de contacter l'administrateur", "Une erreur est survenue", JOptionPane.WARNING_MESSAGE);
                    break;
                case 1045:
                    JOptionPane.showMessageDialog(null, "Username ou mot de passe invalide", "Une erreur est survenue", JOptionPane.WARNING_MESSAGE);
            }

        }
        return false; 
        }
  
    //Select Statement
    public ResultSet Select(String Query) throws SQLException {
        ResultSet resultFinal = null;
        //Ouvrir connexion
        if (this.ouvrirConnexion()) {
            //creation de l'object gerant les requetes
            Statement statement = connexion.createStatement();
            resultFinal = statement.executeQuery(Query);
            System.out.println("Successfully connected to database.");
        }

        return resultFinal;
    }

    //Update
    public void UpdateInsert(String Query) throws SQLException {
        if (this.ouvrirConnexion() == true) {
            Statement statement = connexion.createStatement();
        /* Execution d'une requete d'update */

            int statut = statement.executeUpdate(Query);
        }

      }
  
    //Stocke les données de l'objet dans la base de donnée
    public void rangerDansBase(Object unObjet) throws SQLException {
        this.ouvrirConnexion();
        //Si nous devions créer un client a partir d'un objet 
        if (unObjet instanceof Client) {
            this.UpdateInsert("INSERT INTO Client(NumeroClient, Nom, Raison_Sociale, Numero_Siren, Code_APE, Addresse, Num_Telephone, Fax, Duree_Deplacement, DistanceKm, Num_Agence) VALUES ('" + ((Client) unObjet).getNumClient() + "','" + ((Client) unObjet).getNom() + "','" + ((Client) unObjet).getRaisonSociale() + "','" + ((Client) unObjet).getSiren() + "','" + ((Client) unObjet).getCodeApe() + "," + ((Client) unObjet).getAdresse() + "," + ((Client) unObjet).getTelClient() + ","+ ((Client) unObjet).getFax() + "," + ((Client) unObjet).getDureeDeplacement() + "," + ((Client) unObjet).getDistanceKm() + "," + ((Client) unObjet).getNumAgence() + ")");

        }
        //permet d'entrer de nouveau materiel dans la base de donnée
        
        if (unObjet instanceof Materiel) {
            this.UpdateInsert("INSERT INTO materiel(NumSerie, Nom, DateVente, DateInstallation, Prix, Emplacement, Ref, Num_contrat) VALUES ('" + ((Materiel) unObjet).getNumSerie() + "','" + ((Materiel) unObjet).getNom() + "','" + ((Materiel) unObjet).getDateVente() + "','" + ((Materiel) unObjet).getDateInstallation() + "','" + ((Materiel) unObjet).getPrixVente() + "','" + ((Materiel) unObjet).getEmplacement() + "','" + ((Materiel) unObjet).getRef() + "','" + ((Materiel) unObjet).getNumContrat() + "'");
        }
        //Nous permet de modifier un contrat dans la base de donnée 
        if(unObjet instanceof ContratMaintenance){
           //Prend la date d'aujourdhui 
           LocalDateTime now = LocalDateTime.now();
           //Declare une class qui me permet de formatter le format de la date 
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           //format la date d'aujoudhui 
           String formatDateTime = now.format(formatter);
           
            this.UpdateInsert("UPDATE Contrat SET Date_Renouvellement=" + now + "WHERE Num_contrat='"+ ((ContratMaintenance) unObjet).getNumContrat() + "'");
        }
        

    }

    private Date UnAnsDePlus(Date laDate){
        //Classe pour ajouter un ans de plus a une date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(laDate);
        c.add(Calendar.DATE, 365); // la date de ventre + 1 ans 
        Date dateUnAnsDePlus = c.getTime();
        return dateUnAnsDePlus;
    }
    public Object chargerFamille(String id) throws SQLException{
        //Recuperation de donnee de la base de donnee
        ResultSet result = null;
        result = this.Select("SELECT * FROM Famille_Produit WHERE CodeFamille ='"+ id +"'" );
        String code = null;
        String libelle = null;
        while (result.next()){
            code = result.getString(1);
            libelle = result.getString(2);
        }
         //creation de l'objet avec les valeurs de la BDD
        Famille uneFamille = new Famille(code,libelle);
        return uneFamille;
    }
    
   public Object chargerTypeMateriel(String id) throws SQLException{
       //Recuperation de donnee de la base de donnee
       ResultSet result = null;
       result = this.Select("SELECT * FROM Type_Materiel WHERE CodeFamille = '"+ id +"'" );
       String ref = null;
       String libelle = null; 
       String code = null;
       while (result.next()){
            ref = result.getString(1);
            libelle = result.getString(2);
            code = result.getString(3);
        }
       //creation de l'objet avec les valeurs de la BDD
       TypeMateriel unType = new TypeMateriel(ref,libelle,code);
       return unType;
   }
   public Object chargerMateriel(String id) throws SQLException{
       //Recuperation de donnee de la base de donnee
       ResultSet result = null;
       result = this.Select("SELECT * FROM Materiel WHERE RefTypeMateriel = '"+ id +"'" );
       String numSerie = null;
       String nom = null; 
       Date dateVente = null;
       Date dateInstallation = null; 
       double prix = 0;
       String emplacement = null;
       String ref = null; 
       String numContrat = null;
       int quantite = 0;
       
       
       while (result.next()){
           numSerie = result.getString(1);
           nom = result.getString(2);
           dateVente = result.getDate(3);
           dateInstallation = result.getDate(4);
           prix = result.getDouble(5);
           emplacement  = result.getString(6);
           quantite = result.getInt(7);
           ref = result.getString(8);
           numContrat = result.getString(9);  
           //creation de l'objet avec les valeurs de la BDD
           Materiel leMateriel = new Materiel(numContrat, quantite, dateVente, dateInstallation, prix, numSerie, emplacement, ref, nom, (TypeMateriel) this.chargerTypeMateriel(ref));
           //Met le specifique type de materiel dans l'ArrayList de Materiel
           lesMaterielsAssures.add(leMateriel);
       }
       
       return lesMaterielsAssures;
   }
   
   public Object chargerContrat(String id) throws SQLException{
    //Recuperation de donnee de la base de donnee
    ResultSet result = null;
    result = this.Select("SELECT * FROM Contrat WHERE Num_contrat = '"+ id +"'" );
    //initation de variable 
    int numContrat = 0; 
    Date dateSignature = null;
    Date dateEcheance = null;
    while(result.next()){
       numContrat = result.getInt(1);
       dateSignature = result.getDate(2);
       dateEcheance = result.getDate("Date_Echeance");
       
   }
    ContratMaintenance leContrat = new ContratMaintenance(numContrat, dateSignature, dateEcheance);
    leContrat.loadMaterials();
    return leContrat;
    
   }
    
public Object chargerDepuisBase(String id, String nomClasse) throws SQLException{
    //Recuperation de donnee de la base de donnee
        Connection connection = Connecting.getConnexion();
        if (connection == null) return null;
        Object unObjet = null;
        ResultSet result = null;
        Client unClient = new Client();
        ContratMaintenance unContrat = new ContratMaintenance();
        Materiel unMateriel = new Materiel();

        
        if (null != nomClasse)switch (nomClasse) {
            case "Client":
                result = this.Select("SELECT * FROM Client WHERE NumeroClient = '"+ id + "'");
                while (result.next()){
                    String numClient = result.getString(1);
                    String nom = result.getString(2);
                    String raisonSociale = result.getString(3);
                    String codeAP = result.getString(5);
                    String Addresse = result.getString(6);
                    String numTel = result.getString(7);
                    String urlC = result.getString(12);
                    String logo = result.getString(13);
                    String numAgence = result.getString(11);
                    String numSiren = result.getString(4);
                    int dureeDeplacement = result.getInt(9);
                    int distance = result.getInt(10);
                    unClient.setAdresse(Addresse);
                    unClient.setCodeApe(codeAP);
                    unClient.setDistanceKm(distance);
                    unClient.setDureeDeplacement(dureeDeplacement);
                    unClient.setNom(nom);
                    unClient.setNumAgence(numAgence);
                    unClient.setNumClient(numClient);
                    unClient.setRaisonSociale(raisonSociale);
                    unClient.setSiren(numSiren);
                    unClient.setTelClient(numTel);
                    unClient.setUrl(urlC);
                    lesClients.add(unClient);
                    ResultSet resultContrat = null;
                    resultContrat = this.Select("SELECT * FROM Contrat WHERE NumeroClient=" + "'" + numClient + "'" );
                    while(resultContrat.next()){
                        unContrat = (ContratMaintenance) this.chargerContrat(numClient);
                        lesContrats.add(unContrat);
                        ArrayList<Materiel> temporaire = new ArrayList();
                        temporaire =  (ArrayList<Materiel>) this.chargerMateriel(Integer.toString(unContrat.getNumContrat()));
                        for (Materiel leMateriel : temporaire){
                            lesMaterielsAssures.add(leMateriel);
                        }
                        
                    }
                }
                //Des que l'on a les donnees on les places dans une classe
                
                return lesClients;
            case "Contrat":
                ContratMaintenance contrat = new ContratMaintenance();
                contrat = (ContratMaintenance) this.chargerContrat(id);
                lesContrats.add(contrat);                   
                              
                return lesContrats; // retourne une ArrayList -> dois voir comment convertir en Object
            case "Materiel":
                //recuperation des materiels a partir du numero de contrat
                Materiel materiel = new Materiel();
                
                lesMaterielsAssures = (ArrayList<Materiel>) this.chargerMateriel(id);
                    
                
                

                return lesMaterielsAssures; // retourne une ArrayList -> dois voir comment convertir en Object
            default:
                break;

        }

       return null; 
    }
}


