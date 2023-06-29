package entity;

public class Purchase<T extends AbstractPriceable, N extends Number> {
    private final T item;
    private final N itemsQuantity;

    public Purchase(T item, N itemsQuantityNumber) {
        this.item = item;
        this.itemsQuantity = itemsQuantityNumber;
    }

    public T getItem() {
        return item;
    }

    public N getItemsQuantity() {
        return itemsQuantity;
    }

    public Euro getCost() {
        return item.getPrice().mul(itemsQuantity.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
        return item + "; " + itemsQuantity + "; " + getCost();
    }
}
