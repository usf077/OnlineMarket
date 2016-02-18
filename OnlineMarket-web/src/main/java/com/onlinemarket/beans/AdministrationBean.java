/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.beans;

import com.onlinemarket.domain.dtos.AnnonceDTO;
import com.onlinemarket.domain.interfaces.AnnonceServiceInt;
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
@ManagedBean(name = "AdministrationBean")
@SessionScoped
public class AdministrationBean {

    @EJB
    private AnnonceServiceInt annonceService;

    private PaginationHelper pagination;
    //   private int selectedItemIndex;
    private DataModel dtmdl = null;
    
        private AnnonceDTO annonce;


    /**
     * Creates a new instance of AdministrationBean
     */
    public AdministrationBean() {

    }
//pagaination

    public PaginationHelper getPagination() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String referrer = externalContext.getRequestHeaderMap().get("referer");

        if (pagination == null) {

            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return annonceService.count(null, 0, AnnoanceBean.ANNONCE_DRAFT_ETAT);
                }

                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(annonceService.findRange(null, 0, AnnoanceBean.ANNONCE_DRAFT_ETAT, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        annonce = annonceService.getAnnonceById(identifiant);
        return "detail2";
    }
    
    public String Valider (int identifiant){
        
        annonceService.ValiderAnnonce(identifiant, new Date());
        recreateModel();
        return "validee";
    }

    public AnnonceDTO getAnnonce() {
        return annonce;
    }

    public void setAnnonce(AnnonceDTO annonce) {
        this.annonce = annonce;
    }
    
}
