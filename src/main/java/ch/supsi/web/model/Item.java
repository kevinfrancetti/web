package ch.supsi.web.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="FK_CATEGORY_ID")
    //@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "FK_AUTHOR")
    //@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private User author;

    @Override
    public String toString() {
        return "id: " + id + " title: " + title + " description: " + description + " author: " + author;
    }
}
