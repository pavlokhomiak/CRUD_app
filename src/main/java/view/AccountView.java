package view;

import controller.AccountController;
import model.Account;
import model.AccountStatus;

import java.util.List;
import java.util.Scanner;

public class AccountView {
    AccountController accountController = new AccountController();

    public void viewAccount() {
        System.out.println("Chose operation: " +
                "\n1. Create account" +
                "\n2. Read account" +
                "\n3. Update account" +
                "\n4. Delete account" +
                "\n5. Show all account" +
                "\n6. Back");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                System.out.println(createAccount());
                viewAccount();
                break;
            case 2:
                System.out.println(readAccount());
                viewAccount();
                break;
            case 3:
                System.out.println(update());
                viewAccount();
                break;
            case 4:
                delete();
                viewAccount();
                break;
            case 5:
                System.out.println(getAll());
                viewAccount();
                break;
            case 6:
                break;
            default:
                System.out.println("Incorrect input");
                viewAccount();
                break;
        }
    }

    public Account createAccount() {
        AccountStatus accountStatus = null;
        System.out.println("Chose account status: " +
                "\n1. ACTIVE" +
                "\n2. BANNED" +
                "\n3. DELETED");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                accountStatus = AccountStatus.ACTIVE;
                break;
            case 2:
                accountStatus = AccountStatus.BANNED;
                break;
            case 3:
                accountStatus = AccountStatus.DELETED;
                break;
            default:
                System.out.println("Incorrect input");
                createAccount();
        }
        return accountController.create(new Account(accountStatus));
    }

    public Account readAccount() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        return accountController.read(id);
    }

    public Account update() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        /*
        Account account = createAccount();
        account.setId(id);
        return accountController.update(id, account);
        */
        AccountStatus accountStatus = null;
        System.out.println("Chose account status: " +
                "\n1. ACTIVE" +
                "\n2. BANNED" +
                "\n3. DELETED");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                accountStatus = AccountStatus.ACTIVE;
                break;
            case 2:
                accountStatus = AccountStatus.BANNED;
                break;
            case 3:
                accountStatus = AccountStatus.DELETED;
                break;
            default:
                System.out.println("Incorrect input");
                update();
        }
        return accountController.update(id, new Account(id, accountStatus));
    }

    public void delete() {
        System.out.println("Input id");
        Long id = new Scanner(System.in).nextLong();
        accountController.delete(id);
    }

    public List<Account> getAll() {
        return accountController.getAll();
    }
}