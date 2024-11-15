package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing different types of meat for a sandwich.
 */
public class Meat extends SandwichIngredient {

    private static final Logger logger = LogManager.getLogger(Meat.class);

    /**
     * Constructor to create Meat with a specified name.
     * Initializes the price and calories based on the meat information.
     *
     * @param name the name of the meat
     */
    public Meat(String name) {
        super(name);
        logger.info("Creating a Meat with name: {}", name);
    }

}