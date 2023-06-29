package entity;

import java.util.Scanner;

public class PriceDiscountPurchase extends AbstractPurchase {
    private Euro discountInEuro;

    public PriceDiscountPurchase() {
        super();

        this.discountInEuro = new Euro();
    }

    public PriceDiscountPurchase(Product product, int price, Euro discountInEuro) {
        super(product, price);

        this.discountInEuro = discountInEuro;
    }

    @Override
    protected Euro getFinalCost(Euro baseCost) {
        return baseCost.sub(discountInEuro.mul(getUnitsNumber()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "; " + this.discountInEuro;
    }
}
