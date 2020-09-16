package com.nishant.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddNewItemEP extends ShopTest {

    // Attribute -> name
    // Datatype -> String
    // Range -> number of words in name = [1 - 10]
    @Test
    void nameBelowMinimum() throws Exception {
        // number of words in name = 0
        exception = assertThrows(Exception.class, () -> shop.addNewItem(" ", 25, 999));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void nameAboveMaximum() throws Exception {
        // number of words in name = 11
        exception = assertThrows(Exception.class, () -> shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10 K11", 10, 2500));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    // Attribute -> initialQuantity
    // Datatype -> Integer
    // Range -> initial quantity = [1 - Integer.MAX_VALUE]
    @Test
    void initialQuantityBelowMinimum() throws Exception {
        // initial quantity < 1
        exception = assertThrows(Exception.class, () -> shop.addNewItem(" ", 0, 999));
        assertEquals("Invalid initial quantity", exception.getMessage());
    }

    // Attribute -> perItemCost
    // Datatype -> Integer
    // Range -> per item cost = [1 - 2000000000]
    @Test
    void perItemCostBelowMinimum() throws Exception {
        // per item cost < 1
        exception = assertThrows(Exception.class, () -> shop.addNewItem("Power Adapter", 25, 0));
        assertEquals("Invalid per item cost", exception.getMessage());
    }

    @Test
    void perItemCostAboveMaximum() throws Exception {
        // per item cost > 2000000000
        exception = assertThrows(Exception.class, () -> shop.addNewItem("Power Adapter", 25, Integer.MAX_VALUE));
        assertEquals("Invalid per item cost", exception.getMessage());
    }

    @Test
    void inRange() throws Exception {
        // number of words in name = [1-10]
        // per item cost = [1 - 2000000000]
        // initial quantity = [1 - Integer.MAX_VALUE]
        assertTrue(shop.addNewItem("Printer", 100, random.nextInt(2000000000) + 1));
    }


}
