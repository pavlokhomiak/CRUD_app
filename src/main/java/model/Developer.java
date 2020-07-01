package model;

import lombok.Data;

import java.util.Set;

@Data
public class Developer {
    private Long id;
    private String name;
    private Account account;
    private Set<Skill> skills;

    public Developer(Long id, String name, Account account, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.skills = skills;
    }

    public Developer(String name, Account account, Set<Skill> skills){
        this.name = name;
        this.account = account;
        this.skills = skills;
    }
}
