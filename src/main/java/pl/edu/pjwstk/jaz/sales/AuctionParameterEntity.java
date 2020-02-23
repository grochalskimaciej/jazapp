package pl.edu.pjwstk.jaz.sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="auction_parameter")
public class AuctionParameterEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter;

    @NotNull
    @Column(name = "value")
    private String value;

    public AuctionParameterEntity(){}

    public void setParameter(ParameterEntity parameter) {
        this.parameter = parameter;
    }
    public ParameterEntity getParameter() {
        return parameter;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setAuction(AuctionEntity auction){this.auction = auction;}
    public AuctionEntity getAuction(){return auction;}

}

