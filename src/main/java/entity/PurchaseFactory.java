package entity;

import java.util.Scanner;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new Purchase(scanner);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            PriceDiscountPurchase getPurchase(Scanner scanner) {
                return new PriceDiscountPurchase(scanner);
            }
        },
        PERCENT_DISCOUNT_PURCHASE {
            PercentDiscountPurchase getPurchase(Scanner scanner) {
                return new PercentDiscountPurchase(scanner);
            }
        };

        abstract Purchase getPurchase(Scanner scanner);
    }


    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        return PurchaseKind.valueOf(scanner.next()).getPurchase(scanner);
    }
}
