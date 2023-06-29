package entity;

public abstract class AbstractPriceable implements Priceable {

    protected String name;

    protected abstract String fieldsToString();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "; " + fieldsToString() + "; " + getPrice();
    }
}
