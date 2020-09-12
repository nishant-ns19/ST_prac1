package com.nishant.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    void restockItem() throws Exception {
        Shop shop=new Shop();
//        Exception exception = assertThrows(Exception.class, () -> shop.restockItem("he$$",1));
//        assertEquals("Invalid name",exception.getMessage());
        shop.addNewItem("abc",2,2);
        assertEquals(3,shop.restockItem("abc",1));

    }
}