package domain;

public class Account {

    private Amount balance = Amount.of(0);

    public void deposit(Amount amount) {
        balance = balance.add(amount);
    }

    public Amount getBalance() {
        return balance;
    }
}
