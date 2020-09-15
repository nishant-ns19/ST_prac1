package com.nishant.demo;

//import javafx.util.Pair;
//import jdk.internal.util.xml.impl.Pair;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoundaryValueTestingShopTest {
    Random rand = new Random();
    Exception exception;
    @Test
    void AddNewItem() throws Exception {
        Shop shop = new Shop();
//        Minimum Value for attributes
        assertTrue(shop.addNewItem("Nokia Mobile Phone",1,10000));
        assertTrue(shop.addNewItem("Power Adapter",25,1));
        assertTrue(shop.addNewItem("Headphone",100,5000));

//        Minimum+ Value for attributes
        assertTrue(shop.addNewItem("Laptop",5,50000));
        assertTrue(shop.addNewItem("Water Bottle",100,5));
        assertTrue(shop.addNewItem("Wrist Watch",10,5000));

//        Nominal Value for attributes
        assertTrue(shop.addNewItem("Printer",50,6000));

//        Maximum- Value for attributes
        assertTrue(shop.addNewItem("iPad",22,9999999));
        assertTrue(shop.addNewItem("a1 b2 c3 d4 e5 f6 g7 h8 i9",100,5000));

//        Maximum Value for attributes
        assertTrue(shop.addNewItem("Chair",20,10000000));
        assertTrue(shop.addNewItem("a1 b2 c3 d4 e5 f6 g7 h8 i9 j10",55,5000));
    }

    @Test
    void restockItem() throws Exception {
        Shop shop = new Shop();
        shop.addNewItem("Apple MacBook", rand.nextInt(1000) + 1, 150000);
        shop.addNewItem("OnePlus 8T", rand.nextInt(1000) + 1, 45000);
        shop.addNewItem("Samsung Galaxy Note 10", rand.nextInt(1000) + 1, 55000);
        shop.addNewItem("Mi Band 5", rand.nextInt(1000) + 1, 2500);
        shop.addNewItem("Google Glasses", rand.nextInt(1000) + 1, 20000);
        shop.addNewItem("OnePlus Buds", rand.nextInt(1000) + 1, 7000);
        // 1. In-Range Values
        HashMap<String, Integer> quantity = shop.getQuantity();
        for(String name : quantity.keySet()) {
            int restockQuantity = rand.nextInt(1000) + 1;
            assertEquals(quantity.get(name) + restockQuantity, shop.restockItem(name, restockQuantity));
        }
        // 2. Minimum value
        //    a. quantity [minimum]
        shop.addNewItem("Bugatti Veyron", 1, 100000);
        assertEquals(2, shop.restockItem("Bugatti Veyron", 1));
        //    b. name [minimum]
        shop.addNewItem("Bag", 5, 2000);
        assertEquals(7, shop.restockItem("Bag", 2));

        // 3. Minimum value + 1
        //    a. quantity [minimum + 1]
        shop.addNewItem("Apple Watch", 2, 50000);
        assertEquals(4, shop.restockItem("Apple Watch", 2));
        //    b. name [minimum + 1]
        shop.addNewItem("Skipping Rope", 47, 200);
        assertEquals(100, shop.restockItem("Skipping Rope", 53));

        // 4. Minimum value - 1
        //    a. quantity [minimum - 1]
        shop.addNewItem("Rolex Watch", 6, 34827);
        exception = assertThrows(Exception.class, () -> shop.restockItem("Rolex Watch",0));
        assertEquals("Invalid quantity", exception.getMessage());
        //    b. name [minimum - 1]
        shop.addNewItem("Cap", 50, 90);
        exception = assertThrows(Exception.class, () -> shop.restockItem(" ", 3));
        assertEquals("Invalid item name length", exception.getMessage());

        // 5. Maximum value
        //    a. name [maximum]
        shop.addNewItem("A B C D E 1 2 3 4 5", 10, 100);
        assertEquals(20, shop.restockItem("A B C D E 1 2 3 4 5", 10));

        // 6. Maximum value - 1
        //    a. name [maximum - 1]
        shop.addNewItem("A B C D E 1 2 3 4", 567, 450);
        assertEquals(600, shop.restockItem("A B C D E 1 2 3 4", 33));

        // 7. Maximum value + 1
        //    a. name [maximum + 1]
        shop.addNewItem("A B C D E 1 2 3", 34, 2321);
        exception = assertThrows(Exception.class, () -> shop.restockItem("A B C D E 1 2 3 4 5 6", 16));
        assertEquals("Invalid item name length", exception.getMessage());
    }

    @Test
    void buyItem() throws Exception {
        Shop shop1 = new Shop();
        shop1.addNewItem("Printer",50,6000);
        shop1.addNewItem("Mobile",100,52000);
        HashMap<String, Integer> cart=new HashMap<>();
//        Minimum Value for quantities
        cart.put("Printer",1);
        cart.put("Mobile",1);
        assertEquals(58000,shop1.buyItem(cart));


//        Minimum+ Value for quantity
        cart.clear();
        cart.put("Printer",5);
        cart.put("Mobile",2);
        assertEquals(134000,shop1.buyItem(cart));

//        Nominal Value for quantity
        cart.clear();
        cart.put("Printer",20);
        cart.put("Mobile",50);
        assertEquals(2720000,shop1.buyItem(cart));


//        Maximum- Value for quantity
        cart.clear();
        shop1.restockItem("Printer",26);
        shop1.restockItem("Mobile",53);
        cart.put("Printer",48);
        cart.put("Mobile",97);
        assertEquals(5332000,shop1.buyItem(cart));


//        Maximum Value for quantity
        cart.clear();
        shop1.restockItem("Printer",48);
        shop1.restockItem("Mobile",97);
        cart.put("Printer",50);
        cart.put("Mobile",100);
        assertEquals(5500000,shop1.buyItem(cart));
    }
}