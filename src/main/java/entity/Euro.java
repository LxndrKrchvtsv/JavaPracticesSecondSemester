package entity;

public class Euro implements Comparable<Euro>  {
    private int value;

    public Euro() {
    }

    public Euro(int value) {
        this.value = value;
    }

    public Euro(int euros, int cents) {
        this(100 * euros + cents);
    }

    public Euro add(Euro euro) {
        return new Euro(this.value + euro.value);
    }

    public Euro sub(Euro euro) {
        return new Euro(this.value - euro.value);
    }

    public Euro mul(int number) {
        return new Euro(this.value * number);
    }

    public Euro mul(double k, RoundMethod roundMethod, int d) {
        return new Euro(roundMethod.round(this.value * k, d));
    }

    public Euro round(RoundMethod roundMethod, int d) {
        return new Euro(roundMethod.round(this.value, d));
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", this.value / 100, this.value % 100);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        Euro euro = (Euro) obj;
        return value == euro.value;
    }

    public int compareTo(Euro euro) {
        return this.value - euro.value;
    }
}
