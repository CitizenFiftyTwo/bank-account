package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

    public static final String OPERATION_SEPARATOR = "\n";

    private Amount balance = Amount.of(0);
    private final List<Operation> operations = new ArrayList<>();

    public static Account withInitialAmount(Amount amount) {
        Account account = new Account();
        account.balance = amount;
        return account;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
        registerDepositOperation(amount);
    }

    public void retrieve(Amount amount) {
        balance = balance.substract(amount);
        registerWithdrawOperation(amount);

    }

    private void registerWithdrawOperation(Amount amount) {
        operations.add(new Operation(OperationType.WITHDRAW, LocalDate.now(), amount, balance));
    }

    private void registerDepositOperation(Amount amount) {
        operations.add(new Operation(OperationType.DEPOSIT, LocalDate.now(), amount, balance));
    }

    public String printHistory() {
        return operations.stream()
                .map(Operation::print)
                .map(Object::toString)
                .collect(Collectors.joining(OPERATION_SEPARATOR));
    }

    public Amount getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
