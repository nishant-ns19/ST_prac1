package com.nishant.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestockItemBV extends ShopTest {
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
    void nameMinimum() throws Exception {
        // number of words in name = 1
        shop.addNewItem("Bag", 5, 2000);
        assertEquals(7, shop.restockItem("Bag", 2));
    }

    @Test
    void nameAboveMinimum() throws Exception {
        // number of words in name = 2
        shop.addNewItem("Skipping Rope", 47, 200);
        assertEquals(100, shop.restockItem("Skipping Rope", 53));
    }

    @Test
    void nameBelowMaximum() throws Exception {
        // number of words in name = 9
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9", 567, 450);
        assertEquals(600, shop.restockItem("A1 B2 C3 D4 E5 F6 G7 H8 I9", 33));
    }

    @Test
    void nameMaximum() throws Exception {
        // number of words in name = 10
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 10, 100);
        assertEquals(20, shop.restockItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 10));
    }

    @Test
    void nameAboveMaximum() throws Exception {
        // number of words in name = 11
        shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8", 34, 2321);
        exception = assertThrows(Exception.class, () -> shop.restockItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10 K11", 16));
        assertEquals("Invalid item name length", exception.getMessage());
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
    void quantityMinimum() throws Exception {
        // quantity = 1
        shop.addNewItem("Bugatti Veyron", 1, 100000);
        assertEquals(2, shop.restockItem("Bugatti Veyron", 1));
    }

    @Test
    void quantityAboveMinimum() throws Exception {
        // quantity > 1
        shop.addNewItem("Apple Watch", 2, 50000);
        assertEquals(4, shop.restockItem("Apple Watch", 2));
    }

    @Test
    void inRange() throws Exception {
        // number of words in name = [1 - 10]
        // quantity = [1 - Integer.MAX_VALUE]
        shop.addNewItem("Samsung Galaxy Note 10", 10, 55000);
        int quantity = random.nextInt(Integer.MAX_VALUE) + 1;
        assertEquals(10 + quantity, shop.restockItem("Samsung Galaxy Note 10", quantity));
    }
}
