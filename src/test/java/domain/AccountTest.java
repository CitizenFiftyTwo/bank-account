package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    Account account = new Account();

    @DisplayName("Instanciate account with a specified amount is OK")
    @ParameterizedTest(name = "Instanciate an account with {0} in balance should result with {1} in balance")
    @CsvSource({
            "0, 0.00",
            "42, 42.00",
            "42.5, 42.50",
            "42.55, 42.55",
            "49.994, 49.99",
            "49.995, 50.00",
    })
    public void instanciate_account_with_specified_amount_should_be_ok(double initialBalanceValue,
                                                                       double expectedBalanceValue) {
        Account account = Account.withBalance(Amount.of(initialBalanceValue));

        assertThat(account.getBalance()).isEqualTo(Amount.of(expectedBalanceValue));
    }

    @DisplayName("Deposit money is OK")
    @ParameterizedTest(name = "Deposit {0} and {1} in account should result with {2} in balance")
    @CsvSource({
            "0, 0, 0.00",
            "0, 42, 42.00",
            "0, 42.5, 42.50",
            "42, 8, 50.00",
            "42.5, 7.5, 50.00",
            "42.55, 7.45, 50.00",
            "42.554, 7.454, 50.00",
            "42.555, 7.455, 50.02",
    })
    public void depositMoney_should_add_money_to_balance(double firstDepositAmount,
                                                         double secondDepositAmount,
                                                         double balance) {
        account.deposit(Amount.of(firstDepositAmount));
        account.deposit(Amount.of(secondDepositAmount));

        assertThat(account.getBalance()).isEqualTo(Amount.of(balance));
    }
}
