package com.nishant.demo;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShopTest {

    @Test
    void restockItem() throws Exception {
        Shop shop=new Shop();
//        Exception exception = assertThrows(Exception.class, () -> shop.restockItem("he$$",1));
//        assertEquals("Invalid name",exception.getMessage());
//        shop.addNewItem("abc",2,2);
//        assertEquals(3,shop.restockItem("abc",1));
        List<Pair<String,Integer>> cart=new ArrayList<>();
        cart.add(new Pair<>("anbc",-1));
        Exception exception = assertThrows(Exception.class, () -> shop.buyItem(cart));
        assertEquals("Invalid quantity for: anbc", exception.getMessage());
    }
}