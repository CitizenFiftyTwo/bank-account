package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Operation {

    private static final String COLUMN_SEPARATOR = " | ";

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

    public String print() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return type.name() + COLUMN_SEPARATOR +
                date.format(formatters) + COLUMN_SEPARATOR +
                amount.getValue() + COLUMN_SEPARATOR +
                balance.getValue();
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

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", date=" + date +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
