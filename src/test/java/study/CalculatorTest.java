package study;

import org.assertj.core.internal.bytebuddy.implementation.bytecode.Multiplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @ParameterizedTest
    @CsvSource(value = {"2 + 3 * 4 / 2:10", "2 + 3 * 4 / 2 - 5:5"}, delimiter = ':')
    void 문자열_계산기_테스트(String input, int expected) {
        String[] values = input.split(" ");
        int sum = Integer.parseInt(values[0]);

        for (int i = 0; i < values.length; i++) {
            if (i % 2 != 0 ) {
                Expression expression = Expression.of(values[i]);
                int value = Integer.parseInt(values[i+1]);
                sum = expression.calculation(sum, value);
            }
        }

        assertEquals(sum, expected);
    }

}

enum Expression {
    ADD("+", (a,b) -> a + b),
    MINUS("-", (a,b) -> a - b),
    MULTIPLICATION("*", (a,b) -> a * b),
    DIVISION("/", (a,b) -> a / b);

    private String expression;
    private IntBinaryOperator operator;

    Expression(String expression, IntBinaryOperator operator) {
        this.expression = expression;
        this.operator = operator;
    }

    static Expression of(String expression) {
        return Stream.of(values())
                    .filter(v -> v.expression.equals(expression))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 사칙연산에 해당하지 않는 표현식입니다.", expression)));
    }

    public int calculation(int left, int right) {
        return this.operator.applyAsInt(left, right);
    }
}