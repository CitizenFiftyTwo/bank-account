package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AmountTest {

    @Test
    public void amountOf_expectedValue_should_be_equal_to_instanciated_amount_with_same_value() {
        double value = 52;

        Amount expectedAmount = new Amount(BigDecimal.valueOf(value));

        Assertions.assertThat(expectedAmount).isEqualTo(Amount.of(value));
    }
}
