package com.nishant.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestockItem extends BoundaryValueTestingShopTest {
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
    void nameInRange() throws Exception {
        // number of words in name = [1 - 10]
        shop.addNewItem("Samsung Galaxy Note 10", 10, 55000);
        assertEquals(20, shop.restockItem("Samsung Galaxy Note 10", 10));
    }
    @Test
    void nameBelowMaximum() throws Exception {
        // number of words in name = 9
        shop.addNewItem("A B C D E 1 2 3 4", 567, 450);
        assertEquals(600, shop.restockItem("A B C D E 1 2 3 4", 33));
    }
    @Test
    void nameMaximum() throws Exception {
        // number of words in name = 10
        shop.addNewItem("A B C D E 1 2 3 4 5", 10, 100);
        assertEquals(20, shop.restockItem("A B C D E 1 2 3 4 5", 10));
    }
    @Test
    void nameAboveMaximum() throws Exception {
        // number of words in name = 11
        shop.addNewItem("A B C D E 1 2 3", 34, 2321);
        exception = assertThrows(Exception.class, () -> shop.restockItem("A B C D E 1 2 3 4 5 6", 16));
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
    void quantityInRange() throws Exception {
        // quantity = [1 - Integer.MAX_VALUE]
        shop.addNewItem("Apple MacBook Pro", 5, 150000);
        int quantity = random.nextInt(Integer.MAX_VALUE - 1) + 1;
        assertEquals(5 + quantity, shop.restockItem("Apple MacBook Pro", quantity));
    }
}
