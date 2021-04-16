package domain;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance = BigDecimal.valueOf(0);

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
