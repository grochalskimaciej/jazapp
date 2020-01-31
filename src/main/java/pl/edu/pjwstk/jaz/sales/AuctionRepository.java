package pl.edu.pjwstk.jaz.sales;

import pl.edu.pjwstk.jaz.auth.ProfileEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


@ApplicationScoped
public class AuctionRepository implements Serializable {
    @PersistenceContext
    EntityManager em;

    @Inject
    AuctionRequest auctionRequest;

    public SectionEntity findSectionByID(Long sectionID) { return em.find(SectionEntity.class, sectionID); }
    public AuctionEntity findAuctionByID(Long auctionID) { return em.find(AuctionEntity.class, auctionID); }
    public CategoryEntity findCategoryByID(Long categoryID) { return em.find(CategoryEntity.class, categoryID); }

    @Transactional
    public void deleteAuction(AuctionEntity auction){
        em.remove(em.contains(auction) ? auction : em.merge(auction));
    }

    @Transactional
    public void saveSection(SectionEntity section) {
        if (section.getId() != null) { em.merge(section); }
        else { em.persist(section); }
    }

    @Transactional
    public void saveAuction(AuctionEntity auction) {
        if (auction.getId() != null){ em.merge(auction); }
        else { em.persist(auction); }
    }

    @Transactional
    public void saveCategory(CategoryEntity category) {
        if (category.getId() != null){ em.merge(category); }
        else { em.persist(category); }
    }

    @Transactional
    public void addAuction(String ownerUsername) {
        Query query = em.createQuery("SELECT u from ProfileEntity u where u.username = :ownerUsername", ProfileEntity.class);
        ProfileEntity owner = (ProfileEntity)query.setParameter("ownerUsername", ownerUsername).getSingleResult();

        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setOwner(owner);

        CategoryEntity categoryEntity = new CategoryEntity(auctionRequest.getCategoryName());
        SectionEntity sectionEntity = new SectionEntity(auctionRequest.getSectionName());
        categoryEntity.setSection(sectionEntity);
        auctionEntity.setCategory(categoryEntity);

        auctionEntity.setTitle(auctionRequest.getTitle());
        auctionEntity.setDescription(auctionRequest.getDescription());
        auctionEntity.setPrice(auctionRequest.getPrice());

        em.persist(auctionEntity);

        if(!auctionRequest.getImage1().isEmpty()){
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setLink(auctionRequest.getImage1());
            photoEntity.setAuction(auctionEntity);
            em.persist(photoEntity);
        }

        if(!auctionRequest.getImage2().isEmpty()){
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setLink(auctionRequest.getImage2());
            photoEntity.setAuction(auctionEntity);
            em.persist(photoEntity);
        }

        if(!auctionRequest.getImage3().isEmpty()){
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setLink(auctionRequest.getImage3());
            photoEntity.setAuction(auctionEntity);
            em.persist(photoEntity);
        }

        if(!auctionRequest.getImage4().isEmpty()){
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setLink(auctionRequest.getImage4());
            photoEntity.setAuction(auctionEntity);
            em.persist(photoEntity);
        }
    }

    public List<SectionEntity> findAllSections() {
        return em.createQuery("FROM SectionEntity ", SectionEntity.class).getResultList();
    }

//    public List<AuctionEntity> findAllAuctions() {
//        return em.createQuery("FROM AuctionEntity", AuctionEntity.class).getResultList();
//    }
//
//    public List<AuctionEntity> findMyAuctions(String ownerUsername) {
//        Query query = em.createQuery("SELECT u FROM AuctionEntity u WHERE u.owner.username = :ownerUsername", AuctionEntity.class);
//        return query.setParameter("ownerUsername", ownerUsername).getResultList();
//    }

    public List<PhotoEntity> findMyPhotos(String ownerUsername){
        Query photos = em.createQuery("SELECT u FROM PhotoEntity u WHERE u.auction.owner.username = :ownerUsername ORDER BY auction.id DESC", PhotoEntity.class);
        return photos.setParameter("ownerUsername", ownerUsername).getResultList();
    }

    public List<PhotoEntity> findAllPhotos(){
        return em.createQuery("FROM PhotoEntity ORDER BY auction.id DESC", PhotoEntity.class).getResultList();
    }

    public List<PhotoEntity> moreImages(){
        return null;
    }
}

