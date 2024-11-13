package com.pluralsight.entity;

import com.pluralsight.entity.otherfood.Chips;
import com.pluralsight.entity.otherfood.Drink;
import com.pluralsight.entity.sandwich.Sandwich;
import com.pluralsight.utils.ConstantValue;
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
    private String createTime;
    private double totalPrice;
    private double totalCalories;
    private List<Food> cart;


    /**
     * Constructor to create an Order with a generated order number and current time.
     */
    public Order() {
        this.orderNumber = generatesOrderNumber();
        createTime = LocalDateTime.now().format(ConstantValue.DATE_TIME_FORMATTER);
        cart = new ArrayList<>();
        logger.info("New order created with order number: {}", orderNumber);
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
     * calculate the total calories of all items in the cart
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
     * Adds food to the cart
     * @param food the food will add to the cart
     * @return the item being added to the cart
     */
    public boolean addFood(Food food) {
        boolean add = cart.add(food);
        logger.debug("added Food: {}", food);
        return true;
    }

    /**
     *  Reviews the food items in the cart
     */
    public void reviewCart() {
        cart.forEach(System.out::println);
    }
}