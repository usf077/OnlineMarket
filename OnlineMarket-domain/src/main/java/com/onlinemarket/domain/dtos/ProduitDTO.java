/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.domain.dtos;

import java.io.Serializable;

/**
 *
 * @author ADMINIBM
 */
public class ProduitDTO implements Serializable {
 private int  identifiant ;
 private int idCategorie;
 private String Categorie;
 private String nom;
 private String description;
 private String dateMiseEnVente;
 private Double prix;
 private String imgesUrl;
 private boolean isEditable;
private int idAnnonce;

    public ProduitDTO(int identifiant, int idCategorie, String Categorie, String nom, String description, String dateMiseEnVente, Double prix, String imgesUrl) {
        this.identifiant = identifiant;
        this.idCategorie = idCategorie;
        this.Categorie = Categorie;
        this.nom = nom;
        this.description = description;
        this.dateMiseEnVente = dateMiseEnVente;
        this.prix = prix;
        this.imgesUrl = imgesUrl;
    }

    public ProduitDTO() {
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateMiseEnVente() {
        return dateMiseEnVente;
    }

    public void setDateMiseEnVente(String dateMiseEnVente) {
        this.dateMiseEnVente = dateMiseEnVente;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImgesUrl() {
        return imgesUrl;
    }

    public void setImgesUrl(String imgesUrl) {
        this.imgesUrl = imgesUrl;
    }

    public boolean isIsEditable() {
        return isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

}
