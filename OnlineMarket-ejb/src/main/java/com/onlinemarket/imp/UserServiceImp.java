/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.imp;

import com.onlinemarket.domain.dtos.userDTO;
import com.onlinemarket.domain.interfaces.UserServiceInt;
import com.onlinemarket.entities.Compte;
import com.onlinemarket.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMINIBM
 */
@Stateless
public class UserServiceImp implements UserServiceInt {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public userDTO getUserByUserName(String email) {
       List<Compte> c = em.createNamedQuery("Compte.findByMail").setParameter("mail", email).getResultList();
        User u =  c.isEmpty() ? null : c.get(0).getIdUser();
        return u!=null ? new userDTO(u.getNom(), u.getPrenom(), c.get(0).getMail(), c.get(0).getPwd(), u.getTelephone(), u.getAdresse()) : null;
    
    }

    @Override
    public userDTO createUser(userDTO user) {
    User u = new User();
    Compte c = new Compte();
    u.setAdresse(user.getAdresse());
    u.setNom(user.getNom());
    u.setPrenom(user.getPrenom());
    u.setTelephone(user.getTelephone());
    c.setMail(user.getEmail());
    c.setPwd(user.getPasssword());
    c.setIdUser(u);
    ArrayList<Compte> lst =new ArrayList<>();
    lst.add(c);
    u.setCompteCollection(lst);
    em.persist(u);
    return user;
    }
    
}
