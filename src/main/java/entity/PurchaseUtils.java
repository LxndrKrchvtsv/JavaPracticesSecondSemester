package entity;

public class PurchaseUtils<T extends AbstractPriceable, N extends Number> {

    private final Purchase<T, N> purchase;

    public PurchaseUtils(Purchase<T, N> purchase) {
        this.purchase = purchase;
    }

    public Purchase<T, N> getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println("cost: " + purchase.getCost());
    }

    public void printCostDiff(Purchase<T, N> purchase) {
        Euro currentCost = this.purchase.getCost();
        Euro costToCompare = purchase.getCost();

        int comparePurchasesCost = currentCost.compareTo(costToCompare);
        String diff = "";
        if (comparePurchasesCost > 0) {
            diff = "positive";
        } else if (comparePurchasesCost < 0) {
            diff = "negative";
        }

        Euro costDiff = currentCost.sub(costToCompare);
        System.out.println(diff + " diff: " + costDiff);
    }

    public void printIsSameCost(Purchase<T, N>... purchases) {
        Euro currentCost = purchase.getCost();

        for (Purchase<T, N> purchase : purchases) {
            Euro costToCompare = purchase.getCost();
            if (currentCost.compareTo(costToCompare) == 0) {
                System.out.println("This purchase has the same cost as current purchase: " + purchase);
                return;
            }
        }

        System.out.println("There are no purchases.");
    }
}