package runner;

import entity.*;

import java.util.Arrays;

public class Runner {
    public static int search(AbstractPurchase[] purchases, Euro cost) {
        Product keyProduct = new Product("Key", cost);
        PriceDiscountPurchase keyPurchase = new PriceDiscountPurchase(keyProduct, 1, new Euro(0));

        return Arrays.binarySearch(purchases, keyPurchase);
    }

    private static void printPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    public static void main(String[] args) {
        final Product product = new Product("product", new Euro(5433));

        AbstractPurchase[] purchases = new AbstractPurchase[]{
                new PriceDiscountPurchase(product, 10, new Euro(17)),
                new PriceDiscountPurchase(product, 100, new Euro(10)),

                new PercentDiscountPurchase(product, 100, 17.25),
                new PercentDiscountPurchase(product, 130, 0.64),
        };

        System.out.println("Purchases: ");
        printPurchases(purchases);

        Arrays.sort(purchases);

        System.out.println("Purchases sorted by descending cost: ");
        printPurchases(purchases);

        AbstractPurchase purchaseMinCost = purchases[purchases.length - 1];
        System.out.println("Min cost: " + purchaseMinCost.getCost());

        Euro searchCost = new Euro(350);
        int searchIndex = search(purchases, searchCost);

        System.out.println("Purchase with cost: " + searchCost);

        if (searchIndex >= 0) {
            System.out.println(purchases[searchIndex]);
        } else {
            System.err.println("Purchase with cost " + searchCost + " not found");
        }
    }
}