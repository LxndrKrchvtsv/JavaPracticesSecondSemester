package runner;

import entity.Purchase;
import entity.WeekDays;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class Runner {
    static private double getTotalCost(Purchase[] purchases) {
        return Arrays.stream(purchases).mapToDouble(purchase -> purchase.PRICE).sum();
    };

    static private double getTotalCost(Stream<Purchase> purchases) {
        return purchases.mapToDouble(purchase -> purchase.PRICE).sum();
    };

    static double getAverageCost(Purchase[] purchases, int PURCHASES_NUMBER) {
        return getTotalCost(purchases) / PURCHASES_NUMBER;
    };

    static private WeekDays getDayWithMaxCostPurchase(Purchase[] purchases) {
        return Arrays.stream(purchases).max(Purchase::compareTo).get().getWeekDay();
    }

    static private void printArrayWithFormat(Purchase[] purchases) {
        System.out.println("class constants");
        Arrays.stream(purchases).forEach(purchase -> System.out.println(purchase.NAME + ": " + purchase.PRICE + ";"));
    }

    static Purchase[] initPurchasesArray(int PURCHASES_NUMBER, Scanner purchasesReader) {
        Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

        for (int currentLine = -1; currentLine < purchases.length; currentLine++) {
            String data = purchasesReader.nextLine();
            if (data.length() > 0) {
                String[] separatedLine = data.split(" ");

                Purchase purchase = new Purchase(
                        Integer.parseInt(separatedLine[2]),
                        Integer.parseInt(separatedLine[3]),
                        WeekDays.values()[Integer.parseInt(separatedLine[4])]
                );

                purchases[currentLine] = purchase;
            }
        }

        return purchases;
    }

    public static void main(String[] args) {
        try {
            File purchasesFile = new File("src/in.txt");

            Scanner purchasesReader = new Scanner(purchasesFile);

            final int PURCHASES_NUMBER = purchasesReader.nextInt();

            Purchase[] purchases = initPurchasesArray(PURCHASES_NUMBER, purchasesReader);

            purchasesReader.close();

            printArrayWithFormat(purchases);

            String averageCost = String.format(Locale.US, "%.3f", getAverageCost(purchases, PURCHASES_NUMBER));

            System.out.println("averageCost:" + averageCost);

            Stream<Purchase> mondaysPurchases = Arrays.stream(purchases).filter(purchase -> purchase.getWeekDay() == WeekDays.MONDAY);
            double mondayTotalCost = getTotalCost(mondaysPurchases);

            System.out.println("mondayTotalCost: " + mondayTotalCost);

            WeekDays dayWithMaxCostPurchase = getDayWithMaxCostPurchase(purchases);

            System.out.println("dayWithMaxCostPurchase: " + dayWithMaxCostPurchase);

            Arrays.sort(purchases);

            printArrayWithFormat(purchases);

            Purchase purchaseWith5Units = new Purchase( 5, 0, WeekDays.SUNDAY);

            int purchaseEqual5 = Arrays.binarySearch(purchases, purchaseWith5Units);
            System.out.println(purchaseEqual5);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
