package controller;

import model.Skill;
import repository.SkillRepository;
import repository.io.JavaIOSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public Skill create(Skill skill) {
        return skillRepository.create(skill);
    }

    public Skill read(Long id) {
        return skillRepository.read(id);
    }

    public Skill update(Long id, Skill skill) {
        return skillRepository.update(id, skill);
    }

    public void delete(Long id) {
        skillRepository.delete(id);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }
}
