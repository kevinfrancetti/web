package ch.supsi.web.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {

    public Role(String name){
        this.name = name;
    }

    @Id
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;

}
