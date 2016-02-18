/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.beans;

import com.onlinemarket.domain.dtos.AnnonceDTO;
import com.onlinemarket.domain.dtos.CommandeDTO;
import com.onlinemarket.domain.dtos.userDTO;
import com.onlinemarket.domain.interfaces.AnnonceServiceInt;
import com.onlinemarket.domain.interfaces.CommandeServiceInt;
import com.onlinemarket.util.PaginationHelper;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author ADMINIBM
 */
@ManagedBean(name = "CommandeBean")
@SessionScoped
public class CommandeBean {

    @EJB
    private CommandeServiceInt commandeService;

    private PaginationHelper pagination;
    //   private int selectedItemIndex;
    private DataModel dtmdl = null;
    
    private CommandeDTO commande;


    /**
     * Creates a new instance of AdministrationBean
     */
    public CommandeBean() {

    }
//pagaination

    public PaginationHelper getPagination() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String referrer = externalContext.getRequestHeaderMap().get("referer");

        if (pagination == null) {
            final userDTO user = (userDTO) facesContext.getExternalContext().getSessionMap().get(UsersBean.USER_SESSION_KEY);
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return commandeService.count(user.getIdentifiant());
                }
                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(commandeService.findRange(user.getIdentifiant(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    private void recreateModel() {
        dtmdl = null;
    }

    public DataModel getdtmdl() {
        if (dtmdl == null) {
            dtmdl = getPagination().createPageDataModel();
        }
        return dtmdl;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "home";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "home";
    }

    public String goToPage(int page) {
        getPagination().setPage(page - 1);
        //  getPagination().nextPage();        
        recreateModel();
        return "home";
    }
    public String Detail(int identifiant) {
        commande = commandeService.getCommandeById(identifiant);
        return "detail3";
    }

    public CommandeDTO getCommande() {
        return commande;
    }

    public void setCommande(CommandeDTO commande) {
        this.commande = commande;
    }
   
}
