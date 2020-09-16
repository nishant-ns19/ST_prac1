package com.nishant.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddNewItemBV extends ShopTest {
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
    void nameMinimum() throws Exception {
        // number of words in name = 1
        assertTrue(shop.addNewItem("Headphone", 100, 5000));
    }

    @Test
    void nameAboveMinimum() throws Exception {
        // number of words in name = 2
        assertTrue(shop.addNewItem("Wrist Watch", 10, 5000));
    }

    @Test
    void nameBelowMaximum() throws Exception {
        // number of words in name = 9
        assertTrue(shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9", 100, 5000));
    }

    @Test
    void nameMaximum() throws Exception {
        // number of words in name = 10
        assertTrue(shop.addNewItem("A1 B2 C3 D4 E5 F6 G7 H8 I9 J10", 55, 5000));
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

    @Test
    void initialQuantityMinimum() throws Exception {
        // initial quantity = 1
        assertTrue(shop.addNewItem("Nokia Mobile Phone", 1, 10000));
    }

    @Test
    void initialQuantityAboveMinimum() throws Exception {
        // initial quantity > 1
        assertTrue(shop.addNewItem("Laptop", 5, 50000));
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
    void perItemCostMinimum() throws Exception {
        // per item cost = 1
        assertTrue(shop.addNewItem("Power Adapter", 25, 1));
    }

    @Test
    void perItemCostAboveMinimum() throws Exception {
        // per item cost > 1
        assertTrue(shop.addNewItem("Water Bottle", 100, 3));
    }

    @Test
    void perItemCostBelowMaximum() throws Exception {
        // per item cost < 2000000000
        assertTrue(shop.addNewItem("iPad", 22, 1999999999));
    }

    @Test
    void perItemCostMaximum() throws Exception {
        // per item cost = 2000000000
        assertTrue(shop.addNewItem("Chair", 20, 2000000000));
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
        // initial quantity = [1 - Integer.MAX_VALUE]
        // per item cost = [1 - 2000000000]
        assertTrue(shop.addNewItem("Sony Playstation 5", 10, random.nextInt(2000000000) + 1));
    }


}





