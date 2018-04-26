package myproject;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PDF {

    private Client client;

    public PDF(Client client) {
        this.client = client;
        try {
            this.generatePDF();
        } catch (DocumentException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private boolean generatePDF() throws IOException, DocumentException, URISyntaxException {
        if (this.client == null) return false;

        ArrayList<Materiel> lesMaterielsExpirés = new ArrayList<>();

        if (this.client.getLesContrats() == null) {
            try {
                this.client.loadContracts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ArrayList<ContratMaintenance> lesContrats = this.client.getLesContrats();
        if (lesContrats.size() > 0) {
            for (ContratMaintenance contrat : lesContrats) {
                if (contrat.getLesMaterielsAssures() == null) {
                    try {
                        contrat.loadMaterials();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList<Materiel> lesMateriels = contrat.getLesMaterielsAssures();
                for (Materiel materiel : lesMateriels) {
                    if (materiel.getNbrJourAvantEcheance() <= 0) {
                        lesMaterielsExpirés.add(materiel);
                    }
                }
            }
        }

        String pdfName = "outdated_materials_" + client.getNumClient() + ".pdf";
        String destination = "generated/pdf/" + pdfName;

        File file = new File(destination);
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("Impossible de supprimer le PDF existant.");
            }
        }
        file.getParentFile().mkdirs();

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(destination));
        document.open();

        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

        Paragraph parHeader = new Paragraph();
        parHeader.add(new Paragraph("VDEV", chapterFont));
        parHeader.add(new Paragraph("73 rue Nationale, 75007, Paris", paragraphFont));
        parHeader.add(new Paragraph("03-27-24-79-22", paragraphFont));
        parHeader.add(new Paragraph("contact@vdev.fr", paragraphFont));

        Paragraph parClient = new Paragraph();

        parClient.add(new Paragraph(client.getNom(), chapterFont) {{
            setAlignment(Element.ALIGN_RIGHT);
        }});
        parClient.add(new Paragraph(client.getAdresse(), paragraphFont) {{
            setAlignment(Element.ALIGN_RIGHT);
        }});
        parClient.add(new Paragraph(client.getTelClient(), paragraphFont) {{
            setAlignment(Element.ALIGN_RIGHT);
        }});

        Paragraph parExplanations = new Paragraph();
        parExplanations.setSpacingBefore(50);

        Paragraph parList = null;

        if (lesMaterielsExpirés.size() <= 0) {
            parExplanations.add(new Paragraph("Les matériels présents dans nos contrats sont tous assurés, aucun renouvellement n'est prévu.", paragraphFont) {{
                setAlignment(Element.ALIGN_LEFT);
            }});
        } else {
            parExplanations.add(new Paragraph("Ci-dessous la liste des matériels disposant d'un date d'échéance dépassée. Merci d'en tenir compte.") {{
                setAlignment(Element.ALIGN_LEFT);
            }});

            parList = new Paragraph();
            parList.setSpacingBefore(20);

            List list = new List();

            for (Materiel materiel : lesMaterielsExpirés) {
                ListItem item = new ListItem(materiel.getNumSerie() + " • " + materiel.getNom() + " • " + materiel.getRef());
                list.add(item);
            }

            parList.add(list);
        }

        Image img = PngImage.getImage("resources/vdev.png");
        img.setAbsolutePosition(document.right() - 100, document.bottom());
        img.scaleAbsolute(100, 100);

        document.add(parHeader);
        document.add(parClient);
        document.add(parExplanations);
        if (lesMaterielsExpirés.size() > 0) document.add(parList);
        document.add(img);
        document.close();

        System.out.println("\nFichier PDF pour le contrat avec l'entreprise \u001B[36m" + client.getNom() + "\u001B[0m généré !");

        return true;
    }

}
