/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.domain.interfaces;

import com.onlinemarket.domain.dtos.ProduitDTO;
import com.onlinemarket.domain.dtos.CategorieDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ADMINIBM
 */
@Remote
public interface ProductServiceInt {

  public int CreateProduct(ProduitDTO p);

}
