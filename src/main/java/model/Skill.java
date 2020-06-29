package model;

import lombok.Data;

@Data
public class Skill {
    private Long id;
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
