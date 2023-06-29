package Entity;

import static constants.Constants.*;

public class PricePurchase extends Purchase{
    private final Euro discount;

    public PricePurchase(String name, Euro price, int number, Euro discount) {
        super(name, price, number);
        this.discount = discount;
    }

    public PricePurchase(Euro discount) {
        this.discount = discount;
    }

    public PricePurchase(String[] args) {
        super(args[NAME_FIELD], new Euro(args[PRICE_FIELD]), Integer.parseInt(args[NUMBER_FIELD]));
        this.discount = new Euro(args[DISCOUNT_FIELD]);
    }

    public PricePurchase(){
        this.discount = new Euro();
    }

    public Euro getDiscount() {
        return discount;
    }

    public Euro getCost() {
        return super.getCost().sub(discount.mul(getNumber()));
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + SEPARATOR + discount;
    }
}
