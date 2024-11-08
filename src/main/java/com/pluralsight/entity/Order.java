package com.pluralsight.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private List<Food> cart;
    private double totalPrice;
    private int NumberOfItems;
    private int numberOfDrinks;
    private int numberOfChips;
    private int numberOfSandwiches;

    public Order() {
        cart = new ArrayList<>();

    }
}
