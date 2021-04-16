package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    
    Account account = new Account();

    @DisplayName("Deposit money is OK")
    @ParameterizedTest(name = "Deposit {0} money in account should result with {1} in balance")
    @CsvSource({
            "0, 0.00",
            "42, 42.00",
            "42.5, 42.50",
    })
    public void depositMoney_should_add_money_to_balance(double depositAmount, double balance) {
        account.deposit(Amount.of(depositAmount));

        assertThat(account.getBalance()).isEqualTo(Amount.of(balance));
    }
}
