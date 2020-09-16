package com.nishant.demo;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BoundaryValueTestingShopTest {
    Random rand = new Random();
    Exception exception;
}

class buyItemAttributeName {
    Shop shop = new Shop();
    Exception exception = new Exception();
    Random random = new Random();
    HashMap<String, Integer> cart = new HashMap<>();

    @Test
    void belowMinimum() throws Exception {
        // number of words in name < 1
        cart.clear();
        cart.put(" ", 5);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        // number of words in name = 1
        shop.addNewItem("Printer",50,6000);
        cart.clear();
        cart.put("Printer", 2);
        assertEquals(12000, shop.buyItem(cart));
    }

    @Test
    void aboveMinimum() throws Exception {
        // number of words in name > 1
        shop.addNewItem("Mobile Phone",100,52000);
        cart.clear();
        cart.put("Mobile Phone", 1);
        assertEquals(52000, shop.buyItem(cart));
    }

    @Test
    void inRange() throws Exception {
        // number of words in name = [1 -10]
        shop.addNewItem("JBL Bluetooth Headphones 220", 5, 4000);
        cart.clear();
        cart.put("JBL Bluetooth Headphones 220", 1);
        assertEquals(4000, shop.buyItem(cart));
    }

    @Test
    void belowMaximum() throws Exception {
        // number of words in name < 10
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9", 10, 300);
        cart.clear();
        cart.put("A1 B2 C3 D4 E5 F6 G7 H8 I9", 7);
        assertEquals(2100, shop.buyItem(cart));
    }

    @Test
    void maximum() throws Exception {
        // number of words in name = 10
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 50, 50);
        cart.clear();
        cart.put("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 25);
        assertEquals(1250, shop.buyItem(cart));
    }

    @Test
    void aboveMaximum() throws Exception {
        // number of words in name > 10
        cart.clear();
        cart.put("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10 K11", 5);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid item name length", exception.getMessage());
    }
}

class buyItemAttributeQuantity {
    Shop shop = new Shop();
    Exception exception = new Exception();
    HashMap<String, Integer> cart = new HashMap<>();
    Random random = new Random();

    @Test
    void belowMinimum() throws Exception {
        // quantity < 1
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 0);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid quantity for one/more cart items", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        // quantity = 1
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 1);
        assertEquals(52000, shop.buyItem(cart));
    }

    @Test
    void aboveMinimum() throws Exception {
        // quantity > 1
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 2);
        assertEquals(104000, shop.buyItem(cart));
    }

    @Test
    void inRange() throws Exception {
        // quantity = [1 - MAX_QUANTITY_AVAILABLE]
        shop.addNewItem("iPhone X", 10, 52000);
        int quantity = random.nextInt(9) + 1;
        cart.clear();
        cart.put("iPhone X", quantity);
        assertEquals(quantity * 52000, shop.buyItem(cart));
    }

    @Test
    void belowMaximum() throws Exception {
        // quantity < MAX_QUANTITY_AVAILABLE
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 9);
        assertEquals(468000, shop.buyItem(cart));
    }

    @Test
    void maximum() throws Exception {
        // quantity = MAX_QUANTITY_AVAILABLE
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 10);
        assertEquals(520000, shop.buyItem(cart));
    }

    @Test
    void aboveMaximum() throws Exception {
        // quantity > MAX_QUANTITY_AVAILABLE
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 11);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Insufficient stock for: iPhone X", exception.getMessage());
    }
}

class addNewItemAttributeName {
    Shop shop = new Shop();
    Exception exception = new Exception();

    @Test
    void belowMinimum() throws Exception {
        // number of words in name = 0
        exception = assertThrows(Exception.class, () -> shop.addNewItem(" ", 25, 999));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        // number of words in name = 1
        assertTrue(shop.addNewItem("Headphone",100,5000));
    }

    @Test
    void aboveMinimum() throws Exception {
        // number of words in name = 2
        assertTrue(shop.addNewItem("Wrist Watch",10,5000));
    }

    @Test
    void inRange() throws Exception {
        // number of words in name = [1-10]
        assertTrue(shop.addNewItem("Sony Playstation 5", 10, 50000));
    }

    @Test
    void belowMaximum() throws Exception {
        // number of words in name = 9
        assertTrue(shop.addNewItem("a1 b2 c3 d4 e5 f6 g7 h8 i9",100,5000));
    }

    @Test
    void maximum() throws Exception {
        // number of words in name = 10
        assertTrue(shop.addNewItem("a1 b2 c3 d4 e5 f6 g7 h8 i9 j10",55,5000));
    }

    @Test
    void aboveMaximum() throws Exception {
        // number of words in name = 11
        exception = assertThrows(Exception.class, () -> shop.addNewItem("a1 b2 c3 d4 e5 f6 g7 h8 i9 j10 l11", 10, 2500));
        assertEquals("Invalid item name length", exception.getMessage());
    }
}
class addNewItemAttributeInitialQuantity {
    Shop shop = new Shop();
    Exception exception = new Exception();
    Random random = new Random();

    @Test
    void belowMinimum() throws Exception {
        // initial quantity < 1
        exception = assertThrows(Exception.class, () -> shop.addNewItem(" ", 0, 999));
        assertEquals("Invalid initial quantity", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        // initial quantity = 1
        assertTrue(shop.addNewItem("Nokia Mobile Phone",1,10000));
    }

    @Test
    void aboveMinimum() throws Exception {
        // initial quantity > 1
        assertTrue(shop.addNewItem("Laptop",5,50000));
    }

    @Test
    void inRange() throws Exception {
        // initial quantity = [1 - INT_MAX]
        assertTrue(shop.addNewItem("Printer", random.nextInt(Integer.MAX_VALUE - 1) + 1, 6000));
    }
}

class addNewItemAttributeCost {
    Shop shop = new Shop();
    Exception exception = new Exception();
    Random random = new Random();

