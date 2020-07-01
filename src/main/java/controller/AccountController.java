package controller;

import model.Account;
import repository.AccountRepository;
import repository.io.JavaIOAccountRepositoryImpl;

import java.util.List;

public class AccountController {
    private AccountRepository javaIOAccountRepository = new JavaIOAccountRepositoryImpl();

    public Account create(Account account) {
        return javaIOAccountRepository.create(account);
    }

    public Account read(Long aLong) {
        return javaIOAccountRepository.read(aLong);
    }

    public Account update(Long aLong, Account account) {
        return javaIOAccountRepository.update(aLong, account);
    }

    public void delete(Long aLong) {
        javaIOAccountRepository.delete(aLong);
    }

    public List<Account> getAll() {
        return javaIOAccountRepository.getAll();
    }
}
