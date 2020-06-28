package repository.io;

import com.google.gson.Gson;
import model.Skill;
import repository.SkillRepository;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    private final String REPOSITORY_PATH = "src\\main\\resources\\skills.txt";
    private Skill[] skills = null;
    //private long currentMaxID = getAll().get(getAll().size() - 1).getId();

    public Skill create(Skill skill) {
        //String jsonSkill = new Gson().toJson(skill);
        List<Skill> skillsList = getAll();
        skillsList.add(skill);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(skillsList, bw);
            //bw.write(jsonSkill);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return skill;
    }

    public Skill read(Long aLong) {
        skills = getSkillArray();

        for (int i = 0; i < skills.length; i++) {
            if (skills[i].getId() == aLong) {
                return skills[i];
            }
        }
        return null;

        /*
        String jsonString = "";
        try(BufferedReader br = new BufferedReader(new FileReader(
                "src\\main\\resources\\skills.txt"))) {
            jsonString = br.readLine();
        } catch (IOException e) {
            System.out.println("IOException");
        }
        String[] objectsArray = jsonString.split("\\{");
        for (String s : objectsArray) {
            if (s.contains(aLong.toString())) {
                return new Gson().fromJson("{" + s, Skill.class);
            }
        }
        return null;
        */
    }

    public Skill update(Long aLong, Skill skill) {
        skills = getSkillArray();

        for (int i = 0; i < skills.length; i++) {
            if (skills[i].getId() == aLong) {
                skills[i] = skill;
                skills[i].setId(aLong);
                return skills[i];
            }
        }
        return null;
    }

    public void delete(Long aLong) {
        List<Skill> skillsList = getAll();
        for (int i = 0; i < skillsList.size(); i++) {
            if (skillsList.get(i).getId() == aLong)
                skillsList.remove(i);
        }
    }

    public List<Skill> getAll() {
        return Arrays.asList(getSkillArray());
    }

    private Skill[] getSkillArray() {
        try(BufferedReader br = new BufferedReader(new FileReader(
                REPOSITORY_PATH))) {
            skills = new Gson().fromJson(br, Skill[].class);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return skills;
    }
}
