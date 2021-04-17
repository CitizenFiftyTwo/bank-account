package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static enums.OperationType.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {

    @DisplayName("Print an operation should correctly print informations")
    @Test
    public void printOperation_should_correctly_print_operation_informations() {
        String expectedResult = "DEPOSIT | 15/03/2020 | 42.00 | 50.00";

        Operation operation = new Operation(DEPOSIT, LocalDate.of(2020, 3, 15),
                Amount.of(42), Amount.of(50));

        String printResult = operation.print();

        printTestResult(expectedResult, printResult);
        assertThat(printResult).isEqualTo(expectedResult);
    }

    private void printTestResult(String expected, String actual) {
        System.out.println("Expected:");
        System.out.println(expected);
        System.out.println();
        System.out.println("Actual:");
        System.out.println(actual);
        System.out.println();
    }

}
