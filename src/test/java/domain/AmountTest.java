package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    @DisplayName("Amount instanciation is OK")
    @ParameterizedTest(name = "Amount instanciation with value {0} is instanciated with value {1}")
    @CsvSource({
            "0, 0.00",
            "42, 42.00",
            "42.5, 42.50",
            "42.52, 42.52",
            "42.524, 42.52",
            "42.525, 42.53",
    })
    public void amountOf_expectedValue_should_be_equal_to_instanciated_amount_with_same_value(double instanceValue, BigDecimal expectedValue) {
        Amount expectedAmount = new Amount(expectedValue);

        assertThat(expectedAmount).isEqualTo(Amount.of(instanceValue));
    }

    @DisplayName("isGreaterThanOrEquals is OK")
    @ParameterizedTest(name = "Amount with value {0} isGreaterThanOrEquals to amount with value {1} should return {2}")
    @CsvSource({
            "50, 49, true",
            "99, 100, false",
            "100, 100, true",
    })
    public void isGreaterThanOrEquals_should_return_true_when_value_is_greater_than_parameter(double valueOfCurrentAmount,
                                                                                              double valueToCompare,
                                                                                              boolean expectedResult) {
        Amount amount = Amount.of(valueOfCurrentAmount);

        assertThat(amount.isGreaterThanOrEquals(Amount.of(valueToCompare))).isEqualTo(expectedResult);
    }
}
