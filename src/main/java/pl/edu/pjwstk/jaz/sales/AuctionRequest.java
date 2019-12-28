package pl.edu.pjwstk.jaz.sales;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AuctionRequest {

    private String title;
    private String description;
    private BigDecimal price;
    private String sectionName;
    private String categoryName;
    private List<byte[]> images;


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

//    public List<byte[]> getImages() { return images; }
//    public void setImages(List<byte[]> images){
//        this.images=images;
//    }
//
//    @PostConstruct
//    public void init() {
//        images = new ArrayList<>();
//    }
//
//    public void handleUpload(FileUploadEvent event) {
//        UploadedFile uploadedFile = event.getFile();
//        images.add(uploadedFile.getContents());
//    }
}
