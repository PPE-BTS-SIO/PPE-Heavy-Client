package myproject;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
import com.itextpdf.text.*;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
 
import java.net.URISyntaxException;
 
import java.sql.SQLException;
 
import java.util.ArrayList;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 
import java.util.Calendar;
import java.util.Date;
 
 
 
public class GestionMateriels {
 
    private Client client;
 
    public GestionMateriels(Client client) throws SQLException {
        this.client = client;
        try {
            this.xmlClient() ;
        } catch (DocumentException | IOException | URISyntaxException e) {
        }
    }

 
    private void xmlClient() throws IOException, DocumentException, URISyntaxException, SQLException {
        Connecting connection = new Connecting();
        ArrayList<Materiel> lesMaterielsExpires = new ArrayList<>();
        ArrayList<Materiel> lesMaterielsSousContrat = new ArrayList<>();
        ArrayList<Materiel> lesMaterielAssures = new ArrayList<>();
        
        if (this.client.getLesContrats() == null) {
            try {
                this.client.loadContracts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
        // Permet de remplir les listes des matériels
        System.out.println("\nGénération du fichier XML...");
            Client leClient = new Client();
            leClient = (Client) connection.chargerDepuisBase(this.client.getNumClient(), "Client");
            System.out.println("Client récupéré : " + leClient.getNom());
            ArrayList<ContratMaintenance> leContrat = new ArrayList();
            ArrayList<Materiel> leMateriel = new ArrayList();
            leContrat = leClient.getLesContrats();
            leMateriel = leClient.getLesMateriels();
            for(ContratMaintenance contrat : leContrat){
                System.out.println("le nom du contrat est : " + contrat.getNumContrat());
            int jourRestants = contrat.getJourRestants();
               
                for (Materiel unMateriel : leMateriel){
                    System.out.println("le nom du materiel est : " + unMateriel.getNom());
                    String numContrat = unMateriel.getNumContrat();
                    if (jourRestants <= 0 | numContrat == null) {
                        lesMaterielsExpires.add(unMateriel);
                    }
                    else{
                        lesMaterielsSousContrat.add(unMateriel);
                    }
                } 
            }
            
      
        try {
             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Creation d'un parseur
            final DocumentBuilder builder = factory.newDocumentBuilder();
 
            //Creation d'un document
            final Document document = builder.newDocument();
 
            //Creation des éléments racine
            final Element listeMateriel = document.createElement("listeMateriel");
            final Element lesMateriels = document.createElement("materiels");
 
 
 
            //ajout élément racine au document
            document.appendChild(listeMateriel);
            listeMateriel.appendChild(lesMateriels);
            lesMateriels.setAttribute("idClient", String.valueOf(client.getNumClient()));

        for (Materiel unMaterielExpire : lesMaterielsExpires) {
            //Creation des sous éléments
 
            final Element type = document.createElement("type");
            final Element famille = document.createElement("famille");
            final Element quantite = document.createElement("quantite");
            final Element date_vente = document.createElement("date_vente");
            final Element date_installation = document.createElement("date_installation");
            final Element prix_vente = document.createElement("prix_vente");
            final Element emplacement = document.createElement("emplacement");
            final Element nbJourAvantEcheance = document.createElement("nbJourAvantEcheance");
            final Element sousContrat = document.createElement("sousContrat");
            final Element horsContrat = document.createElement("horsContrat");
            final Element unMateriel = document.createElement("materiel");
 
            lesMateriels.appendChild(horsContrat);
            horsContrat.appendChild(unMateriel);
 
            unMateriel.setAttribute("numSerie", String.valueOf(unMaterielExpire.getNumSerie()));
 
 
            type.setAttribute("refInterne", unMaterielExpire.getRef());
            type.setAttribute("libelle", unMaterielExpire.getNom());
 
            unMateriel.appendChild(type);
            //System.out.println(unMaterielExpire.getLeType().getLaFamille().getCodeFamille());
            famille.setAttribute("codeFamille", String.valueOf(unMaterielExpire.getLeType().getLaFamille().getCodeFamille()));
            famille.setAttribute("libelle", String.valueOf(unMaterielExpire.getLeType().getLaFamille().getLibelleFamille()));
            unMateriel.appendChild(famille);
            
            quantite.appendChild(document.createTextNode(String.valueOf(unMaterielExpire.getQuantite())));
            unMateriel.appendChild(quantite);
 
            date_vente.appendChild(document.createTextNode(unMaterielExpire.getDateVente()));
            unMateriel.appendChild(date_vente);
 
            date_installation.appendChild(document.createTextNode(String.valueOf(unMaterielExpire.getDateInstallation())));
            unMateriel.appendChild(date_installation);
 
            prix_vente.appendChild(document.createTextNode(String.valueOf(unMaterielExpire.getPrixVente())));
            unMateriel.appendChild(prix_vente);
 
            emplacement.appendChild(document.createTextNode(unMaterielExpire.getEmplacement()));
            unMateriel.appendChild(emplacement);
 
            nbJourAvantEcheance.appendChild(document.createTextNode(String.valueOf(unMaterielExpire.getNbrJourAvantEcheance())));
            unMateriel.appendChild(nbJourAvantEcheance);
 
        }
 
        for (Materiel unMaterielSousContrat : lesMaterielsSousContrat){
 
            final Element type = document.createElement("type");
            final Element famille = document.createElement("famille");
            final Element quantite = document.createElement("quantite");
            final Element date_vente = document.createElement("date_vente");
            final Element date_installation = document.createElement("date_installation");
            final Element prix_vente = document.createElement("prix_vente");
            final Element emplacement = document.createElement("emplacement");
            final Element nbJourAvantEcheance = document.createElement("nbJourAvantEcheance");
            final Element sousContrat = document.createElement("sousContrat");
            final Element horsContrat = document.createElement("horsContrat");
            final Element unMateriel = document.createElement("materiel");
            
            lesMateriels.appendChild(sousContrat);
            sousContrat.appendChild(unMateriel);
 
            unMateriel.setAttribute("numSerie", String.valueOf(unMaterielSousContrat.getNumSerie()));
 
 
            type.setAttribute("refInterne", unMaterielSousContrat.getRef());
            type.setAttribute("libelle", unMaterielSousContrat.getNom());
 
            unMateriel.appendChild(type);
 
            famille.setAttribute("codeFamille", String.valueOf(unMaterielSousContrat.getLeType().getLaFamille().getCodeFamille()));
            famille.setAttribute("libelle", String.valueOf(unMaterielSousContrat.getLeType().getLaFamille().getLibelleFamille()));
            unMateriel.appendChild(famille);
            
            quantite.appendChild(document.createTextNode(String.valueOf(unMaterielSousContrat.getQuantite())));
            unMateriel.appendChild(quantite);
 
            date_vente.appendChild(document.createTextNode(unMaterielSousContrat.getDateVente()));
            unMateriel.appendChild(date_vente);
 
            date_installation.appendChild(document.createTextNode(String.valueOf(unMaterielSousContrat.getDateInstallation())));
            unMateriel.appendChild(date_installation);
 
            prix_vente.appendChild(document.createTextNode(String.valueOf(unMaterielSousContrat.getPrixVente())));
            unMateriel.appendChild(prix_vente);
 
            emplacement.appendChild(document.createTextNode(unMaterielSousContrat.getEmplacement()));
            unMateriel.appendChild(emplacement);
 
            nbJourAvantEcheance.appendChild(document.createTextNode(String.valueOf(unMaterielSousContrat.getNbrJourAvantEcheance())));
            unMateriel.appendChild(nbJourAvantEcheance);
 
        }
 
 
            //AFFICHAGE
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
 
            //PROLOGUE
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
 
            //INDENTATION
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            String pdfName = "xml_" + client.getNumClient() + ".xml";
            String destination = "generated/xml/" + pdfName;
            //StreamResult result = new StreamResult(new File("/Users/raphaeltribouilloy/Desktop/PPE-Heavy-Client/Myproject/generated/xml/Materielclientcli"+ client.getNumClient() +".xml"));
            File file = new File(destination);
            if (file.exists()) {
                if (!file.delete()) {
                    System.out.println("Impossible de supprimer le fichier existant.");
                }
            }
            file.getParentFile().mkdirs();
            StreamResult result = new StreamResult(file);
          
            //Sortie sur la console
            //StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
 
            System.out.println("Fichier sauvegardé!");
 
        } catch (final ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
 
 
    }
 
 
 
}

