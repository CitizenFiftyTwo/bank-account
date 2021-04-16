package domain;

import enums.OperationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private Amount balance = Amount.of(0);
    private final List<Operation> operations = new ArrayList<>();

    public static Account withBalance(Amount amount) {
        Account account = new Account();
        account.balance = amount;
        return account;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
        registerDepositOperation(amount);
    }

    private void registerDepositOperation(Amount amount) {
        operations.add(new Operation(OperationType.DEPOSIT, LocalDate.now(), amount, balance));
    }

    public void retrieveMoney(Amount amount) {
        balance = balance.substract(amount);
    }

    public Amount getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
