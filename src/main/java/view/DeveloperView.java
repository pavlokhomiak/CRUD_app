package view;

import controller.DeveloperController;
import model.Account;
import model.Developer;
import model.Skill;

import java.util.*;

public class DeveloperView {
    private DeveloperController developerController = new DeveloperController();
    private AccountView accountView = new AccountView();
    private SkillView skillView = new SkillView();

    public void viewDeveloper() {
        System.out.println("Chose operation: " +
                "\n1. Create developer" +
                "\n2. Read developer" +
                "\n3. Update developer" +
                "\n4. Delete developer" +
                "\n5. Show all developer" +
                "\n6. Open account menu" +
                "\n7. Open skill menu" +
                "\n8. Close");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                System.out.println(createDeveloper());
                viewDeveloper();
                break;
            case 2:
                System.out.println(readDeveloper());
                viewDeveloper();
                break;
            case 3:
                System.out.println(update());
                viewDeveloper();
                break;
            case 4:
                delete();
                viewDeveloper();
                break;
            case 5:
                System.out.println(developerController.getAll());
                viewDeveloper();
                break;
            case 6:
                accountView.viewAccount();
                viewDeveloper();
                break;
            case 7:
                skillView.viewSkill();
                viewDeveloper();
                break;
            case 8:
                System.exit(0);
            default:
                System.out.println("Incorrect input");
                viewDeveloper();
        }
    }

    public Developer createDeveloper() {
        System.out.println("Input developer name");
        String developerName = new Scanner(System.in).nextLine();

        System.out.println("Chose account");
        System.out.println(accountView.getAll());
        Account account = accountView.readAccount();

        System.out.println("Chose skill");
        System.out.println(skillView.getAll());
        Set<Skill> skills = new HashSet<>();
        while (true) {
            System.out.println("Chose skill");
            Skill skill = skillView.readSkill();
            if (skill != null) {
                skills.add(skill);
            } else {
                break;
            }
        }

        return developerController.create(new Developer(
                developerName,
                account,
                skills));
    }

    public Developer readDeveloper() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        return developerController.read(id);
    }

    public Developer update() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        Developer developer = createDeveloper();
        developer.setId(id);
        return developerController.update(id, developer);
    }

    public void delete() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        developerController.delete(id);
    }

    public List<Developer> getAll() {
        return developerController.getAll();
    }
}
