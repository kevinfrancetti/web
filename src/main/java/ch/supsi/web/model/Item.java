package ch.supsi.web.model;

import lombok.*;
import javax.persistence.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @Column(columnDefinition = "TEXT")
    private String name;

    private String description;

    private String author;

    @ManyToOne
    @JoinColumn(name="FK_USER_ID")
    private User user;


    @Override
    public String toString(){
        return "id: " + id + " name: " + name + " description: " + description + " author: " + author;
    }
}
