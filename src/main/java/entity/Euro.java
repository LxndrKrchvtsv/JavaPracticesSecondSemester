package entity;

import java.util.Scanner;

public class Euro implements Comparable<Euro>  {
    private int entityValueInCents;

    public Euro() {
    }

    public Euro(int value) {
        this.entityValueInCents = value;
    }

    public Euro(int euros, int cents) {
        this(euros + cents * 100);
    }

    public Euro(Euro euro) {
        this(euro.entityValueInCents);
    }

    public Euro(Scanner scanner) {
        this.entityValueInCents = scanner.nextInt();
    }

    public Euro add(Euro euro) {
        this.entityValueInCents += euro.entityValueInCents;
        return this;
    }

    public Euro sub(Euro euro) {
        this.entityValueInCents -= euro.entityValueInCents;
        return this;
    }

    public Euro mul(int number) {
        this.entityValueInCents *= number;
        return this;
    }

    public Euro mul(double k, RoundMethod roundMethod, int d) {
        this.entityValueInCents = roundMethod.round(this.entityValueInCents * k, d);
        return this;
    }

    public Euro round(RoundMethod roundMethod, int d) {
        this.entityValueInCents = roundMethod.round(this.entityValueInCents, d);
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", this.entityValueInCents / 100, this.entityValueInCents % 100);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    public int compareTo(Euro euro) {
        return this.entityValueInCents - euro.entityValueInCents;
    }
}
