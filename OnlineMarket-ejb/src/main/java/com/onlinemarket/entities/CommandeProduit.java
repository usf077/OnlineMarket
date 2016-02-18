/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMINIBM
 */
@Entity
@Table(name = "commande_produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeProduit.findAll", query = "SELECT c FROM CommandeProduit c"),
    @NamedQuery(name = "CommandeProduit.findByIdCom", query = "SELECT c FROM CommandeProduit c WHERE c.commandeProduitPK.idCom = :idCom"),
    @NamedQuery(name = "CommandeProduit.findByIdProduit", query = "SELECT c FROM CommandeProduit c WHERE c.commandeProduitPK.idProduit = :idProduit"),
    @NamedQuery(name = "CommandeProduit.findByQteCmd", query = "SELECT c FROM CommandeProduit c WHERE c.qteCmd = :qteCmd")})
public class CommandeProduit implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommandeProduitPK commandeProduitPK;
    @Column(name = "qte_cmd")
    private Integer qteCmd;
    @JoinColumn(name = "id_com", referencedColumnName = "id_cmd", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commande commande;
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produit produit;

    public CommandeProduit() {
    }

    public CommandeProduit(CommandeProduitPK commandeProduitPK) {
        this.commandeProduitPK = commandeProduitPK;
    }

    public CommandeProduit(int idCom, int idProduit) {
        this.commandeProduitPK = new CommandeProduitPK(idCom, idProduit);
    }

    public CommandeProduitPK getCommandeProduitPK() {
        return commandeProduitPK;
    }

    public void setCommandeProduitPK(CommandeProduitPK commandeProduitPK) {
        this.commandeProduitPK = commandeProduitPK;
    }

    public Integer getQteCmd() {
        return qteCmd;
    }

    public void setQteCmd(Integer qteCmd) {
        this.qteCmd = qteCmd;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandeProduitPK != null ? commandeProduitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeProduit)) {
            return false;
        }
        CommandeProduit other = (CommandeProduit) object;
        if ((this.commandeProduitPK == null && other.commandeProduitPK != null) || (this.commandeProduitPK != null && !this.commandeProduitPK.equals(other.commandeProduitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinemarket.entities.CommandeProduit[ commandeProduitPK=" + commandeProduitPK + " ]";
    }
    
}
