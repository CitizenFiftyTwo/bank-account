package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

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

        Assertions.assertThat(expectedAmount).isEqualTo(Amount.of(instanceValue));
    }
}
