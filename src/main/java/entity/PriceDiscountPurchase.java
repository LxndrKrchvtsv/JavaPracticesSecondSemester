package entity;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {

    private Euro discountInEuro;

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(String name, Euro price, int number, Euro discountEuro) {
        super(name, price, number);

        this.discountInEuro = discountEuro;
    }

    public PriceDiscountPurchase(Scanner scanner) {
        super(scanner);

        this.discountInEuro = new Euro(scanner);
    }

    @Override
    public Euro getCost() {
        return new Euro(getPrice()).sub(this.discountInEuro).mul(getNumber());
    }

    @Override
    public String toString() {
        return getClass().getName() + "; " + this.discountInEuro;
    }
}
