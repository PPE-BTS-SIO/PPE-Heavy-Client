/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.Date;
import java.io.File;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Joel
 */
public class Materiel {
    private int numSerie;
    private Date dateVente, dateInstallation;
    private double prixVente;
    private String emplacement, ref, numContrat;
    private String nom;
    private TypeMateriel leType;
    
    //Focntion pour avoir les jours avant fin contrat 
    public int getNbrJourAvantEcheance(){
        //si object n'est pas sur contrat retourne 0
        if (dateVente != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(dateVente); //Utilise la date de vente 
            c.add(Calendar.DATE, 365); // la date de ventre + 1 ans 
            Date DateEcheance = c.getTime();
            if (dateVente.after(DateEcheance)){
                int diffJour = (int)(DateEcheance.getTime() - dateVente.getTime()) / (1000 * 60 * 60 * 24);
                return diffJour; //retourne la difference 
            }else{
            return 0;
            }
            
        }
        return 0;
    }
    public String getNumContrat(){
        return numContrat;
    }
    public String getRef(){
        return ref;
    }
    public String  getDateInstallation(){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String strDateInstallation = df.format(dateInstallation);
        
        return strDateInstallation;
    }
    public String getNom(){
        return nom;
    }
    public int getNumSerie() {
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
    
    public String xmlMateriel() throws ParserConfigurationException{
        //Récuperation d'une instance de la classe "DocumentBuilderFactory
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try {
            //Creation d'un parseur 
            final DocumentBuilder builder = factory.newDocumentBuilder();
            
            //Creation d'un document 
            final Document document= builder.newDocument();
            
            //Creation de l'élément racine 
            final Element materiel = document.createElement("materiel");
            
            //ajout élément racine au document
            document.appendChild(materiel);
                      
            materiel.setAttribute("numSerie", String.valueOf(this.getNumSerie()));
            
            //TOUT CE QUI EST EN DESSOUS DE MATERIEL
            final Element type = document.createElement("type");
            type.setAttribute("refInterne", this.getRef() );
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
            }
	catch (final ParserConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerException e) {
	    e.printStackTrace();
	}	
       			
    return "cest null";
    }
    
}
        