package model;

import lombok.Data;

@Data
public class Account {
    private AccountStatus accountStatus;

    public Account(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
