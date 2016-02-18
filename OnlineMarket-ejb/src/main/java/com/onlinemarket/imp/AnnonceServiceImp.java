/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.imp;

import com.onlinemarket.domain.dtos.AnnonceDTO;
import com.onlinemarket.domain.dtos.CategorieDTO;
import com.onlinemarket.domain.dtos.ProduitDTO;
import com.onlinemarket.domain.interfaces.AnnonceServiceInt;
import com.onlinemarket.entities.Annonce;
import com.onlinemarket.entities.Annonce_;
import com.onlinemarket.entities.Categorie;
import com.onlinemarket.entities.Categorie_;
import com.onlinemarket.entities.Produit;
import com.onlinemarket.entities.Produit_;
import com.onlinemarket.entities.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author ADMINIBM
 */
@Stateless
public class AnnonceServiceImp implements AnnonceServiceInt {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CategorieDTO> getCategories() {
        List<Categorie> lstc = em.createNamedQuery("Categorie.findAll").getResultList();
        List<CategorieDTO> lstcr = new ArrayList<>();
        for (Categorie c : lstc) {
            lstcr.add(new CategorieDTO(c.getIdCat(), c.getNomC()));
        }
        return lstcr;
    }

    @Override
    public List<AnnonceDTO> getAnnonces() {
        List<Annonce> lsta = em.createNamedQuery("Annonce.findByEtatA").setParameter("etatA", "disponible").getResultList();
        List<AnnonceDTO> lstar = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Annonce a : lsta) {
            AnnonceDTO adto = new AnnonceDTO(a.getIdAnnonce(), a.getNomA(), a.getDescriptionA(),
                    a.getDateCreation() != null ? df.format(a.getDateCreation()) : "",
                    a.getDateValidation() != null ? df.format(a.getDateValidation()) : "",
                    a.getDateMiseVente() != null ? df.format(a.getDateValidation()) : "",
                    a.getProduitCollection().size(), a.getEtatA());
            for (Produit p : a.getProduitCollection()) {
                if (adto.getLstProduit() == null) {
                    adto.setLstProduit(new ArrayList<ProduitDTO>());
                }
                adto.getLstProduit().add(new ProduitDTO(p.getIdProduit(), p.getIdCat() != null ? p.getIdCat().getIdCat() : 0, p.getCategorie(), p.getImage(), p.getDescriptionP(),
                        p.getDateMiseVente() != null ? df.format(p.getDateMiseVente()) : "", p.getPrix(), p.getImage()));
            }
            lstar.add(adto);
        }
        return lstar;
    }

    @Override
    public int count(String txt, int idCategorie, String etat) {
        //  javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        //javax.persistence.criteria.Root<Annonce> rt = cq.from(Annonce.class);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<Annonce> annonce = cq.from(Annonce.class);
        Expression e = null;

        Join<Annonce, Produit> produit = annonce.join(Annonce_.produitCollection);
        Join<Produit, Categorie> categorie = produit.join(Produit_.idCat);
        if (idCategorie != 0 && idCategorie != 2) {
            e = cb.equal(categorie.get(Categorie_.idCat), idCategorie);
        }
        if (txt != null && !txt.isEmpty()) {
            if (e == null) {
                e = cb.or(cb.like(cb.lower(annonce.get(Annonce_.nomA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(annonce.get(Annonce_.descriptionA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(produit.get(Produit_.nomP)), "%" + txt.toLowerCase() + "%"),
                        cb.like(cb.lower(produit.get(Produit_.descriptionP)), "%" + txt.toLowerCase() + "%"));
            } else {
                e = cb.and(e, cb.or(cb.like(cb.lower(annonce.get(Annonce_.nomA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(annonce.get(Annonce_.descriptionA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(produit.get(Produit_.nomP)), "%" + txt.toLowerCase() + "%"),
                        cb.like(cb.lower(produit.get(Produit_.descriptionP)), "%" + txt.toLowerCase() + "%")));
            }
        }
        if (etat != null && !etat.isEmpty()) {
            if (e == null) {
                e = cb.equal(annonce.get(Annonce_.etatA), etat);
            } else {
                e = cb.and(e, cb.equal(annonce.get(Annonce_.etatA), etat));
            }
        }
        if (e!=null){
            cq.where(e);
        }
        cq.select(annonce).distinct(true);
        cq.select(em.getCriteriaBuilder().countDistinct(annonce));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<AnnonceDTO> findRange(String txt, int idCategorie, String etat, int[] range) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Expression e=null;
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<Annonce> annonce = cq.from(Annonce.class);
// cq.select(cq.from(Annonce.class));
        Join<Annonce, Produit> produit = annonce.join(Annonce_.produitCollection);
        Join<Produit, Categorie> categorie = produit.join(Produit_.idCat);
        if (idCategorie != 0 && idCategorie != 2) {
            e = cb.equal(categorie.get(Categorie_.idCat), idCategorie);
        }
        if (txt != null && !txt.isEmpty()) {
            if (e == null) {
                e = cb.or(cb.like(cb.lower(annonce.get(Annonce_.nomA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(annonce.get(Annonce_.descriptionA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(produit.get(Produit_.nomP)), "%" + txt.toLowerCase() + "%"),
                        cb.like(cb.lower(produit.get(Produit_.descriptionP)), "%" + txt.toLowerCase() + "%"));
            } else {
                e = cb.and(e, cb.or(cb.like(cb.lower(annonce.get(Annonce_.nomA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(annonce.get(Annonce_.descriptionA)), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(produit.get(Produit_.nomP)), "%" + txt.toLowerCase() + "%"),
                        cb.like(cb.lower(produit.get(Produit_.descriptionP)), "%" + txt.toLowerCase() + "%")));
            }
        }
        if (etat != null && !etat.isEmpty()) {
            if (e == null) {
                e = cb.equal(annonce.get(Annonce_.etatA), etat);
            } else {
                e = cb.and(e, cb.equal(annonce.get(Annonce_.etatA), etat));
            }
        }
        if (e!=null){
            cq.where(e);
        }
        cq.orderBy(cb.desc(annonce.get(Annonce_.dateValidation)));
        cq.select(annonce).distinct(true);
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        List<Annonce> lsta = q.getResultList();
        List<AnnonceDTO> lstar = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Annonce a : lsta) {
            AnnonceDTO adto = new AnnonceDTO(a.getIdAnnonce(), a.getNomA(), a.getDescriptionA(),
                    a.getDateCreation() != null ? df.format(a.getDateCreation()) : "",
                    a.getDateValidation() != null ? df.format(a.getDateValidation()) : "",
                    a.getDateMiseVente() != null ? df.format(a.getDateValidation()) : "",
                    a.getProduitCollection().size(), a.getEtatA());
            for (Produit p : a.getProduitCollection()) {
                if (adto.getLstProduit() == null) {
                    adto.setLstProduit(new ArrayList<ProduitDTO>());
                }
                adto.setPrix(adto.getPrix() + p.getPrix());
                adto.getLstProduit().add(new ProduitDTO(p.getIdProduit(), p.getIdCat() != null ? p.getIdCat().getIdCat() : 0, p.getCategorie(), p.getImage(), p.getDescriptionP(),
                        p.getDateMiseVente() != null ? df.format(p.getDateMiseVente()) : "", p.getPrix(), p.getImage()));
            }
            lstar.add(adto);
        }
        return lstar;
    }

    @Override
    public int CreateAnnonce(AnnonceDTO a) {
        Annonce ae = new Annonce();
        //  List<Produit> lstPe = new ArrayList<Produit>();
        ae.setNomA(a.getLibelle());
        ae.setDescriptionA(a.getDescription());
        ae.setDateCreation(new Date());
        User u = em.find(User.class, a.getIdUtilisateur());
        if (u != null) {
            ae.setIdUser(u);
        }
        ae.setEtatA("Draft");
//        for (ProduitDTO p : a.getLstProduit()) {
//            Produit pe = new Produit();
//            pe.setNomP(p.getNom());
//            pe.setDescriptionP(p.getDescription());
//            Categorie c = em.find(Categorie.class, p.getIdCategorie());
//            if (c != null) {
//                pe.setIdCat(c);
//            }
//            pe.setIdAnnonce(ae);
//            pe.setImage(p.getImgesUrl());
//            pe.setDateMiseVente(new Date(p.getDateMiseEnVente()));
//            lstPe.add(pe);
//        }
//        ae.setProduitCollection(lstPe);
        em.persist(ae);
        em.flush();
        a.setIdentifiant(ae.getIdAnnonce());
        return ae.getIdAnnonce();
    }

    @Override
    public AnnonceDTO UpdateAnnonce(AnnonceDTO a) {
        Annonce ae = em.find(Annonce.class, a.getIdentifiant());
        ae.setNomA(a.getLibelle());
        ae.setDescriptionA(a.getDescription());
        ae.setEtatA(a.getEtat());
        ae.setDateValidation(new Date(a.getDateValidation()));
        ae.setDateMiseVente(new Date(a.getDateMiseEnVente()));
        List<ProduitDTO> lstP = a.getLstProduit();
        List<Produit> lstPe = new ArrayList<Produit>();
        Produit pe;
        if (ae.getProduitCollection() != null || ae.getProduitCollection().size() == 0) {
            for (ProduitDTO p : a.getLstProduit()) {
                pe = new Produit();
                pe.setNomP(p.getNom());
                pe.setDescriptionP(p.getDescription());
                Categorie c = em.find(Categorie.class, p.getIdCategorie());
                if (c != null) {
                    pe.setIdCat(c);
                }
                pe.setIdAnnonce(ae);
                pe.setImage(p.getImgesUrl());
                pe.setDateMiseVente(new Date(p.getDateMiseEnVente()));
                lstPe.add(pe);
            }
            ae.setProduitCollection(lstPe);
        }
        em.merge(ae);
        return a;
    }

    @Override
    public AnnonceDTO getAnnonceById(int id) {
        Annonce a = em.find(Annonce.class, id);
        AnnonceDTO adto = null;
        if (a != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            adto = new AnnonceDTO(a.getIdAnnonce(), a.getNomA(), a.getDescriptionA(),
                    a.getDateCreation() != null ? df.format(a.getDateCreation()) : "",
                    a.getDateValidation() != null ? df.format(a.getDateValidation()) : "",
                    a.getDateMiseVente() != null ? df.format(a.getDateValidation()) : "",
                    a.getProduitCollection().size(), a.getEtatA());
            for (Produit p : a.getProduitCollection()) {
                if (adto.getLstProduit() == null) {
                    adto.setLstProduit(new ArrayList<ProduitDTO>());
                }
                adto.setPrix(adto.getPrix() + p.getPrix());
                adto.getLstProduit().add(new ProduitDTO(p.getIdProduit(), p.getIdCat() != null ? p.getIdCat().getIdCat() : 0, p.getCategorie(), p.getNomP(), p.getDescriptionP(),
                        p.getDateMiseVente() != null ? df.format(p.getDateMiseVente()) : "", p.getPrix(), p.getImage()));
            }
        }
        return adto;
    }

    @Override
    public int ValiderAnnonce(int identifiant, Date dateValidation) {
        Annonce a = em.find(Annonce.class, identifiant);
        if (a!=null){
            a.setEtatA("Disponible");
            a.setDateValidation(dateValidation);
            a.setDateMiseVente(dateValidation);
            em.merge(a);
            return a.getIdAnnonce();
        }
           return -1;
          
    }

}
