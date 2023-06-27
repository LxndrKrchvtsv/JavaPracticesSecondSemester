package entity;

import com.sun.jdi.IncompatibleThreadStateException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.annotation.IncompleteAnnotationException;

import static org.testng.Assert.*;

public class PurchaseTest {
    @Test
    public void testPurchaseParametrizeConstructor() {
        Purchase purchase = new Purchase(
                1,
                10,
                WeekDays.SATURDAY
        );

        String actualProductName = purchase.NAME;
        int actualPrice = purchase.PRICE;
        int actualAmountPurchaseUnits = purchase.getAmountPurchaseUnits();
        int actualDiscountPercent = purchase.getDiscountPercent();
        WeekDays actualWeekday = purchase.getWeekDay();

        String expectProductName = "Lemon";
        int expectPrice = 317;
        int expectAmountPurchaseUnits = 1;
        int expectDiscountPercent = 10;
        WeekDays expectWeekday = WeekDays.SATURDAY;

        Assert.assertEquals(actualProductName, expectProductName, "Name does not match.");
        Assert.assertEquals(actualPrice, expectPrice, "Price does not match.");
        Assert.assertEquals(actualAmountPurchaseUnits, expectAmountPurchaseUnits, "Amount does not match.");
        Assert.assertEquals(actualDiscountPercent, expectDiscountPercent, "Discount does not match.");
        Assert.assertEquals(actualWeekday, expectWeekday, "WeekDay does not match.");
    }

    @Test
    public void testPurchaseDefaultConstructor() {
        Purchase purchase = new Purchase();

        String actualProductName = purchase.NAME;
        int actualPrice = purchase.PRICE;
        int actualAmountPurchaseUnits = purchase.getAmountPurchaseUnits();
        int actualDiscountPercent = purchase.getDiscountPercent();
        WeekDays actualWeekday = purchase.getWeekDay();

        String expectProductName = "Lemon";
        int expectPrice = 317;
        int expectAmountPurchaseUnits = 0;
        int expectDiscountPercent = 0;
        WeekDays expectWeekday = WeekDays.SUNDAY;

        Assert.assertEquals(actualProductName, expectProductName, "Name does not match.");
        Assert.assertEquals(actualPrice, expectPrice, "Price does not match.");
        Assert.assertEquals(actualAmountPurchaseUnits, expectAmountPurchaseUnits, "Amount does not match.");
        Assert.assertEquals(actualDiscountPercent, expectDiscountPercent, "Discount does not match.");
        Assert.assertEquals(actualWeekday, expectWeekday, "WeekDay does not match.");
    }

    @Test
    public void testGetCost() {
        Purchase purchase = new Purchase(
                1,
                10,
                WeekDays.SATURDAY
        );

        int actualPurchaseCost = purchase.getCost();

        int expectPurchaseCost = 300;

        Assert.assertEquals(actualPurchaseCost, expectPurchaseCost, "Cost does not match.");
    }

    @Test
    public void compareTwoClassInstances() {
        Purchase purchaseWithParams = new Purchase(
                1,
                10,
                WeekDays.SATURDAY
        );

        Purchase purchaseDefault = new Purchase();
        Assert.assertNotEquals(purchaseWithParams, purchaseDefault, "The class instances is not equal");

    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void weekDayThrowException() {
        Purchase purchaseWithParams = new Purchase(
                1,
                10,
                8
        );
    }

    @Test
    public void checkCostRoundingUp() {
        Purchase purchase = new Purchase(11, 0, WeekDays.MONDAY);
        assertEquals(3500, purchase.getCost());
    }

    @Test
    public void checkGetCostRoundingDown() {
        Purchase purchase = new Purchase(5, 15, WeekDays.MONDAY);
        assertEquals(1300, purchase.getCost());
    }
}