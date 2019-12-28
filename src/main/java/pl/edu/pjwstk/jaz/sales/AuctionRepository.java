package pl.edu.pjwstk.jaz.sales;

import pl.edu.pjwstk.jaz.auth.ProfileEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;


@ApplicationScoped
public class AuctionRepository {
    @PersistenceContext
    EntityManager em;

    @Inject
    AuctionRequest auctionRequest;

    public SectionEntity findSectionByID(Long sectionID) { return em.find(SectionEntity.class, sectionID); }
    public AuctionEntity findAuctionByID(Long auctionID) { return em.find(AuctionEntity.class, auctionID); }

    @Transactional
    public void saveSection(@NotNull SectionEntity section) {
        if (section.getId()!=null) { em.merge(section); }
        else { em.persist(section); }
    }

    @Transactional
    public void saveAuction(@NotNull AuctionEntity auction) {
        if (auction.getId()!=null){ em.merge(auction); }
        else { em.persist(auction); }
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


//        List<byte[]> photoList = auctionRequest.getImages();
//        for(byte[] photoFile: photoList)
//        {
//            PhotoEntity photoEntity = new PhotoEntity(photoFile);
//            photoEntity.setAuction(auctionEntity);
//            em.persist(photoEntity);
//        }
    }

    public List<SectionEntity> findAllSections() {
        return em.createQuery("FROM SectionEntity ", SectionEntity.class).getResultList();
    }

    public List<AuctionEntity> findAllAuctions() {
        return em.createQuery("FROM AuctionEntity", AuctionEntity.class).getResultList();
    }

    public List<AuctionEntity> findMyAuctions(String ownerUsername) {
        Query query = em.createQuery("SELECT u FROM AuctionEntity u WHERE u.owner.username = :ownerUsername", AuctionEntity.class);
        return query.setParameter("ownerUsername", ownerUsername).getResultList();
    }
}

