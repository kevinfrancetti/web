package ch.supsi.web.model;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Item {
    private int id;
    private String name;
    private String description;
    private String author;
}
