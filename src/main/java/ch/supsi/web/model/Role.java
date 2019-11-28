package ch.supsi.web.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    @Id
    private String name;
}
