package controller;

import model.Developer;
import repository.io.JavaIODeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private JavaIODeveloperRepositoryImpl javaIODeveloperRepository =
            new JavaIODeveloperRepositoryImpl();

    public Developer create(Developer developer) {
        return javaIODeveloperRepository.create(developer);
    }

    public Developer read(Long id) {
        return javaIODeveloperRepository.read(id);
    }

    public Developer update(Long id, Developer developer) {
        return javaIODeveloperRepository.update(id, developer);
    }

    public void delete(Long id) {
        javaIODeveloperRepository.delete(id);
    }

    public List<Developer> getAll() {
        return javaIODeveloperRepository.getAll();
    }
}
