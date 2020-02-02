package pl.edu.pjwstk.jaz.sales;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class AuctionController implements Serializable {
    @Inject
    private HttpServletRequest request;

    @Inject
    private AuctionRepository auctionRepository;

    @Inject
    private AuctionRequest auctionRequest;

    private SectionEntity section;
    private AuctionEntity auction;
    private CategoryEntity category;

    public List<PhotoEntity> getAllPhotos(){return auctionRepository.findAllPhotos();}
    public List<PhotoEntity> getMyPhotoEntityList() {
        var session = request.getSession(false);
        String sessionUsername = (String)session.getAttribute("username");
        return auctionRepository.findMyPhotos(sessionUsername);
    }
    public List<SectionEntity> getSectionEntityList() { return auctionRepository.findAllSections(); }
    public List<PhotoEntity> getMoreImages() {
        return auctionRepository.moreImages();
    }
//    public List<AuctionEntity> getAuctionEntityList() { return auctionRepository.findAllAuctions(); }
//    public List<AuctionEntity> getMyAuctionEntityList() {
//        var session = request.getSession(false);
//        String sessionUsername = (String)session.getAttribute("username");
//        return auctionRepository.findMyAuctions(sessionUsername);
//    }

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public SectionEntity getSection(){
        if (id == null) { section = new SectionEntity(); }
        else { section = auctionRepository.findSectionByID(id); }

        return section;
    }
    public String saveSection() {
        auctionRepository.saveSection(section);

        return "index";
    }

    public AuctionEntity getAuction(){
        if (id == null) { auction = new AuctionEntity(); }
        else { auction = auctionRepository.findAuctionByID(id); }

        return auction;
    }
    public String saveAuction() {
        auction = getAuction();
        auction.setTitle(auctionRequest.getTitle());
        auction.setDescription(auctionRequest.getDescription());
        auction.setPrice(auctionRequest.getPrice());

        auctionRepository.saveAuction(auction);

        return "index";
    }
    public String addAuction() {
        var session = request.getSession(false);
        String sessionUsername = (String)session.getAttribute("username");

        auctionRepository.addAuction(sessionUsername);
        return "index";
    }
    public String deleteAuction(){
        auction = getAuction();

        auctionRepository.deleteAuction(auction);
        return "showMyAuctions";
    }

    public CategoryEntity getCategory(){
        if (id == null) { category = new CategoryEntity(); }
        else { category = auctionRepository.findCategoryByID(id); }

        return category;
    }
    public String addCategory(){
        String username = sessionUsername();
        if(auctionRepository.isAdmin(username)){
            auctionRepository.addCategory();

            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("category", "category added succesfully");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("isNotAdmin", "You have to be an admin to add category");
        }
        return "index";
    }

    public String saveCategory() {
        String username = sessionUsername();
        if(auctionRepository.isAdmin(username)){
            auctionRepository.saveCategory(category);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("isNotAdmin", "You have to be an admin to edit category");
        }
        return "index";
    }

    public String sessionUsername(){
        var session = request.getSession(false);
        return (String)session.getAttribute("username");
    }
}
