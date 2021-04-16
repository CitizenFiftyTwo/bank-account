package domain;

import enums.OperationType;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {

    private final OperationType type;
    private final LocalDate date;
    private final Amount amount;
    private final Amount balance;

    public Operation(OperationType type, LocalDate date, Amount amount, Amount balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return type == operation.type &&
                Objects.equals(date, operation.date) &&
                Objects.equals(amount, operation.amount) &&
                Objects.equals(balance, operation.balance);
    }
}
