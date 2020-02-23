package pl.edu.pjwstk.jaz.sales;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class AuctionRequest {

    private String title;
    private String description;
    private BigDecimal price;
    private String sectionName;
    private String categoryName;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    public AuctionRequest(){}

    public String getTitle() { return title; }
    public void setTitle(String title){
        this.title=title;
    }
    public String getDescription() { return description; }
    public void setDescription(String description){
        this.description=description;
    }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public String getSectionName() { return sectionName; }
    public void setSectionName(String  sectionName){
        this.sectionName=sectionName;
    }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }

    public String getImage1() { return image1; }
    public void setImage1(String image1){
        this.image1 = image1;
    }

    public String getImage2() { return image2; }
    public void setImage2(String image2){
        this.image2 = image2;
    }

    public String getImage3() { return image3; }
    public void setImage3(String image3){
        this.image3 = image3;
    }

    public String getImage4() { return image4; }
    public void setImage4(String image4){
        this.image4 = image4;
    }
}
