package runner;

import entity.Euro;
import entity.Purchase;
import entity.PurchaseFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        try  {
            File purchasesFile = new File("src/in.txt");

            Scanner scanner = new Scanner(purchasesFile);

            scanner.useLocale(Locale.ENGLISH);

            final int PURCHASES_NUMBER = 6;
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

            Euro maxCost = new Euro();

            Purchase maxCostPurchase = null;

            boolean isPurchasesEqual = true;

            Purchase firstPurchase = purchases[0];

            for (int i = 0; i < purchases.length; i++) {
                PurchaseFactory PurchaseFactory = new PurchaseFactory();
                purchases[i] = PurchaseFactory.getPurchaseFromFactory(scanner);
                Purchase currentPurchase = purchases[i];

                System.out.println(currentPurchase);

                Euro cost = currentPurchase.getCost();

                if (cost.compareTo(maxCost) > 0) {
                    maxCost = cost;
                    maxCostPurchase = currentPurchase;
                }

                if (isPurchasesEqual) {
                    isPurchasesEqual = currentPurchase.equals(firstPurchase);
                }
            }

            System.out.println("Purchase with maximum cost: ");
            System.out.println(maxCostPurchase);
            System.out.println("All purchases are equal: " + isPurchasesEqual);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}
