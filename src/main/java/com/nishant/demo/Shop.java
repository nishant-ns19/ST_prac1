package com.nishant.demo;

import java.util.HashMap;

public class Shop {
    private final HashMap<String, Integer> quantity;
    private final HashMap<String, Integer> cost;

    Shop() {
        quantity = new HashMap<>();
        cost = new HashMap<>();
    }

    private boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }

    public boolean isPresent(String s) {
        return quantity.containsKey(s);
    }

    public boolean addNewItem(String name, Integer initialQuantity,Integer perItemCost) throws Exception {
        if(perItemCost<=0){
            throw new Exception("Invalid per item cost");
        }
        if (initialQuantity <= 0) {
            throw new Exception("Invalid quantity");
        }
        if (!isAlphaNumeric(name)) {
            throw new Exception("Invalid name");
        }
        if (isPresent(name)) {
            throw new Exception("Existing item can't be added again");
        }
        quantity.put(name, initialQuantity);
        cost.put(name, perItemCost);
        return true;
    }

    public Integer restockItem(String name, Integer quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Invalid quantity");
        }
        if (!isAlphaNumeric(name)) {
            throw new Exception("Invalid name");
        }
        if (!isPresent(name)) {
            throw new Exception("Item not available for re-stocking");
        }
        this.quantity.put(name, this.quantity.get(name) + quantity);
        return this.quantity.get(name);
    }

}




