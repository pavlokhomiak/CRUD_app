package model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private AccountStatus accountStatus;

    public Account(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Account(Long id, AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
        this.id = id;
    }
}
