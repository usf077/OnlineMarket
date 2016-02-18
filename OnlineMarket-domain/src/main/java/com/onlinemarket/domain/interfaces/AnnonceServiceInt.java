/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.domain.interfaces;

import com.onlinemarket.domain.dtos.AnnonceDTO;
import com.onlinemarket.domain.dtos.CategorieDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ADMINIBM
 */
@Remote
public interface AnnonceServiceInt {
  public List<CategorieDTO> getCategories();
  public List<AnnonceDTO> getAnnonces();
  public int count(String txt, int idCategorie,String etat);
  public List<AnnonceDTO>  findRange(String txt, int idCategorie,String etat,int[] range);
  public int CreateAnnonce(AnnonceDTO a);
  public AnnonceDTO UpdateAnnonce (AnnonceDTO a);
  public AnnonceDTO getAnnonceById(int id);
  public int ValiderAnnonce(int identifiant , Date dateValidation);
 
}
