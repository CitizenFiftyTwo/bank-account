package domain;

public class Account {

    private Amount balance = Amount.of(0);

    public static Account withBalance(Amount amount) {
        Account account = new Account();
        account.balance = amount;
        return account;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
    }

    public Amount getBalance() {
        return balance;
    }
}
