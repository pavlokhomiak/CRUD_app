package model;

import java.util.Set;

public class Developer {
    private Long id;
    private String name;
    private Account account;
    private Set<Skill> skills;

    public Developer(Long id, String name, Account account){
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }
}
