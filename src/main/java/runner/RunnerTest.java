package runner;

import entity.Purchase;
import entity.WeekDays;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.testng.Assert.*;

public class RunnerTest {
    @Test
    public void testAverageCost() {
        try {
            File purchasesFile = new File("src/in.txt");

            Scanner purchasesReader = new Scanner(purchasesFile);

            final int PURCHASES_NUMBER = purchasesReader.nextInt();

            Purchase[] purchases = Runner.initPurchasesArray(PURCHASES_NUMBER, purchasesReader);

            String actualAverageCost = String.format(Locale.US, "%.3f", Runner.getAverageCost(purchases, PURCHASES_NUMBER));

            String expectlAverageCost = "6005.400";

            Assert.assertEquals(actualAverageCost, expectlAverageCost, "Average Cost does not match.");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}