package com.pluralsight.entity.sandwich.toppings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing a bread ingredient for a sandwich.
 */
public class Bread extends SandwichIngredient {

    private static final Logger logger = LogManager.getLogger(Bread.class);

    /**
     * Constructor to create Bread with a specified name, price, and calories.
     *
     * @param name the name of the bread
     * @param price the base price of the bread
     * @param calories the base calories of the bread
     */
    public Bread(String name, double price, double calories) {
        super(name, price, calories);
        logger.info("Creating Bread object with name: {}, price: {}, calories: {}", name, price, calories);

    }

    /**
     * Constructor to create Bread with a specified name.
     * Initializes the price and calories based on the bread information.
     *
     * @param name the name of the bread
     * @throws IllegalArgumentException if the bread type is unknown
     */
    public Bread(String name) {
        super(name);
    }


}