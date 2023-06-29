package runner;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {

    private static Result getResult(String fileName) throws MissingResourceException {

        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();

        final String KEY_REG_EXP = "index(.*)";
        final String NUM_REG_EXP = "[1-9]\\d*";

        Pattern indexPattern = Pattern.compile(KEY_REG_EXP);
        Pattern numPattern = Pattern.compile(NUM_REG_EXP);

        final int TAIL_INDEX = 1;
        final String VALUE = "value";

        int errors = 0;
        double sum = 0.0;

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Matcher keyMatcher = indexPattern.matcher(key);

            if (keyMatcher.matches()) {
                String keyNumber = keyMatcher.group(TAIL_INDEX);
                String indexValue = rb.getString(key).trim();

                Matcher numberMatcher = numPattern.matcher(keyNumber);
                Matcher valueMatcher = numPattern.matcher(indexValue);

                if (numberMatcher.matches() && valueMatcher.matches()) {
                    String value = VALUE + keyNumber + indexValue;

                    try {
                        sum += Double.parseDouble(rb.getString(value));
                    } catch (NumberFormatException | MissingResourceException e) {
                        errors++;
                    }
                } else {
                    errors++;
                }
            }
        }

        return new Result(errors, sum);
    }

    private static class Result {
        private static final String SUM_EQUAL = "sum: ";
        private static final String ERROR_LINES_EQUAL = "error-lines: ";
        private final int errorLines;
        private final double sum;

        public Result() {
            this(0,0.0);
        }

        public Result(int errorLines, double sum) {
            this.errorLines = errorLines;
            this.sum = sum;
        }

        public int getErrorLines() {
            return errorLines;
        }

        public double getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return SUM_EQUAL + sum + "\n" + ERROR_LINES_EQUAL + errorLines;
        }
    }

    @Test
    public void verifyMainScenarioMultiTest() throws FileNotFoundException {
        class TestCase {
            private static final String SEPARATOR = "; ";
            private final Result result;
            private final String filename;


            public TestCase(int errorLines, double sum, String filename) {
                this.result = new Result(errorLines, sum);
                this.filename = filename;
            }

            public int getErrorLines() {
                return result.getErrorLines();
            }

            public double getSum() {
                return result.getSum();
            }

            @Override
            public String toString() {
                return result + SEPARATOR + filename;
            }
        }

        final double DELTA = 0.000001;
        final String FILE_SOURCE = "in";

        TestCase[] testCases = {
                new TestCase(3,8.24, "src/in1.properties"),
                new TestCase(9, 30.242, "src/in2.properties"),
                new TestCase(0, 1.9, "src/in3.properties")
        };

        int index = 1;

        for (TestCase testCase: testCases){
            Assert.assertEquals(testCase.result.getErrorLines(), getResult(FILE_SOURCE + index).getErrorLines());
            Assert.assertEquals(testCase.getSum(), getResult(FILE_SOURCE + index).getSum(), DELTA);
            index++;

        }
    }

    @Test(expectedExceptions = MissingResourceException.class)
    public void testWrongFileName() throws MissingResourceException {
        Result result = getResult("src/in4");
    }
}