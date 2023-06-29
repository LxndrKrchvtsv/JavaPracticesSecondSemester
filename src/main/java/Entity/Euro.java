package Entity;

import static constants.Constants.DIVISOR;
import static constants.Constants.FRACTIONAL_NUMBER_FORMAT;

public class Euro implements Comparable<Euro> {
    private final int price;

    public Euro(int price) {
        this.price = price;
    }

    public Euro() {
        this(0);
    }

    public Euro(Euro euro) {
        this(euro.price);
    }

    public Euro(int euros, int cents) {
        this(euros * DIVISOR + cents);
    }

    public Euro(String cents) {
        this(Integer.parseInt(cents));
    }

    public Euro add(Euro euro) {
        return new Euro(price + euro.price);
    }

    public Euro sub(Euro euro) {
        return new Euro(price - euro.price);
    }

    public Euro mul(int value) {
        return new Euro(price * value);
    }

    private int getCoins() {
        return Math.abs(price) % DIVISOR;
    }

    private int getEuros() {
        return price / DIVISOR;
    }

    @Override
    public int compareTo(Euro euro) {
        return price - euro.price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + price;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Euro other = (Euro) obj;
        if (price != other.price) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format(FRACTIONAL_NUMBER_FORMAT, getEuros(), getCoins());
    }
}
