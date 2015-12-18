/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMINIBM
 */
@Entity
@Table(name = "livres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livres.findAll", query = "SELECT l FROM Livres l"),
    @NamedQuery(name = "Livres.findByIdentifiant", query = "SELECT l FROM Livres l WHERE l.identifiant = :identifiant"),
    @NamedQuery(name = "Livres.findByTitre", query = "SELECT l FROM Livres l WHERE l.titre = :titre"),
    @NamedQuery(name = "Livres.findByAuteur", query = "SELECT l FROM Livres l WHERE l.auteur = :auteur")})
public class Livres implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifiant")
    private Integer identifiant;
    @Size(max = 255)
    @Column(name = "titre")
    private String titre;
    @Size(max = 255)
    @Column(name = "auteur")
    private String auteur;

    public Livres() {
    }

    public Livres(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identifiant != null ? identifiant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livres)) {
            return false;
        }
        Livres other = (Livres) object;
        if ((this.identifiant == null && other.identifiant != null) || (this.identifiant != null && !this.identifiant.equals(other.identifiant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinemarket.entities.Livres[ identifiant=" + identifiant + " ]";
    }
    
}
