package com.pluralsight.entity.sandwich.toppings;

import com.pluralsight.utils.ConstantValue;
import com.pluralsight.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing a sauce ingredient for a sandwich.
 */
public class Sauce extends SandwichIngredient {

    private static final Logger logger = LogManager.getLogger(Sauce.class);

    /**
     * Constructor to create a Sauce with a specified name.
     * Initializes the calories based on the sauce information.
     *
     * @param name the name of the sauce
     */
    public Sauce(String name) {
        super(name);
        logger.info("Creating a Sauce with name: {}", name);
    }

}