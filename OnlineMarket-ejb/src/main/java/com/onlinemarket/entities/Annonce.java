/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMINIBM
 */
@Entity
@Table(name = "annonce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Annonce.findAll", query = "SELECT a FROM Annonce a"),
    @NamedQuery(name = "Annonce.findByIdAnnonce", query = "SELECT a FROM Annonce a WHERE a.idAnnonce = :idAnnonce"),
    @NamedQuery(name = "Annonce.findByNomA", query = "SELECT a FROM Annonce a WHERE a.nomA = :nomA"),
    @NamedQuery(name = "Annonce.findByDescriptionA", query = "SELECT a FROM Annonce a WHERE a.descriptionA = :descriptionA"),
    @NamedQuery(name = "Annonce.findByDateCreation", query = "SELECT a FROM Annonce a WHERE a.dateCreation = :dateCreation"),
    @NamedQuery(name = "Annonce.findByDateValidation", query = "SELECT a FROM Annonce a WHERE a.dateValidation = :dateValidation"),
    @NamedQuery(name = "Annonce.findByNbrProd", query = "SELECT a FROM Annonce a WHERE a.nbrProd = :nbrProd"),
    @NamedQuery(name = "Annonce.findByDateMiseVente", query = "SELECT a FROM Annonce a WHERE a.dateMiseVente = :dateMiseVente"),
    @NamedQuery(name = "Annonce.findByEtatA", query = "SELECT a FROM Annonce a WHERE a.etatA = :etatA")})
public class Annonce implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_annonce")
    private Integer idAnnonce;
    @Size(max = 254)
    @Column(name = "nom_a")
    private String nomA;
    @Size(max = 254)
    @Column(name = "description_a")
    private String descriptionA;
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Column(name = "date_validation")
    @Temporal(TemporalType.DATE)
    private Date dateValidation;
    @Column(name = "nbr_prod")
    private Integer nbrProd;
    @Column(name = "date_mise_vente")
    @Temporal(TemporalType.DATE)
    private Date dateMiseVente;
    @Size(max = 255)
    @Column(name = "etat_a")
    private String etatA;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnnonce" , fetch = FetchType.EAGER)
    private Collection<Produit> produitCollection;

    public Annonce() {
    }

    public Annonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Integer getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public String getDescriptionA() {
        return descriptionA;
    }

    public void setDescriptionA(String descriptionA) {
        this.descriptionA = descriptionA;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Integer getNbrProd() {
        return nbrProd;
    }

    public void setNbrProd(Integer nbrProd) {
        this.nbrProd = nbrProd;
    }

    public Date getDateMiseVente() {
        return dateMiseVente;
    }

    public void setDateMiseVente(Date dateMiseVente) {
        this.dateMiseVente = dateMiseVente;
    }

    public String getEtatA() {
        return etatA;
    }

    public void setEtatA(String etatA) {
        this.etatA = etatA;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnnonce != null ? idAnnonce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annonce)) {
            return false;
        }
        Annonce other = (Annonce) object;
        if ((this.idAnnonce == null && other.idAnnonce != null) || (this.idAnnonce != null && !this.idAnnonce.equals(other.idAnnonce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinemarket.entities.Annonce[ idAnnonce=" + idAnnonce + " ]";
    }
    
}
