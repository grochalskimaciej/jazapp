package pl.edu.pjwstk.jaz.sales;
import pl.edu.pjwstk.jaz.auth.ProfileEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "auction")
public class AuctionEntity implements Serializable {

        @NotNull
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotNull
        @Column(name="title")
        private String title;

        @Column(name="description")
        private String description;

        @NotNull
        @Column(name="price")
        private BigDecimal price;

        @OneToOne(optional = false)
        @JoinColumn(name = "owner_id")
        private ProfileEntity owner;

        @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
        @JoinColumn(name = "category_id")
        private CategoryEntity category;

        @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "auction")
        @OrderColumn(name = "order")
        private List<PhotoEntity> photos;

        public AuctionEntity(){}

        public Long getId() {return id;}
        public void setId(Long id){this.id = id;}
        public String getTitle(){return title;}
        public void setTitle(String title) { this.title = title; }
        public String getDescription(){return description;}
        public void setDescription(String description) { this.description = description; }
        public BigDecimal getPrice(){return price;}
        public void setPrice(BigDecimal price) { this.price = price; }
        public ProfileEntity getOwner(){return owner;}
        public void setOwner(ProfileEntity owner) { this.owner = owner; }
        public CategoryEntity getCategory(){return category;}
        public void setCategory(CategoryEntity category) { this.category = category; }
        public List<PhotoEntity> getPhotos(){return photos;}
        public void setPhotos(List<PhotoEntity> photos) { this.photos = photos; }

}
