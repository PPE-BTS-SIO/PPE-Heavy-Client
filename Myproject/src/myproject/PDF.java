package myproject;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDF {

    private Client client;

    public PDF(Client client) {
        this.client = client;
        try {
            this.generatePDF();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private boolean generatePDF() throws FileNotFoundException, DocumentException {
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

        parExplanations.add(new Paragraph("Ci-dessous la liste des matériels disposant d'un date d'échéance dépassée. Merci d'en tenir compte.") {{
            setAlignment(Element.ALIGN_LEFT);
        }});

        Paragraph parList = new Paragraph();
        parList.setSpacingBefore(20);

        List list = new List();

        ListItem item = new ListItem("test1");
        ListItem item2 = new ListItem("test2");

        list.add(item);
        list.add(item2);

        parList.add(list);

        document.add(parHeader);
        document.add(parClient);
        document.add(parExplanations);
        document.add(parList);
        document.close();

        System.out.println("\nFichier PDF pour le contrat avec l'entreprise \u001B[36m" + client.getNom() + "\u001B[0m généré !");

        return true;
    }

}
