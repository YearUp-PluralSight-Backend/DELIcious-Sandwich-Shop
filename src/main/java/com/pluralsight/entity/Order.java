package com.pluralsight.entity;

import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.utils.ConstantValue;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing an order in the application.
 */
@Data
public class Order {

    private long orderNumber;
    private List<Food> cart;
    private String createTime;

    /**
     * Constructor to create an Order with a generated order number and current time.
     */
    public Order() {
        this.orderNumber = generatesOrderNumber();
        createTime = LocalDateTime.now().format(ConstantValue.DATE_TIME_FORMATTER);
        cart = new ArrayList<>();
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @return the total price of the order
     */
    public double calculateTotalPrice() {
        return cart.stream().mapToDouble(Food::getPrice).sum();
    }

    /**
     * Generates a random order number.
     *
     * @return a random order number between 1 and 200000
     */
    private long generatesOrderNumber() {
        return new Random().nextLong(1, 200001);
    }

    /**
     * Gets the number of chips in the cart.
     *
     * @return the number of chips in the cart
     */
    public long getNumberOfChips() {
        return cart.stream().filter(food -> food instanceof Chips).count();
    }

    /**
     * Gets the number of drinks in the cart.
     *
     * @return the number of drinks in the cart
     */
    public long getNumberOfDrinks() {
        return cart.stream().filter(food -> food instanceof Drink).count();
    }

    /**
     * Gets the number of sandwiches in the cart.
     *
     * @return the number of sandwiches in the cart
     */
    public long getNumberOfSandwiches() {
        return cart.stream().filter(food -> food instanceof Sandwich).count();
    }

    /**
     * Gets the total number of items in the cart.
     *
     * @return the total number of items in the cart
     */
    public long getNumberOfItems() {
        return cart.size();
    }
}