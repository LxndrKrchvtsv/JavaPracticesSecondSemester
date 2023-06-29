package entity;

public class DiscountProduct extends Product {

    private Euro discount;

    public DiscountProduct() {
        super();
    }

    public DiscountProduct(String name, Euro price, Euro discount) {
        super(name, price);
        this.discount = discount;
    }

    public Euro getDiscount() {
        return discount;
    }

    public Euro getPrice() {
        return super.getPrice().sub(this.discount);
    }


    public String toString() {
        return getName() + "; " + getPrice() + "; " + discount + "; " + getPrice();
    }

}
