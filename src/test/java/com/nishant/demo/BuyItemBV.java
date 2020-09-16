package com.nishant.demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyItemBV extends ShopTest {
    HashMap<String, Integer> cart = new HashMap<>();

    // Attribute -> name
    // Datatype -> String
    // Range -> number of words in name = [1 - 10]
    @Test
    void nameBelowMinimum() throws Exception {
        // number of words in name < 1
        cart.clear();
        cart.put(" ", 5);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void nameMinimum() throws Exception {
        // number of words in name = 1
        shop.addNewItem("Printer", 50, 6000);
        cart.clear();
        cart.put("Printer", 2);
        assertEquals(12000, shop.buyItem(cart));
    }

    @Test
    void nameAboveMinimum() throws Exception {
        // number of words in name > 1
        shop.addNewItem("Mobile Phone", 100, 52000);
        cart.clear();
        cart.put("Mobile Phone", 1);
        assertEquals(52000, shop.buyItem(cart));
    }

    @Test
    void nameBelowMaximum() throws Exception {
        // number of words in name < 10
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9", 10, 300);
        cart.clear();
        cart.put("A1 B2 C3 D4 E5 F6 G7 H8 I9", 7);
        assertEquals(2100, shop.buyItem(cart));
    }

    @Test
    void nameMaximum() throws Exception {
        // number of words in name = 10
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 50, 50);
        cart.clear();
        cart.put("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 25);
        assertEquals(1250, shop.buyItem(cart));
    }

    @Test
    void nameAboveMaximum() throws Exception {
        // number of words in name > 10
        cart.clear();
        cart.put("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10 K11", 5);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    // Attribute -> quantity
    // Datatype -> Integer
    // Range -> quantity = [1 - MAX_QUANTITY_AVAILABLE]
    @Test
    void quantityBelowMinimum() throws Exception {
        // quantity < 1
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 0);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid quantity for one/more cart items", exception.getMessage());
    }

    @Test
    void quantityMinimum() throws Exception {
        // quantity = 1
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 1);
        assertEquals(52000, shop.buyItem(cart));
    }

    @Test
    void quantityAboveMinimum() throws Exception {
        // quantity > 1
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 2);
        assertEquals(104000, shop.buyItem(cart));
    }

    @Test
    void quantityBelowMaximum() throws Exception {
        // quantity < MAX_QUANTITY_AVAILABLE
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 9);
        assertEquals(468000, shop.buyItem(cart));
    }

    @Test
    void quantityMaximum() throws Exception {
        // quantity = MAX_QUANTITY_AVAILABLE
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 10);
        assertEquals(520000, shop.buyItem(cart));
    }

    @Test
    void quantityAboveMaximum() throws Exception {
        // quantity > MAX_QUANTITY_AVAILABLE
        shop.addNewItem("iPhone X", 10, 52000);
        cart.clear();
        cart.put("iPhone X", 11);
        exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Insufficient stock for: iPhone X", exception.getMessage());
    }

    @Test
    void inRange() throws Exception {
        // number of words in name = [1 -10]
        // quantity = [1 - MAX_QUANTITY_AVAILABLE]
        shop.addNewItem("JBL Bluetooth Headphones 220", 10, 4000);
        cart.clear();
        int quantity = random.nextInt(10) + 1;
        cart.put("JBL Bluetooth Headphones 220", quantity);
        assertEquals(4000 * quantity, shop.buyItem(cart));
    }
}
