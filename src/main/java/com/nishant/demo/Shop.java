package com.nishant.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    private final HashMap<String, Integer> quantity;
    private final HashMap<String, Integer> cost;
    private final int MIN_WORDS = 1;
    private final int MAX_WORDS = 10;
    private final int MAX_COST = 10000000;
    private final int MIN_COST = 1;
    private final int MIN_INITIAL_QUANTITY = 1;


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

    public boolean addNewItem(String name, Integer initialQuantity, Integer perItemCost) throws Exception {
        if (perItemCost < MIN_COST) {
            throw new Exception("Invalid per item cost");
        }
        if (initialQuantity < MIN_INITIAL_QUANTITY) {
            throw new Exception("Invalid quantity");
        }
        String words[] = name.split("\\s+");
        if(words.length < MIN_WORDS || words.length > MAX_WORDS) {
            throw new Exception("Invalid item name length");
        }
        for(String word : words){
            if (!isAlphaNumeric(word)) {
                throw new Exception("Invalid name");
            }
        }
        if (isPresent(name)) {
            throw new Exception("Existing item can't be added again");
        }
        quantity.put(name, initialQuantity);
        cost.put(name, perItemCost);
        return true;
    }

    public int restockItem(String name, Integer quantity) throws Exception {
        if (quantity <= MIN_INITIAL_QUANTITY) {
            throw new Exception("Invalid quantity");
        }
        if (!isPresent(name)) {
            throw new Exception("Item not available for re-stocking");
        }
        this.quantity.put(name, this.quantity.get(name) + quantity);
        return this.quantity.get(name);
    }

    public int buyItem(HashMap<String, Integer> cart) throws Exception {
        int totalBill = 0;
        for (String itemName : cart.keySet()) {
            int itemQuantity = cart.get(itemName);
            if (itemQuantity <= MIN_INITIAL_QUANTITY) {
                throw new Exception("Invalid quantity for: " + itemName);
            }
            if (!isPresent(itemName) || quantity.get(itemName) <= MIN_INITIAL_QUANTITY) {
                throw new Exception("Item not available/Invalid item name : " + itemName);
            }
            if (quantity.get(itemName) < itemQuantity) {
                throw new Exception("Insufficient stock for: " + itemName);
            }
            quantity.put(itemName, quantity.get(itemName) - itemQuantity);
            totalBill = totalBill + itemQuantity * cost.get(itemName);
        }
        return totalBill;
    }

}




