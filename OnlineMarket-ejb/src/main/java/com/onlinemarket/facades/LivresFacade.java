/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.facades;

import com.onlinemarket.entities.Livres;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMINIBM
 */
@Stateless
public class LivresFacade extends AbstractFacade<Livres> implements LivresFacadeLocal {
    @PersistenceContext(unitName = "com.onlinemarket_OnlineMarket-ejb_ejb_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivresFacade() {
        super(Livres.class);
    }
    
}
