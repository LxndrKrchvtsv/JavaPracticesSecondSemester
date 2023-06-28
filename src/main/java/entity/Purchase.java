package entity;

import java.util.Scanner;

public class Purchase {
    private String name;
    private Euro price;
    private int number;

    public Purchase() {};

    public Purchase(String name, Euro price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    };

    public Purchase(Scanner scanner) {
        this.name = scanner.next();
        this.price = new Euro(scanner);
        this.number = scanner.nextInt();
    };

    public void setName(String name) {
        this.name = name;
    };

    public void setPrice(Euro price) {
        this.price = price;
    };

    public void setNumber(int number) {
        this.number = number;
    };

    public String getName() {
        return name;
    };

    public Euro getPrice() {
        return price;
    };

    public int getNumber() {
        return number;
    };

    public Euro getCost() {
        return new Euro(this.getPrice()).mul(this.getNumber());
    };

    @Override
    public String toString() {
        return getClass().getName() + "; " + name + "; " + price + "; " + number + "; " + getCost();
    }

    public boolean equals(Purchase obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        return (this.name != null && this.name.equals(obj.getName())) &&
                (this.price != null && this.price.equals(obj.getPrice()));
    };
}
