/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Joel
 */
public class Materiel {
    private int numContrat, quantite;
    private Date dateVente, dateInstallation;
    private double prixVente;
    private String numSerie, emplacement, ref, nom;
    private TypeMateriel leType;
    public Materiel(){
        
    }
    public Materiel(int numContrat, int quantite, Date dateVente, Date dateInstallation, double prixVente, String numSerie, String emplacement, String ref, String nom, TypeMateriel leType) {
        this.numContrat = numContrat;
        this.quantite = quantite;
        this.dateVente = dateVente;
        this.dateInstallation = dateInstallation;
        this.prixVente = prixVente;
        this.numSerie = numSerie;
        this.emplacement = emplacement;
        this.ref = ref;
        this.nom = nom;
        this.leType = leType;
    }

    public Materiel(
            String numSerie,
            int numContrat, 
            Date dateVente,         
            Date dateInstallation, 
            double prixVente, 
            String emplacement, 
            String ref, 
            String nom,
            int quantite)
  
    {
        this.numSerie = numSerie;
        this.numContrat = numContrat;
        this.dateVente = dateVente;
        this.dateInstallation = dateInstallation;
        this.prixVente = prixVente;
        this.emplacement = emplacement;
        this.ref = ref;
        this.nom = nom;
        this.quantite = quantite;
    }
    
    

    //Fonction pour avoir les jours avant fin contrat
    public int getNbrJourAvantEcheance() {
        //si object n'est pas sur contrat retourne 0
        if (dateVente != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(dateVente); //Utilise la date de vente 
            c.add(Calendar.DATE, 365); // la date de vente + 1 ans
            Date DateEcheance = c.getTime();
            if (dateVente.after(DateEcheance)) {
                int diffJour = (int) (DateEcheance.getTime() - dateVente.getTime()) / (1000 * 60 * 60 * 24);
                return diffJour; //retourne la difference 
            } else {
                return 0;
            }
        }
        return 0;
    }

    public int getNumContrat() {
        return numContrat;
    }

    public String getRef() {
        return ref;
    }

    public Date getDateInstallation() {
        return this.dateInstallation;
    }

    public String getNom() {
        return nom;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public String getDateVente() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String strDateVente = df.format(dateVente);

        return strDateVente;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public TypeMateriel getLeType() {
        return leType;
    }
    
    public int getQuantite(){
        return quantite;
    }

    public String xmlMateriel() throws ParserConfigurationException {
        //Récuperation d'une instance de la classe "DocumentBuilderFactory
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //Creation d'un parseur 
            final DocumentBuilder builder = factory.newDocumentBuilder();

            //Creation d'un document 
            final Document document = builder.newDocument();

            //Creation de l'élément racine 
            final Element materiel = document.createElement("materiel");

            //ajout élément racine au document
            document.appendChild(materiel);

            materiel.setAttribute("numSerie", String.valueOf(this.getNumSerie()));

            //TOUT CE QUI EST EN DESSOUS DE MATERIEL
            final Element type = document.createElement("type");
            type.setAttribute("refInterne", this.getRef());
            type.setAttribute("libelle", this.getNom());
            materiel.appendChild(type);

            final Element famille = document.createElement("famille");
            famille.setAttribute("codeFamille", this.getLeType().getCode());
            famille.setAttribute("libelle", this.getLeType().getLibelleTypeMateriel());
            materiel.appendChild(famille);

            final Element quantite = document.createElement("quantite");
            quantite.appendChild(document.createTextNode("1"));
            materiel.appendChild(quantite);

            final Element date_vente = document.createElement("date_vente");
            date_vente.appendChild(document.createTextNode(this.getDateVente()));
            materiel.appendChild(date_vente);

            final Element date_installation = document.createElement("date_installation");
            date_installation.appendChild(document.createTextNode("this.getDateInstallation()"));
            materiel.appendChild(date_installation);

            final Element prix_vente = document.createElement("prix_vente");
            prix_vente.appendChild(document.createTextNode(String.valueOf(this.getPrixVente())));
            materiel.appendChild(prix_vente);

            final Element emplacement = document.createElement("emplacement");
            emplacement.appendChild(document.createTextNode("this.getEmplacement"));
            materiel.appendChild(emplacement);

            final Element nbJourAvantEcheance = document.createElement("nbJourAvantEcheance");
            nbJourAvantEcheance.appendChild(document.createTextNode(String.valueOf(this.getNbrJourAvantEcheance())));
            materiel.appendChild(nbJourAvantEcheance);

            //AFFICHAGE
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(document);

            StringWriter outWriter = new StringWriter();
            final StreamResult result = new StreamResult(outWriter);


            //PROLOGUE
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            //INDENTATION 
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            //sortie
            transformer.transform(source, result);
            StringBuffer sb = outWriter.getBuffer();
            String finalstring = sb.toString();
            return finalstring;
        } catch (final ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

        return " " ;
    }

}
        