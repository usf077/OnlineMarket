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

public class userDTO implements Serializable{
    private String nom;
    private String prenom;
    private String email;
    private String passsword;
    private String telephone;
    private String adresse;

    public userDTO(String nom, String prenom, String email, String passsword, String telephone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passsword = passsword;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    public userDTO(){
    }

    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the passsword
     */
    public String getPasssword() {
        return passsword;
    }

    /**
     * @param passsword the passsword to set
     */
    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
}
