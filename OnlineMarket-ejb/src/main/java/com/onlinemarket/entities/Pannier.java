/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMINIBM
 */
@Entity
@Table(name = "pannier", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_pannier"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pannier.findAll", query = "SELECT p FROM Pannier p"),
    @NamedQuery(name = "Pannier.findByIdPannier", query = "SELECT p FROM Pannier p WHERE p.idPannier = :idPannier")})
public class Pannier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pannier", nullable = false)
    private Integer idPannier;
    @JoinTable(name = "pannier_produit", joinColumns = {
        @JoinColumn(name = "id_pannier", referencedColumnName = "id_pannier", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_produit", referencedColumnName = "id_produit", nullable = false)})
    @ManyToMany
    private Collection<Produit> produitCollection;
    @JoinColumn(name = "id_cmd", referencedColumnName = "id_cmd", nullable = false)
    @ManyToOne(optional = false)
    private Commande idCmd;

    public Pannier() {
    }

    public Pannier(Integer idPannier) {
        this.idPannier = idPannier;
    }

    public Integer getIdPannier() {
        return idPannier;
    }

    public void setIdPannier(Integer idPannier) {
        this.idPannier = idPannier;
    }

    @XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    public Commande getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Commande idCmd) {
        this.idCmd = idCmd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPannier != null ? idPannier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pannier)) {
            return false;
        }
        Pannier other = (Pannier) object;
        if ((this.idPannier == null && other.idPannier != null) || (this.idPannier != null && !this.idPannier.equals(other.idPannier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinemarket.entities.Pannier[ idPannier=" + idPannier + " ]";
    }
    
}
