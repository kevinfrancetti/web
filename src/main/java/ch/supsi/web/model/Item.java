package ch.supsi.web.model;

import lombok.*;
import javax.persistence.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    private String author;

    @Override
    public String toString(){
        return "id: " + id + " name: " + name + " description: " + description + " author: " + author;
    }
}
