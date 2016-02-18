/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.imp;

import com.onlinemarket.domain.dtos.CommandeDTO;
import com.onlinemarket.domain.dtos.ProduitDTO;
import com.onlinemarket.domain.interfaces.CommandeServiceInt;
import com.onlinemarket.entities.Commande;
import com.onlinemarket.entities.CommandeProduit;
import com.onlinemarket.entities.CommandeProduitPK;
import com.onlinemarket.entities.Produit;
import com.onlinemarket.entities.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMINIBM
 */
@Stateless
public class CommandeServiceImp implements CommandeServiceInt {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count(int idUtilisateur) {
        //  javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        //javax.persistence.criteria.Root<Annonce> rt = cq.from(Annonce.class);
        String sql =" SELECT COUNT(c) FROM Commande c WHERE c.idUser.idUser = :id_user  GROUP BY c.idUser.idUser";
        
         return ((Number)em.createQuery(sql).setParameter("id_user", idUtilisateur).getSingleResult()).intValue();
    
        
        
    }

    @Override
    public List<CommandeDTO> findRange(int idUtilisateur, int[] range) {
        
         String sql ="SELECT c FROM Commande c WHERE c.idUser.idUser = :id_user";
         List<Commande> lst = em.createQuery(sql).setParameter("id_user", idUtilisateur).setMaxResults(range[1] - range[0]).setFirstResult(range[0]).getResultList();
         
        List<CommandeDTO> lstc = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Commande c : lst) {
            CommandeDTO cdto = new CommandeDTO(c.getIdCmd(), df.format(c.getDateCmd()), idUtilisateur, c.getEtatCmd());
            for (CommandeProduit p : c.getCommandeProduitCollection()) {
                if (cdto.getLstProduit() == null) {
                    cdto.setLstProduit(new ArrayList<ProduitDTO>());
                }
                cdto.setPrix(cdto.getPrix() + p.getProduit().getPrix());
                cdto.getLstProduit().add(new ProduitDTO(p.getProduit().getIdProduit(), p.getProduit().getIdCat() != null ? p.getProduit().getIdCat().getIdCat() : 0, p.getProduit().getCategorie(), p.getProduit().getImage(), p.getProduit().getDescriptionP(),
                        p.getProduit().getDateMiseVente() != null ? df.format(p.getProduit().getDateMiseVente()) : "", p.getProduit().getPrix(), p.getProduit().getImage()));
            }
            lstc.add(cdto);
        }
        return lstc;
    }

    @Override
    public int CreateCommande(CommandeDTO c) {
        Commande cmd = new Commande();
        //  List<Produit> lstPe = new ArrayList<Produit>();
        cmd.setEtatCmd(c.getEtat());
        
        cmd.setDateCmd(new Date());
        User u = em.find(User.class, c.getIdUtilisateur());
        if (u != null) {
            cmd.setIdUser(u);
        } 
        em.persist(cmd);
        em.flush();
        return cmd.getIdCmd();
    }

   
    @Override
    public CommandeDTO getCommandeById(int id) {
        Commande c = em.find(Commande.class, id);
        CommandeDTO cdto = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (c != null) {
            cdto = new CommandeDTO(c.getIdCmd(), df.format(c.getDateCmd()), c.getIdUser().getIdUser(), c.getEtatCmd());
            for (CommandeProduit p : c.getCommandeProduitCollection()) {
                if (cdto.getLstProduit() == null) {
                    cdto.setLstProduit(new ArrayList<ProduitDTO>());
                }
                cdto.setPrix(cdto.getPrix() + p.getProduit().getPrix());
                cdto.getLstProduit().add(new ProduitDTO(p.getProduit().getIdProduit(), p.getProduit().getIdCat() != null ? p.getProduit().getIdCat().getIdCat() : 0, p.getProduit().getCategorie(), p.getProduit().getImage(), p.getProduit().getDescriptionP(),
                        p.getProduit().getDateMiseVente() != null ? df.format(p.getProduit().getDateMiseVente()) : "", p.getProduit().getPrix(), p.getProduit().getImage()));
            }
        }
        return cdto;
    }

    @Override
    public void CreateCommandeProduct(int id_cmd, int id_product, int nbr) {
       
       CommandeProduit cmdp = new CommandeProduit();
       Commande cmd = em.find(Commande.class, id_cmd);
       Produit p = em.find(Produit.class, id_product);
       if(cmd!=null && p!=null){
           cmdp.setCommande(cmd);
           cmdp.setProduit(p);
           cmdp.setQteCmd(nbr);
           cmdp.setCommandeProduitPK(new CommandeProduitPK(id_cmd, id_product));
           p.getCommandeProduitCollection().add(cmdp);
           cmd.getCommandeProduitCollection().add(cmdp);
           em.persist(cmdp);
           em.merge(cmd);
           em.merge(p);
           em.flush();
       }
    }


   
}
