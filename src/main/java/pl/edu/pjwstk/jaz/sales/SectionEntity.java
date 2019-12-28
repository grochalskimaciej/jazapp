package pl.edu.pjwstk.jaz.sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="section")
public class SectionEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    public SectionEntity(){ }
    public SectionEntity(String name){
        this.name = name;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}


}
