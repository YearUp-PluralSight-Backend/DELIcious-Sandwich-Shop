package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing a vegetable ingredient for a sandwich.
 */
public class Vegetable extends SandwichIngredient {

    private static final Logger logger = LogManager.getLogger(Vegetable.class);

    /**
     * Constructor to create a Vegetable with a specified name.
     * Initializes the price and calories based on the vegetable information.
     *
     * @param name the name of the vegetable
     */
    public Vegetable(String name) {
        super(name);
        logger.info("Creating a Vegetable object with name: " + name);
    }

}