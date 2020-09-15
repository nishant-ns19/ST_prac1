package com.nishant.demo;

//import javafx.util.Pair;
//import jdk.internal.util.xml.impl.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoundaryValueTestingShopTest {

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
    void restockItem() {




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


//        Maximum- Value for qunatity
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