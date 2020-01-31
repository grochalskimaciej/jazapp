package pl.edu.pjwstk.jaz.sales;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

//@ManagedBean
//@SessionScoped
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

    public CategoryEntity getCategory(){
        if (id == null) { category = new CategoryEntity(); }
        else { category = auctionRepository.findCategoryByID(id); }

        return category;
    }
    public String saveCategory() {
        auctionRepository.saveCategory(category);

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
    public String addCategory(){
        auctionRepository.addCategory();

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .put("category", "category added succesfully");
        return "index";
    }
    ////////////////////////////////////////////////////
//    private List<AuctionRequest> uploadFiles = null;
//
//    @PostConstruct
//    public void init(){
//        if(uploadFiles==null){
//            uploadFiles = new ArrayList<AuctionRequest>();
//        }
//    }
//
//    public void uploadFiles() throws IOException, ServletException {
//        Collection<Part> parts = getParts();
//        for(Part part: parts){
//            if(part.getSubmittedFileName()!=null){
//                AuctionRequest fileBean = new AuctionRequest();
//                fileBean.setFileName((part.getSubmittedFileName()));
//                uploadFiles.add(fileBean);
//            }
//        }
//    }
//
//    public Collection<Part> getParts() throws IOException, ServletException {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
//        return request.getParts();
//    }

//    @Transactional
//    public List<AuctionEntity> getMyAuctionList() {
//        var session = request.getSession(false);
//        String sessionUsername = (String)session.getAttribute("username");
//
//        Query getAuctions = em.createQuery("SELECT u FROM AuctionEntity u WHERE u.owner.username = :sessionUsername", AuctionEntity.class);
//        List<AuctionEntity> auctions = getAuctions.setParameter("sessionUsername", sessionUsername).getResultList();
//
//        return auctions;
//    }
//
//    @Transactional
//    public String editAuction(){
//        var session = request.getSession(false);
//        String sessionUsername = (String)session.getAttribute("username");
//
//
//        Query query = em.createQuery("SELECT u FROM ProfileEntity u WHERE u.username = :sessionUsername", ProfileEntity.class);
//        ProfileEntity owner = (ProfileEntity)query.setParameter("sessionUsername", sessionUsername).getSingleResult();
//
//
//        Query findID = em.createQuery("SELECT AuctionEntity FROM AuctionEntity a WHERE a.id = :auctionId", ProfileEntity.class);
//        AuctionEntity auctionEntity = (AuctionEntity)findID.getSingleResult();
//
//        auctionEntity.setOwner(owner);
//
//        CategoryEntity categoryEntity = new CategoryEntity(auctionRequest.getCategoryName());
//        SectionEntity sectionEntity = new SectionEntity(auctionRequest.getSectionName());
//        categoryEntity.setSection(sectionEntity);
//        auctionEntity.setCategory(categoryEntity);
//
//        auctionEntity.setTitle(auctionRequest.getTitle());
//        auctionEntity.setDescription(auctionRequest.getDescription());
//        auctionEntity.setPrice(auctionRequest.getPrice());
//
//        em.merge(auctionEntity);
//
//
//        List<byte[]> photoList = auctionRequest.getImages();
//        for(byte[] photoFile: photoList)
//        {
//            PhotoEntity photoEntity = new PhotoEntity(photoFile);
//            photoEntity.setAuction(auctionEntity);
//            em.merge(photoEntity);
//        }
//
//        return "index";
//    }


//TODO zoptymalizowac

//    @Transactional
//    public String addSection(){
//        String sName = auctionRequest.getSectionName();
//        Query findSection = em.createQuery("SELECT s FROM SectionEntity s WHERE s.name = :sName", SectionEntity.class);
//        List<SectionEntity> section = findSection.setParameter("sName", sName).getResultList();
//
//        if(section.isEmpty()){
//            SectionEntity sectionEntity = new SectionEntity(auctionRequest.getSectionName());
//            em.persist(sectionEntity);
//        }
//
//        return "index";
//    }

//TODO zoptymalizowac

//    @Transactional
//    public String editSection() {
//
//        Locale sectionID = new Locale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sectionID"));
//        Long id = 1L;
//        SectionEntity sectionEntity = em.find(SectionEntity.class, id);
//        em.merge(sectionEntity);
//
//        String sectionName = auctionRequest.getSectionName();
//
//        Query findSection = em.createQuery("SELECT s FROM SectionEntity s WHERE s.id = :sectionID", SectionEntity.class);
//        List<SectionEntity> section = findSection.setParameter("sectionID", sectionID).getResultList();
//
//        for(SectionEntity s : section){
//            s.setName(sectionName);
//            em.merge(s);
//        }
//
//        return "index";
//    }
}
