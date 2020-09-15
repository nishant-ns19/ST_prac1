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
}