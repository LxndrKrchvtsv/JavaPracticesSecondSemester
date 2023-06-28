package runner;

import entity.*;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.*;

public class TestRunner {
    // --EURO CLASS TEST--
    @Test
    public void testEuroDefaultConstructor() {
        Euro defaultEuro = new Euro();
        Euro expectedEuro = new Euro(0);

        assertEquals(expectedEuro, defaultEuro);

        assertEquals(expectedEuro.toString(), defaultEuro.toString());
    }

    @Test
    public void testEuroParametrizedConstructors() {
        Euro euro1 = new Euro(123456);
        Euro euro2 = new Euro(1234, 56);
        Euro euro3 = new Euro(new Euro(123456));

        assertEquals(euro1, euro2);
        assertEquals(euro2, euro3);

        assertEquals(euro1.toString(), euro2.toString());
        assertEquals(euro2.toString(), euro3.toString());
    }

    @Test
    public void testEuroAdd() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(878);
        euro1.add(euro2);

        assertEquals(new Euro(1001), euro1);
    }

    @Test
    public void testEuroSub() {
        Euro euro1 = new Euro(321);
        Euro euro2 = new Euro(123);
        euro1.sub(euro2);

        assertEquals(new Euro(198), euro1);
    }

    @Test
    public void testEuroMul() {
        Euro euro = new Euro(123);
        euro.mul(2);

        assertEquals(new Euro(246), euro);
    }

    @Test
    public void testEuroMulDouble() {
        Euro euro1 = new Euro(123456);
        euro1.mul(0.99, RoundMethod.FLOOR, 2);

        assertEquals(new Euro(122200), euro1);

        Euro euro2 = new Euro(123456);
        euro2.mul(0.99, RoundMethod.ROUND, 2);

        assertEquals(new Euro(122200), euro2);

        Euro euro3 = new Euro(123456);
        euro3.mul(0.99, RoundMethod.CEIL, 2);

        assertEquals(new Euro(122300), euro3);
    }

    @Test
    public void testEuroToString() {
        assertEquals("0.00", new Euro().toString());
        assertEquals("0.01", new Euro(1).toString());
        assertEquals("0.12", new Euro(12).toString());
        assertEquals("12.34", new Euro(1234).toString());
    }

    @Test
    public void testEuroEquals() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(123);
        Euro euro3 = new Euro(567);

        assertEquals(euro1, euro2);
        assertNotEquals(euro1, euro3);
    }

    @Test
    public void testEuroCompareTo() {
        Euro euro1 = new Euro(123);
        Euro euro2 = new Euro(567);
        Euro euro3 = new Euro(567);

        assertTrue(euro1.compareTo(euro2) < 0);
        assertTrue(euro2.compareTo(euro1) > 0);
        assertEquals(0, euro2.compareTo(euro3));
    }

    // --PURCHASE CLASS TEST--
    @Test
    public void testPurchaseDefaultConstructors() {
        Purchase[] purchases = new Purchase[]{
                new Purchase(),
                new PriceDiscountPurchase(),
                new PercentDiscountPurchase()
        };

        for (Purchase purchase : purchases) {
            assertNull(purchase.getName());
            assertNull(purchase.getPrice());
            assertEquals(0, purchase.getNumber());
        }
    }

    @Test
    public void testPurchaseParametrizedConstructors() {
        Purchase[] purchases = new Purchase[]{
                new Purchase("Apple", new Euro(100), 30),
                new PriceDiscountPurchase("Apple", new Euro(100), 30, new Euro(50)),
                new PercentDiscountPurchase("Apple", new Euro(100), 30, 7.25)
        };

        for (Purchase purchase : purchases) {
            assertEquals("Apple", purchase.getName());
            assertEquals(new Euro(100), purchase.getPrice());
            assertEquals(20, purchase.getNumber());
        }
    }

    @Test
    public void testPurchaseGetCost() {
        Euro price = new Euro(123);

        Purchase purchase = new Purchase("Apple", price, 20);

        assertEquals(new Euro(2460), purchase.getCost());
        assertEquals(new Euro(123), price);
    }

    @Test
    public void testPriceDiscountPurchaseGetCost() {
        Euro price = new Euro(300);

        Purchase purchase = new PriceDiscountPurchase("Apple", price, 2, new Euro(50));

        assertEquals(new Euro(500), purchase.getCost());
        assertEquals(new Euro(300), price);
    }

    @Test
    public void testPercentDiscountPurchaseGetCost() {
        Euro price1 = new Euro(500);
        Euro price2 = new Euro(500);

        Purchase purchase1 = new PercentDiscountPurchase("Apple", price1, 30, 7.25);
        Purchase purchase2 = new PercentDiscountPurchase("Apple", price2, 15, 7.25);

        assertEquals(new Euro(9418), purchase1.getCost());
        assertEquals(new Euro(7500), purchase2.getCost());
        assertEquals(new Euro(500), price1);
        assertEquals(new Euro(500), price2);
    }

    @Test
    public void testPurchaseEquals() {
        Purchase purchase = new Purchase("Apple", new Euro(100), 20);
        Purchase priceDiscountPurchase = new PriceDiscountPurchase("Apple", new Euro(100), 30, new Euro(60));
        Purchase percentDiscountPurchase = new PercentDiscountPurchase("Apple", new Euro(100), 40, 12.34);

        assertEquals(purchase, priceDiscountPurchase);
        assertEquals(purchase, percentDiscountPurchase);

        Purchase anotherPurchase = new Purchase("Sprite", new Euro(300), 35);

        assertNotEquals(purchase, anotherPurchase);
        assertNotEquals(priceDiscountPurchase, anotherPurchase);
        assertNotEquals(percentDiscountPurchase, anotherPurchase);
    }

    @Test
    public void testPurchaseFactory() {
        String input = "PERCENT_DISCOUNT_PURCHASE Apple 500 30 7.25";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        PurchaseFactory PurchasesFactory;
        Purchase testPurchase = PurchaseFactory.getPurchaseFromFactory(scanner);

        assertTrue(testPurchase instanceof PercentDiscountPurchase);
    }
}