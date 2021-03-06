package com.nishant.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestockItemEP extends ShopTest {

    // Attribute -> name
    // Datatype -> String
    // Range -> number of words in name = [1 - 10]
    @Test
    void nameBelowMinimum() throws Exception {
        // number of words in name = 0
        shop.addNewItem("Cap", 50, 90);
        exception = assertThrows(Exception.class, () -> shop.restockItem(" ", 3));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void nameAboveMaximum() throws Exception {
        // number of words in name = 11
        exception = assertThrows(Exception.class, () -> shop.restockItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10 K11", 16));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void nameNonAlphanumeric() throws Exception {
        // one or more words != ^[a-zA-Z0-9]*$
        exception = assertThrows(Exception.class, () -> shop.restockItem("Not @lpha num£ric", 20));
        assertEquals("Invalid name, words should be alphanumeric", exception.getMessage());
    }

    // Attribute -> quantity
    // Datatype -> Integer
    // Range -> quantity = [1 - Integer.MAX_VALUE]
    @Test
    void quantityBelowMinimum() throws Exception {
        // quantity < 1
        shop.addNewItem("Rolex Watch", 6, 34827);
        exception = assertThrows(Exception.class, () -> shop.restockItem("Rolex Watch", 0));
        assertEquals("Invalid quantity", exception.getMessage());
    }

    @Test
    void inRange() throws Exception {
        // quantity = [1 - Integer.MAX_VALUE]
        // number of words in name = [1 - 10]
        shop.addNewItem("Apple MacBook Pro", 5, 150000);
        int quantity = random.nextInt(Integer.MAX_VALUE) + 1;
        assertEquals(5 + quantity, shop.restockItem("Apple MacBook Pro", quantity));
    }
}
