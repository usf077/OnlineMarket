/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.beans;

import com.onlinemarket.domain.dtos.AnnonceDTO;
import com.onlinemarket.domain.dtos.CategorieDTO;
import com.onlinemarket.domain.dtos.CommandeDTO;
import com.onlinemarket.domain.dtos.ProduitDTO;
import com.onlinemarket.domain.dtos.userDTO;
import com.onlinemarket.domain.interfaces.AnnonceServiceInt;
import com.onlinemarket.domain.interfaces.CommandeServiceInt;
import com.onlinemarket.domain.interfaces.ProductServiceInt;
import com.onlinemarket.util.PaginationHelper;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 *
 * @author ADMINIBM
 */
@ManagedBean(name = "annonceBean")
@SessionScoped
public class AnnoanceBean {

    @EJB
    private AnnonceServiceInt annonceService;
    @EJB
    private ProductServiceInt productService;

    @EJB
    private CommandeServiceInt commandeService;
    
    private int searchSelectectedCategorie =0;
    private String searchTxt;
    public List<CategorieDTO> categories;

    private PaginationHelper pagination;
    //   private int selectedItemIndex;
    private DataModel dtmdl = null;

    private AnnonceDTO annonce;

    private Part file = null;
    private String fileName;

    //DataModel for created products
    private DataModel dtmdlp = null;

    //produits achetés
    private Map<Integer,ProduitDTO> products;
    private double montantAchete;

     public static final String ANNONCE_DISPONIBE_ETAT = "Disponible";
     public static final String ANNONCE_DRAFT_ETAT = "Draft";
     
    public AnnoanceBean() {
        annonce = new AnnonceDTO();
    }

    public int getSearchSelectectedCategorie() {
        return searchSelectectedCategorie;
    }

