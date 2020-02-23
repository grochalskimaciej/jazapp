package pl.edu.pjwstk.jaz.sales;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="category")
public class CategoryEntity implements Serializable {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private SectionEntity section;

    public CategoryEntity(){}
    public CategoryEntity(String name){ this.name = name;}

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setId(Long id){this.id = id;}
    public Long getId() {return id;}

    public void setSection(SectionEntity section){this.section = section;}
    public SectionEntity getSection(){return section;}
}
