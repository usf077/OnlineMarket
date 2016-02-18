/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ADMINIBM
 */
@Embeddable
public class CommandeProduitPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_com")
    private int idCom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_produit")
    private int idProduit;

    public CommandeProduitPK() {
    }

    public CommandeProduitPK(int idCom, int idProduit) {
        this.idCom = idCom;
        this.idProduit = idProduit;
    }

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCom;
        hash += (int) idProduit;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeProduitPK)) {
            return false;
        }
        CommandeProduitPK other = (CommandeProduitPK) object;
        if (this.idCom != other.idCom) {
            return false;
        }
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinemarket.entities.CommandeProduitPK[ idCom=" + idCom + ", idProduit=" + idProduit + " ]";
    }
    
}
