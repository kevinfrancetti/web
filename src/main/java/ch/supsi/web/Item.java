package ch.supsi.web;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Item {
    private String name;
    private String description;
    private String author;
    private int id;
}
