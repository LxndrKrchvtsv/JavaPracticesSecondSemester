package entity;

import java.util.Scanner;

public class PercentDiscountPurchase extends AbstractPurchase {

    private static final int MINIMUM_NUMBER_PURCHASES = 21;

    private double discountInPercent;

    public PercentDiscountPurchase() {
        super();

        this.discountInPercent = 0;
    }

    public PercentDiscountPurchase(Product product, int price, double discountInPercent) {
        super(product, price);

        this.discountInPercent = discountInPercent;
    }

    @Override
    public Euro getFinalCost(Euro baseCost) {
        if (getUnitsNumber() > MINIMUM_NUMBER_PURCHASES) {
            return baseCost.mul(1 - discountInPercent / 100, RoundMethod.ROUND, 0);
        }

        return baseCost;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountInPercent;
    }
}
