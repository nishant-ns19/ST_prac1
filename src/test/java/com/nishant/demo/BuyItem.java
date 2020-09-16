package com.nishant.demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuyItem {
    Shop shop = new Shop();
    HashMap<String, Integer> cart = new HashMap<>();
    Exception exception = new Exception();
    Random random = new Random();
    // attributes -> (HashMap<String, Integer> cart)

    static class nameBV extends BuyItem {
        // Range -> number of words in name = [1 - 10]
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
    static class quantityBV extends BuyItem {
        // Range -> quantity = [1 - MAX_QUANTITY_AVAILABLE]
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
}
