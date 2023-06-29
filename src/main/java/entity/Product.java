package entity;

public class Product extends AbstractPriceable {
    private String name;
    private Euro price;

    public Product() {
        super();
    }

    public Product(String name, Euro price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Euro getPrice() {
        return price;
    }

    @Override
    public String fieldsToString() {
        return name + "; " + price + "; " + getPrice();
    }
}
