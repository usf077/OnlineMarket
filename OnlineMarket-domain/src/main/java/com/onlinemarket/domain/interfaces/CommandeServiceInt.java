/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.domain.interfaces;

import com.onlinemarket.domain.dtos.AnnonceDTO;
import com.onlinemarket.domain.dtos.CategorieDTO;
import com.onlinemarket.domain.dtos.CommandeDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ADMINIBM
 */
@Remote
public interface CommandeServiceInt {
  public int count(int idUtilisateur);
  public List<CommandeDTO>  findRange(int idUtilisateur ,int[] range);
  public int CreateCommande(CommandeDTO c);
  public CommandeDTO getCommandeById (int id);
  public void CreateCommandeProduct(int id_cmd,int id_product,int nbt);
}
