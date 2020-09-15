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
    void restockItem() throws Exception {
        Shop shop=new Shop();
        assertTrue(shop.addNewItem("abc b b b b b b b b b", 2, 2));
//        Exception exception = assertThrows(Exception.class, () -> shop.addNewItem("abc n b b b b b%% b b b",2,2));
//        assertEquals("Invalid name",exception.getMessage());

//        assertEquals(3,shop.restockItem("abc",1));
//        HashMap<String,Integer> cart=new HashMap<>();
////        Pair<String,Integer> newItem = new Pair("anbc", -1);
//        cart.put("anbc",-1);
//        Exception exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
//        assertEquals("Invalid quantity for: anbc", exception.getMessage());
    }
}