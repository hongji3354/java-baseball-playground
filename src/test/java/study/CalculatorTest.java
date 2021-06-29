package study;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @ParameterizedTest
    @CsvSource(value = {"2 + 3 * 4 / 2:10", "2 + 3 * 4 / 2 - 5:5"}, delimiter = ':')
    void 문자열_계산기_테스트(String input, int expected) {
        String[] values = input.split(" ");
        int sum = Integer.parseInt(values[0]);

        for (int i = 0; i < values.length; i++) {
            if (i % 2 != 0 ) {
                int value = Integer.parseInt(values[i+1]);
                if (values[i].equals("+")) {
                    sum += value;
                    continue;
                }

                if (values[i].equals("*")) {
                    sum *= value;
                    continue;
                }

                if (values[i].equals("/")) {
                    sum /= value;
                    continue;
                }

                if (values[i].equals("-")) {
                    sum -= value;
                }
            }
        }

        assertEquals(sum, expected);
    }

}