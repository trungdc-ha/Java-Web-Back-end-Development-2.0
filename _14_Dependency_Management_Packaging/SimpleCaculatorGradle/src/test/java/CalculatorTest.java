import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    @DisplayName("Testing add 0 + 0")
    public void testAdd0And0() {
        int first = 0;
        int second = 0;
        int expected = 0;

        int result = calculator.plus(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing multiply 1 + 0")
    public void testMultiply1And0() {
        int first = 1;
        int second = 0;
        int expected = 0;

        int result = calculator.multiply(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing divide 0 / 1")
    public void testDivide0And1() {
        int first = 0;
        int second = 1;
        int expected = 0;

        double result = calculator.divide(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing add 5 + 3")
    public void testAdd5And3() {
        int first = 5;
        int second = 3;
        int expected = 8;

        int result = calculator.plus(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing sub 0 - 0")
    public void testSub0and0() {
        int first = 0;
        int second = 0;
        int expected = 0;

        int result = calculator.subtract(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing divide 11/0")
    public void testDivide11and0() {
        int first = 1;
        int second = 0;
        Exception expected = new RuntimeException("Can't divide by zero");

        int result = calculator.subtract(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing multiply 12 * 3")
    public void testMultiply0and1() {
        int first = 12;
        int second = 3;
        int expected = 36;

        int result = calculator.multiply(first, second);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing divide 6 / 3")
    public void testDivide6and3() {
        int first = 6;
        int second = 3;
        int expected = 2;

        double result = calculator.divide(first, second);
        assertEquals(expected, result);
    }
}
