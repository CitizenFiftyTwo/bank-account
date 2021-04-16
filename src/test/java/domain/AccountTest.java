package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static enums.OperationType.DEPOSIT;
import static enums.OperationType.WITHDRAW;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

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
    @ParameterizedTest(name = "Deposit {1} in account with {0} should result with {2} in balance")
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
    public void depositMoney_should_add_money_to_balance(double initialBalanceValue,
                                                         double depositAmountValue,
                                                         double expectedBalanceValue) {
        Account account = Account.withBalance(Amount.of(initialBalanceValue));

        account.deposit(Amount.of(depositAmountValue));

        assertThat(account.getBalance()).isEqualTo(Amount.of(expectedBalanceValue));
    }

    @DisplayName("Retrieve money is OK")
    @ParameterizedTest(name = "Retrieve {1} in account with {0} should result with {2} in balance")
    @CsvSource({
            "0, 1, -1.00",
            "42, 41, 1.00",
            "42.5, 41, 1.50",
            "42.5, 41.5, 1.00",
            "42.55, 41.55, 1.00",
            "42.55, 41.554, 1.00",
            "42.55, 41.555, 0.99",
    })
    public void retrieveMoney_should_substract_money_from_balance(double initialBalanceValue,
                                                                  double retrievedAmountValue,
                                                                  double expectedBalanceValue) {
        Account account = Account.withBalance(Amount.of(initialBalanceValue));

        account.retrieveMoney(Amount.of(retrievedAmountValue));

        assertThat(account.getBalance()).isEqualTo(Amount.of(expectedBalanceValue));
    }

    @DisplayName("Deposit money should register a deposit operation in account")
    @Test
    public void depositMoney_should_register_a_deposit_operation_in_account() {
        Account account = new Account();

        Amount depositAmount = Amount.of(42);
        Amount expectedBalanceValue = Amount.of(42);
        Operation expectedOperation = new Operation(DEPOSIT, now(), depositAmount, expectedBalanceValue);

        account.deposit(depositAmount);

        assertThat(account.getOperations()).hasSize(1);
        assertThat(account.getOperations().get(0)).isEqualTo(expectedOperation);
    }

    @DisplayName("Retrieve money should register a withdraw operation in account")
    @Test
    public void retrieveMoney_should_register_a_withdraw_operation_in_account() {
        Account account = Account.withBalance(Amount.of(42));

        Amount retrievedAmount = Amount.of(41);
        Amount expectedBalanceValue = Amount.of(1);
        Operation expectedOperation = new Operation(WITHDRAW, now(), retrievedAmount, expectedBalanceValue);

        account.retrieveMoney(retrievedAmount);

        assertThat(account.getOperations()).hasSize(1);
        assertThat(account.getOperations().get(0)).isEqualTo(expectedOperation);
    }
}
