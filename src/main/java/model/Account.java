package model;

public class Account {
    private AccountStatus accountStatus;

    public Account(){
        accountStatus = AccountStatus.ACTIVE;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
