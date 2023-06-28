package entity;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {

    private static final int MINIMUM_NUMBER_PURCHASES = 21;

    private double discountInPercent;

    public PercentDiscountPurchase() {
        super();
    }

    public PercentDiscountPurchase(String name, Euro price, int number, double discountInPercent) {
        super(name, price, number);

        this.discountInPercent = discountInPercent;
    }

    public PercentDiscountPurchase(Scanner scanner) {
        super(scanner);

        discountInPercent = scanner.nextDouble();
    }

    @Override
    public Euro getCost() {
        Euro cost = super.getCost();

        if (getNumber() > MINIMUM_NUMBER_PURCHASES) {
            cost.mul(1 - discountInPercent / 100, RoundMethod.ROUND, 0);
        }

        return cost;
    }

    @Override
    public String toString() {
        return getClass().getName() + "; " + this.discountInPercent;
    }
}
