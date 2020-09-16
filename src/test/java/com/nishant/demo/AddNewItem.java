package com.nishant.demo;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddNewItem {
    Shop shop = new Shop();
    Exception exception = new Exception();
    Random random = new Random();
    // attributes -> (String name, Integer initialQuantity, Integer perItemCost)

    static class nameBV extends AddNewItem {
        // Range -> number of words in name = [1 - 10]
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
    static class initialQuantityBV extends AddNewItem {
        // Range -> initial quantity = [1 - Integer.MAX_VALUE]
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
    static class perItemCostBV extends AddNewItem {
        // Range -> per item cost = [1 - 2000000000]
        @Test
        void belowMinimum() throws Exception {
            // per item cost < 1
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
}





