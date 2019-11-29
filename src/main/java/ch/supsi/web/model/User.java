package ch.supsi.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;


import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String name, Role role){
        this.name = name;
        this.role = role;
    }

    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "fk_role")
    private Role role;

}
