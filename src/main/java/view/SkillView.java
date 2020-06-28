package view;

import controller.SkillController;
import model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkillView {
    SkillController skillController = new SkillController();
    private String name = "";
    private String input = "";
    private Long id = 0L;
    private Long currentMaxID = 0L;

    private String input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return input;
    }

    private void inputSkill() {
        while (true) {
            System.out.println("Input skill name");
            name = input();
            if (name.length() != 0) {
                break;
            }
        }

        /*
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Input skill name");
                name = br.readLine();
                if (name.length() != 0) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        */
    }

    private void inputId() {
        while (true) {
            System.out.println("Input Id");
            if (isNumeric(input())) {
                id = Long.parseLong(input());
                break;
            } else {
                System.out.println("Incorrect input");
            }
        }
        /*
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Input Id");
                String s = br.readLine();
                if (isNumeric(s)) {
                    id = Long.parseLong(s);
                    break;
                } else {
                    System.out.println("Incorrect input");
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        */
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void generateCurrentMaxID() {
        if (skillController.getAll().size() != 0) {
            currentMaxID = skillController.getAll().get(skillController.getAll().size()).getId();
        }
    }

    public Skill createSkill() {
        inputSkill();
        generateCurrentMaxID();
        return skillController.create(new Skill(currentMaxID, name));
    }

    public Skill readSkill() {
        inputId();
        return skillController.read(id);
    }

    public Skill update() {
        inputId();
        inputSkill();
        return skillController.update(id, new Skill(id, name));
    }

    public void delete() {
        inputId();
        skillController.delete(id);
    }

    public void viewSkill() {
        System.out.println("Chose operation: " +
                "\n1. Create skill" +
                "\n2. Read skill" +
                "\n3. Update skill" +
                "\n4. Delete skill" +
                "\n5. Show all skills");
        switch (input()) {
            case ("1"):
                System.out.println(createSkill());
            case ("2"):
                System.out.println(readSkill());
            case ("3"):
                System.out.println(update());
            case ("4"):
                delete();
            case ("5"):
                System.out.println(skillController.getAll());
            default:
                System.out.println("Incorrect input");
                viewSkill();
        }
    }
}
