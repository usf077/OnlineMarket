/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.imp;

import com.onlinemarket.domain.dtos.ProduitDTO;
import com.onlinemarket.domain.interfaces.ProductServiceInt;
import com.onlinemarket.entities.Annonce;
import com.onlinemarket.entities.Categorie;
import com.onlinemarket.entities.Produit;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMINIBM
 */
@Stateless
public class ProductServiceImp implements ProductServiceInt {

     @PersistenceContext
    private EntityManager em;
     
    @Override
    public int CreateProduct(ProduitDTO p) {
         try {
             Produit pe = new Produit();
             Annonce a = em.find(Annonce.class, p.getIdAnnonce());
             if(a!=null){
             pe.setNomP(p.getNom());
             pe.setDescriptionP(p.getDescription());
             pe.setCategorie(p.getCategorie());
             pe.setIdAnnonce(a);
             pe.setPrix(p.getPrix());
             pe.setDateMiseVente(DateFormat.getInstance().parse(p.getDateMiseEnVente()));
             pe.setImage(p.getImgesUrl());
             Categorie c = em.find(Categorie.class, p.getIdCategorie());
             if(c!=null)
                 pe.setIdCat(c);
             a.getProduitCollection().add(pe);
             em.persist(pe);
             em.merge(a);
             em.flush();
             return pe.getIdProduit();
             }
         } catch (ParseException ex) {
             Logger.getLogger(ProductServiceImp.class.getName()).log(Level.SEVERE, null, ex);
         }
         return -1;   
    }
    
}
