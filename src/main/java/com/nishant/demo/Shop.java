package com.nishant.demo;

import javafx.util.Pair;

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

    public boolean addNewItem(String name, Integer initialQuantity, Integer perItemCost) throws Exception {
        if (perItemCost <= 0) {
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

    public int restockItem(String name, Integer quantity) throws Exception {
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

    public int buyItem(Pair<String, Integer>[] cart) throws Exception {
        int totalBill = 0;
        for (Pair<String, Integer> item : cart) {
            if (!isAlphaNumeric(item.getKey())) {
                throw new Exception("Invalid name: " + item.getKey());
            }
            if (item.getValue() <= 0) {
                throw new Exception("Invalid quantity for: " + item.getKey());
            }
            if (!isPresent(item.getKey()) || quantity.get(item.getKey()) <= 0) {
                throw new Exception("Item not available: " + item.getKey());
            }
            if (quantity.get(item.getKey()) < item.getValue()) {
                throw new Exception("Insufficient stock for: " + item.getKey());
            }
            quantity.put(item.getKey(), quantity.get(item.getKey()) - item.getValue());
            totalBill = totalBill + item.getValue() * cost.get(item.getKey());
        }
        return totalBill;
    }

}




