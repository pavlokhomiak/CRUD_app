package model;

public enum AccountStatus {
    ACTIVE("ACTIVE"), BANNED("BANNED"), DELETED("DELETED");

    private String accountStatus;

    AccountStatus(String accountStatus){
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return accountStatus;
    }
}
