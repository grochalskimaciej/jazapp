package pl.edu.pjwstk.jaz.sales;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="photo")
public class PhotoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="link")
    private String link;

    @ManyToOne
    @JoinColumn(name="auction_id")
    private AuctionEntity auction;

    public PhotoEntity(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public AuctionEntity getAuction() {
        return auction;
    }
    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }
}
