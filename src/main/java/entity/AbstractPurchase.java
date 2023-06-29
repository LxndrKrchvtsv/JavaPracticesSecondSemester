package entity;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
    private final int unitsNumber;

    public AbstractPurchase() {
        this(null, 0);
    }

    public AbstractPurchase(Product product, int unitsNumber) {
        this.product = product;
        this.unitsNumber = unitsNumber;
    }

    public Product getProduct() {
        return product;
    }

    public int getUnitsNumber() {
        return unitsNumber;
    }

    protected abstract Euro getFinalCost(Euro baseCost);

    public Euro getCost() {
        Euro baseCost = this.product.getPrice().mul(unitsNumber);
        Euro finalCost = getFinalCost(baseCost);
        return finalCost.round(RoundMethod.FLOOR, 2);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + ";" + this.product + ";" + this.unitsNumber;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
        return getCost().compareTo(purchase.getCost());
    }
}
