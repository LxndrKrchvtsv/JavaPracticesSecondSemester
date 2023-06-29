package entity;

public class PurchaseWithTransportExpenses extends AbstractPurchase {
    private Euro transportExpenses;

    public PurchaseWithTransportExpenses() {}

    public PurchaseWithTransportExpenses(Product product, int unitsCount) {
        super(product, unitsCount);
    }

    public PurchaseWithTransportExpenses(Product product, int unitsCount, Euro transportExpenses) {
        super(product, unitsCount);

        this.transportExpenses = transportExpenses;
    }

    public Euro getTransportExpenses() {
        return transportExpenses;
    }

    public void setTransportExpenses(Euro transportExpenses) {
        this.transportExpenses = transportExpenses;
    }

    @Override
    protected Euro getFinalCost(Euro baseCost) {
        return baseCost.add(transportExpenses);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + ";" + this.getProduct() + "; " + this.getUnitsNumber();
    }
}