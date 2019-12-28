package pl.edu.pjwstk.jaz.sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="photo")
public class PhotoEntity implements Serializable {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "uploadedPhoto")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    @OrderColumn(name = "order")
    private AuctionEntity auction;

    public PhotoEntity(){ }
    public PhotoEntity(byte[] photo){
        this.photo = photo;
    }

    public void setPhoto(byte[] photo){this.photo = photo;}
    public byte[] getPhoto(){return photo;}

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}

    public void setAuction(AuctionEntity auction) { this.auction = auction; }
    public AuctionEntity getAuction() { return auction; }
}
