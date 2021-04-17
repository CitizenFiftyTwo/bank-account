package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Amount {

    private final BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public static Amount of(double value) {
        return new Amount(BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP));
    }

    public Amount add(Amount amount) {
        return new Amount(value.add(amount.value));
    }

    public Amount substract(Amount amount) {
        return new Amount(value.subtract(amount.value));
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }
}
