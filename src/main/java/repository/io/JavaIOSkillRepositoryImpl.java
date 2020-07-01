package repository.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import model.Skill;
import repository.SkillRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class JavaIOSkillRepositoryImpl implements SkillRepository {
    private final String REPOSITORY_PATH = "src\\main\\resources\\skills.txt";
    private Long currentMaxID = 0L;

    private void generateCurrentMaxID() {
        if (!getAll().isEmpty()) {
            currentMaxID = getAll().get(getAll().size() - 1).getId() + 1;
        }
    }

    public Skill create(Skill skill) {
        List<Skill> skillsList = new ArrayList<>(getAll());
        generateCurrentMaxID();
        skill.setId(currentMaxID);
        skillsList.add(skill);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(skillsList, bw);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return skill;
    }

    public Skill read(Long aLong) {
        return getAll().stream()
                .filter(n -> n.getId().equals(aLong))
                .findFirst()
                .orElse(null);
    }

    public Skill update(Long aLong, Skill skill) {
        List<Skill> skillsList = getAll();

        if(skillsList.stream().anyMatch(n -> n.getId().equals(aLong))) {
            skillsList.stream()
                    .filter(n -> n.getId().equals(aLong))
                    .forEach(n -> n.setName(skill.getName()));

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                    REPOSITORY_PATH))) {
                new Gson().toJson(skillsList, bw);
            } catch (IOException e) {
                System.out.println("Exception");
            }
            return skill;
        } else {
            return null;
        }
    }

    public void delete(Long aLong) {
        List<Skill> skillsList = new ArrayList<>(getAll());

        int index = IntStream.range(0, skillsList.size())
                .filter(i -> aLong.intValue() == skillsList.get(i).getId())
                .findFirst()
                .orElse(-1);
        skillsList.remove(skillsList.get(index));

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(skillsList, bw);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public List<Skill> getAll() {
        List<Skill> skills = null;

        try(BufferedReader br = new BufferedReader(new FileReader(
                REPOSITORY_PATH))) {
            skills = new Gson().fromJson(
                    br, new TypeToken<List<Skill>>(){}.getType());
            if (skills== null) {
                skills = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return skills;
    }
}
