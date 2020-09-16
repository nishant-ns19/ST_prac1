package com.nishant.demo;

import java.util.HashMap;

public class Shop {
    private final HashMap<String, Integer> quantity;
    private final HashMap<String, Integer> cost;
    private final int MIN_WORDS = 1;
    private final int MAX_WORDS = 10;
    private final int MAX_COST = 2000000000;
    private final int MIN_COST = 1;
    private final int MIN_INITIAL_QUANTITY = 1;


    Shop() {
        quantity = new HashMap<>();
        cost = new HashMap<>();
    }

    private boolean isAlphaNumeric(String name) {
        return name != null && name.matches("^[a-zA-Z0-9]*$");
    }

    public boolean isPresent(String name) {
        return quantity.containsKey(name) && quantity.get(name) >= MIN_INITIAL_QUANTITY;
    }

    public boolean checkRegex(String name) {
        String words[] = name.split("\\s+");
        for (String word : words) {
            if (!isAlphaNumeric(word)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNameLength(String name) {
        Integer length = name.split("\\s+").length;
        return (length >= MIN_WORDS && length <= MAX_WORDS);
    }

    public boolean checkQuantity(Integer quantity) {
        return quantity >= MIN_INITIAL_QUANTITY;
    }

    public boolean checkCost(Integer cost) {
        return (cost >= MIN_COST && cost <= MAX_COST);
    }

    public boolean addNewItem(String name, Integer initialQuantity, Integer perItemCost) throws Exception {
        if (!checkCost(perItemCost)) {
            throw new Exception("Invalid per item cost");
        }
        if (!checkQuantity(initialQuantity)) {
            throw new Exception("Invalid initial quantity");
        }
        if (!checkNameLength(name)) {
            throw new Exception("Invalid item name length");
        }
        if (!checkRegex(name)) {
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
        if (!checkQuantity(quantity)) {
            throw new Exception("Invalid quantity");
        }
        if (!checkNameLength(name)) {
            throw new Exception("Invalid item name length");
        }
        if (!checkRegex(name)) {
            throw new Exception("Invalid name");
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
            if (!checkNameLength(itemName)) {
                throw new Exception("Invalid item name length");
            }
            if (!checkRegex(itemName)) {
                throw new Exception("Invalid name");
            }
            if (!checkQuantity(itemQuantity)) {
                throw new Exception("Invalid quantity for one/more cart items");
            }
            if (!isPresent(itemName)) {
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

    public HashMap<String, Integer> getQuantity() {
        return quantity;
    }

    public HashMap<String, Integer> getCost() {
        return cost;
    }
}




