package entity;

public class Product {
    private final String name;
    private final Euro price;

    public Product() {
        this("", new Euro());
    }

    public Product(String name, Euro price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Euro getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "; " + price;
    }
}
