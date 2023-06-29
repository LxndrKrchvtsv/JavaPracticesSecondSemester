package Entity;

import static constants.Constants.*;

public class Purchase {
    private final String name;
    private final Euro price;
    private final int number;

    public Purchase(String name, Euro price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Purchase() {
        this(null, new Euro(), 0);
    }

    public Purchase(String[] args) {
        this(args[NAME_FIELD], new Euro(args[PRICE_FIELD]), Integer.parseInt(args[NUMBER_FIELD]));
    }

    public String getName() {
        return name;
    }

    public Euro getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Euro getCost() {
        return price.mul(number);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + SEPARATOR + name + SEPARATOR + price + SEPARATOR + number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Purchase)) {
            return false;
        }

        Purchase other = (Purchase) obj;
        return name.equals(other.name) && price.equals(other.price);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return fieldsToString() + SEPARATOR + getCost();
    }
}
