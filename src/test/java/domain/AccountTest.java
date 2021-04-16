package domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    
    Account account = new Account();
    
    @Test
    public void depositMoney_should_add_money_to_balance() {
        BigDecimal depositAmount = BigDecimal.valueOf(42);

        account.deposit(depositAmount);

        assertThat(account.getBalance()).isEqualTo(depositAmount);
    }
}
