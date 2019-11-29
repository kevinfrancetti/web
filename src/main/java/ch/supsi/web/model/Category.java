package ch.supsi.web.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="categories")
//@JsonIgnoreProperties(value = "items")
public class Category {

    public Category(String name){
        this.name = name;
    }

    @Id
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Item> items;



}
