/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.facades;

import com.onlinemarket.entities.Livres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ADMINIBM
 */
@Local
public interface LivresFacadeLocal {

    void create(Livres livres);

    void edit(Livres livres);

    void remove(Livres livres);

    Livres find(Object id);

    List<Livres> findAll();

    List<Livres> findRange(int[] range);

    int count();
    
}
