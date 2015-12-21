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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMINIBM
 */
@Entity
@Table(name = "produit", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_produit"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit"),
    @NamedQuery(name = "Produit.findByNomP", query = "SELECT p FROM Produit p WHERE p.nomP = :nomP"),
    @NamedQuery(name = "Produit.findByCategorie", query = "SELECT p FROM Produit p WHERE p.categorie = :categorie"),
    @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix"),
    @NamedQuery(name = "Produit.findByDateMiseVente", query = "SELECT p FROM Produit p WHERE p.dateMiseVente = :dateMiseVente"),
    @NamedQuery(name = "Produit.findByDescriptionP", query = "SELECT p FROM Produit p WHERE p.descriptionP = :descriptionP"),
    @NamedQuery(name = "Produit.findByImage", query = "SELECT p FROM Produit p WHERE p.image = :image")})
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produit", nullable = false)
    private Integer idProduit;
    @Size(max = 254)
    @Column(name = "nom_p", length = 254)
    private String nomP;
    @Size(max = 254)
    @Column(name = "categorie", length = 254)
    private String categorie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix", precision = 17, scale = 17)
    private Double prix;
    @Column(name = "date_mise_vente")
    @Temporal(TemporalType.DATE)
    private Date dateMiseVente;
    @Size(max = 254)
    @Column(name = "description_p", length = 254)
    private String descriptionP;
    @Size(max = 254)
    @Column(name = "image", length = 254)
    private String image;
    @ManyToMany(mappedBy = "produitCollection")
    private Collection<Pannier> pannierCollection;
    @JoinColumn(name = "id_annonce", referencedColumnName = "id_annonce", nullable = false)
    @ManyToOne(optional = false)
    private Annonce idAnnonce;
    @JoinColumn(name = "id_cat", referencedColumnName = "id_cat", nullable = false)
    @ManyToOne(optional = false)
    private Categorie idCat;

    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getDateMiseVente() {
        return dateMiseVente;
    }

    public void setDateMiseVente(Date dateMiseVente) {
        this.dateMiseVente = dateMiseVente;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Collection<Pannier> getPannierCollection() {
        return pannierCollection;
    }

    public void setPannierCollection(Collection<Pannier> pannierCollection) {
        this.pannierCollection = pannierCollection;
    }

    public Annonce getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Categorie getIdCat() {
        return idCat;
    }

    public void setIdCat(Categorie idCat) {
        this.idCat = idCat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinemarket.entities.Produit[ idProduit=" + idProduit + " ]";
    }
    
}
