package runner;

import entity.*;

public class Runner {
    public static void main(String[] args) {
        Product milk = new Product("Milk", new Euro(1,49));
        Purchase<AbstractPriceable, Number> purchase1 = new Purchase<>(milk, 10);

        PurchaseUtils<AbstractPriceable, Number> p1Utils = new PurchaseUtils<>(purchase1);
        p1Utils.printCost();

        Product joint = new Product("Joint", new Euro(90));
        Purchase<AbstractPriceable, Number> purchase2 = new Purchase<>(joint, 21.3);

        PurchaseUtils<AbstractPriceable, Number> p2Utils = new PurchaseUtils<>(purchase2);
        p2Utils.printCost();
        p2Utils.printCostDiff(purchase1);

        DiscountProduct discountJoint = new DiscountProduct("Joint", new Euro(351), new Euro(49));
        Purchase<AbstractPriceable, Number> purchase3 = new Purchase<>(discountJoint, 30);

        Service carRentService = new Service("Car rent", new Euro(150, 99), 10);
        PurchaseUtils<AbstractPriceable, Number> p4Utils = new PurchaseUtils<>(new Purchase<>(carRentService, 23.25));

        System.out.println(p4Utils.getPurchase().getItem());

        p4Utils.printCost();

        p2Utils.printIsSameCost(purchase1, purchase3, p4Utils.getPurchase());
    }
}
