package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static domain.OperationType.*;

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
        if (balance.isGreaterThanOrEquals(amount)) {
            balance = balance.substract(amount);
            registerWithdrawOperation(amount);
        } else {
            registerRefusedOperation(amount);
        }
    }

    private void registerRefusedOperation(Amount amount) {
        operations.add(new Operation(REFUSED, LocalDate.now(), amount, balance));
    }

    private void registerWithdrawOperation(Amount amount) {
        operations.add(new Operation(WITHDRAW, LocalDate.now(), amount, balance));
    }

    private void registerDepositOperation(Amount amount) {
        operations.add(new Operation(DEPOSIT, LocalDate.now(), amount, balance));
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
