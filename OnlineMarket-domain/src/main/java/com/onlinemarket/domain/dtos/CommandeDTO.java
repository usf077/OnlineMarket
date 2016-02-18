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

public class CommandeDTO implements Serializable{
    private int identifiant;
    private String dateCommande;
    private String etat;
    private int idUtilisateur;
    private List<ProduitDTO> lstProduit;
    private double prix;
    
    public CommandeDTO(int identifiant, String dateCommande, int idUtilisateur, String etat) {
        this.identifiant = identifiant;
        this.dateCommande = dateCommande;
        this.idUtilisateur = idUtilisateur;
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
    
   
   
  
    public CommandeDTO(){
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
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

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }


    
}
