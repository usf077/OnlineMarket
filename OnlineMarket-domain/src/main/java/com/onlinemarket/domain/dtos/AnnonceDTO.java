/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.domain.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ADMINIBM
 */

public class AnnonceDTO implements Serializable{
    private int identifiant;
    private String libelle;
    private String description;
    private String dateCreation;
    private String dateValidation;
    private String dateMiseEnVente;
    private int nbrProduit =0;
    private String etat;
    private int idUtilisateur;
    private List<ProduitDTO> lstProduit;
    private double prix =0;

    public AnnonceDTO(int identifiant, String libelle, String description, String dateCreation, String dateValidation, String dateMiseEnVente, int nbrProduit , String etat) {
        this.identifiant = identifiant;
        this.libelle = libelle;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateValidation = dateValidation;
        this.dateMiseEnVente = dateMiseEnVente;
        this.nbrProduit = nbrProduit;
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<ProduitDTO> getLstProduit() {
        return lstProduit;
    }

    public void setLstProduit(List<ProduitDTO> lstProduit) {
        this.lstProduit = lstProduit;
    }
    
   
   
  
    public AnnonceDTO(){
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getDateMiseEnVente() {
        return dateMiseEnVente;
    }

    public void setDateMiseEnVente(String dateMiseEnVente) {
        this.dateMiseEnVente = dateMiseEnVente;
    }

    public int getNbrProduit() {
        return nbrProduit;
    }

    public void setNbrProduit(int nbrProduit) {
        this.nbrProduit = nbrProduit;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    
}
