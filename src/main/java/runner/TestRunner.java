package runner;

import entity.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestRunner {

    @Test
    public void testEuroDefaultConstructor() {
        Euro defaultEuro = new Euro();
        Euro expectedEuro = new Euro(0);

        assertEquals(expectedEuro, defaultEuro);
    }

    @Test
    public void testEuroParametrizedConstructors() {
        Euro euro1 = new Euro(5433);
        Euro euro2 = new Euro(54, 33);

        assertEquals(euro1, euro2);
    }

    @Test
    public void testEuroAdd() {
        Euro euro1 = new Euro(123);
        Euro euro2 = euro1.add(new Euro(878));

        assertEquals(new Euro(1001), euro2);
    }

    @Test
    public void testEuroSubtract() {
        Euro euro1 = new Euro(321);

        assertEquals(new Euro(321), euro1);
    }

    @Test
    public void testEuroMultiplyInt() {
        Euro euro1 = new Euro(123);
        Euro euro2 = euro1.mul(2);

        assertEquals(new Euro(246), euro2);
    }

    @Test
    public void testEuroMultiplyDouble() {
        Euro euro = new Euro(123456);

        Euro euro2 = euro.mul(0.99, RoundMethod.FLOOR, 2);
        assertEquals(new Euro(122200), euro2);
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

        assertEquals(euro1, euro2);
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

    @Test
    public void testProductEquals() {
        Product product1 = new Product("product", new Euro(332));
        Product product2 = new Product("product", new Euro(332));

        assertEquals(product1, product2);
    }

    @Test
    public void testPurchasesDefaultConstructors() {
        AbstractPurchase[] purchases = new AbstractPurchase[]{
                new PurchaseWithTransportExpenses(),
                new PriceDiscountPurchase(),
                new PercentDiscountPurchase()
        };

        for (AbstractPurchase purchase : purchases) {
            assertNull(purchase.getProduct());
            assertEquals(0, purchase.getUnitsNumber());
        }
    }

    @Test
    public void testPurchasesParametrizedConstructors() {
        Product product = new Product("product", new Euro(100));

        AbstractPurchase[] purchases = new AbstractPurchase[]{
                new PurchaseWithTransportExpenses(product, 20, new Euro(100)),
                new PriceDiscountPurchase(product, 20, new Euro(50)),
                new PercentDiscountPurchase(product, 20, 7.99)
        };

        for (AbstractPurchase purchase : purchases) {
            assertEquals(product, purchase.getProduct());
            assertEquals(20, purchase.getUnitsNumber());
        }
    }

    @Test
    public void testTransportationExpensesPurchaseGetCost() {
        Euro price = new Euro(123);
        Product product = new Product("product", price);
        AbstractPurchase purchase = new PurchaseWithTransportExpenses(product, 20, new Euro(400));

        assertEquals(new Euro(2800), purchase.getCost());
        assertEquals(new Euro(123), price);
    }

    @Test
    public void testPriceDiscountPurchaseGetCost() {
        Euro price = new Euro(300);
        Product product = new Product("product", price);
        AbstractPurchase purchase = new PriceDiscountPurchase(product, 2, new Euro(50));

        assertEquals(new Euro(500), purchase.getCost());
        assertEquals(new Euro(300), price);
    }

    @Test
    public void testPercentDiscountPurchaseGetCost() {
        Euro price = new Euro(500);
        Product product = new Product("product", price);
        AbstractPurchase purchase = new PercentDiscountPurchase(product, 20, 7.99);

        assertEquals(new Euro(9400), purchase.getCost());
    }

    @Test
    public void testPurchasesComparison() {
        Product product = new Product("product", new Euro(1000));
        AbstractPurchase purchase1 = new PriceDiscountPurchase(product, 20, new Euro(100));
        AbstractPurchase purchase2 = new PercentDiscountPurchase(product, 20, 10.0);

        assertEquals(0, purchase1.compareTo(purchase2));

        AbstractPurchase purchase4 = new PriceDiscountPurchase(product, 100, new Euro());

        assertTrue(purchase4.compareTo(purchase1) > 0);

        AbstractPurchase purchase5 = new PriceDiscountPurchase(product, 10, new Euro());

        assertTrue(purchase5.compareTo(purchase1) < 0);
    }

    @Test
    public void testSearchMethod() {
        final Product KEY = new Product("Key", new Euro(500));
        AbstractPurchase[] purchases = new AbstractPurchase[]{
                new PriceDiscountPurchase(KEY, 5, new Euro(0)),
                new PriceDiscountPurchase(KEY, 200, new Euro(5)),
                new PercentDiscountPurchase(KEY, 100, 12.34),
                new PercentDiscountPurchase(KEY, 5, 0.1),
                new PurchaseWithTransportExpenses(KEY, 1, new Euro(2500)),
                new PurchaseWithTransportExpenses(KEY, 200, new Euro(27)),
        };

        Euro searchCost1 = new Euro(2500);
        assertEquals(0, Runner.search(purchases, searchCost1));

        Euro searchCost2 = new Euro(100_100);
        assertTrue(Runner.search(purchases, searchCost2) < 0);
    }
}
