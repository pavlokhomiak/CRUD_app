package view;

import controller.SkillController;
import model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    SkillController skillController = new SkillController();

    public void viewSkill() {
        System.out.println("Chose operation: " +
                "\n1. Create skill" +
                "\n2. Read skill" +
                "\n3. Update skill" +
                "\n4. Delete skill" +
                "\n5. Show all skills" +
                "\n6. Back");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                System.out.println(createSkill());
                viewSkill();
                break;
            case 2:
                System.out.println(readSkill());
                viewSkill();
                break;
            case 3:
                System.out.println(update());
                viewSkill();
                break;
            case 4:
                delete();
                viewSkill();
                break;
            case 5:
                System.out.println(getAll());
                viewSkill();
                break;
            case 6:
                break;
            default:
                System.out.println("Incorrect input");
                viewSkill();
        }
    }

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

        //Skill skill = createSkill();
        //skill.setId(id);
        //return skillController.update(id, skill);

        System.out.println("Input name");
        String name = new Scanner(System.in).nextLine();
        return skillController.update(id, new Skill(id, name));

    }

    public void delete() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        skillController.delete(id);
    }

    public List<Skill> getAll() {
        return skillController.getAll();
    }
}
