/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Joel  / Thomas / Raphael
 */
public class SearchClient extends javax.swing.JFrame {
    
    private Client selectedClient;
    public Boolean flagConnection = false;

    /**
     * Creates new form SearchClient
     */
    public SearchClient() throws SQLException{
        initComponents();
         connectionBDD();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboxClient = new javax.swing.JComboBox<>();
        btnXml = new javax.swing.JButton();
        btnContrat = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnRetour = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        jLabel2.setText("       CASHCASH");
        jLabel2.setToolTipText("");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setText("Nom du Client");

        cboxClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxClientActionPerformed(evt);
            }
        });

        btnXml.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        btnXml.setText("XML");
        btnXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXmlActionPerformed(evt);
            }
        });

        btnContrat.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        btnContrat.setText("Contrat de Maintenance");
        btnContrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContratActionPerformed(evt);
            }
        });

        btnPDF.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        btnPDF.setText("PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btnRetour.setText("Déconnection");
        btnRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboxClient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnXml, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnContrat))))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRetour)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXml)
                    .addComponent(btnPDF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContrat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(btnRetour)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                   

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        new PDF(selectedClient);
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnContratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContratActionPerformed
        //new EtatContrat(selectedClient);
        this.setVisible(false);
        try {
            new EtatContrat().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnContratActionPerformed

    private void cboxClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxClientActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        String clientName = (String) cb.getSelectedItem();
        for (Client client : Client.getLesClients()) {
            if (client.getNom().equalsIgnoreCase(clientName)) {
                this.selectedClient = client;
                break;
            }
        }

    }//GEN-LAST:event_cboxClientActionPerformed

    private void btnXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXmlActionPerformed
        try {
            // TODO add your handling code here:
            new GestionMateriels(selectedClient);
        } catch (SQLException ex) {
            Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXmlActionPerformed

    private void btnRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetourActionPerformed
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_btnRetourActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked
  
    private void connectionBDD() throws SQLException {
        if (flagConnection == false){
            Connecting connection = new Connecting();

            System.out.println("\nRécupération des clients...");
            ResultSet result = connection.Select("SELECT * FROM Client");
            while (result.next()) {
                String numClient = result.getString(1);
                String nom = result.getString(2);
                String raisonSociale = result.getString(3);
                String siren = result.getString(4);
                String codeApe = result.getString(5);
                String adresse = result.getString(6);
                String numTelephone = result.getString(7);
                String fax = result.getString(8);
                int dureeDeplacement = result.getInt(9);
                int distanceKm = result.getInt(10);
                String numAgence = result.getString(11);
                String url = result.getString(12);
                String logo = result.getString(13);

                new Client(
                    numClient,
                    raisonSociale,
                    siren,
                    codeApe,
                    adresse,
                    numTelephone,
                    url,
                    logo,
                    numAgence,
                    nom,
                    dureeDeplacement,
                    distanceKm
                );

                cboxClient.addItem(nom);
            }
            System.out.println("Clients récupérés : \u001B[36m" + Client.getLesClients().size() + "\u001B[0m.");

            //****** Récupération des Familles Produits dans la BDD *******//

            System.out.println("\nRécupération des Familles produits");
            ResultSet result_Famille_Produit = connection.Select("SELECT * FROM Famille_Produit");
            while (result_Famille_Produit.next()){
                String unCodeFamille = result_Famille_Produit.getString(1);
                String unLibelleFamille = result_Famille_Produit.getString(2);

                new Famille(
                        unCodeFamille,
                        unLibelleFamille
                );
            }
            System.out.println("\nFamille Produit récupérés : \u001B[36m " + Famille.getLesFamilles().size() + "\u001B[0m .");

            //****** Récupération des Type de Matériels dans la BDD *******//

            System.out.println("\nRécupération des Types de Matériels");
            ResultSet result_Type_Materiel = connection.Select("SELECT * FROM Type_Materiel");
            while (result_Type_Materiel.next()){
                String referenceInterne = result_Type_Materiel.getString(1);
                String libelleTypeMateriel = result_Type_Materiel.getString(2);
                String codeFamille = result_Type_Materiel.getString(3);


                new TypeMateriel(
                        referenceInterne,
                        libelleTypeMateriel,
                        codeFamille
                );
            }
            System.out.println("\nTypes de Matériels récupérés : \u001B[36m" + TypeMateriel.getLesTypesMateriel().size() + "\u001B[0m .");
            flagConnection = true ;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new SearchClient().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContrat;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnRetour;
    private javax.swing.JButton btnXml;
    private static javax.swing.JComboBox<String> cboxClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}

