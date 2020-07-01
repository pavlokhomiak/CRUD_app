package repository.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Account;
import model.Skill;
import repository.AccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    private final String REPOSITORY_PATH = "src\\main\\resources\\accounts.txt";
    private Long currentMaxID = 0L;

    private void generateCurrentMaxID() {
        if (!getAll().isEmpty()) {
            currentMaxID = getAll().get(getAll().size() - 1).getId() + 1;
        }
    }

    @Override
    public Account create(Account account) {
        List<Account> accountsList = new ArrayList<>(getAll());
        generateCurrentMaxID();
        account.setId(currentMaxID);
        accountsList.add(account);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(accountsList, bw);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return account;
    }

    @Override
    public Account read(Long aLong) {
        return getAll().stream()
                .filter(n -> n.getId() == aLong)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Account update(Long aLong, Account account) {
        List<Account> accountsList = new ArrayList<>(getAll());

        if (accountsList.stream().anyMatch(n -> n.getId() == aLong)) {
            accountsList.stream()
                    .filter(n -> n.getId() == aLong)
                    .forEach(n -> n.setAccountStatus(account.getAccountStatus()));
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                    REPOSITORY_PATH))) {
                new Gson().toJson(accountsList, bw);
            } catch (IOException e) {
                System.out.println("IOException");
            }
            return account;
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long aLong) {
        List<Account> accountList = new ArrayList<>(getAll());

        int index = IntStream.range(0, accountList.size())
                .filter(i -> aLong.intValue() == accountList.get(i).getId())
                .findFirst()
                .orElse(-1);
        accountList.remove(accountList.get(index));

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(accountList, bw);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = null;

        try(BufferedReader br = new BufferedReader(new FileReader(
                REPOSITORY_PATH))) {
            accounts = new Gson().fromJson(
                    br, new TypeToken<List<Account>>(){}.getType());
            if (accounts == null) {
                accounts = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return accounts;
    }
}
