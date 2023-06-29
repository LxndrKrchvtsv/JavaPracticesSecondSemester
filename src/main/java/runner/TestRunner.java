package runner;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestRunner {
    private final static String BEFORE_SIGN = " ";
    private final static String AFTER_SIGN = " ";
    private final static String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
    private final static String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
    private final static String DELIMITER = "; ";
    private final static String RESULT_HEAD = "result(";
    private final static String RESULT_TAIL = ") = ";

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            double numResult = 0;
            double nowResult;
            int errorLines = 0;

            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(DELIMITER);

                try {
                    int index = Integer.parseInt(words[0]);
                    nowResult = Double.parseDouble(words[index]);
                    numResult += nowResult;

                    String Sign = (nowResult >= 0) ? PLUS : MINUS;
                    strResult.append(Sign).append(Math.abs(nowResult));

                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    errorLines++;
                }
            }

            if (strResult.length() > 0) {
                final int SIGN_MINUS_LENGTH = MINUS.length();
                final int SIGN_PLUS_LENGTH = PLUS.length();
                final char CHAR_MINUS = '-';

                if (strResult.substring(0, SIGN_MINUS_LENGTH).equals(MINUS)) {
                    strResult.delete(0, SIGN_MINUS_LENGTH);
                    strResult.insert(0, CHAR_MINUS);
                }

                if (strResult.substring(0, SIGN_PLUS_LENGTH).equals(PLUS)) {
                    strResult.delete(0, SIGN_MINUS_LENGTH);
                }
            }
            strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(numResult);

            return errorLines;
        }
    }

    @Test
    public void testInput1() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in1.csv", result);
        String expectedIn1 = RESULT_HEAD + "5.2" + MINUS + "3.14" + PLUS + "0.0" + RESULT_TAIL + "2.06";

        Assert.assertEquals(3, errorLines);
        Assert.assertEquals(expectedIn1, result.toString());
    }

    @Test
    public void testInput2() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in2.csv", result);
        String expectedIn2 = RESULT_HEAD + "-3.1" + MINUS + "1.0" + RESULT_TAIL + "-4.1";

        Assert.assertEquals(0, errorLines);
        Assert.assertEquals(expectedIn2, result.toString());
    }

    @Test
    public void testInput3() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in3.csv", result);
        String expectedIn3 = RESULT_HEAD + "0.75" + RESULT_TAIL + "0.75";

        Assert.assertEquals(0, errorLines);
        Assert.assertEquals(expectedIn3, result.toString());
    }

    @Test
    public void testInput4() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in4.csv", result);
        String expectedIn4 = RESULT_HEAD + "0.0" + RESULT_TAIL + "0.0";

        Assert.assertEquals(0, errorLines);
        Assert.assertEquals(expectedIn4, result.toString());
    }

    @Test
    public void testInput5() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in5.csv", result);
        String expectedIn5 = RESULT_HEAD + "" + RESULT_TAIL + "0.0";

        Assert.assertEquals(1, errorLines);
        Assert.assertEquals(expectedIn5, result.toString());
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void testInexistentFile() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        getResult("src/nonexistent.csv", result);
    }
}