    @Test
    void belowMinimum() throws Exception {
        // initial cost < 1
        exception = assertThrows(Exception.class, () -> shop.addNewItem("Power Adapter",25,0));
        assertEquals("Invalid per item cost", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        // per item cost = 1
        assertTrue(shop.addNewItem("Power Adapter",25,1));
    }

    @Test
    void aboveMinimum() throws Exception {
        // per item cost > 1
        assertTrue(shop.addNewItem("Water Bottle",100,3));
    }

    @Test
    void inRange() throws Exception {
        // per item cost = [1 - 2000000000]
        assertTrue(shop.addNewItem("Printer", 5, random.nextInt(1999999999) + 1));
    }

    @Test
    void belowMaximum() throws Exception {
        // per item cost < 2000000000
        assertTrue(shop.addNewItem("iPad",22,1999999999));
    }

    @Test
    void maximum() throws Exception {
        // per item cost = 2000000000
        assertTrue(shop.addNewItem("Chair",20,2000000000));
    }

    @Test
    void aboveMaximum() throws Exception {
        // per item cost > 2000000000
        exception = assertThrows(Exception.class, () -> shop.addNewItem("Power Adapter",25,Integer.MAX_VALUE));
        assertEquals("Invalid per item cost", exception.getMessage());
    }
}

class restockItemAttributeQuantity {
    Random rand = new Random();
    Exception exception = new Exception();
    Shop shop = new Shop();

    @Test
    void belowMinimum() throws Exception {
        shop.addNewItem("Rolex Watch", 6, 34827);
        exception = assertThrows(Exception.class, () -> shop.restockItem("Rolex Watch", 0));
        assertEquals("Invalid quantity", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        shop.addNewItem("Bugatti Veyron", 1, 100000);
        assertEquals(2, shop.restockItem("Bugatti Veyron", 1));
    }

    @Test
    void aboveMinimum() throws Exception {
        shop.addNewItem("Apple Watch", 2, 50000);
        assertEquals(4, shop.restockItem("Apple Watch", 2));
    }

    @Test
    void inRange() throws Exception {
        shop.addNewItem("Apple MacBook", rand.nextInt(1000) + 1, 150000);
        shop.addNewItem("OnePlus 8T", rand.nextInt(1000) + 1, 45000);
        shop.addNewItem("Samsung Galaxy Note 10", rand.nextInt(1000) + 1, 55000);
        shop.addNewItem("Mi Band 5", rand.nextInt(1000) + 1, 2500);
        shop.addNewItem("Google Glasses", rand.nextInt(1000) + 1, 20000);
        shop.addNewItem("OnePlus Buds", rand.nextInt(1000) + 1, 7000);
        HashMap<String, Integer> quantity = shop.getQuantity();
        for(String name : quantity.keySet()) {
            int restockQuantity = rand.nextInt(1000) + 1;
            assertEquals(quantity.get(name) + restockQuantity, shop.restockItem(name, restockQuantity));
        }
    }
}

class restockItemAttributeName {
    Random rand = new Random();
    Exception exception = new Exception();
    Shop shop = new Shop();

    @Test
    void belowMinimum() throws Exception {
        // number of words in name = 0
        shop.addNewItem("Cap", 50, 90);
        exception = assertThrows(Exception.class, () -> shop.restockItem(" ", 3));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void minimum() throws Exception {
        // number of words in name = 1
        shop.addNewItem("Bag", 5, 2000);
        assertEquals(7, shop.restockItem("Bag", 2));
    }

    @Test
    void aboveMinimum() throws Exception {
        // number of words in name = 2
        shop.addNewItem("Skipping Rope", 47, 200);
        assertEquals(100, shop.restockItem("Skipping Rope", 53));
    }

    @Test
    void inRange() throws Exception {
        // number of words in name = [1 - 10]
        shop.addNewItem("Apple MacBook", rand.nextInt(1000) + 1, 150000);
        shop.addNewItem("OnePlus 8T", rand.nextInt(1000) + 1, 45000);
        shop.addNewItem("Samsung Galaxy Note 10", rand.nextInt(1000) + 1, 55000);
        shop.addNewItem("Mi Band 5", rand.nextInt(1000) + 1, 2500);
        shop.addNewItem("Google Glasses", rand.nextInt(1000) + 1, 20000);
        shop.addNewItem("OnePlus Buds", rand.nextInt(1000) + 1, 7000);
        HashMap<String, Integer> quantity = shop.getQuantity();
        for(String name : quantity.keySet()) {
            int restockQuantity = rand.nextInt(1000) + 1;
            assertEquals(quantity.get(name) + restockQuantity, shop.restockItem(name, restockQuantity));
        }
    }

    @Test
    void belowMaximum() throws Exception {
        // number of words in name = 9
        shop.addNewItem("A B C D E 1 2 3 4", 567, 450);
        assertEquals(600, shop.restockItem("A B C D E 1 2 3 4", 33));
    }

    @Test
    void maximum() throws Exception {
        // number of words in name = 10
        shop.addNewItem("A B C D E 1 2 3 4 5", 10, 100);
        assertEquals(20, shop.restockItem("A B C D E 1 2 3 4 5", 10));
    }

    @Test
    void aboveMaximum() throws Exception {
        // number of words in name = 11
        shop.addNewItem("A B C D E 1 2 3", 34, 2321);
        exception = assertThrows(Exception.class, () -> shop.restockItem("A B C D E 1 2 3 4 5 6", 16));
        assertEquals("Invalid item name length", exception.getMessage());
    }
}