package entity;

public class Purchase implements Comparable<Purchase> {
    private String productName;
    private int price;
    private int amountPurchaseUnits;
    private int discountPercent;
    private WeekDays weekDay;

    public Purchase() {};

    public Purchase(String productName, int price, int amountPurchaseUnits, int discountPercent, WeekDays weekDay) {
        this.productName = productName;
        this.price = price;
        this.amountPurchaseUnits = amountPurchaseUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    };

    public String getProductName() {
        return productName;
    };

    public int getPrice() {
        return price;
    };

    public int getAmountPurchaseUnits() {
        return amountPurchaseUnits;
    };

    public int getDiscountPercent() {
        return discountPercent;
    };

    public WeekDays getWeekDay() {
        return weekDay;
    };

    public void setProductName(String productName) {
         this.productName = productName;
    };

    public void setPrice(int  price) {
         this.price = price;
    };

    public void setAmountPurchaseUnits(int amountPurchaseUnits) {
         this.amountPurchaseUnits = amountPurchaseUnits;
    };

    public void setDiscountPercent(int discountPercent) {
         this.discountPercent = discountPercent;
    };

    public void setWeekDay(WeekDays weekDay) {
        this.weekDay = weekDay;
    };

    public int getCost() {
        return price * amountPurchaseUnits * (100 - discountPercent) / 100;
    };

    public String toString() {
        return productName + "; " + price + "; " + amountPurchaseUnits + "; " + discountPercent + "; " + getCost();
    }

    public int compareTo(Purchase purchase) {
        return amountPurchaseUnits - purchase.getAmountPurchaseUnits();
    };
}
