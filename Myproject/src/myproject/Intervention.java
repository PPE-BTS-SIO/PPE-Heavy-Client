/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import javax.persistence.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Joel
 */
@Entity
@Table(name = "intervention", catalog = "ppe", schema = "")
@NamedQueries({
        @NamedQuery(name = "Intervention.findAll", query = "SELECT i FROM Intervention i")
        , @NamedQuery(name = "Intervention.findByNumeroIntervention", query = "SELECT i FROM Intervention i WHERE i.numeroIntervention = :numeroIntervention")
        , @NamedQuery(name = "Intervention.findByDateIntervention", query = "SELECT i FROM Intervention i WHERE i.dateIntervention = :dateIntervention")
        , @NamedQuery(name = "Intervention.findByCommentaire", query = "SELECT i FROM Intervention i WHERE i.commentaire = :commentaire")
        , @NamedQuery(name = "Intervention.findByLocalisation", query = "SELECT i FROM Intervention i WHERE i.localisation = :localisation")
        , @NamedQuery(name = "Intervention.findByMatricule", query = "SELECT i FROM Intervention i WHERE i.matricule = :matricule")
        , @NamedQuery(name = "Intervention.findByNumeroClient", query = "SELECT i FROM Intervention i WHERE i.numeroClient = :numeroClient")})
public class Intervention implements Serializable {

    private static final long serialVersionUID = 1L;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Numero_Intervention")
    private Integer numeroIntervention;
    @Column(name = "Date_Intervention")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIntervention;
    @Column(name = "Commentaire")
    private String commentaire;
    @Column(name = "Localisation")
    private String localisation;
    @Column(name = "Matricule")
    private String matricule;
    @Column(name = "NumeroClient")
    private String numeroClient;

    public Intervention() {
    }

    public Intervention(Integer numeroIntervention) {
        this.numeroIntervention = numeroIntervention;
    }

    public Integer getNumeroIntervention() {
        return numeroIntervention;
    }

    public void setNumeroIntervention(Integer numeroIntervention) {
        Integer oldNumeroIntervention = this.numeroIntervention;
        this.numeroIntervention = numeroIntervention;
        changeSupport.firePropertyChange("numeroIntervention", oldNumeroIntervention, numeroIntervention);
    }

    public Date getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(Date dateIntervention) {
        Date oldDateIntervention = this.dateIntervention;
        this.dateIntervention = dateIntervention;
        changeSupport.firePropertyChange("dateIntervention", oldDateIntervention, dateIntervention);
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        String oldCommentaire = this.commentaire;
        this.commentaire = commentaire;
        changeSupport.firePropertyChange("commentaire", oldCommentaire, commentaire);
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        String oldLocalisation = this.localisation;
        this.localisation = localisation;
        changeSupport.firePropertyChange("localisation", oldLocalisation, localisation);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        String oldMatricule = this.matricule;
        this.matricule = matricule;
        changeSupport.firePropertyChange("matricule", oldMatricule, matricule);
    }

    public String getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(String numeroClient) {
        String oldNumeroClient = this.numeroClient;
        this.numeroClient = numeroClient;
        changeSupport.firePropertyChange("numeroClient", oldNumeroClient, numeroClient);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroIntervention != null ? numeroIntervention.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervention)) {
            return false;
        }
        Intervention other = (Intervention) object;
        if ((this.numeroIntervention == null && other.numeroIntervention != null) || (this.numeroIntervention != null && !this.numeroIntervention.equals(other.numeroIntervention))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myproject.Intervention[ numeroIntervention=" + numeroIntervention + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
