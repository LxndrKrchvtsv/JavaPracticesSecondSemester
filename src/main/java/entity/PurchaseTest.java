package entity;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PurchaseTest {
    @Test
    public void testPurchaseParametrizeConstructor() {
        Purchase purchase = new Purchase(
                "Test",
                8875,
                1,
                10,
                WeekDays.SATURDAY
        );

        String actualProductName = purchase.getProductName();
        int actualPrice = purchase.getPrice();
        int actualAmountPurchaseUnits = purchase.getAmountPurchaseUnits();
        int actualDiscountPercent = purchase.getDiscountPercent();
        WeekDays actualWeekday = purchase.getWeekDay();

        String expectProductName = "Test";
        int expectPrice = 8875;
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

        String actualProductName = purchase.getProductName();
        int actualPrice = purchase.getPrice();
        int actualAmountPurchaseUnits = purchase.getAmountPurchaseUnits();
        int actualDiscountPercent = purchase.getDiscountPercent();
        WeekDays actualWeekday = purchase.getWeekDay();

        String expectProductName = null;
        int expectPrice = 0;
        int expectAmountPurchaseUnits = 0;
        int expectDiscountPercent = 0;
        WeekDays expectWeekday = null;

        Assert.assertEquals(actualProductName, expectProductName, "Name does not match.");
        Assert.assertEquals(actualPrice, expectPrice, "Price does not match.");
        Assert.assertEquals(actualAmountPurchaseUnits, expectAmountPurchaseUnits, "Amount does not match.");
        Assert.assertEquals(actualDiscountPercent, expectDiscountPercent, "Discount does not match.");
        Assert.assertEquals(actualWeekday, expectWeekday, "WeekDay does not match.");
    }

    @Test
    public void testGetCost() {
        Purchase purchase = new Purchase(
                "Test",
                8875,
                1,
                10,
                WeekDays.SATURDAY
        );

        int actualPurchaseCost = purchase.getCost();

        int expectPurchaseCost = 7987;

        Assert.assertEquals(actualPurchaseCost, expectPurchaseCost, "Cost does not match.");
    }
}