package model;

import lombok.Data;

@Data
public class Skill {
    private Long id;
    private String skill;

    public Skill(Long id, String skill) {
        this.id = id;
        this.skill = skill;
    }
}
