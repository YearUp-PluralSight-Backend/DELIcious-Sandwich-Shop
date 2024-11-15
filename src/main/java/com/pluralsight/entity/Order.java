package com.pluralsight.entity;

import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing an order in the application.
 */
@Data
public class Order {

    private static final Logger logger = LogManager.getLogger(Order.class);
    private long orderNumber;
    private Shop shop;
    private LocalDateTime createTime;
    private double totalPrice;
    private double totalCalories;
    private List<Food> cart;

    /**
     * Constructor to create an Order with a generated order number and current time.
     * The shop is set to Year Up United, the address is 85 Broad Street, 6th Floor, New York, NY 10004.
     */
    public Order() {
        this.orderNumber = generatesOrderNumber();
        this.shop = new Shop("Year Up United", new Address("85 Broad Street, 6th Floor", "New York", "NY", "10004"), "(212) 785-3340", "Harry");
        String formattedNow = LocalDateTime.now().format(ConstantValue.DATE_TIME_FORMATTER);
        createTime = LocalDateTime.parse(formattedNow, ConstantValue.DATE_TIME_FORMATTER);
        cart = new ArrayList<>();
        logger.info("New order created with order number: {}", orderNumber);
    }

    /**
     * Resets the order by clearing the cart and setting the total price to 0.
     */
    public void reset() {
        cart.clear();
        totalPrice = 0;
        totalCalories = 0;
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @return the total price of the order
     */
    public double calculateTotalPrice() {
        double total = cart.stream().mapToDouble(Food::getPrice).sum();
        this.totalPrice = total;
        logger.debug("Calculated total price: {}", total);
        return total;
    }

    /**
     * Calculates the total calories of all items in the cart.
     *
     * @return the total calories of the order
     */
    public double getTotalCalories() {
        double total = cart.stream().mapToDouble(Food::getCalories).sum();
        this.totalCalories = total;
        logger.debug("Calculated total calories: {}", total);
        return total;
    }

    /**
     * Generates a random order number.
     *
     * @return a random order number between 1 and 200000
     */
    private long generatesOrderNumber() {
        long orderNum = new Random().nextLong(1, 200001);
        logger.debug("Generated order number: {}", orderNum);
        return orderNum;
    }

    /**
     * Gets the number of chips in the cart.
     *
     * @return the number of chips in the cart
     */
    public long getNumberOfChips() {
        long count = cart.stream().filter(food -> food instanceof Chips).count();
        logger.debug("Number of chips in cart: {}", count);
        return count;
    }

    /**
     * Gets the number of drinks in the cart.
     *
     * @return the number of drinks in the cart
     */
    public long getNumberOfDrinks() {
        long count = cart.stream().filter(food -> food instanceof Drink).count();
        logger.debug("Number of drinks in cart: {}", count);
        return count;
    }

    /**
     * Gets the number of sandwiches in the cart.
     *
     * @return the number of sandwiches in the cart
     */
    public long getNumberOfSandwiches() {
        long count = cart.stream().filter(food -> food instanceof Sandwich).count();
        logger.debug("Number of sandwiches in cart: {}", count);
        return count;
    }

    /**
     * Gets the total number of items in the cart.
     *
     * @return the total number of items in the cart
     */
    public long getNumberOfItems() {
        long count = cart.size();
        logger.debug("Total number of items in cart: {}", count);
        return count;
    }

    /**
     * Adds food to the cart.
     *
     * @param food the food to add to the cart
     * @return true if the food was added successfully
     */
    public boolean addFood(Food food) {
        boolean add = cart.add(food);
        logger.debug("Added food: {}", food);
        return add;
    }

    /**
     * Reviews the food items in the cart.
     */
    public void reviewCart() {
        if (cart.isEmpty()) {
            Utility.println.accept("Cart is empty");
            logger.info("Cart is empty");
        } else {
            cart.forEach(System.out::println);
            cart.forEach(food -> logger.info(food.toString()));
        }
    }

    /**
     * Prints the receipt for the order.
     *
     * @return the formatted receipt as a string
     */
    @Override
    public String toString() {
        return Utility.formatReceipt(this);
    }
}