    public void setSearchSelectectedCategorie(int searchSelectectedCategorie) {
        this.searchSelectectedCategorie = searchSelectectedCategorie;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

  

    public List<CategorieDTO> getCategories() {
        return annonceService.getCategories();
    }

    public List<AnnonceDTO> getAnnonces() {
        return annonceService.getAnnonces();
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
                    return annonceService.count(searchTxt, searchSelectectedCategorie, ANNONCE_DISPONIBE_ETAT);
                }

                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(annonceService.findRange( searchTxt, searchSelectectedCategorie ,ANNONCE_DISPONIBE_ETAT,new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public DataModel getdtmdl() {
        if (dtmdl == null) {
            dtmdl = getPagination().createPageDataModel();
        }
        return dtmdl;
    }

    public DataModel getdtmdlp() {
        if (dtmdlp == null) {
            dtmdlp = new ListDataModel(new ArrayList<ProduitDTO>());
        }
        return dtmdlp;
    }

    private void recreateModel() {
        dtmdl = null;
    }

    private void recreateModelp() {
        dtmdlp = null;
    }

    private void recreatePagination() {
        pagination = null;
    }
    
    public String Search()
    {
        dtmdl = getPagination().createPageDataModel();
        return "app-main";
    }

    /* private void updateCurrentItem() {
     int count = annonceService.count();
     if (selectedItemIndex >= count) {

     // selected index cannot be bigger than number of items:
     selectedItemIndex = count - 1;

     // go to previous page if last page disappeared:
     if (pagination.getPageFirstItem() >= count) {

     pagination.previousPage();
     }
     }
     if (selectedItemIndex >= 0) {
     item = annonceService.findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
     }
     }
     */
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

    public String CreateAnnonce() {
        return "createStep2";

    }

    public String AddProduct() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (file != null) {

            try {
                saveImage();

            } catch (IOException ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error uploqding i;qge!",
                        "Unexpected error when uploqding the image.  Please contact the system Administrator");
                context.addMessage(null, message);
                Logger.getAnonymousLogger().log(Level.SEVERE,
                        "Unable to upload the image",
                        ex);
            }
        }

        List<ProduitDTO> lstp = (List<ProduitDTO>) dtmdlp.getWrappedData();

        for (ProduitDTO pp : lstp) {
            pp.setIsEditable(false);
            annonce.setPrix(annonce.getPrix() + pp.getPrix());
        }
        if (fileName != null && lstp.size() > 0) {
            lstp.get(lstp.size() - 1).setImgesUrl(fileName);
        }
        ProduitDTO p = new ProduitDTO();
        p.setIsEditable(true);
        lstp.add(p);
        dtmdlp = new ListDataModel(lstp);
        file = null;
        fileName = null;
        return "home";
    }

    public String RemoveProduct(int id) {
        List<ProduitDTO> lstp = (List<ProduitDTO>) dtmdlp.getWrappedData();
        lstp.remove(id);
        dtmdlp = new ListDataModel(lstp);
        return "home";
    }

    public String Detail(int identifiant) {
        annonce = annonceService.getAnnonceById(identifiant);
        return "detail";
    }

    public void validateFile(FacesContext ctx,
            UIComponent comp,
            Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file.getSize() > (long) 1048576) {
            msgs.add(new FacesMessage("file too big"));
        }
        if (!"image/jpeg".equals(file.getContentType())) {
            msgs.add(new FacesMessage("not a  jpeg image file"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public void onCreateLoad() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            annonce = new AnnonceDTO();
        }
    }

    public String FinalizeAnnonce() {
        FacesContext context = FacesContext.getCurrentInstance();
        List<ProduitDTO> lstp = (List<ProduitDTO>) dtmdlp.getWrappedData();
        if (file != null) {
            try {
                saveImage();
                lstp.get(lstp.size() - 1).setImgesUrl(fileName);
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error uploqding i;qge!",
                        "Unexpected error when uploqding the image.  Please contact the system Administrator");
                context.addMessage(null, message);
                Logger.getAnonymousLogger().log(Level.SEVERE,
                        "Unable to upload the image",
                        ex);
            }
        }
        for (ProduitDTO pp : lstp) {
            annonce.setPrix(annonce.getPrix() + pp.getPrix());
        }
        annonce.setLstProduit(lstp);
        return "createStep3";
    }

    public String PublishAnnonce() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {

            userDTO user = (userDTO) context.getExternalContext().getSessionMap().get(UsersBean.USER_SESSION_KEY);
            annonce.setIdUtilisateur(user.getIdentifiant());
            annonce.setLstProduit(null);

            int idAnnonce = annonceService.CreateAnnonce(annonce);
            List<ProduitDTO> lstp = (List<ProduitDTO>) dtmdlp.getWrappedData();
            for (ProduitDTO p : lstp) {
                p.setDateMiseEnVente(DateFormat.getInstance().format(new Date()));
                p.setIdAnnonce(idAnnonce);
                productService.CreateProduct(p);
            }
            dtmdlp = null;
           // dtmdl=null;
            annonce = new AnnonceDTO();

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error creating annonce!",
                    "Unexpected error when creating the annonce.  Please contact the system Administrator");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "Unable to create the new annonce",
                    e);

        }
        return "annonce_creee";
    }

    public String addProductToCart(int idProduct) {
        if (products == null) {
                    products = new LinkedHashMap<Integer,ProduitDTO>();
                }
        for (ProduitDTO p : annonce.getLstProduit()) {
            if (p.getIdentifiant() == idProduct) {
                products.put(p.getIdentifiant(), p);
                montantAchete +=p.getPrix(); 
                break;
            }
        }
        return "home";
    }
    public String RemoveProductFromBasket( int identifiant ){
        montantAchete-=products.get(identifiant).getPrix();
        products.remove(identifiant);
    return "home";
}
    
    public AnnonceDTO getAnnonce() {
        return annonce;
    }

    public void setAnnonce(AnnonceDTO annonce) {
        this.annonce = annonce;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Map<Integer, ProduitDTO> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, ProduitDTO> products) {
        this.products = products;
    }

    public double getMontantAchete() {
        return montantAchete;
    }

    public void setMontantAchete(double montantAchete) {
        this.montantAchete = montantAchete;
    }
    
    public String init(){
        
        searchTxt=null;
        searchSelectectedCategorie=0;
        dtmdl=null;
        return "app-main";
    }
    
    public String createCommande() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {

            userDTO user = (userDTO) context.getExternalContext().getSessionMap().get(UsersBean.USER_SESSION_KEY);
            CommandeDTO commande = new CommandeDTO();
            commande.setIdUtilisateur(user.getIdentifiant());
            commande.setLstProduit(null);

            commande.setEtat("EnCours");
            commande.setDateCommande(DateFormat.getInstance().format(new Date()));
            commande.setPrix(montantAchete);
            int idCommande = commandeService.CreateCommande(commande);
            for (ProduitDTO p : products.values()) {
                commandeService.CreateCommandeProduct(idCommande, p.getIdentifiant(), 1);
            }
            products =null;
            montantAchete=0;
            
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error creating annonce!",
                    "Unexpected error when creating the annonce.  Please contact the system Administrator");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "Unable to create the new annonce",
                    e);

        }
        return "commande_succes";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }

    private void saveImage() throws IOException {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        UUID id = UUID.randomUUID();
        fileName = "XX" + id + "XX" + getFilename(file);
        String filePath = path.concat("resources\\products\\");
        Image image = ImageIO.read(file.getInputStream());
        BufferedImage imgBuffredThumb = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
        BufferedImage imgBuffredReal = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        imgBuffredReal.createGraphics().drawImage(image, 0, 0, null);
        imgBuffredThumb.createGraphics().drawImage(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(imgBuffredThumb, "jpg", new File(filePath + "\\thumbnail\\" + fileName));
        ImageIO.write(imgBuffredReal, "jpg", new File(filePath + fileName));
    }

}
