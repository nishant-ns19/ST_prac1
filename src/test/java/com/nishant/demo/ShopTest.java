package com.nishant.demo;

//import javafx.util.Pair;
//import jdk.internal.util.xml.impl.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    void boundaryValueTestingForAddNewItem() throws Exception {
        Shop shop = new Shop();
//        Minimum Value for attributes
        assertTrue(shop.addNewItem("Mobile Phone",1,10000));
        assertTrue(shop.addNewItem("Adapter",25,1));
        assertTrue(shop.addNewItem("Headphone",100,5000));

//        Minimum+ Value for attributes
        assertTrue(shop.addNewItem("Laptop",5,50000));
        assertTrue(shop.addNewItem("Water Bottle",100,5));
        assertTrue(shop.addNewItem("Wrist Watch",100,5000));

//        Nominal Value for attributes
        assertTrue(shop.addNewItem("Printer",50,6000));







    }
}