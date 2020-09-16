package com.nishant.demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyItemEP extends ShopTest {
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
        // quantity = [1 - MAX_QUANTITY_AVAILABLE]
        // number of words in name = [1 -10]
        shop.addNewItem("iPhone X", 10, 52000);
        int quantity = random.nextInt(9) + 1;
        cart.clear();
        cart.put("iPhone X", quantity);
        assertEquals(quantity * 52000, shop.buyItem(cart));
    }
}
