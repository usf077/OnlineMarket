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

public class CategorieDTO implements Serializable{
    private int identifiant;
    private String libelle;
    private String parentId;

    public CategorieDTO(int identifiant, String libelle) {
        this.identifiant = identifiant;
        this.libelle = libelle;
    }
   
  
    public CategorieDTO(){
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    
}
