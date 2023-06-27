package entity;

public class Purchase implements Comparable<Purchase> {
    public static final String NAME = "Lemon";
    public static final int PRICE = 317;
    private int amountPurchaseUnits;
    private int discountPercent;
    private WeekDays weekDay;

    public Purchase() {
        this( 0, 0, 0);
    };

    public Purchase(int amountPurchaseUnits, int discountPercent, WeekDays weekDay) {
        this.amountPurchaseUnits = amountPurchaseUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    };

    public Purchase(int amountPurchaseUnits, int discountPercent, int weekDay) {
        this.amountPurchaseUnits = amountPurchaseUnits;
        this.discountPercent = discountPercent;
        this.weekDay = WeekDays.values()[weekDay];
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
        double cost = PRICE * amountPurchaseUnits * (100 - discountPercent) / 100;
        return (int) Math.round(cost / 100) * 100;
    };

    public String toString() {
        return NAME + "; " + PRICE + "; " + amountPurchaseUnits + "; " + discountPercent + "; " + getCost();
    }

    public int compareTo(Purchase purchase) {
        return amountPurchaseUnits - purchase.getAmountPurchaseUnits();
    };
}
