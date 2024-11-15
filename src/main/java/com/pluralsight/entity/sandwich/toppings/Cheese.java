package com.pluralsight.entity.sandwich.toppings;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing different types of cheese for a sandwich.
 */
public class Cheese extends SandwichIngredient {


    private static final Logger logger = LogManager.getLogger(Cheese.class);

    /**
     * Constructor to create a Cheese with a specified name.
     * Initializes the price and calories based on the cheese information.
     *
     * @param name the name of the cheese
     */
    public Cheese(String name) {
        super(name);
        logger.info("Creating a Cheese with name: {}", name);

    }

}