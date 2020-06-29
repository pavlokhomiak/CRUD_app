package view;

import controller.SkillController;
import model.Skill;

import java.util.Scanner;

public class SkillView {
    SkillController skillController = new SkillController();

    public Skill createSkill() {
        System.out.println("Input name");
        String name = new Scanner(System.in).nextLine();
        return skillController.create(new Skill(name));
    }

    public Skill readSkill() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        return skillController.read(id);
    }

    public Skill update() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        System.out.println("Input name");
        String name = new Scanner(System.in).nextLine();
        return skillController.update(id, new Skill(id, name));
    }

    public void delete() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        skillController.delete(id);
    }

    public void viewSkill() {
        System.out.println("Chose operation: " +
                "\n1. Create skill" +
                "\n2. Read skill" +
                "\n3. Update skill" +
                "\n4. Delete skill" +
                "\n5. Show all skills");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                System.out.println(createSkill());
                break;
            case 2:
                System.out.println(readSkill());
                break;
            case 3:
                System.out.println(update());
                break;
            case 4:
                delete();
                break;
            case 5:
                System.out.println(skillController.getAll());
                break;
            default:
                System.out.println("Incorrect input");
                viewSkill();
        }
    }
}
